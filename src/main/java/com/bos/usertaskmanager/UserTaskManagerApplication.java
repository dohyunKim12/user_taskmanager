package com.bos.usertaskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.admin.SpringApplicationAdminMXBeanRegistrar;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Set;

@SpringBootApplication
public class UserTaskManagerApplication {

    public static void main(String[] args) throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("org.springframework.boot:type=Admin,name=SpringApplication");
        if (!mBeanServer.isRegistered(name)) {
            System.out.println("MBean 직접 등록 시도");
            ObjectInstance objectInstance = mBeanServer.registerMBean(new SpringApplicationAdminMXBeanRegistrar(), name);
        }
        SpringApplication.run(UserTaskManagerApplication.class, args);
//
//        SpringApplication app = new SpringApplication(UserTaskManagerApplication.class);
//        app.setRegisterShutdownHook(false);  // MBean 등록을 위한 설정
//        ConfigurableApplicationContext context = app.run(args);
//
//        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
//        Set<ObjectName> mbeans = mBeanServer.queryNames(null, null);
//
//        System.out.println("== Registered MBeans ==");
//        for (ObjectName mbean : mbeans) {
//            System.out.println(mbean.toString());
//        }
//
//        // MBean 강제 등록 확인
//        boolean mbeanExists = mbeans.stream()
//                .anyMatch(mbean -> mbean.toString().equals("org.springframework.boot:type=Admin,name=SpringApplication"));
//        System.out.println("SpringApplication MBean Exists: " + mbeanExists);

//        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
//        Set<ObjectName> mbeans = mBeanServer.queryNames(null, null);
//
//        System.out.println("== Registered MBeans ==");
//        for (ObjectName mbean : mbeans) {
//            System.out.println(mbean.toString());
//        }
//
//        System.out.println("MBeanServer Initialized: " + (mBeanServer != null));
//        SpringApplication.run(UserTaskManagerApplication.class, args);
    }

}
