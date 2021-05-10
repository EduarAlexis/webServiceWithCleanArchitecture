package com.nisium.usecase.phone;
import com.nisium.model.phone.Phone;
import com.nisium.model.phone.gateways.PhoneRepository;
import com.nisium.model.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PhoneUseCaseTest {
    @Mock
    private PhoneRepository phoneRepository;
    @InjectMocks
    private PhoneUseCase phoneUseCase;

    private User user;
    private Phone phone;
    private List<User> userList;
    private List<Phone> phoneList;

    @Before
    public void init(){
        phone = Phone.builder().id(java.util.UUID.randomUUID()).countrycode("57").citycode("1")
                .number("1234567899").build();
        phoneList = new ArrayList<Phone>();
        phoneList.add(phone);
        user = User.builder().name("eduar").id(java.util.UUID.randomUUID()).email("ealexis@gmail.com").password("Pass12").phones(phoneList).build();
        userList = new ArrayList<User>();
        userList.add(user);
        when(phoneUseCase.saveAll(phoneList)).thenReturn(phoneList);
        when(phoneUseCase.findByUser(user)).thenReturn(phoneList);
        phoneUseCase = new PhoneUseCase(phoneRepository);
    }

    @Test
    public void saveTestCase() {
        System.out.println("Save test");
        assertEquals(phoneList, phoneUseCase.findByUser(user));
    }

    @Test
    public void getAllTestCase() {
        System.out.println("Save test");
        assertEquals(phoneList, phoneUseCase.saveAll(phoneList));
    }
}
