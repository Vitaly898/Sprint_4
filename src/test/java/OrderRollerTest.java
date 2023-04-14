import PageObject.MainPage;
import PageObject.firstOrderPage;
import PageObject.secondOrderPage;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Assert;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

//Обьявил параметризованный тест
@RunWith(Parameterized.class)
public class OrderRollerTest {
    //Инициализирую переменные которые нужны для заполнения полей
    WebDriver driver;
    private final String nameValue;
    private final String surnameValue;
    private final String addressValue;
    private final String phoneValue;
    private final String metroValue;
    private final String date;
    private final String buttonLocator;
//Обьявил конструктор с полями для инпутов
    public OrderRollerTest(String nameValue, String surnameValue, String addressValue, String phoneValue, String metroValue, String date,String buttonLocator) {
        this.nameValue = nameValue;
        this.surnameValue = surnameValue;
        this.addressValue = addressValue;
        this.phoneValue = phoneValue;
        this.metroValue = metroValue;
        this.date = date;
        this.buttonLocator=buttonLocator;
    }
    //создал обьект с передаваемыми значениями
    @Parameterized.Parameters
    public static Object [][] personValues(){
        return new Object[][]{
                {"Виталий","Петров","Санкт-Петербург","89991112233","Черкизовская","31.04.2023",".Header_Nav__AGCXC .Button_Button__ra12g"},
                {"Николас","Кейдж","Москва","89991234567","Чистые пруды","3.05.2024",".Home_FinishButton__1_cWm button"}
        };
    }


    @Test
    public void test() {
        //обьявил драйвер и создал его экземпляр
        System.setProperty("webdriver.chrome.driver", "/Users/vitalypetrov/IdeaProjects/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        //WebDriver driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        //Обьявил экземпляр главной страницы
        MainPage objMainPage = new MainPage(driver);
        //Закрыл куки
        objMainPage.closeCookie();
        //Подождал 3 секунды
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //нажал на кнопку заказать(2 раза в хедере и 2 раза в середине страницы)
        driver.findElement(By.cssSelector(buttonLocator)).click();
        //Подождал 3 секунды
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //Обьявил экземпляр первой страницы заказа
        firstOrderPage orderPage1 = new firstOrderPage(driver);
        //Заполнил все поля и кликнул Далее
        orderPage1.fillAllInputsAndPressNext(nameValue,surnameValue,addressValue,phoneValue,metroValue);
        //Обьявил экземпляр второй страницы заказа
        secondOrderPage orderPage2 = new secondOrderPage(driver);
        //Заполнил на ней все поля, кликунл на Заказать и подтвердил нажав на ДА
        orderPage2.fillSecondPageFields(date);
        //Наше текст воявившейся в результате удачного заказа модалки
        String result = driver.findElement(By.cssSelector(".Order_Modal__YZ-d3 .Order_ModalHeader__3FDaJ")).getText();
        //завел переменную в которой сохранил появившуюся модалку
        WebElement modal = driver.findElement(By.cssSelector(".Order_Modal__YZ-d3"));
        Assert.assertThat(result, CoreMatchers.startsWith("Заказ оформлен"));
        //Проверяю что модалка отобразилась
        //Assert.assertTrue(modal.isDisplayed());
        driver.quit();
    }




}
