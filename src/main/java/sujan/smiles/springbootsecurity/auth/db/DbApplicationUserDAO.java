package sujan.smiles.springbootsecurity.auth.db;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface DbApplicationUserDAO extends CrudRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);

}
