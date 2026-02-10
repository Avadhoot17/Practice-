import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

// ----------- Custom Annotation for Injection -----------
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Inject {
}

// ----------- Service Interface -----------
interface MessageService {
    void sendMessage();
}

// ----------- Implementation -----------
class EmailService implements MessageService {
    public void sendMessage() {
        System.out.println("Email sent successfully!");
    }
}

// ----------- Client Class -----------
class NotificationManager {

    @Inject
    private MessageService service;   // Will be injected automatically

    public void notifyUser() {
        service.sendMessage();
    }
}

// ----------- Mini DI Container -----------
class DIContainer {

    private Map<Class<?>, Object> container = new HashMap<>();

    public DIContainer() throws Exception {
        // Manually registering implementation (like Spring scanning)
        container.put(MessageService.class, new EmailService());
    }

    public <T> T getBean(Class<T> clazz) throws Exception {

        T obj = clazz.getDeclaredConstructor().newInstance();

        for (Field field : clazz.getDeclaredFields()) {

            if (field.isAnnotationPresent(Inject.class)) {

                Object dependency = container.get(field.getType());

                field.setAccessible(true);
                field.set(obj, dependency);
            }
        }
        return obj;
    }
}

// ----------- Main Class -----------
public class MiniDIContainer {

    public static void main(String[] args) throws Exception {

        DIContainer di = new DIContainer();

        NotificationManager manager = di.getBean(NotificationManager.class);

        manager.notifyUser();
    }
}
