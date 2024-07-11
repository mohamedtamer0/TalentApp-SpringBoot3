package com.tamer.talentapp.services;


import com.tamer.talentapp.entity.JobSeekerProfile;
import com.tamer.talentapp.entity.RecruiterProfile;
import com.tamer.talentapp.entity.Users;
import com.tamer.talentapp.repository.JobSeekerProfileRepository;
import com.tamer.talentapp.repository.RecruiterProfileRepository;
import com.tamer.talentapp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository, JobSeekerProfileRepository jobSeekerProfileRepository, RecruiterProfileRepository recruiterProfileRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Users addNew(Users users) {
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users savedUser = usersRepository.save(users);

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
