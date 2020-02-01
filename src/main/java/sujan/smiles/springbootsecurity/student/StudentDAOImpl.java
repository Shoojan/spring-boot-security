package sujan.smiles.springbootsecurity.student;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("memoryDb")
public class StudentDAOImpl implements StudentDAO {

    private static List<Student> students = new ArrayList<>();

    static {
        students.add(new Student(students.size() + 1, "Student1"));
        students.add(new Student(students.size() + 1, "Student2"));
        students.add(new Student(students.size() + 1, "Student3"));
    }

    public List<Student> getStudents() {
        return students;
    }

    public Optional<Student> getStudentById(int id) {
        return students.stream().filter(student -> student.getId() == id).findAny();
    }

}
