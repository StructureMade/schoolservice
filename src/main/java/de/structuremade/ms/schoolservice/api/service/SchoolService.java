package de.structuremade.ms.schoolservice.api.service;

import de.structuremade.ms.schoolservice.api.json.SettingsJson;
import de.structuremade.ms.schoolservice.database.entity.School;
import de.structuremade.ms.schoolservice.database.entity.Schoolsettings;
import de.structuremade.ms.schoolservice.database.entity.User;
import de.structuremade.ms.schoolservice.database.repo.SchoolRepository;
import de.structuremade.ms.schoolservice.database.repo.SchoolSettingsRepository;
import de.structuremade.ms.schoolservice.database.repo.UserRepository;
import de.structuremade.ms.schoolservice.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class SchoolService {

    private final Logger LOGGER = LoggerFactory.getLogger(SchoolService.class);

    @Autowired
    SchoolRepository schoolRepo;

    @Autowired
    SchoolSettingsRepository schoolsettingsRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    JWTUtil jwtUtil;

    public int create(String name, String email) {
        try {
            if (userRepo.existsByEmail(email)) {
                return 1;
            }
            School school = new School();
            school.setName(name);
            school.setEmail(email);
            schoolRepo.save(school);
            createUser(email, school.getId());
        } catch (Exception e) {
            LOGGER.error("Couldn't create School", e.fillInStackTrace());
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

    public int setupSettings(SettingsJson json, String schoolid, String jwt) {
        try {
            LOGGER.info("Check if User have rights for do this on this School");
            User user = userRepo.getOne(jwtUtil.extractIdOrEmail(jwt));
            if (user.getLastSchool().equals(schoolid)) {
                LOGGER.info("All was fine");
                Schoolsettings schoolsettings = schoolsettingsRepo.findBySchool(schoolRepo.getOne(schoolid));
                if(schoolsettings == null) {
                schoolsettings = new Schoolsettings();
                }
                schoolsettings.setDateOfBegin(convertDate(json.getBegin()));
                schoolsettings.setDateOfEnd(convertDate(json.getEnd()));
                schoolsettingsRepo.save(schoolsettings);
                return 0;
            }
            return 1;
        } catch (Exception e) {
            LOGGER.error("Couldn't setup Settings", e.fillInStackTrace());
            return 2;
        }
    }

    private Date convertDate(String date){
        String [] splitDate = date.split("\\.");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splitDate[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(splitDate[1]) - 1);
        calendar.set(Calendar.YEAR, Integer.parseInt(splitDate[2]));
        return calendar.getTime();
    }

}
