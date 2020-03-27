package com.zufar.requestsystem.repository;

import com.zufar.requestsystem.entity.Role;
import com.zufar.requestsystem.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}

