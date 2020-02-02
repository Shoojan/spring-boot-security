package sujan.smiles.springbootsecurity.auth.memory;

import sujan.smiles.springbootsecurity.auth.ApplicationUser;

import java.util.Optional;

public interface ApplicationUserDAO {
    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
