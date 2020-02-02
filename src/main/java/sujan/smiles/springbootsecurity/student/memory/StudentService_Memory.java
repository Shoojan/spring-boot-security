package sujan.smiles.springbootsecurity.student.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sujan.smiles.springbootsecurity.exception.ApiRequestException;
import sujan.smiles.springbootsecurity.student.Student;

import java.util.List;

@Service
public class StudentService_Memory {

    private final StudentDAO studentDAO;

    @Autowired
    public StudentService_Memory(@Qualifier(value = "memoryDb") StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }


    public List<Student> getStudents() {
        return studentDAO.getStudents();
    }

    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id)
                .orElseThrow(() -> new ApiRequestException("Student " + id + " not available!"));
    }


    public List<Student> registerNewStudent(Student student) {
        return studentDAO.registerNewStudent(student);
    }

    public List<Student> deleteStudent(int id) {
        return studentDAO.deleteStudent(id);

    }

    public List<Student> updateStudentName(int id, String name) {
        return studentDAO.updateStudentName(id, name);
    }
}
