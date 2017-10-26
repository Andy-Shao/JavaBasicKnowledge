package andyshao.util;

import java.util.*;

public class ServiceLoaderTest {
    public static void main(String[] args) {
        //java -classpath build/libs/JavaBasicKnowledge.jar andyshao.util.ServiceLoaderTest
        ServiceLoader<IService> serviceLoader = ServiceLoader.load(IService.class);
        for (IService service : serviceLoader) {
            System.out.println(service.getScheme() + "=" + service.sayHello());
        }
        System.out.println("END!!");
    }
}
