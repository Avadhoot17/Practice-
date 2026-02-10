import java.lang.annotation.*;
import java.lang.reflect.*;

// ---------- Custom Annotation ----------
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface AutoRun {
}

// ---------- Main Demo Class ----------
class TaskRunner {

    @AutoRun
    public void firstTask() {
        System.out.println("Running First Task");
    }

    @AutoRun
    public void secondTask() {
        System.out.println("Running Second Task");
    }

    public void normalMethod() {
        System.out.println("This should NOT run automatically");
    }
}

// ---------- Executor Class ----------
public class AutoRunMethods {

    public static void main(String[] args) throws Exception {

        TaskRunner obj = new TaskRunner();

        // Get all methods using reflection
        Method[] methods = obj.getClass().getDeclaredMethods();

        for (Method method : methods) {

            // Check if method has @AutoRun annotation
            if (method.isAnnotationPresent(AutoRun.class)) {

                System.out.println("Executing: " + method.getName());
                method.invoke(obj); // dynamic execution
            }
        }
    }
}
