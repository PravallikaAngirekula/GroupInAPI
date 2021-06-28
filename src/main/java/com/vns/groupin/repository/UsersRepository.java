package com.vns.groupin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vns.groupin.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long>{

	@Query(value = "SELECT * FROM users WHERE users.username = ?1 ", nativeQuery = true)
	Users findByUserName(String mobileNumber);

//	@Query(value = "SELECT * FROM users WHERE users.username = :username ", nativeQuery = true)
//	Users finduIdByUserName(@Param("username") String userName);
	
//	Users finduIdByUserName(String userName);


}
