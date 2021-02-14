package de.structuremade.ms.schoolservice.api.routes;

import de.structuremade.ms.schoolservice.api.json.SettingsJson;
import de.structuremade.ms.schoolservice.api.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import de.structuremade.ms.schoolservice.api.json.CreateSchoolJson;

import javax.servlet.http.HttpServletRequest;
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

    @CrossOrigin
    @PutMapping("/settings/{schoolid}")
    public void settings(@RequestBody SettingsJson settingsJson, @PathVariable String schoolid, HttpServletResponse response, HttpServletRequest request){
        switch (schoolService.setupSettings(settingsJson, schoolid, request.getHeader("Authorization").substring(7))){
            case 0 -> response.setStatus(HttpStatus.OK.value());
            case 1 -> response.setStatus(HttpStatus.UNAUTHORIZED.value());
            case 2 -> response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
