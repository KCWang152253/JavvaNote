package card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CardApplication {

    private static final Logger logger = LoggerFactory.getLogger(CardApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(CardApplication.class, args);
            logger.info("************************************card  startup success**************************");
        } catch (Exception e) {
            logger.info("************************************card  startup failed***************************");

        }
    }

}
