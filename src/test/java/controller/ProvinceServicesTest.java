package controller;

import model.Province;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProvinceServicesTest {
    ProvinceServices pS=new ProvinceServices();
    @AfterEach
    void after(){
        pS.getConnector().flush();
    }

    @Test
    void testGetAll(){
        ArrayList<Province> list= pS.readAll();
        assertEquals("[Province{id=1, name='Ha Noi'}, " +
                "Province{id=2, name='Ho Chi Minh'}, " +
                "Province{id=3, name='Da Nang'}, " +
                "Province{id=4, name='Hai Phong'}]",list.toString());
    }
    @Test
    void testCreate(){
        pS.create(new Province("Ca Mau"));
        ArrayList<Province> list= pS.readAll();
        assertEquals("[Province{id=1, name='Ha Noi'}, " +
                "Province{id=2, name='Ho Chi Minh'}, " +
                "Province{id=3, name='Da Nang'}, " +
                "Province{id=4, name='Hai Phong'}, " +
                "Province{id=5, name='Ca Mau'}]",list.toString());
        pS.create(new Province("Dak Lak"));

        list= pS.readAll();
        assertEquals("[Province{id=1, name='Ha Noi'}, " +
                "Province{id=2, name='Ho Chi Minh'}, " +
                "Province{id=3, name='Da Nang'}, " +
                "Province{id=4, name='Hai Phong'}, " +
                "Province{id=5, name='Ca Mau'}, " +
                "Province{id=6, name='Dak Lak'}]",list.toString());

    }
    @Test
    void testFindByIndex(){
        Province province= pS.findByIndex(1);
        assertEquals("Province{id=1, name='Ha Noi'}",province.toString());
        province= pS.findByIndex(2);
        assertEquals("Province{id=2, name='Ho Chi Minh'}",province.toString());
    }
    @Test
    void testUpdate(){
        pS.update(new Province(1,"Hello"));
        assertEquals("Province{id=1, name='Hello'}",pS.findByIndex(1).toString());

        pS.update(new Province(2,"Good Bye"));
        assertEquals("Province{id=2, name='Good Bye'}",pS.findByIndex(2).toString());
    }
    @Test
    @Disabled
    void testDelete(){
        pS.delete(1);
        ArrayList<Province> list= pS.readAll();
        assertEquals("[Province{id=2, name='Ho Chi Minh'}, " +
                "Province{id=3, name='Da Nang'}, " +
                "Province{id=4, name='Hai Phong'}, " +
                "Province{id=5, name='Ca Mau'}]",list.toString());
        pS.delete(3);
        assertEquals("[Province{id=2, name='Ho Chi Minh'}," +
                "Province{id=4, name='Hai Phong'}, " +
                "Province{id=5, name='Ca Mau'}]",list.toString());
    }
}