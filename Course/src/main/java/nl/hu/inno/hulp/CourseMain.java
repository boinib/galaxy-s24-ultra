package nl.hu.inno.hulp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableCassandraRepositories(basePackages = "nl.hu.inno.hulp.data")
public class CourseMain {
    public static void main(String[] args) {
        SpringApplication.run(CourseMain.class, args);
    }
}