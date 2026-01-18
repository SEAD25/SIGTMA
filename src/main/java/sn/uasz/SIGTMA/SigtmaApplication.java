package sn.uasz.SIGTMA;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SigtmaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SigtmaApplication.class, args);
    }

    // Ce bean est nécessaire pour éviter l'erreur dans votre Controller
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}