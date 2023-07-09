
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Banking {

    Calculator calculator = new Calculator();

    public void profit(){

    }

    public void loss(){

    }

    public void addSalary(){
        int r;
       r =calculator.add(40,40);
        System.out.println("Add is "+r);
    }

    public static void main(String[] args) {

        Banking banking = new Banking();
        banking.addSalary();

        /*FirefoxDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.get("https://www.javatpoint.com/java-tutorial");*/

        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.get("https://www.javatpoint.com/java-tutorial");


    }
}
