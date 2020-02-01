package sujan.smiles.springbootsecurity.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static sujan.smiles.springbootsecurity.security.ApplicationUserRole.*;

@Repository("userMemoryDb")
public class FakeApplicationUserDAOService implements ApplicationUserDAO {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDAOService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }


    private List<ApplicationUser> getApplicationUsers() {

        return Lists.newArrayList(
                new ApplicationUser(ADMIN.getGrantedAuthorities(),
                        "admin",
                        passwordEncoder.encode("pass"),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(ADMIN_TRAINEE.getGrantedAuthorities(),
                        "trainee",
                        passwordEncoder.encode("pass"),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(STUDENT.getGrantedAuthorities(),
                        "sujan",
                        passwordEncoder.encode("pass"),
                        true,
                        true,
                        true,
                        true
                )
        );
    }
}
