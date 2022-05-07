import com.example.spe_majorproject.controller.ElectiveController;
import com.example.spe_majorproject.bean.Elective;
import com.example.spe_majorproject.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ElectiveControllerTest {

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
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(electiveController).build();
    }

    ObjectMapper objectMapper=new ObjectMapper();

    @Test
    public void testGetProjectElectives() throws Exception
    {
        Elective elective1=new Elective(1,"Elective 1","Test Elective",10,"Test faculty","test@test.com","Project Elective");
        Elective elective2=new Elective(2,"Elective 2","Test Elective",20,"Test faculty","test@test.com","Project Elective");

        List<Elective> projctElectiveList=new ArrayList<>();
        projctElectiveList.add(elective1);
        projctElectiveList.add(elective2);

//        JSONArray jsonArray=new JSONArray(projctElectiveList);
//        String projectElectivesJson=objectMapper.writeValueAsString(projctElectiveList);
//        JSONObject json = new JSONObject(projectElectivesJson);
//        System.out.println(json.toString());

        String json = objectMapper.writeValueAsString(projctElectiveList);
        System.out.println(json);
        JSONArray jsonArray=new JSONArray(json);
        System.out.println(jsonArray);


        when(electiveRepository.findByType("Project Elective")).thenReturn(projctElectiveList);

        mockMvc.perform(MockMvcRequestBuilders.get("/dashboard/ProjectElectives"))
                .andExpect(status().isOk());


    }
}