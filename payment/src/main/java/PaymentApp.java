import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "rest")
public class PaymentApp {


    public static void main(String[] args) {
        SpringApplication.run(PaymentApp.class, args);
    }
}
