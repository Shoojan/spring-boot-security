package sujan.smiles.springbootsecurity.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/api/student")
public class StudentManagementController {

    private final StudentService studentService;

    @Autowired
    public StudentManagementController(StudentService studentService) {
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

    @PostMapping
    public List<Student> registerNewStudent(@RequestBody Student student) {
        return studentService.registerNewStudent(student);
    }

    @DeleteMapping("{id}")
    public List<Student> deleteStudent(@PathVariable("id") int id) {
        return studentService.deleteStudent(id);

    }

    @PutMapping("{id}")
    public List<Student> updateStudentName(@PathVariable("id") int id, @RequestBody Student student) {
        return studentService.updateStudentName(id, student.getName());
    }
}
