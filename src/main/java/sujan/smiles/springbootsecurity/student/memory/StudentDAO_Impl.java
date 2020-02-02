package sujan.smiles.springbootsecurity.student.memory;

import org.springframework.stereotype.Repository;
import sujan.smiles.springbootsecurity.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("memoryDb")
public class StudentDAO_Impl implements StudentDAO {

    private static List<Student> students = new ArrayList<>();

    static {
        students.add(new Student(students.size() + 1, "Student1", 12));
        students.add(new Student(students.size() + 1, "Student2", 13));
        students.add(new Student(students.size() + 1, "Student3", 16));
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
    public List<Student> updateStudentName(int id, String studentName) {
        Student oldStudentDetail = students.stream()
                .filter(student -> student.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Student " + id + " not found"));
        students.remove(oldStudentDetail);
        students.add(new Student(id, studentName, oldStudentDetail.getAge()));
        return students;
    }

    @Override
    public List<Student> updateStudentAge(int id, int age) {
        Student oldStudentDetail = students.stream()
                .filter(student -> student.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Student " + id + " not found"));
        students.remove(oldStudentDetail);
        students.add(new Student(id, oldStudentDetail.getName(), age));
        return students;
    }

}
