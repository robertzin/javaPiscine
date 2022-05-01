package edu.school21.sockets.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan("edu.school21.sockets")
@PropertySource("classpath:db.properties")
public class SocketsApplicationConfig {

    private final Environment environment;

    public SocketsApplicationConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean("hikariDataSource")
    public HikariDataSource hikariDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl(environment.getProperty("db.url"));
        hikariDataSource.setUsername(environment.getProperty("db.user"));
        hikariDataSource.setPassword(environment.getProperty("db.password"));
        hikariDataSource.setDriverClassName(environment.getProperty("db.driver.name"));

        return hikariDataSource;
    }

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
