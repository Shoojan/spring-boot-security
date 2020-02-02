package sujan.smiles.springbootsecurity.student.memory;

import sujan.smiles.springbootsecurity.student.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDAO {

    List<Student> getStudents();

    Optional<Student> getStudentById(int id);

    List<Student> registerNewStudent(Student student);

    List<Student> deleteStudent(int id);

    List<Student> updateStudentName(int id, String name);

    List<Student> updateStudentAge(int id, int age);

}
