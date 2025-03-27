package deliveryapp.app.service;
import deliveryapp.app.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import deliveryapp.app.entity.Courier;
import java.util.List;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CourierRepository courierRepository;

    public void sendEmailToCouriers(List<Long> courierIds) {
        List<Courier> couriers = courierRepository.findAllById(courierIds);
        
        for (Courier courier : couriers) {
            if (courier.getemail() != null) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("delivery-app@myradov.com");
                message.setTo(courier.getemail());
                message.setSubject("Delivery App Notification");
                message.setText("Hello " + courier.getname() + ",\n\n" +
                                "You have new package assignments or updates in the Myradov.\n" +
                                "Please check the app for details.\n\n" +
                                "Best regards,\nMyradov Team");
                
                mailSender.send(message);
            }
        }
    }
}