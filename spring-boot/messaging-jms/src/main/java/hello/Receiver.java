package hello;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
  // The JmsListener annotation defines the name of the Destination that this method should listen to and the
  // reference to the JmsListenerContainerFactory to use to create the underlying message listener container.
  // Strictly speaking, that last attribute is not necessary unless you need to customize the way the container
  // is built, as Spring Boot registers a default factory if necessary.
  @JmsListener(destination = "mailbox", containerFactory = "myFactory")
  public void receiveMessage(Email email) {
    System.out.println("received <" + email + ">");
  }
}
