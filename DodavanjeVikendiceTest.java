import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class DodavanjeVikendiceTest {

    WebDriverWait wait;

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        //Nema potrebe za System.setProperty, Selenium Manager automatski preuzima odgovarajući ChromeDriver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        //Otvaranje aplikacije
        driver.get("http://localhost:4200/login");
    }

    @Test
    public void Test52() {
        //Login kao owner1
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Korisničko ime']"));
        usernameField.sendKeys("owner1");
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Lozinka']"));
        passwordField.sendKeys("pass123");

        //Klik na dugme "Vikendice"
        driver.findElement(By.xpath("//button[normalize-space()='Prijavi se']")).click();

        //Popunjavanje forme sa validnim podacima (LK klase)
        driver.findElement(By.xpath("//div[@class='main-layout']//app-sidebar//div[@class='sidebar sidebar-expanded']//div//li[@class='sidebar-item'][normalize-space()='Vikendice']")).click();

        String cottageName = "Vikendica 1";

        //Pronađi polja za unos i popuni ih
        //Ovde su putanje izmenjene u xpath jer je prvobitno bio id
        driver.findElement(By.xpath("//div[3]//div[1]//input[1]")).sendKeys("Vikendica 1");  // LK: naziv sadrzi brojeve i specijalne znakove
        driver.findElement(By.xpath("//div[3]//div[2]//input[1]")).sendKeys("Zlatibor");      // LK: mesto samo slova
        driver.findElement(By.xpath("//body//app-root//div[3]//div[3]//textarea[1]")).sendKeys("Klima, Wi-Fi, 2 TV"); // LK: tekst sa brojevima i spec. znak.
        driver.findElement(By.xpath("//div[3]//div[4]//input[1]")).sendKeys("3000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[5]//input[1]")).sendKeys("4000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[6]//input[1]")).sendKeys("+123123123");  // LK: brojevi + '+'

        //Pre dodavanja uzmi broj vikendica
        List<WebElement> cottagesBefore = driver.findElements(By.cssSelector(".cottage-card"));
        int countBefore = cottagesBefore.size();

        //Klik na dugme "Dodaj vikendicu"
        driver.findElement(By.xpath("//button[normalize-space()='Dodaj vikendicu']")).click();

        //Sačekaj da se pojavi novi element
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".cottage-card"), countBefore));

        //Proveri da li nova vikendica postoji po nazivu
        List<WebElement> cottagesAfter = driver.findElements(By.cssSelector(".cottage-card"));
        boolean found = cottagesAfter.stream().anyMatch(c -> c.findElement(By.xpath(".//label[text()='Naziv:']/following-sibling::input")).getAttribute("value").equals(cottageName));

        //Assertion
        Assert.assertTrue(found, "Nova vikendica nije dodata ili nije vidljiva!");
    }

    @Test
    public void Test55() {
        //Login kao owner1
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Korisničko ime']"));
        usernameField.sendKeys("owner1");
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Lozinka']"));
        passwordField.sendKeys("pass123");

        //Klik na dugme "Vikendice"
        driver.findElement(By.xpath("//button[normalize-space()='Prijavi se']")).click();

        //Popunjavanje forme sa validnim podacima (LK klase)
        driver.findElement(By.xpath("//div[@class='main-layout']//app-sidebar//div[@class='sidebar sidebar-expanded']//div//li[@class='sidebar-item'][normalize-space()='Vikendice']")).click();

        String cottageName = "";

        //Pronađi polja za unos i popuni ih
        //Ovde su putanje izmenjene u xpath jer je prvobitno bio id
        driver.findElement(By.xpath("//div[3]//div[1]//input[1]")).sendKeys("");  // LK: naziv sadrzi brojeve i specijalne znakove
        driver.findElement(By.xpath("//div[3]//div[2]//input[1]")).sendKeys("Zlatibor");      // LK: mesto samo slova
        driver.findElement(By.xpath("//body//app-root//div[3]//div[3]//textarea[1]")).sendKeys("Klima, Wi-Fi, 2 TV"); // LK: tekst sa brojevima i spec. znak.
        driver.findElement(By.xpath("//div[3]//div[4]//input[1]")).sendKeys("3000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[5]//input[1]")).sendKeys("4000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[6]//input[1]")).sendKeys("+123123123");  // LK: brojevi + '+'

        //Pre dodavanja uzmi broj vikendica
        List<WebElement> cottagesBefore = driver.findElements(By.cssSelector(".cottage-card"));
        int countBefore = cottagesBefore.size();

        //Klik na dugme "Dodaj vikendicu"
        driver.findElement(By.xpath("//button[normalize-space()='Dodaj vikendicu']")).click();

        List<WebElement> vikendicePosle = driver.findElements(By.cssSelector(".cottage-card"));
        int countAfter = vikendicePosle.size();

        boolean valid;
        if(countAfter==countBefore)
            valid=true;
        else
            valid=false;

        //Assertion
        Assert.assertTrue(valid, "Nova vikendica nije dodata ili nije vidljiva!");
    }

    @Test
    public void Test56() {
        //Login kao owner1
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Korisničko ime']"));
        usernameField.sendKeys("owner1");
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Lozinka']"));
        passwordField.sendKeys("pass123");

        //Klik na dugme "Vikendice"
        driver.findElement(By.xpath("//button[normalize-space()='Prijavi se']")).click();

        //Popunjavanje forme sa validnim podacima (LK klase)
        driver.findElement(By.xpath("//div[@class='main-layout']//app-sidebar//div[@class='sidebar sidebar-expanded']//div//li[@class='sidebar-item'][normalize-space()='Vikendice']")).click();

        String cottageName = "Vikendica 1";

        //Pronađi polja za unos i popuni ih
        //Ovde su putanje izmenjene u xpath jer je prvobitno bio id
        driver.findElement(By.xpath("//div[3]//div[1]//input[1]")).sendKeys("Vikendica 1");  // LK: naziv sadrzi brojeve i specijalne znakove
        driver.findElement(By.xpath("//div[3]//div[2]//input[1]")).sendKeys("");      // LK: mesto samo slova
        driver.findElement(By.xpath("//body//app-root//div[3]//div[3]//textarea[1]")).sendKeys("Klima, Wi-Fi, 2 TV"); // LK: tekst sa brojevima i spec. znak.
        driver.findElement(By.xpath("//div[3]//div[4]//input[1]")).sendKeys("3000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[5]//input[1]")).sendKeys("4000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[6]//input[1]")).sendKeys("+123123123");  // LK: brojevi + '+'

        //Pre dodavanja uzmi broj vikendica
        List<WebElement> cottagesBefore = driver.findElements(By.cssSelector(".cottage-card"));
        int countBefore = cottagesBefore.size();

        //Klik na dugme "Dodaj vikendicu"
        driver.findElement(By.xpath("//button[normalize-space()='Dodaj vikendicu']")).click();

        List<WebElement> vikendicePosle = driver.findElements(By.cssSelector(".cottage-card"));
        int countAfter = vikendicePosle.size();

        boolean valid;
        if(countAfter==countBefore)
            valid=true;
        else
            valid=false;

        //Assertion
        Assert.assertTrue(valid, "Nova vikendica nije dodata ili nije vidljiva!");
    }

    @Test
    public void Test57() {
        //Login kao owner1
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Korisničko ime']"));
        usernameField.sendKeys("owner1");
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Lozinka']"));
        passwordField.sendKeys("pass123");

        //Klik na dugme "Vikendice"
        driver.findElement(By.xpath("//button[normalize-space()='Prijavi se']")).click();

        //Popunjavanje forme sa validnim podacima (LK klase)
        driver.findElement(By.xpath("//div[@class='main-layout']//app-sidebar//div[@class='sidebar sidebar-expanded']//div//li[@class='sidebar-item'][normalize-space()='Vikendice']")).click();

        String cottageName = "Vikendica 1";

        //Pronađi polja za unos i popuni ih
        //Ovde su putanje izmenjene u xpath jer je prvobitno bio id
        driver.findElement(By.xpath("//div[3]//div[1]//input[1]")).sendKeys("Vikendica 1");  // LK: naziv sadrzi brojeve i specijalne znakove
        driver.findElement(By.xpath("//div[3]//div[2]//input[1]")).sendKeys("Zlatibor");      // LK: mesto samo slova
        driver.findElement(By.xpath("//body//app-root//div[3]//div[3]//textarea[1]")).sendKeys(""); // LK: tekst sa brojevima i spec. znak.
        driver.findElement(By.xpath("//div[3]//div[4]//input[1]")).sendKeys("3000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[5]//input[1]")).sendKeys("4000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[6]//input[1]")).sendKeys("+123123123");  // LK: brojevi + '+'

        //Pre dodavanja uzmi broj vikendica
        List<WebElement> cottagesBefore = driver.findElements(By.cssSelector(".cottage-card"));
        int countBefore = cottagesBefore.size();

        //Klik na dugme "Dodaj vikendicu"
        driver.findElement(By.xpath("//button[normalize-space()='Dodaj vikendicu']")).click();

        List<WebElement> vikendicePosle = driver.findElements(By.cssSelector(".cottage-card"));
        int countAfter = vikendicePosle.size();

        boolean valid;
        if(countAfter==countBefore)
            valid=true;
        else
            valid=false;

        //Assertion
        Assert.assertTrue(valid, "Nova vikendica nije dodata ili nije vidljiva!");
    }

    @Test
    public void Test58() {
        //Login kao owner1
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Korisničko ime']"));
        usernameField.sendKeys("owner1");
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Lozinka']"));
        passwordField.sendKeys("pass123");

        //Klik na dugme "Vikendice"
        driver.findElement(By.xpath("//button[normalize-space()='Prijavi se']")).click();

        //Popunjavanje forme sa validnim podacima (LK klase)
        driver.findElement(By.xpath("//div[@class='main-layout']//app-sidebar//div[@class='sidebar sidebar-expanded']//div//li[@class='sidebar-item'][normalize-space()='Vikendice']")).click();

        String cottageName = "Vikendica 1";

        //Pronađi polja za unos i popuni ih
        //Ovde su putanje izmenjene u xpath jer je prvobitno bio id
        driver.findElement(By.xpath("//div[3]//div[1]//input[1]")).sendKeys("Vikendica 1");  // LK: naziv sadrzi brojeve i specijalne znakove
        driver.findElement(By.xpath("//div[3]//div[2]//input[1]")).sendKeys("Zlatibor");      // LK: mesto samo slova
        driver.findElement(By.xpath("//body//app-root//div[3]//div[3]//textarea[1]")).sendKeys("Klima, Wi-Fi, 2 TV"); // LK: tekst sa brojevima i spec. znak.
        driver.findElement(By.xpath("//div[3]//div[4]//input[1]")).sendKeys("-3000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[5]//input[1]")).sendKeys("4000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[6]//input[1]")).sendKeys("+123123123");  // LK: brojevi + '+'

        //Pre dodavanja uzmi broj vikendica
        List<WebElement> cottagesBefore = driver.findElements(By.cssSelector(".cottage-card"));
        int countBefore = cottagesBefore.size();

        //Klik na dugme "Dodaj vikendicu"
        driver.findElement(By.xpath("//button[normalize-space()='Dodaj vikendicu']")).click();

        List<WebElement> vikendicePosle = driver.findElements(By.cssSelector(".cottage-card"));
        int countAfter = vikendicePosle.size();

        boolean valid;
        if(countAfter==countBefore)
            valid=true;
        else
            valid=false;

        //Assertion
        Assert.assertTrue(valid, "Nova vikendica nije dodata ili nije vidljiva!");
    }

    @Test
    public void Test59() {
        //Login kao owner1
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Korisničko ime']"));
        usernameField.sendKeys("owner1");
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Lozinka']"));
        passwordField.sendKeys("pass123");

        //Klik na dugme "Vikendice"
        driver.findElement(By.xpath("//button[normalize-space()='Prijavi se']")).click();

        //Popunjavanje forme sa validnim podacima (LK klase)
        driver.findElement(By.xpath("//div[@class='main-layout']//app-sidebar//div[@class='sidebar sidebar-expanded']//div//li[@class='sidebar-item'][normalize-space()='Vikendice']")).click();

        String cottageName = "Vikendica 1";

        //Pronađi polja za unos i popuni ih
        //Ovde su putanje izmenjene u xpath jer je prvobitno bio id
        driver.findElement(By.xpath("//div[3]//div[1]//input[1]")).sendKeys("Vikendica 1");  // LK: naziv sadrzi brojeve i specijalne znakove
        driver.findElement(By.xpath("//div[3]//div[2]//input[1]")).sendKeys("Zlatibor");      // LK: mesto samo slova
        driver.findElement(By.xpath("//body//app-root//div[3]//div[3]//textarea[1]")).sendKeys("Klima, Wi-Fi, 2 TV"); // LK: tekst sa brojevima i spec. znak.
        driver.findElement(By.xpath("//div[3]//div[4]//input[1]")).sendKeys("3000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[5]//input[1]")).sendKeys("-4000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[6]//input[1]")).sendKeys("+123123123");  // LK: brojevi + '+'

        //Pre dodavanja uzmi broj vikendica
        List<WebElement> cottagesBefore = driver.findElements(By.cssSelector(".cottage-card"));
        int countBefore = cottagesBefore.size();

        //Klik na dugme "Dodaj vikendicu"
        driver.findElement(By.xpath("//button[normalize-space()='Dodaj vikendicu']")).click();

        List<WebElement> vikendicePosle = driver.findElements(By.cssSelector(".cottage-card"));
        int countAfter = vikendicePosle.size();

        boolean valid;
        if(countAfter==countBefore)
            valid=true;
        else
            valid=false;

        //Assertion
        Assert.assertTrue(valid, "Nova vikendica nije dodata ili nije vidljiva!");
    }

    @Test
    public void Test60() {
        //Login kao owner1
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Korisničko ime']"));
        usernameField.sendKeys("owner1");
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Lozinka']"));
        passwordField.sendKeys("pass123");

        //Klik na dugme "Vikendice"
        driver.findElement(By.xpath("//button[normalize-space()='Prijavi se']")).click();

        //Popunjavanje forme sa validnim podacima (LK klase)
        driver.findElement(By.xpath("//div[@class='main-layout']//app-sidebar//div[@class='sidebar sidebar-expanded']//div//li[@class='sidebar-item'][normalize-space()='Vikendice']")).click();

        String cottageName = "Vikendica 1";

        //Pronađi polja za unos i popuni ih
        //Ovde su putanje izmenjene u xpath jer je prvobitno bio id
        driver.findElement(By.xpath("//div[3]//div[1]//input[1]")).sendKeys("Vikendica 1");  // LK: naziv sadrzi brojeve i specijalne znakove
        driver.findElement(By.xpath("//div[3]//div[2]//input[1]")).sendKeys("Zlatibor");      // LK: mesto samo slova
        driver.findElement(By.xpath("//body//app-root//div[3]//div[3]//textarea[1]")).sendKeys("Klima, Wi-Fi, 2 TV"); // LK: tekst sa brojevima i spec. znak.
        driver.findElement(By.xpath("//div[3]//div[4]//input[1]")).sendKeys("3000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[5]//input[1]")).sendKeys("4000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[6]//input[1]")).sendKeys("");  // LK: brojevi + '+'

        //Pre dodavanja uzmi broj vikendica
        List<WebElement> cottagesBefore = driver.findElements(By.cssSelector(".cottage-card"));
        int countBefore = cottagesBefore.size();

        //Klik na dugme "Dodaj vikendicu"
        driver.findElement(By.xpath("//button[normalize-space()='Dodaj vikendicu']")).click();

        List<WebElement> vikendicePosle = driver.findElements(By.cssSelector(".cottage-card"));
        int countAfter = vikendicePosle.size();

        boolean valid;
        if(countAfter==countBefore)
            valid=true;
        else
            valid=false;

        //Assertion
        Assert.assertTrue(valid, "Nova vikendica nije dodata ili nije vidljiva!");
    }

    @Test
    public void Test61() {
        //Login kao owner1
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Korisničko ime']"));
        usernameField.sendKeys("owner1");
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Lozinka']"));
        passwordField.sendKeys("pass123");

        //Klik na dugme "Vikendice"
        driver.findElement(By.xpath("//button[normalize-space()='Prijavi se']")).click();

        //Popunjavanje forme sa validnim podacima (LK klase)
        driver.findElement(By.xpath("//div[@class='main-layout']//app-sidebar//div[@class='sidebar sidebar-expanded']//div//li[@class='sidebar-item'][normalize-space()='Vikendice']")).click();

        String cottageName = "Vikendica 1";

        //Pronađi polja za unos i popuni ih
        //Ovde su putanje izmenjene u xpath jer je prvobitno bio id
        driver.findElement(By.xpath("//div[3]//div[1]//input[1]")).sendKeys("Vikendica 1");  // LK: naziv sadrzi brojeve i specijalne znakove
        driver.findElement(By.xpath("//div[3]//div[2]//input[1]")).sendKeys("Zlatibor");      // LK: mesto samo slova
        driver.findElement(By.xpath("//body//app-root//div[3]//div[3]//textarea[1]")).sendKeys("Klima, Wi-Fi, 2 TV"); // LK: tekst sa brojevima i spec. znak.
        driver.findElement(By.xpath("//div[3]//div[4]//input[1]")).sendKeys("3000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[5]//input[1]")).sendKeys("4000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[6]//input[1]")).sendKeys("+123123123");  // LK: brojevi + '+'
        driver.findElement(By.id("koordinateGeoSirine")).sendKeys("-100");
        driver.findElement(By.id("koordinateGeoSirine")).sendKeys("110.6");

        //Pre dodavanja uzmi broj vikendica
        List<WebElement> cottagesBefore = driver.findElements(By.cssSelector(".cottage-card"));
        int countBefore = cottagesBefore.size();

        //Klik na dugme "Dodaj vikendicu"
        driver.findElement(By.xpath("//button[normalize-space()='Dodaj vikendicu']")).click();

        List<WebElement> vikendicePosle = driver.findElements(By.cssSelector(".cottage-card"));
        int countAfter = vikendicePosle.size();

        boolean valid;
        if(countAfter==countBefore)
            valid=true;
        else
            valid=false;

        //Assertion
        Assert.assertTrue(valid, "Nova vikendica nije dodata ili nije vidljiva!");
    }

    @Test
    public void Test62() {
        //Login kao owner1
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Korisničko ime']"));
        usernameField.sendKeys("owner1");
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Lozinka']"));
        passwordField.sendKeys("pass123");

        //Klik na dugme "Vikendice"
        driver.findElement(By.xpath("//button[normalize-space()='Prijavi se']")).click();

        //Popunjavanje forme sa validnim podacima (LK klase)
        driver.findElement(By.xpath("//div[@class='main-layout']//app-sidebar//div[@class='sidebar sidebar-expanded']//div//li[@class='sidebar-item'][normalize-space()='Vikendice']")).click();

        String cottageName = "Vikendica 1";

        //Pronađi polja za unos i popuni ih
        //Ovde su putanje izmenjene u xpath jer je prvobitno bio id
        driver.findElement(By.xpath("//div[3]//div[1]//input[1]")).sendKeys("Vikendica 1");  // LK: naziv sadrzi brojeve i specijalne znakove
        driver.findElement(By.xpath("//div[3]//div[2]//input[1]")).sendKeys("Zlatibor");      // LK: mesto samo slova
        driver.findElement(By.xpath("//body//app-root//div[3]//div[3]//textarea[1]")).sendKeys("Klima, Wi-Fi, 2 TV"); // LK: tekst sa brojevima i spec. znak.
        driver.findElement(By.xpath("//div[3]//div[4]//input[1]")).sendKeys("3000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[5]//input[1]")).sendKeys("4000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[6]//input[1]")).sendKeys("+123123123");  // LK: brojevi + '+'
        driver.findElement(By.id("koordinateGeoSirine")).sendKeys("100");
        driver.findElement(By.id("koordinateGeoSirine")).sendKeys("110.6");

        //Pre dodavanja uzmi broj vikendica
        List<WebElement> cottagesBefore = driver.findElements(By.cssSelector(".cottage-card"));
        int countBefore = cottagesBefore.size();

        //Klik na dugme "Dodaj vikendicu"
        driver.findElement(By.xpath("//button[normalize-space()='Dodaj vikendicu']")).click();

        List<WebElement> vikendicePosle = driver.findElements(By.cssSelector(".cottage-card"));
        int countAfter = vikendicePosle.size();

        boolean valid;
        if(countAfter==countBefore)
            valid=true;
        else
            valid=false;

        //Assertion
        Assert.assertTrue(valid, "Nova vikendica nije dodata ili nije vidljiva!");
    }

    @Test
    public void Test63() {
        //Login kao owner1
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Korisničko ime']"));
        usernameField.sendKeys("owner1");
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Lozinka']"));
        passwordField.sendKeys("pass123");

        //Klik na dugme "Vikendice"
        driver.findElement(By.xpath("//button[normalize-space()='Prijavi se']")).click();

        //Popunjavanje forme sa validnim podacima (LK klase)
        driver.findElement(By.xpath("//div[@class='main-layout']//app-sidebar//div[@class='sidebar sidebar-expanded']//div//li[@class='sidebar-item'][normalize-space()='Vikendice']")).click();

        String cottageName = "Vikendica 1";

        //Pronađi polja za unos i popuni ih
        //Ovde su putanje izmenjene u xpath jer je prvobitno bio id
        driver.findElement(By.xpath("//div[3]//div[1]//input[1]")).sendKeys("Vikendica 1");  // LK: naziv sadrzi brojeve i specijalne znakove
        driver.findElement(By.xpath("//div[3]//div[2]//input[1]")).sendKeys("Zlatibor");      // LK: mesto samo slova
        driver.findElement(By.xpath("//body//app-root//div[3]//div[3]//textarea[1]")).sendKeys("Klima, Wi-Fi, 2 TV"); // LK: tekst sa brojevima i spec. znak.
        driver.findElement(By.xpath("//div[3]//div[4]//input[1]")).sendKeys("3000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[5]//input[1]")).sendKeys("4000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[6]//input[1]")).sendKeys("+123123123");  // LK: brojevi + '+'
        driver.findElement(By.id("koordinateGeoSirine")).sendKeys("20.5");
        driver.findElement(By.id("koordinateGeoSirine")).sendKeys("-200");

        //Pre dodavanja uzmi broj vikendica
        List<WebElement> cottagesBefore = driver.findElements(By.cssSelector(".cottage-card"));
        int countBefore = cottagesBefore.size();

        //Klik na dugme "Dodaj vikendicu"
        driver.findElement(By.xpath("//button[normalize-space()='Dodaj vikendicu']")).click();

        List<WebElement> vikendicePosle = driver.findElements(By.cssSelector(".cottage-card"));
        int countAfter = vikendicePosle.size();

        boolean valid;
        if(countAfter==countBefore)
            valid=true;
        else
            valid=false;

        //Assertion
        Assert.assertTrue(valid, "Nova vikendica nije dodata ili nije vidljiva!");
    }

    @Test
    public void Test64() {
        //Login kao owner1
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Korisničko ime']"));
        usernameField.sendKeys("owner1");
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Lozinka']"));
        passwordField.sendKeys("pass123");

        //Klik na dugme "Vikendice"
        driver.findElement(By.xpath("//button[normalize-space()='Prijavi se']")).click();

        //Popunjavanje forme sa validnim podacima (LK klase)
        driver.findElement(By.xpath("//div[@class='main-layout']//app-sidebar//div[@class='sidebar sidebar-expanded']//div//li[@class='sidebar-item'][normalize-space()='Vikendice']")).click();

        String cottageName = "Vikendica 1";

        //Pronađi polja za unos i popuni ih
        //Ovde su putanje izmenjene u xpath jer je prvobitno bio id
        driver.findElement(By.xpath("//div[3]//div[1]//input[1]")).sendKeys("Vikendica 1");  // LK: naziv sadrzi brojeve i specijalne znakove
        driver.findElement(By.xpath("//div[3]//div[2]//input[1]")).sendKeys("Zlatibor");      // LK: mesto samo slova
        driver.findElement(By.xpath("//body//app-root//div[3]//div[3]//textarea[1]")).sendKeys("Klima, Wi-Fi, 2 TV"); // LK: tekst sa brojevima i spec. znak.
        driver.findElement(By.xpath("//div[3]//div[4]//input[1]")).sendKeys("3000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[5]//input[1]")).sendKeys("4000");       // LK: cena >= 0
        driver.findElement(By.xpath("//div[3]//div[6]//input[1]")).sendKeys("+123123123");  // LK: brojevi + '+'
        driver.findElement(By.id("koordinateGeoSirine")).sendKeys("20.5");
        driver.findElement(By.id("koordinateGeoSirine")).sendKeys("200");

        //Pre dodavanja uzmi broj vikendica
        List<WebElement> cottagesBefore = driver.findElements(By.cssSelector(".cottage-card"));
        int countBefore = cottagesBefore.size();

        //Klik na dugme "Dodaj vikendicu"
        driver.findElement(By.xpath("//button[normalize-space()='Dodaj vikendicu']")).click();

        List<WebElement> vikendicePosle = driver.findElements(By.cssSelector(".cottage-card"));
        int countAfter = vikendicePosle.size();

        boolean valid;
        if(countAfter==countBefore)
            valid=true;
        else
            valid=false;

        //Assertion
        Assert.assertTrue(valid, "Nova vikendica nije dodata ili nije vidljiva!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}