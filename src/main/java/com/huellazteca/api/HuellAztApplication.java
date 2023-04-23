package com.huellazteca.api;

import com.huellazteca.core.CoreApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HuellAztApplication extends CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuellAztApplication.class, args);
    }

}
