package de.structuremade.ms.schoolservice.api.routes;

import de.structuremade.ms.schoolservice.api.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.structuremade.ms.schoolservice.api.json.CreateSchoolJson;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("service/school")
public class SchoolRoute {

    @Autowired
    SchoolService schoolService;

    @PostMapping(path = "/create", produces = "application/json")
    public void createSchool(@RequestBody @Valid CreateSchoolJson schoolJson, HttpServletResponse response) {

        switch (schoolService.create(schoolJson.getName(), schoolJson.getEmail())) {
            case 0:
                response.setStatus(HttpStatus.CREATED.value());
                break;
            case 1:
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                break;
            case 2:
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                break;
        }
    }
}
