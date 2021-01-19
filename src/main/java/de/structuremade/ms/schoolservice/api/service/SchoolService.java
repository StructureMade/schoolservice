package de.structuremade.ms.schoolservice.api.service;

import de.structuremade.ms.schoolservice.database.entity.School;
import de.structuremade.ms.schoolservice.database.entity.User;
import de.structuremade.ms.schoolservice.database.repo.SchoolRepository;
import de.structuremade.ms.schoolservice.database.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class SchoolService {

    @Autowired
    SchoolRepository schoolRepo;

    @Autowired
    UserRepository userRepo;

    public int create(String name, String email) {
        try {
            if (userRepo.existsByEmail(email)) {
                return 1;
            }
            School school = new School();
            school.setName(name);
            school.setEmail(email);
            schoolRepo.save(school);
            school = schoolRepo.findByEmail(email);
            createUser(email, school.getId());
        } catch (Exception e) {
            return 2;
        }
        return 0;
    }

    private void createUser(String email, String schoolid) {
        List<String> roles = new ArrayList<>();
        List<School> schools = new ArrayList<>();
        schools.add(schoolRepo.getOne(schoolid));
        roles.add("Admin");
        User user = new User();
        user.setEmail(email);
        user.setFirstname("Admin");
        user.setSchools(schools);
        user.setCreationDate(Calendar.getInstance().getTime());
        user.setName("");
        userRepo.save(user);
    }
}
