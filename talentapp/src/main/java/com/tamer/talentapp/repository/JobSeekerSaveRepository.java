package com.tamer.talentapp.repository;


import com.tamer.talentapp.entity.JobPostActivity;
import com.tamer.talentapp.entity.JobSeekerProfile;
import com.tamer.talentapp.entity.JobSeekerSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {

    public List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);

    List<JobSeekerSave> findByJob(JobPostActivity job);

}