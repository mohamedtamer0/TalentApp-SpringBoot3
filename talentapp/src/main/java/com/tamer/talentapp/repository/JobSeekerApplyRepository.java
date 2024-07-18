package com.tamer.talentapp.repository;

import com.tamer.talentapp.entity.JobPostActivity;
import com.tamer.talentapp.entity.JobSeekerApply;
import com.tamer.talentapp.entity.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply, Integer> {

    List<JobSeekerApply> findByUserId(JobSeekerProfile userId);

    List<JobSeekerApply> findByJob(JobPostActivity job);
}