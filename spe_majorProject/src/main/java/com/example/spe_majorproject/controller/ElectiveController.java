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
import java.util.Map;

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
  @GetMapping("/MyElectives")
  public List<Elective> getElectives(@RequestBody Map<String,String> factEmail)
  {
    //System.out.println(factEmail);
    return electiverepo.findByEmail(factEmail.get("email"));
  }
  @PostMapping("/MyElectives/Add")
  public Boolean addElective(@RequestBody Elective elective)
  {
    Faculty factname=factrepo.findById(elective.getEmail()).orElse(new Faculty());
    if(!factname.equals("none"))
    {
      elective.setFaculty(factname.getName());
      electiverepo.save(elective);
      return true;
    }
    return false;
  }
  @PostMapping("/MyElectives/Update")
  public Boolean updateElective(@RequestBody Elective elective)
  {
    Faculty factname=factrepo.findById(elective.getEmail()).orElse(new Faculty());
    if(!factname.equals("none"))
    {
      elective.setFaculty(factname.getName());
      electiverepo.save(elective);
      return true;
    }
    return false;
  }

  @GetMapping("/Applications")
  public List<Application> getApplications(@RequestBody Map<String,String> factEmail)
  {
    return apprepo.findByFacultyemail(factEmail.get("facultyemail"));
  }
  @PostMapping("/Applications/Accept")
  public Boolean acceptApplication(@RequestBody Map<String,Integer> application)
  {
    int aid=application.get("aid");
    int eid=application.get("eid");
    Application appl=apprepo.findById(aid).orElse(new Application());
    Elective elec=electiverepo.findById(eid).orElse(new Elective());
    if(appl.getStudemail().equals("none") || !appl.getStatus().equals("Pending"))
    {
      return false;
    }
    if(elec.getEmail().equals("none"))
    {
      return false;
    }
    if(elec.getVacancy()<=0)
    {
      return false;
    }
    appl.setStatus("Accepted");
    apprepo.save(appl);
    elec.setVacancy(elec.getVacancy()-1);
    electiverepo.save(elec);
    return true;
  }
  @PostMapping("/Applications/Reject")
  public Boolean rejectApplication(@RequestBody Map<String,Integer> application)
  {
    int aid=application.get("aid");
    Application appl=apprepo.findById(aid).orElse(new Application());
    if(appl.getStudemail().equals("none") || !appl.getStatus().equals("Pending"))
    {
      return false;
    }

    appl.setStatus("Rejected");
    apprepo.save(appl);
    return true;
  }
}
