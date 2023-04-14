package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
public class MainPage {
    //Инициализировал драйвер
    private WebDriver driver;
    //Кнопка закрытия куков
    private final By cookieCloseButton = By.cssSelector(".App_CookieButton__3cvqF");
    //Тайтл списка "Вопросы о важном"
    private final By mainQuestionText = By.xpath(".//div[text()='Вопросы о важном']");
    //Кнопка заказа в хедере
    public By orderButton = By.cssSelector(".Header_Nav__AGCXC .Button_Button__ra12g");
    //Кнопка заказа в середине страницы
    public By orderButton2 = By.cssSelector(".Home_FinishButton__1_cWm button");
    //Вопросы и ответы о Важном
    //Вопросы
    public By Question1 = By.cssSelector("#accordion__heading-0");

    public By Question2 = By.cssSelector("#accordion__heading-1");
    public By Question3 = By.cssSelector("#accordion__heading-2");
    public By Question4 = By.cssSelector("#accordion__heading-3");
    public By Question5 = By.cssSelector("#accordion__heading-4");
    public By Question6 = By.cssSelector("#accordion__heading-5");
    public By Question7 = By.cssSelector("#accordion__heading-6");
    public By Question8 = By.cssSelector("#accordion__heading-7");
    //Ответы
    public By Answer1 = By.cssSelector("#accordion__panel-0");
    public By Answer2 = By.cssSelector("#accordion__panel-1");
    public By Answer3 = By.cssSelector("#accordion__panel-2");
    public By Answer4 = By.cssSelector("#accordion__panel-3");
    public By Answer5 = By.cssSelector("#accordion__panel-4");
    public By Answer6 = By.cssSelector("#accordion__panel-5");
    public By Answer7 = By.cssSelector("#accordion__panel-6");
    public By Answer8 = By.cssSelector("#accordion__panel-7");

    //Кнопка перехода на страницу статуса заказа
    public By orderStatusButton = By.cssSelector(".Header_Nav__AGCXC .Header_Link__1TAG7");

    //Конструктор MainPage с аргументом
    public MainPage(WebDriver driver) {

        this.driver = driver;
    }
        //Метод для закрытия куков
        public void closeCookie() {
            driver.findElement(cookieCloseButton).click();
        }

        //Метод скролла к элементу
        public void scrollToTheElement(){
            WebElement searchingElement = driver.findElement(mainQuestionText);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", searchingElement);
        }

}


