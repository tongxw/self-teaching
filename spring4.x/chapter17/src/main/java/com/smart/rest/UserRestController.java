package com.smart.rest;

import com.smart.UserService;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.Callable;

@RestController
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/api")
    public Callable<User> api() {
        System.out.println("api====start");
        return new Callable<User>() {
            @Override
            public User call() throws Exception {
                Thread.sleep(10L * 1000);
                User user = new User();
                user.setUserId("1111");
                user.setUserName("haha");
                return user;
            }
        };
    }

    public static void main(String[] args) {
        AsyncRestTemplate template = new AsyncRestTemplate();

        // no block
        ListenableFuture<ResponseEntity<User>> future = template.getForEntity(
                "http://localhost:8080/chapter17/api", User.class);

        // async
        future.addCallback(new ListenableFutureCallback<ResponseEntity<User>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("====client failure: " + throwable);
            }

            @Override
            public void onSuccess(ResponseEntity<User> userResponseEntity) {
                System.out.println("====client get result: " + userResponseEntity.getBody());
            }
        });

        System.out.println("===no wait===");
    }

}
