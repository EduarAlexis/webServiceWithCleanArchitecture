package com.nisium.jpa.helper.userTest;

import com.nisium.jpa.phone.PhoneData;
import com.nisium.jpa.user.UserData;
import com.nisium.jpa.user.UserRepository;
import com.nisium.jpa.user.UserRepositoryAdapter;
import com.nisium.model.phone.Phone;
import com.nisium.model.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.reactivecommons.utils.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryAdapterTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ObjectMapper mapper;
    @InjectMocks
    private UserRepositoryAdapter userRepositoryAdapter;

    private User user;
    private UserData userData;
    private PhoneData phoneData;
    private Phone phone;
    private List<UserData> userDataList;
    private List<User> userList;
    private List<Phone> phoneList;
    private List<PhoneData> phoneDataList;

    @Before
    public void init() {
        phoneData = new PhoneData();
        phoneData.setId(java.util.UUID.randomUUID());
        phoneData.setCitycode("57");
        phoneData.setCountrycode("1");
        phoneData.setNumber("1234567899");

        phoneDataList = new ArrayList<>();
        phoneDataList.add(phoneData);

        phone = Phone.builder().id(java.util.UUID.randomUUID()).countrycode("57").citycode("1")
                .number("1234567899").build();

        phoneList = new ArrayList<>();
        phoneList.add(phone);

        user = User.builder().name("eduar").id(java.util.UUID.randomUUID()).email("ealexis@gmail.com").password("Pass12").phones(phoneList).build();
        userList = new ArrayList<>();
        userList.add(user);

        userData = new UserData();
        userData.setName("eduar");
        userData.setId(java.util.UUID.randomUUID());
        userData.setEmail("ealexis@gmail.com");
        userData.setPassword("Pass12");
        userData.setPhones(phoneDataList);

        userDataList = new ArrayList<UserData>();
        userDataList.add(userData);
        when(mapper.map(user, UserData.class)).thenReturn(userData);
        when(mapper.mapBuilder(userData, User.UserBuilder.class)).thenReturn(user.toBuilder());
        when(userRepository.findByEmail("ealexis@gmail.com")).thenReturn(userData);
        when(userRepository.findAll()).thenReturn(userDataList);
        userRepositoryAdapter = new UserRepositoryAdapter(userRepository, mapper);
    }

    @Test
    public void findByUserTestCase() {
        assertEquals(userList, userRepositoryAdapter.getAll());
    }

    @Test
    public void saveAllTestCase() {
        assertEquals(user, userRepositoryAdapter.findByEmail("ealexis@gmail.com"));
    }
}