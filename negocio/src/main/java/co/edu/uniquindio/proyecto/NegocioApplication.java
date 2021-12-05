package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.servicio.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class NegocioApplication {

    //@Autowired
    //private EmailSenderService senderService;

    public static void main(String[] args) {
        SpringApplication.run(NegocioApplication.class,args);

    }

   // @EventListener(ApplicationReadyEvent.class)
    //public void sendMail(){
   //     senderService.sendEmail("jimv9200@gmail.com","Correo de prueba","Hola mundo");
    //}
}
