package com.example.customendpoint;

import com.example.model.User;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Endpoint(id = "active-users")
public class ActiveUsersEndpoint {
    private final Map<String, User> users = new HashMap<>();

    ActiveUsersEndpoint() {
        users.put("kike", new User("enrique coello"));
        users.put("mike", new User("michael baizano"));
        users.put("chris", new User("christoper baizano"));
    }

    @SuppressWarnings("unchecked")
    @ReadOperation
    public List getAll() {
        return new ArrayList(users.values());
    }

    @ReadOperation
    public User getActiveUser(@Selector String user) {
        return users.get(user);
    }

    @WriteOperation
    public void updateActiveUser(@Selector String name, String user) {
        users.put(name, new User(user));
    }

    @DeleteOperation
    public void deleteActiveUser(@Selector String name) {
        users.remove(name);
    }
}
