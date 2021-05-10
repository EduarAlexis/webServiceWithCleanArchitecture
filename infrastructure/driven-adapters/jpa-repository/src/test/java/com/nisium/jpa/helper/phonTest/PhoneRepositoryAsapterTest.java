package com.nisium.jpa.helper.phonTest;

import com.nisium.jpa.phone.PhoneData;
import com.nisium.jpa.phone.PhoneRepositoryAdapter;
import com.nisium.jpa.user.UserData;
import com.nisium.model.phone.Phone;
import com.nisium.jpa.phone.PhoneRepository;
import com.nisium.model.user.User;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.reactivecommons.utils.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class PhoneRepositoryAsapterTest {
    @Mock
    private PhoneRepository phoneRepository;
    @Mock
    private ObjectMapper mapper;
    @InjectMocks
    private PhoneRepositoryAdapter phoneRepositoryAdapter;

    private User user;
    private UserData userData;
    private PhoneData phoneData;
    private Phone phone;
    private List<UserData> userList;
    private List<Phone> phoneList;
    private List<PhoneData> phoneDataList;

    @Before
    public void init() {
        phoneData = new PhoneData();
        phoneData.setId(java.util.UUID.randomUUID());
        phoneData.setCitycode("57");
        phoneData.setCountrycode("1");
        phoneData.setNumber("1234567899");

        phone = Phone.builder().id(java.util.UUID.randomUUID()).countrycode("57").citycode("1")
                .number("1234567899").build();

        phoneList = new ArrayList<>();
        phoneList.add(phone);

        phoneDataList = new ArrayList<>();
        phoneDataList.add(phoneData);

        user = User.builder().name("eduar").id(java.util.UUID.randomUUID()).email("ealexis@gmail.com").password("Pass12").phones(phoneList).build();

        userData = new UserData();
        userData.setName("eduar");
        userData.setId(java.util.UUID.randomUUID());
        userData.setEmail("ealexis@gmail.com");
        userData.setPassword("Pass12");
        userData.setPhones(phoneDataList);

        userList = new ArrayList<UserData>();
        userList.add(userData);
        when(mapper.map(user, UserData.class)).thenReturn(userData);
        when(mapper.mapBuilder(phoneData, Phone.PhoneBuilder.class)).thenReturn(phone.toBuilder());
        when(phoneRepository.findByUser(userData)).thenReturn(phoneDataList);
        when(phoneRepository.saveAll(phoneDataList)).thenReturn(phoneDataList);
        phoneRepositoryAdapter = new PhoneRepositoryAdapter(phoneRepository, mapper);
    }

    @Test
    public void findByUserTestCase() {
        assertEquals(phoneList, phoneRepositoryAdapter.findByUser(user));
    }

    @Test
    public void saveAllTestCase() {
        assertEquals(phoneList, phoneRepositoryAdapter.saveAll(phoneList));
    }
}