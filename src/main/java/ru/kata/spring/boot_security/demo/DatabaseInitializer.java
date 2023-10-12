package ru.kata.spring.boot_security.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);

        User admin = new User();
        admin.setUsername("admin");
        admin.setName("AL");
        admin.setEmail("alfh@gmail.com");
        admin.setSurname("Pup");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(adminRoles);
        userRepository.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setName("Ev");
        user.setEmail("evfh@gmail.com");
        user.setSurname("Pet");
        user.setPassword(passwordEncoder.encode("user"));
        user.setRoles(userRoles);
        userRepository.save(user);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setName("Ser");
        user2.setEmail("ser@gmail.com");
        user2.setSurname("Iv");
        user2.setPassword(passwordEncoder.encode("user2"));
        user2.setRoles(userRoles);
        userRepository.save(user2);

        User user3 = new User();
        user3.setUsername("user3");
        user3.setName("Nik");
        user3.setEmail("nikfh@gmail.com");
        user3.setSurname("Leg");
        user3.setPassword(passwordEncoder.encode("user3"));
        user3.setRoles(userRoles);
        userRepository.save(user3);
    }
}