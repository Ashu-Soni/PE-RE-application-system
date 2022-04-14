package com.example.spe_majorproject.controller;

import com.example.spe_majorproject.bean.Elective;
import com.example.spe_majorproject.repository.ElectiveRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class ElectiveController {

  @Autowired
  private ElectiveRepository electiverepo;

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


}
