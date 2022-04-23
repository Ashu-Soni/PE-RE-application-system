package com.example.spe_majorproject.controller;

import com.example.spe_majorproject.bean.Application;
import com.example.spe_majorproject.bean.Elective;
import com.example.spe_majorproject.bean.Faculty;
import com.example.spe_majorproject.bean.Student;
import com.example.spe_majorproject.repository.ApplicationRepository;
import com.example.spe_majorproject.repository.ElectiveRepository;
import com.example.spe_majorproject.repository.FacultyRepository;
import com.example.spe_majorproject.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

@CrossOrigin
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
  public ResponseEntity<List<Elective>> getProjectElectives()
  {
    List<Elective> projectElectives=electiverepo.findByType("Project Elective");

    return ResponseEntity.ok().header("Content-Type", "application/json")
            .body(projectElectives);
  }

  @GetMapping("/ResearchElectives")
  public ResponseEntity<List<Elective>> getResearchElectives()
  {
    List<Elective> researchElectives=electiverepo.findByType("Research Elective");
    return ResponseEntity.ok().header("Content-Type", "application/json")
            .body(researchElectives);
  }

  @GetMapping("/getAllElectives")
  public List<Elective> getAllElectives()
  {
    return electiverepo.findAll();
  }

  @PostMapping("/ProjectElectives/apply")
  public ResponseEntity<Response> applyForPE(@RequestBody Application application)
  {
    Response response=new Response();
    Student student=studentRepository.findById(application.getEmail()).orElse(new Student());
    Elective elective=electiverepo.findById(application.getEid()).orElse(new Elective());
    String studentName=student.getName();
    String factemail=elective.getEmail();
    String projectName=elective.getName();
    Application existing_application=apprepo.findByEmailAndEid(application.getEmail(),application.getEid());
    if(existing_application!=null)
    {
      response.setStatus("Failed");
      response.setMessage("Application submission failed because you have already applied to this elective");
      return ResponseEntity.badRequest().header("Content-Type", "application/json")
              .body(response);
    }
    if(!studentName.equals("none") && !factemail.equals("none"))
    {
      application.setStudname(studentName);
      application.setFacultyemail(factemail);
      application.setName(projectName);
      application.setStatus("Pending");
      apprepo.save(application);
      response.setStatus("Success");
      response.setMessage("Application submitted successfully");
      return ResponseEntity.ok().header("Content-Type", "application/json")
              .body(response);
    }
    response.setStatus("Failed");
    response.setMessage("Application submission failed");
    return ResponseEntity.badRequest().header("Content-Type", "application/json")
            .body(response);
  }

  @PostMapping("/ResearchElectives/apply")
  public ResponseEntity<Response> applyForRE(@RequestBody Application application)
  {
    Response response=new Response();
    Student student=studentRepository.findById(application.getEmail()).orElse(new Student());
    Elective elective=electiverepo.findById(application.getEid()).orElse(new Elective());
    String studentName=student.getName();
    System.out.println(application);
    System.out.println(studentName);
    String factemail=elective.getEmail();
    String projectName=elective.getName();
    Application existing_application=apprepo.findByEmailAndEid(application.getEmail(),application.getEid());
    if(existing_application!=null)
    {
      response.setStatus("Failed");
      response.setMessage("Application submission failed because you have already applied to this elective");
      return ResponseEntity.badRequest().header("Content-Type", "application/json")
              .body(response);
    }
    if(!studentName.equals("none")  && !factemail.equals("none"))
    {
      application.setStudname(studentName);
      application.setFacultyemail(factemail);
      application.setName(projectName);
      application.setStatus("Pending");
      System.out.println(application);
      apprepo.save(application);
      response.setStatus("Success");
      response.setMessage("Application submitted successfully");
      return ResponseEntity.ok().header("Content-Type", "application/json")
              .body(response);
    }
    response.setStatus("Failed");
    response.setMessage("Application submission failed");
    return ResponseEntity.badRequest().header("Content-Type", "application/json")
            .body(response);
  }
  @PostMapping ("/MyElectives")
  public ResponseEntity<List<Elective>> getElectives(@RequestBody Map<String,String> factEmail)
  {
    return ResponseEntity.ok().header("Content-Type", "application/json")
            .body(electiverepo.findByEmail(factEmail.get("email")));
  }
  @PostMapping("/MyElectives/Add")
  public ResponseEntity<Response> addElective(@RequestBody Elective elective)
  {
    Response response=new Response();
    Faculty factname=factrepo.findById(elective.getEmail()).orElse(new Faculty());
    if(!factname.equals("none"))
    {
      elective.setFaculty(factname.getName());
      if(elective.getType().equals("project_elective"))
      {
          elective.setType("Project Elective");
      }
      if(elective.getType().equals("research_elective"))
      {
          elective.setType("Research Elective");
      }
      electiverepo.save(elective);
      response.setStatus("Success");
      response.setMessage("New Elective added successfully");
      return ResponseEntity.ok().header("Content-Type", "application/json")
              .body(response);
    }
    response.setStatus("Failed");
    response.setMessage("Failed to add new elective");
    return ResponseEntity.badRequest().header("Content-Type", "application/json")
            .body(response);
  }


  @PostMapping("/MyElectives/Update")
  public ResponseEntity<Response> updateElective(@RequestBody Elective elective)
  {
    Response response=new Response();
    Faculty factname=factrepo.findById(elective.getEmail()).orElse(new Faculty());
    if(!factname.equals("none"))
    {
      elective.setFaculty(factname.getName());
      electiverepo.save(elective);
      response.setStatus("Success");
      response.setMessage("Elective details updated successfully");
      return ResponseEntity.ok().header("Content-Type", "application/json")
              .body(response);
    }
    response.setStatus("Failed");
    response.setMessage("Elective details not updated");
    return ResponseEntity.badRequest().header("Content-Type", "application/json")
            .body(response);
  }
  @PostMapping("/MyApplications")
  public List<Application> getStudentApplications(@RequestBody Map<String ,String> studEmail)
  {
    return apprepo.findByEmail(studEmail.get("studentemail"));
  }

  @PostMapping ("/Applications")
  public List<Application> getApplications(@RequestBody Map<String,String> factEmail)
  {
    String status="Pending";
    return apprepo.findByFacultyemailAndStatus(factEmail.get("facultyemail"),status);
  }


  @PostMapping("/Applications/Accept")
  public ResponseEntity<Response> acceptApplication(@RequestBody Map<String,Integer> application)
  {
    Response response=new Response();
    int aid=application.get("aid");
    int eid=application.get("eid");
    Application appl=apprepo.findById(aid).orElse(new Application());
    Elective elec=electiverepo.findById(eid).orElse(new Elective());
    if(appl.getEmail().equals("none") || !appl.getStatus().equals("Pending"))
    {
      response.setStatus("Failed");
      response.setMessage("Invalid student id");
      return ResponseEntity.badRequest().header("Content-Type", "application/json")
              .body(response);
    }
    if(!appl.getStatus().equals("Pending"))
    {
      response.setStatus("Failed");
      response.setMessage("Cannot accept the application because either it is already accepted or rejected");
      return ResponseEntity.badRequest().header("Content-Type", "application/json")
              .body(response);
    }
    if(elec.getEmail().equals("none"))
    {
      response.setStatus("Failed");
      response.setMessage("Invalid faculty id");
      return ResponseEntity.badRequest().header("Content-Type", "application/json")
              .body(response);
    }
    if(elec.getVacancy()<=0)
    {
      response.setStatus("Failed");
      response.setMessage("No vacancy available in this elective");
      return ResponseEntity.badRequest().header("Content-Type", "application/json")
              .body(response);
    }
    appl.setStatus("Accepted");
    apprepo.save(appl);
    elec.setVacancy(elec.getVacancy()-1);
    electiverepo.save(elec);
    response.setStatus("Success");
    response.setMessage("Application accepted successfully");
    return ResponseEntity.ok().header("Content-Type", "application/json")
            .body(response);
  }

  @PostMapping("/Applications/Reject")
  public ResponseEntity<Response> rejectApplication(@RequestBody Map<String,Integer> application)
  {
    Response response=new Response();
    int aid=application.get("aid");
    Application appl=apprepo.findById(aid).orElse(new Application());
    if(appl.getEmail().equals("none") || !appl.getStatus().equals("Pending"))
    {
      response.setStatus("Failed");
      response.setMessage("Invalid student id");
      return ResponseEntity.badRequest().header("Content-Type", "application/json")
              .body(response);
    }

    appl.setStatus("Rejected");
    apprepo.save(appl);
    response.setStatus("Success");
    response.setMessage("Application rejected successfully");
    return ResponseEntity.ok().header("Content-Type", "application/json")
            .body(response);
  }

  @GetMapping("/Faculty")
  public List<Faculty> getFaculty()
  {
    return factrepo.findAll();
  }
}
