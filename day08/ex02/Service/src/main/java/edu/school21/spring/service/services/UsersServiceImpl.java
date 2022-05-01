package edu.school21.spring.service.services;

import edu.school21.spring.service.models.User;
import edu.school21.spring.service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("usersService")
public class UsersServiceImpl implements UsersService {
    UsersRepository usersRepository;
//    private static Long id = 12L;

    @Autowired
    public UsersServiceImpl(@Qualifier("jdbcRepository")UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(Long id, String email) {
        String password = generatePassword();
        usersRepository.save(new User(id, email), password);
        return password;
    }

    private String generatePassword() {
        return new Random().ints(10, 33, 122).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
