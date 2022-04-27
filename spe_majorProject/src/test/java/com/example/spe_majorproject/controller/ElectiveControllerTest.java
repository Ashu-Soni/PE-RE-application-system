package com.example.spe_majorproject.controller;

import com.example.spe_majorproject.repository.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.jsf.FacesContextUtils;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
class ElectiveControllerTest {

    private MockMvc mockMvc;

    @Mock
    ApplicationRepository applicationRepository;

    @Mock
    ElectiveRepository electiveRepository;

    @Mock
    FacultyRepository facultyRepository;

    @Mock
    StudentRepository studentRepository;

    @Mock
    UserCredentialsRepository userCredentialsRepository;

    @InjectMocks
    private ElectiveController electiveController;

    @Before
    void setUp() {

    }
}