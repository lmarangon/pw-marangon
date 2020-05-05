/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.pw.users;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author luca
 */
@ApplicationScoped
public class UserStore {

    private final Map<Long, User> users = new HashMap<>();

    @PostConstruct
    public void init() {
        Stream.of(new User(1l, "marangon", "marangonpwd"), new User(2l, "scavarda", "scavardapwd"), new User(3l, "zinutti", "zinuttipwd"))
                .forEach(v -> users.put(v.getId(), v));
    }

    public Collection<User> all() {
        return users.values();
    }

    public User find(Long id) {
        return users.get(id);
    }

    public User create(User u) {
        System.out.println("create user " + u);
        users.putIfAbsent(u.getId(), u);
        return users.get(u.getId());
    }

    public User update(User u) {
        System.out.println("update user " + u);
        users.put(u.getId(), u);
        return users.get(u.getId());
    }

    public void delete(Long id) {
        System.out.println("delete user " + id);
        users.remove(id);
    }

    public Collection<User> search(String search) {
        return users.values().stream()
                .filter(v -> this.search(v,search)).collect(Collectors.toList());
    }
    
    private boolean search(User u, String search){
        return  (u.getFirstName()!= null && u.getFirstName().contains(search))
                || (u.getLastName()!= null && u.getLastName().contains(search)) 
                || (u.getUsr()!= null && u.getUsr().contains(search));
    }
}