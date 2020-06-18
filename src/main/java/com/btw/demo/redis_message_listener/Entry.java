package com.btw.demo.redis_message_listener;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Scanner;

public class Entry {

    public static void main(String[] args) {
        try (GenericApplicationContext appCtx = new AnnotationConfigApplicationContext(RedisConfig.class)) {
            Scanner scan = new Scanner(System.in);
            System.out.println(appCtx.getEnvironment().getProperty("user.home"));
            System.out.println("IcerUpdateListener started");
            while (!"z".equalsIgnoreCase(scan.nextLine())) {
                System.out.println("Please enter 'z' to exit");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
