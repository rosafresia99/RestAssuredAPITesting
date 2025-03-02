package TestNG;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import example.StaticProvider;

public class TestNG {
    String name = "Rosa";
    @BeforeClass
    //biasanya untuk scenario login, kredential
    public void beforeClassSetup(){
        System.out.println("Before class");
    }

    @BeforeMethod
    public void setup(){
        System.out.println("Hi");
    }

    @Test
    public void scenarioTest1(){
        System.out.println("test 1");
        Assert.assertEquals(name, "Rosa");
    }
    @Test
    public void scenarioTest2(){
        System.out.println("test 2");
        Assert.assertEquals(name, "Rosa");
    }

    @Test
    public void scenarioTest3(){
        System.out.println("test 3");
        Assert.assertEquals(name, "Rosa");
    }

    @Test (dataProvider = "dataprovider", dataProviderClass = StaticProvider.class)
    public void scenarioTestData(String name, int umur){
        System.out.println("Nama: " +name+ " Umur: " +umur);
    }

    @AfterMethod
    public void afterSetup(){
        System.out.println("Bye");

    }

    @AfterClass
    public void afterClassSetup(){
        System.out.println("After class");
    }

}
