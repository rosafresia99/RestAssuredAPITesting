package example;

import org.testng.annotations.DataProvider;

public class StaticProvider {
    @DataProvider(name="dataprovider")
    public Object[][] dataTest(){
        return new Object[][]{
            {"Rudy", 10},
            {"Sari", 20},
            {"Joko", 25},
        };
    }
}
