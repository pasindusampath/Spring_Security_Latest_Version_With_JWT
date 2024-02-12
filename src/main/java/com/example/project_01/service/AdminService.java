package com.example.project_01.service;

import com.example.project_01.dto.AdminDTO;
import com.example.project_01.ex.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService  extends UserDetailsService {
    public AdminDTO search(String email,String password) throws UserNotFoundException;
}
