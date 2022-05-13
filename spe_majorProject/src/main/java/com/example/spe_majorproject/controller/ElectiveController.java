package com.example.spe_majorproject.controller;

import com.example.spe_majorproject.bean.Application;
import com.example.spe_majorproject.bean.Elective;
import com.example.spe_majorproject.bean.Faculty;
import com.example.spe_majorproject.bean.Student;
import com.example.spe_majorproject.repository.ApplicationRepository;
import com.example.spe_majorproject.repository.ElectiveRepository;
import com.example.spe_majorproject.repository.FacultyRepository;
import com.example.spe_majorproject.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/dashboard")
public class  ElectiveController {

  @Autowired
  private ElectiveRepository electiverepo;

  @Autowired
  private ApplicationRepository apprepo;

  @Autowired
  private FacultyRepository factrepo;

  @Autowired
  private StudentRepository studentRepository;

  private final Logger logger= LogManager.getLogger(ElectiveController.class);

  @GetMapping("/ProjectElectives")
  public ResponseEntity<List<Elective>> getProjectElectives()
  {
    logger.info("[PROJECT_ELECTIVES] - GET ALL");
    List<Elective> projectElectives=electiverepo.findByType("Project Elective");
    return ResponseEntity.ok().header("Content-Type", "application/json")
            .body(projectElectives);
  }

