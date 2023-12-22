package com.coherent.zipcode.userController;

import com.coherent.User;
import com.coherent.zipcode.BasicTestClass;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class GetUsers extends BasicTestClass {

    @Test
    public void shouldGetUserswithGET(){
        List<User> userList = userController.sendGetUsers();
        userList.forEach(System.out::println);

        Assertions.assertThat(userList.size()).isEqualTo(6);

    }
}
