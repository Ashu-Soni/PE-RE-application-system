package com.example.spe_majorproject.services;

import com.example.spe_majorproject.bean.Elective;
import com.example.spe_majorproject.repository.ElectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DashboardService {
    @Autowired
    private ElectiveRepository electiverepo;

    public List<Elective> getProjectElectives()
    {
        List<Elective> projectElectives=electiverepo.findByType("PE");
        return projectElectives;
    }
    public List<Elective> getResearchElectives()
    {
        List<Elective> researchElectives=electiverepo.findByType("RE");
        return researchElectives;
    }

}
