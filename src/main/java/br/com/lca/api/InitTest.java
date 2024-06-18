//package br.com.lca.api;

//import br.com.lca.api.config.SecutiryRoles;
//import br.com.lca.api.domain.model.User;
//import br.com.lca.api.domain.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class InitTest implements CommandLineRunner {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        User user = new User(
//                "Admin",
//                "Manager",
//                "admin@email.com",
//                passwordEncoder.encode("admin"),
//                "11990909090",
//                null,
//                null
//        );
//
//        user.setRoles(SecutiryRoles.ADMIN);
//        userRepository.save(user);
//    }
//}
