package com.nisium.jpa.user;

import java.util.List;

import com.nisium.jpa.helper.AdapterOperations;
import com.nisium.model.user.User;
import com.nisium.model.user.gateways.UserRepository;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter extends AdapterOperations<User, UserData, Long, com.nisium.jpa.user.UserRepository>
        implements UserRepository {

    public UserRepositoryAdapter(com.nisium.jpa.user.UserRepository repository, ObjectMapper mapper) {
        /**
         * Could be use mapper.mapBuilder if your domain model implement builder pattern
         * super(repository, mapper, d ->
         * mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build()); Or using
         * mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.mapBuilder(d, User.UserBuilder.class).build());
    }

    @Override
    public List<User> getAll() {
        return this.toList(this.repository.findAll());
    }

    @Override
    public User findByEmail(String email) {
        return this.toEntity(this.repository.findByEmail(email));
    }
}
