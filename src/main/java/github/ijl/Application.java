package github.ijl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication(scanBasePackages = {"github.ijl.luxtronik2", "github.ijl.haos"})
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        log.info("Starting Luxtronic + HAOS integration service");
        SpringApplication.run(Application.class, args);

    }
}
