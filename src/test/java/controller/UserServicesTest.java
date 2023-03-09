package controller;

import model.Province;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserServicesTest {
    UserServices uS=new UserServices();
    @AfterEach
    void after(){
        uS.getConnector().flush();
    }
    @Test
    void testGetALl(){
        ArrayList<User> list=uS.readAll();
        assertEquals("[User{id=1, name='phuc', province=Province{id=1, name='Ha Noi'}}, " +
                "User{id=2, name='hai', province=Province{id=2, name='Ho Chi Minh'}}, " +
                "User{id=3, name='hieu', province=Province{id=3, name='Da Nang'}}, " +
                "User{id=4, name='chau', province=Province{id=1, name='Ha Noi'}}, " +
                "User{id=5, name='thao', province=Province{id=3, name='Da Nang'}}]",list.toString());
    }
    @Test
    void testCreate(){
        User user=new User("yeah",new Province(1));
        uS.create(user);
        ArrayList<User> list=uS.readAll();
        assertEquals("[User{id=1, name='phuc', province=Province{id=1, name='Ha Noi'}}, " +
                "User{id=2, name='hai', province=Province{id=2, name='Ho Chi Minh'}}, " +
                "User{id=3, name='hieu', province=Province{id=3, name='Da Nang'}}, " +
                "User{id=4, name='chau', province=Province{id=1, name='Ha Noi'}}, " +
                "User{id=5, name='thao', province=Province{id=3, name='Da Nang'}}, " +
                "User{id=6, name='yeah', province=Province{id=1, name='Ha Noi'}}]",list.toString());
        uS.create(new User("woah",new Province(3)));
        list=uS.readAll();
        assertEquals("[User{id=1, name='phuc', province=Province{id=1, name='Ha Noi'}}, " +
                "User{id=2, name='hai', province=Province{id=2, name='Ho Chi Minh'}}, " +
                "User{id=3, name='hieu', province=Province{id=3, name='Da Nang'}}, " +
                "User{id=4, name='chau', province=Province{id=1, name='Ha Noi'}}, " +
                "User{id=5, name='thao', province=Province{id=3, name='Da Nang'}}, " +
                "User{id=6, name='yeah', province=Province{id=1, name='Ha Noi'}}, " +
                "User{id=7, name='woah', province=Province{id=3, name='Da Nang'}}]",list.toString());
    }
    @Test
    void testFindByIndex(){
        User user=uS.findByIndex(3);
        assertEquals("User{id=3, name='hieu', province=Province{id=3, name='Da Nang'}}",user.toString());
        user=uS.findByIndex(5);
        assertEquals("User{id=5, name='thao', province=Province{id=3, name='Da Nang'}}",user.toString());
    }
    @Test
    void testUpdate(){
        User user=new User(4,"paksdjfpdaf",new Province(4));
        uS.update(user);
        user=uS.findByIndex(4);
        assertEquals("User{id=4, name='paksdjfpdaf', province=Province{id=4, name='Hai Phong'}}",user.toString());

        user=new User(3,"ddddsf",new Province(3));
        uS.update(user);
        user=uS.findByIndex(3);
        assertEquals("User{id=3, name='ddddsf', province=Province{id=3, name='Da Nang'}}",user.toString());
    }
}