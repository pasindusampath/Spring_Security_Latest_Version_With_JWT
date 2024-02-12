package com.example.project_01.service.impl;

import com.example.project_01.dto.AdminDTO;
import com.example.project_01.entity.Admin;
import com.example.project_01.ex.UserNotFoundException;
import com.example.project_01.repo.AdminRepo;
import com.example.project_01.service.AdminService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminServiceImpl implements AdminService {

    private final AdminRepo repo;
    private final Gson gson;

    public AdminServiceImpl(AdminRepo repo,Gson gson) {
        this.repo = repo;
        this.gson = gson;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Admin adminByUsername = repo.findAdminByUsername(username);
            if (adminByUsername != null) {
                List<String > list = gson.fromJson(adminByUsername.getType(), new TypeToken<ArrayList<String>>() {
                }.getType());
                String[] objects = (String[])list.toArray();
                return User.builder().username(
                        adminByUsername.getUsername()
                ).password(adminByUsername.getPassword()).roles(objects).build();
            }
            throw new UsernameNotFoundException("User not found");
        }catch (Exception e){
            throw new UsernameNotFoundException(e.getMessage(),e);
        }
    }

    @Override
    public AdminDTO search(String email, String password) throws UserNotFoundException {
        try {
            Admin adminByUsername = repo.findAdminByEmailAndPassword(email,password);
            if (adminByUsername != null && adminByUsername.getPassword().equals(password) && adminByUsername.getEmail().equals(email)) {
                ArrayList<String> list = gson.fromJson(adminByUsername.getType(), new TypeToken<ArrayList<String>>() {});
                String[] objects = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    objects[i] = list.get(i);
                }
                return new AdminDTO(adminByUsername.getUsername(),adminByUsername.getEmail(),adminByUsername.getPassword(),objects);

            }
            throw new UserNotFoundException("User not found");
        }catch (Exception e){
            throw new UserNotFoundException(e.getMessage(),e);
        }
    }
}
