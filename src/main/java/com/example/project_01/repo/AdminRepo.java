package com.example.project_01.repo;

import com.example.project_01.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends CrudRepository<Admin, Integer> {
    public Admin findAdminByUsername(String username);
    public Admin findAdminByEmailAndPassword(String email, String password);

}
