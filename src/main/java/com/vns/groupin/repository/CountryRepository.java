package com.vns.groupin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vns.groupin.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

}
