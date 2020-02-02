package sujan.smiles.springbootsecurity.auth.db;

import javax.persistence.*;

@Entity
@Table(name = "user_tbl")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int uId;

    private String role;
    private String username;
    private String password;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public AppUser() {
    }

    public AppUser(AppUser appUser) {
        setuId(appUser.getuId());
        setUsername(appUser.getUsername());
        setPassword(appUser.getPassword());
        setRole(appUser.getRole());
        setAccountNonExpired(appUser.isAccountNonExpired());
        setAccountNonLocked(appUser.isAccountNonLocked());
        setCredentialsNonExpired(appUser.isCredentialsNonExpired());
        setEnabled(appUser.isEnabled());
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
