package com.pbt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbt.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
