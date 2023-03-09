package com.example.bookstore.security;

import com.example.bookstore.samples.sampinf;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuthSerwiceTest {
        @Test
        public void test(){
                ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("applicationContext.xml");
                sampinf s = c.getBean("sampinf", sampinf.class);
                System.out.println(s);
                assertNotNull(s);
        }
}