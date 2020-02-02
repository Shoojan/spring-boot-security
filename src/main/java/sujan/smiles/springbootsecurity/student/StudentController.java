package sujan.smiles.springbootsecurity.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//import sujan.smiles.springbootsecurity.student.memory.StudentService_Memory;
import sujan.smiles.springbootsecurity.student.db.StudentService;


import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("{id}")
    public Student getStudentById(@PathVariable("id") int id) {
        return studentService.getStudentById(id);
    }
}
