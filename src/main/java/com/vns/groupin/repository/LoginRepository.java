package com.vns.groupin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vns.groupin.entity.UserLogin;

@Repository
public interface LoginRepository extends JpaRepository<UserLogin,Long>{

}
