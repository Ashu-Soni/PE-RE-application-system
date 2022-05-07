import com.example.spe_majorproject.MainController;
import com.example.spe_majorproject.bean.Faculty;
import com.example.spe_majorproject.bean.Student;
import com.example.spe_majorproject.bean.UserCredentials;
import com.example.spe_majorproject.repository.FacultyRepository;
import com.example.spe_majorproject.repository.StudentRepository;
import com.example.spe_majorproject.repository.UserCredentialsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class MainControllerTest {

    private MockMvc mockMvc;

    @Mock
    StudentRepository studentRepository;

    @Mock
    FacultyRepository facultyRepository;

    @Mock
    UserCredentialsRepository userCredentialsRepository;

    @InjectMocks
    private MainController mainController;

    ObjectMapper objectMapper=new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void testRegisterStudent() throws Exception
    {
        Student student=new Student();
        student.setEmail("testEmail@test.com");
        student.setName("test");
        student.setPassword("test123");
        student.setBranch("CSE");
        student.setDegree("Mtech");
        student.setRollno("MT2021000");
        student.setMobile("000000000");
        String studentjson=objectMapper.writeValueAsString(student);
       mockMvc.perform(post("/home/register_student")
               .contentType(MediaType.APPLICATION_JSON)
               .content(studentjson))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.status", Matchers.is("Success")))
               .andExpect(jsonPath("$.message",Matchers.is("User registered successfully")));
    }

    @Test
    public void testRegisterFaculty() throws Exception
    {
        Faculty faculty=new Faculty();
        faculty.setName("testfaculty");
        faculty.setEmail("testfaculty@test.com");
        faculty.setName("Test");
        faculty.setWebsite("test.com");
        faculty.setResearch("Testing");
        faculty.setOffice("T-000");
        faculty.setPhone("00000000");
        faculty.setDesignation("Tester");
        faculty.setPassword("testfacultypassword");
        String facultyjson=objectMapper.writeValueAsString(faculty);

        mockMvc.perform(post("/home/register_faculty")
                .contentType(MediaType.APPLICATION_JSON)
                .content(facultyjson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("Success")))
                .andExpect(jsonPath("$.message",Matchers.is("User registered successfully")));
    }

    @Test
    public void testlogin() throws Exception
    {
        UserCredentials userCredentials=new UserCredentials();
        userCredentials.setName("testname");
        userCredentials.setUserType("Student");
        userCredentials.setEmail("testEmail@test.com");
        userCredentials.setPassword("test123");
        String usercredjson=objectMapper.writeValueAsString(userCredentials);

        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        userCredentials.setPassword(bCryptPasswordEncoder.encode(userCredentials.getPassword()));

        when(userCredentialsRepository.findById(userCredentials.getEmail())).thenReturn(Optional.of(userCredentials));

        mockMvc.perform(post("/home/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usercredjson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("Success")))
                .andExpect(jsonPath("$.message",Matchers.is("User Credentials Verified")));
    }

    @Test
    public void testRegisterFacultyFailure() throws Exception
    {
        Faculty faculty=new Faculty();
        faculty.setName("testfaculty");
        faculty.setEmail("testfaculty@test.com");
        faculty.setName("Test");
        faculty.setWebsite("test.com");
        faculty.setResearch("Testing");
        faculty.setOffice("T-000");
        faculty.setPhone("00000000");
        faculty.setDesignation("Tester");
        faculty.setPassword("testfacultypassword");
        String facultyjson=objectMapper.writeValueAsString(faculty);

        when(userCredentialsRepository.existsById(faculty.getEmail())).thenReturn(true);

        mockMvc.perform(post("/home/register_faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(facultyjson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", Matchers.is("Failed")))
                .andExpect(jsonPath("$.message",Matchers.is("User already exists")));
    }

    @Test
    public void testStudentRegisterFailure() throws Exception
    {
        Student student=new Student();
        student.setEmail("testEmail@test.com");
        student.setName("test");
        student.setPassword("test123");
        student.setBranch("CSE");
        student.setDegree("Mtech");
        student.setRollno("MT2021000");
        student.setMobile("000000000");
        String studentjson=objectMapper.writeValueAsString(student);

        when(userCredentialsRepository.existsById(student.getEmail())).thenReturn(true);

        mockMvc.perform(post("/home/register_student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentjson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", Matchers.is("Failed")))
                .andExpect(jsonPath("$.message",Matchers.is("User already exists")));
    }

    @Test
    public void testLoginFailure() throws Exception
    {
        UserCredentials userCredentials=new UserCredentials();
        userCredentials.setName("testname");
        userCredentials.setUserType("Student");
        userCredentials.setEmail("testEmail@test.com");
        userCredentials.setPassword("test123");
        String usercredjson=objectMapper.writeValueAsString(userCredentials);

        mockMvc.perform(post("/home/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usercredjson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", Matchers.is("Failed")))
                .andExpect(jsonPath("$.message",Matchers.is("Invalid User Credentials")));
    }
}