package com.tamer.talentapp.repository;

import com.tamer.talentapp.entity.RecruiterProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterProfileRepository
        extends JpaRepository<RecruiterProfile, Integer> {

}