  @GetMapping("/ResearchElectives")
  public ResponseEntity<List<Elective>> getResearchElectives()
  {
    logger.info("[RESEARCH_ELECTIVES] - GET ALL");
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
    logger.info("[PROJECT_ELECTIVES] - APPLY");
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
      logger.info("[PROJECT_ELECTIVES APPLY] - "+response.getStatus());
      return ResponseEntity.badRequest().header("Content-Type", "application/json")
              .body(response);
    }
    if(!studentName.equals("none") && !factemail.equals("none"))
    {
      application.setStudname(studentName);
      application.setFacultyemail(factemail);
      application.setName(projectName);
      application.setStatus("Pending");
      application.setType("Project Elective");
      apprepo.save(application);
      response.setStatus("Success");
      response.setMessage("Application submitted successfully");
      logger.info("[PROJECT_ELECTIVES APPLY] - "+response.getStatus());
      return ResponseEntity.ok().header("Content-Type", "application/json")
              .body(response);
    }
    response.setStatus("Failed");
    response.setMessage("Application submission failed");
    logger.info("[PROJECT_ELECTIVES APPLY] - "+response.getStatus());
    return ResponseEntity.badRequest().header("Content-Type", "application/json")
            .body(response);
  }

  @PostMapping("/ResearchElectives/apply")
  public ResponseEntity<Response> applyForRE(@RequestBody Application application)
  {
    logger.info("[RESEARCH_ELECTIVES] - APPLY");
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
      logger.info("[RESEARCH_ELECTIVES APPLY] - "+response.getStatus());
      return ResponseEntity.badRequest().header("Content-Type", "application/json")
              .body(response);
    }
    if(!studentName.equals("none")  && !factemail.equals("none"))
    {
      application.setStudname(studentName);
      application.setFacultyemail(factemail);
      application.setName(projectName);
      application.setStatus("Pending");
      application.setType("Research Elective");
      System.out.println(application);
      apprepo.save(application);
      response.setStatus("Success");
      response.setMessage("Application submitted successfully");
      logger.info("[RESEARCH_ELECTIVES APPLY] - "+response.getStatus());
      return ResponseEntity.ok().header("Content-Type", "application/json")
              .body(response);
    }
    response.setStatus("Failed");
    response.setMessage("Application submission failed");
    logger.info("[RESEARCH_ELECTIVES APPLY] - "+response.getStatus());
    return ResponseEntity.badRequest().header("Content-Type", "application/json")
            .body(response);
  }
  @PostMapping ("/MyElectives")
  public ResponseEntity<List<Elective>> getElectives(@RequestBody Map<String,String> factEmail)
  {
    logger.info("[GET_MY_ELECTIVES] - "+factEmail.get("email"));
    return ResponseEntity.ok().header("Content-Type", "application/json")
            .body(electiverepo.findByEmail(factEmail.get("email")));
  }
  @PostMapping("/MyElectives/Add")
  public ResponseEntity<Response> addElective(@RequestBody Elective elective)
  {
    logger.info("[ADD_MY_ELECTIVES] - "+elective.getEmail());
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
      logger.info("[ADD_MY_ELECTIVES - STATUS] - "+response.getStatus());
      return ResponseEntity.ok().header("Content-Type", "application/json")
              .body(response);
    }
    response.setStatus("Failed");
    response.setMessage("Failed to add new elective");
    logger.info("[ADD_MY_ELECTIVES - STATUS] - "+response.getStatus());
    return ResponseEntity.badRequest().header("Content-Type", "application/json")
            .body(response);
  }


  @PostMapping("/MyElectives/Update")
  public ResponseEntity<Response> updateElective(@RequestBody Elective elective)
  {
    logger.info("[UPDATE_MY_ELECTIVES] - "+elective.getEmail());
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
      response.setMessage("Elective details updated successfully");
      logger.info("[UPDATE_MY_ELECTIVES - STATUS] - "+response.getStatus());
      return ResponseEntity.ok().header("Content-Type", "application/json")
              .body(response);
    }
    response.setStatus("Failed");
    response.setMessage("Elective details not updated");
    logger.info("[UPDATE_MY_ELECTIVES - STATUS] - "+response.getStatus());
    return ResponseEntity.badRequest().header("Content-Type", "application/json")
            .body(response);
  }
  @PostMapping("/MyApplications")
  public List<Application> getStudentApplications(@RequestBody Map<String ,String> studEmail)
  {
    logger.info("[APPLICATIONS BY STUDENT] - "+studEmail.get("studentemail"));
    return apprepo.findByEmail(studEmail.get("studentemail"));
  }

  @PostMapping ("/Applications")
  public List<Application> getApplications(@RequestBody Map<String,String> factEmail)
  {
    String status="Pending";
    logger.info("[APPLICATIONS FOR FACULTY] - "+factEmail.get("facultyemail"));
    return apprepo.findByFacultyemailAndStatus(factEmail.get("facultyemail"),status);
  }


  @PostMapping("/Applications/Accept")
  public ResponseEntity<Response> acceptApplication(@RequestBody Map<String,Integer> application)
  {
    logger.info("[ACCEPT APPLICATION]");
    Response response=new Response();
    int aid=application.get("aid");
    int eid=application.get("eid");
    Application appl=apprepo.findById(aid).orElse(new Application());
    Elective elec=electiverepo.findById(eid).orElse(new Elective());
    if(appl.getEmail().equals("none") || !appl.getStatus().equals("Pending"))
    {
      response.setStatus("Failed");
      response.setMessage("Invalid student id");
      logger.info("[ACCEPT APPLICATION - STATUS] - "+response.getStatus());
      return ResponseEntity.badRequest().header("Content-Type", "application/json")
              .body(response);
    }
    if(!appl.getStatus().equals("Pending"))
    {
      response.setStatus("Failed");
      response.setMessage("Cannot accept the application because either it is already accepted or rejected");
      logger.info("[ACCEPT APPLICATION - STATUS] - "+response.getStatus());
      return ResponseEntity.badRequest().header("Content-Type", "application/json")
              .body(response);
    }
    if(elec.getEmail().equals("none"))
    {
      response.setStatus("Failed");
      response.setMessage("Invalid faculty id");
      logger.info("[ACCEPT APPLICATION - STATUS] - "+response.getStatus());
      return ResponseEntity.badRequest().header("Content-Type", "application/json")
              .body(response);
    }
    if(elec.getVacancy()<=0)
    {
      response.setStatus("Failed");
      response.setMessage("No vacancy available in this elective");
      logger.info("[ACCEPT APPLICATION - STATUS] - "+response.getStatus());
      return ResponseEntity.badRequest().header("Content-Type", "application/json")
              .body(response);
    }
    appl.setStatus("Accepted");
    apprepo.save(appl);
    elec.setVacancy(elec.getVacancy()-1);
    electiverepo.save(elec);
    response.setStatus("Success");
    response.setMessage("Application accepted successfully");
    logger.info("[ACCEPT APPLICATION - STATUS] - "+response.getStatus());
    return ResponseEntity.ok().header("Content-Type", "application/json")
            .body(response);
  }

  @PostMapping("/Applications/Reject")
  public ResponseEntity<Response> rejectApplication(@RequestBody Map<String,Integer> application)
  {
    logger.info("[REJECT APPLICATION]");
    Response response=new Response();
    int aid=application.get("aid");
    Application appl=apprepo.findById(aid).orElse(new Application());
    if(appl.getEmail().equals("none") || !appl.getStatus().equals("Pending"))
    {
      response.setStatus("Failed");
      response.setMessage("Invalid student id");
      logger.info("[REJECT APPLICATION - STATUS] - "+response.getStatus());
      return ResponseEntity.badRequest().header("Content-Type", "application/json")
              .body(response);
    }

    appl.setStatus("Rejected");
    apprepo.save(appl);
    response.setStatus("Success");
    response.setMessage("Application rejected successfully");
    logger.info("[REJECT APPLICATION - STATUS] - "+response.getStatus());
    return ResponseEntity.ok().header("Content-Type", "application/json")
            .body(response);
  }

  @GetMapping("/Faculty")
  public List<Faculty> getFaculty()
  {
    logger.info("[GET FACULTY LIST]");
    return factrepo.findAll();
  }
}
