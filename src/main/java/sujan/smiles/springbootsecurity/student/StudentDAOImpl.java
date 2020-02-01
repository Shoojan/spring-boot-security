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

    @Override
    public List<Student> getStudents() {
        return students;
    }

    @Override
    public Optional<Student> getStudentById(int id) {
        return students.stream().filter(student -> student.getId() == id).findAny();
    }

    @Override
    public List<Student> registerNewStudent(Student student) {
        if (student.getId() < 0)
            student.setId(students.size() + 1);
        students.add(student);
        return students;
    }

    @Override
    public List<Student> deleteStudent(int id) {
        students.removeIf(student -> student.getId() == id);
        return students;
    }

    @Override
    public List<Student> updateStudentName(int id, String name) {
        Student oldStudentDetail = students.stream()
                .filter(student -> student.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Student " + id + " not found"));
        students.remove(oldStudentDetail);
        students.add(new Student(id, name));
        return students;
    }

}
