package com.DemoTest.demo;

import com.DemoTest.demo.model.Sample;
import org.junit.Test;

public class SampleTests {


    @Test
    public void test() {
        Sample objSample = new Sample();
        objSample.setId(1);
        objSample.setName("Vipin");


        Sample objSample1 = new Sample();
        objSample1.setId(1);
        objSample1.setName("Vipin1");

        if (objSample.equals(objSample1)) {
            System.out.println("match found");
        } else {
            System.out.println("match not found");
        }
        System.out.println(objSample.toString());

        System.out.println();
    }
}
