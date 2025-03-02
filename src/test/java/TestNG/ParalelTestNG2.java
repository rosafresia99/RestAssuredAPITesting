package TestNG;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParalelTestNG2 {
    String name = "Rosa";
    @Test
    public void scenarioTest1(){
        System.out.println("test 1");
        Assert.assertEquals(name, "Rosa");
        System.out.println(Thread.currentThread());
    }
    @Test
    public void scenarioTest2(){
        System.out.println("test 2");
        Assert.assertEquals(name, "Rosa");
        System.out.println(Thread.currentThread());
    }

    @Test
    public void scenarioTest3(){
        System.out.println("test 3");
        Assert.assertEquals(name, "Rosa");
        System.out.println(Thread.currentThread());
    }
}
