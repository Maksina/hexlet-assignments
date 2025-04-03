package exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

// BEGIN

// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN

    @Bean
    public Daytime getDaytime(){
        if (LocalDateTime.now().getHour() >= 6 && LocalDateTime.now().getHour() < 22){
            return new Day();
        }else{
            return new Night();
        }
    }

    // END
}
