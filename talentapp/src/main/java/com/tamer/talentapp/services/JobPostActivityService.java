package com.tamer.talentapp.services;


import com.tamer.talentapp.entity.JobPostActivity;
import com.tamer.talentapp.repository.JobPostActivityRepository;
import org.springframework.stereotype.Service;
@Service
public class JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;

    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity) {
        return jobPostActivityRepository.save(jobPostActivity);
    }
}