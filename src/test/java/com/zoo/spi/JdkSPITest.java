package com.zoo.spi;

import org.junit.Test;

import java.util.ServiceLoader;

public class JdkSPITest {
    @Test
    public void testSayHi() throws Exception {
        ServiceLoader<Developer> serviceLoader = ServiceLoader.load(Developer.class);
        serviceLoader.forEach(Developer::sayHi);
    }
}
