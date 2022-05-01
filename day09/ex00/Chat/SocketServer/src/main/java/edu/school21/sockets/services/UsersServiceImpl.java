package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("usersService")
public class UsersServiceImpl implements UsersService {
    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;


    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String signUp(Long id, String username, String pwd) {
        String password = passwordEncoder.encode(pwd);
        User user = new User(id, username, password);

        usersRepository.save(user);
        return password;
    }
}
