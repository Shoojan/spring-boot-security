package sujan.smiles.springbootsecurity.student.db;

import org.springframework.data.repository.CrudRepository;
import sujan.smiles.springbootsecurity.student.Student;

public interface StudentDAO extends CrudRepository<Student, Integer> {
}
