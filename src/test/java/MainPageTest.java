import PageObject.MainPage;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


//обьявил параметризованный тест
@RunWith(Parameterized.class)
public class MainPageTest  {
    //Инициализировал драйвер и переменные с ожидаемым результатом и локаторами вопроса и ответа о важном
    WebDriver driver;
    private final String expectedResult;
    private final String questionLocator;
    private final String answerLocator;
    //Обьявил конструктор теста главной страницы с параметрами
    public MainPageTest(String expectedResult, String questionLocator,String answerLocator) {
        this.expectedResult = expectedResult;
        this.answerLocator=answerLocator;
        this.questionLocator = questionLocator;

    }
    //Создал обьект с парарметрами
    @Parameterized.Parameters
    public static Object[][] accordionItems(){
        return new Object[][]{
                { "Сутки — 400 рублей. Оплата курьеру — наличными или картой.","#accordion__heading-0","#accordion__panel-0"},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.","#accordion__heading-1","#accordion__panel-1"},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.","#accordion__heading-2","#accordion__panel-2"},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.","#accordion__heading-3","#accordion__panel-3"},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.","#accordion__heading-4","#accordion__panel-4"},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.","#accordion__heading-5","#accordion__panel-5"},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.","#accordion__heading-6","#accordion__panel-6"},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.","#accordion__heading-7","#accordion__panel-7"}

        };

    }



    @Test
    public void test() {
        //обьявил драйвер
        System.setProperty("webdriver.chrome.driver", "/Users/vitalypetrov/IdeaProjects/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*","--headless");
        WebDriver driver = new ChromeDriver(options);
        //WebDriver driver = new FirefoxDriver();
        //Перешел на главную
        driver.get("https://qa-scooter.praktikum-services.ru/");
        //Создал экземпляр главной страницы
        MainPage objMainPage = new MainPage(driver);
        //Закрыл куки
        objMainPage.closeCookie();
        //Проскроллил до элемент
        objMainPage.scrollToTheElement();
        //Кликнул на вопрос о важном
        driver.findElement(By.cssSelector(questionLocator)).click();
        //Достал текст из div из дропдауна
        String result =  driver.findElement(By.cssSelector(answerLocator)).getText();
        //Сравнил с правильным текстом
        Assert.assertEquals("Текст не совпал",expectedResult,result);
        //Закрыл драйвер
        driver.quit();
    }

}
