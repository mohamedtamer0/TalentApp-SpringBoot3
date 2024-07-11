package com.tamer.talentapp.services;


import com.tamer.talentapp.entity.JobSeekerProfile;
import com.tamer.talentapp.entity.RecruiterProfile;
import com.tamer.talentapp.entity.Users;
import com.tamer.talentapp.repository.JobSeekerProfileRepository;
import com.tamer.talentapp.repository.RecruiterProfileRepository;
import com.tamer.talentapp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class UsersService {

    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, JobSeekerProfileRepository jobSeekerProfileRepository, RecruiterProfileRepository recruiterProfileRepository) {
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
    }


    public Users addNew(Users users) {
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));

        // Save the user first
        Users savedUser = usersRepository.save(users);

        // Determine user type and save corresponding profile
        int userTypeId = users.getUserTypeId().getUserTypeId();
        if (userTypeId == 21) {
            RecruiterProfile recruiterProfile = new RecruiterProfile(savedUser);
            recruiterProfileRepository.save(recruiterProfile);
        } else {
            JobSeekerProfile jobSeekerProfile = new JobSeekerProfile(savedUser);
            jobSeekerProfileRepository.save(jobSeekerProfile);
        }

        return savedUser;
    }

    public Optional<Users> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

}
