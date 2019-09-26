package todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(TaskRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Task("Tak1za", "Java", "Learn Spring Boot")));
            log.info("Preloading " + repository.save(new Task("Tak1za", ".NET", "Complete the AccountOwner Application")));
        };
    }
}
