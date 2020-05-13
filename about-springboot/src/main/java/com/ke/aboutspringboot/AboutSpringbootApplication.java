package com.ke.aboutspringboot;

import com.ke.aboutspringboot.initialize.SecondInitializer;
import com.ke.aboutspringboot.listener.SecondListener;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AboutSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AboutSpringbootApplication.class, args);

//       SpringApplication application = new SpringApplication(AboutSpringbootApplication.class);
//       application.addInitializers(new SecondInitializer());
//       application.addListeners(new SecondListener());
//       application.run(args);
    }

}
