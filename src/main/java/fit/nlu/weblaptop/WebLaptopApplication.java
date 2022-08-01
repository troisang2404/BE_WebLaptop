package fit.nlu.weblaptop;

import fit.nlu.weblaptop.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class WebLaptopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebLaptopApplication.class, args);
    }

}
