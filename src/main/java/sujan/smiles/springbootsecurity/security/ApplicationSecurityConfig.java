package sujan.smiles.springbootsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import sujan.smiles.springbootsecurity.auth.db.DbApplicationUserService;

import java.util.concurrent.TimeUnit;

import static sujan.smiles.springbootsecurity.security.ApplicationUserPermission.*;
import static sujan.smiles.springbootsecurity.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = DbApplicationUserService.class)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
//    private final ApplicationUserService applicationUserService;
    private final DbApplicationUserService dbApplicationUserService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, DbApplicationUserService dbApplicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.dbApplicationUserService = dbApplicationUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()  //whitelist these patterns
                .antMatchers("/api/**").hasRole(STUDENT.getRole())
                .antMatchers(HttpMethod.POST, "/management/api/**").hasAnyAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAnyAuthority(STUDENT_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAnyAuthority(STUDENT_WRITE.getPermission())
                .antMatchers("/management/api/**").hasAnyRole(ADMIN.getRole(), ADMIN_TRAINEE.getRole())
                .anyRequest()
                .authenticated()
                .and()

//                .httpBasic();
                .formLogin()
                .loginPage("/login").permitAll()
//                .defaultSuccessUrl("/courses", true)
                .and()
                .rememberMe()
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(20))
                .and()

                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login");
    }


//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails adminDetail = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("pass"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(adminDetail);
//    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails adminDetail = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("pass"))
////                .roles(ADMIN.getRole())    //ROLE_ADMIN
//                .authorities(ADMIN.getGrantedAuthorities())
//                .build();
//
//        UserDetails adminTraineeDetail = User.builder()
//                .username("trainee")
//                .password(passwordEncoder.encode("pass"))
////                .roles(ADMIN.getRole())    //ROLE_ADMIN
//                .authorities(ADMIN_TRAINEE.getGrantedAuthorities())
//                .build();
//
//        UserDetails studentDetail = User.builder()
//                .username("sujan")
//                .password(passwordEncoder.encode("pass"))
////                .roles(STUDENT.getRole())   //ROLE_STUDENT
//                .authorities(STUDENT.getGrantedAuthorities())
//                .build();
//
//        return new InMemoryUserDetailsManager(adminDetail, studentDetail, adminTraineeDetail);
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(dbApplicationUserService);
        return provider;
    }
}
