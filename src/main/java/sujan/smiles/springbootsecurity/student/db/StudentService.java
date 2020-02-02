package sujan.smiles.springbootsecurity.student.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sujan.smiles.springbootsecurity.student.Student;

import java.util.List;

@Service
public class StudentService {

    //    private final StudentDAO studentDAO;
    private final StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }


    public List<Student> getStudents() {
        return (List<Student>) studentDAO.findAll();
    }

    public Student getStudentById(int id) {
        return studentDAO.findById(id).orElseThrow(() -> new IllegalStateException("Student + " + id + " not found!"));
    }


    public List<Student> registerNewStudent(Student student) {
        studentDAO.save(student);
        return getStudents();
    }

    public List<Student> deleteStudent(int id) {
        studentDAO.deleteById(id);
        return getStudents();
    }

    public List<Student> updateStudentName(int id, Student newStudentDetail) {

        Student oldStudentDetail = getStudentById(id);
        studentDAO.deleteById(id);
        studentDAO.save(new Student(id, newStudentDetail.getName(), oldStudentDetail.getAge()));

        return getStudents();

    }
}
