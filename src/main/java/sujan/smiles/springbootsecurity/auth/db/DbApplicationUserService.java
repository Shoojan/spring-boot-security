package sujan.smiles.springbootsecurity.auth.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sujan.smiles.springbootsecurity.auth.ApplicationUser;

@Service
public class DbApplicationUserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final DbApplicationUserDAO dbApplicationUserDAO;

    @Autowired
    public DbApplicationUserService(PasswordEncoder passwordEncoder, DbApplicationUserDAO dbApplicationUserDAO) {
        this.passwordEncoder = passwordEncoder;
        this.dbApplicationUserDAO = dbApplicationUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = dbApplicationUserDAO.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username))
                );
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return new ApplicationUser(appUser);
    }
}
