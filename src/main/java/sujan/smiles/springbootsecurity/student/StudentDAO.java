package sujan.smiles.springbootsecurity.student;

import java.util.List;
import java.util.Optional;

public interface StudentDAO {

    List<Student> getStudents();
    Optional<Student> getStudentById(int id);

}
