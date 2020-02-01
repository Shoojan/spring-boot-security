package sujan.smiles.springbootsecurity.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentDAO studentDAO;

    @Autowired
    public StudentService(@Qualifier(value = "memoryDb") StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }


    public List<Student> getStudents() {
        return studentDAO.getStudents();
    }

    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id).orElse(null);
    }
}
