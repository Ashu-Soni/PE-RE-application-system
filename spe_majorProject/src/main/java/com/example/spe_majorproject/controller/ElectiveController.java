package com.example.spe_majorproject.controller;

import com.example.spe_majorproject.bean.Application;
import com.example.spe_majorproject.bean.Elective;
import com.example.spe_majorproject.bean.Faculty;
import com.example.spe_majorproject.bean.Student;
import com.example.spe_majorproject.repository.ApplicationRepository;
import com.example.spe_majorproject.repository.ElectiveRepository;
import com.example.spe_majorproject.repository.FacultyRepository;
import com.example.spe_majorproject.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class ElectiveController {

  @Autowired
  private ElectiveRepository electiverepo;

  @Autowired
  private ApplicationRepository apprepo;

  @Autowired
  private FacultyRepository factrepo;

  @Autowired
  private StudentRepository studentRepository;

  @GetMapping("/ProjectElectives")
  public List<Elective> getProjectElectives()
  {
    List<Elective> projectElectives=electiverepo.findByType("PE");
    return projectElectives;
  }

  @GetMapping("/ResearchElectives")
  public List<Elective> getResearchElectives()
  {
    List<Elective> researchElectives=electiverepo.findByType("RE");
    return researchElectives;
  }

  @GetMapping("/getAllElectives")
  public List<Elective> getAllElectives()
  {
    return electiverepo.findAll();
  }

  @PostMapping("/ProjectElectives/apply")
  public Boolean applyForPE(@RequestBody Application application)
  {
    Student student=studentRepository.findById(application.getStudemail()).orElse(new Student());
    Elective elective=electiverepo.findById(application.getEid()).orElse(new Elective());
    String studentName=student.getName();
    String factemail=elective.getEmail();
    if(!studentName.equals("none") && !factemail.equals("none"))
    {
      application.setStudname(studentName);
      application.setFacultyemail(factemail);
      apprepo.save(application);
      return true;
    }
    return false;
  }

  @PostMapping("/ResearchElectives/apply")
  public Boolean applyForRE(@RequestBody Application application)
  {
    Student student=studentRepository.findById(application.getStudemail()).orElse(new Student());
    String studentName=student.getName();
    if(!studentName.equals("none"))
    {
      application.setStudname(studentName);
      apprepo.save(application);
      return true;
    }
    return false;
  }

}
