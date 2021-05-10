package com.nisium.jpa.phone;

import java.util.List;
import java.util.stream.Collectors;

import com.nisium.jpa.helper.AdapterOperations;
import com.nisium.jpa.user.UserData;
import com.nisium.model.phone.Phone;
import com.nisium.model.phone.gateways.PhoneRepository;
import com.nisium.model.user.User;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneRepositoryAdapter extends
        AdapterOperations<Phone, PhoneData, Long, com.nisium.jpa.phone.PhoneRepository> implements PhoneRepository {

    public PhoneRepositoryAdapter(com.nisium.jpa.phone.PhoneRepository repository, ObjectMapper mapper) {
        /**
         * Could be use mapper.mapBuilder if your domain model implement builder pattern
         * super(repository, mapper, d ->
         * mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build()); Or using
         * mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.mapBuilder(d, Phone.PhoneBuilder.class).build());
    }

    @Override
    public List<Phone> findByUser(User user) {
        return this.toList(this.repository.findByUser(this.mapper.map(user, UserData.class)));
    }

    @Override
    public List<Phone> saveAll(List<Phone> phoneList) {
        return this.toList(this.repository
                .saveAll(phoneList.stream().map(phone -> this.toData(phone)).collect(Collectors.toList())));
    }
}
