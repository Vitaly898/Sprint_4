package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class firstOrderPage {
    //Инициализировал драйвер
    private WebDriver driver;
    //Конструктор страницы заказа с аргументом Driver
    public firstOrderPage(WebDriver driver){
        this.driver = driver;
    }

    //Нашел все локаторы инпутов
    //Имя
    public By nameField = By.xpath(".//input[@placeholder = '* Имя']");
    //Фамилия
    public By surnameField = By.xpath(".//input[@placeholder = '* Фамилия']");
    //Адрес
    public By addressField  = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    //Станция метро
    public By metroField = By.xpath(".//input[@placeholder = '* Станция метро']");
    //Теефон
    public By phoneNumber = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //Кнопка далее
    public By buttonNext = By.xpath(".//button[text()='Далее']");
    //Методы заполнения инпутов
    //Общий метод для заполнения инпута - передаю в него локатор инпута и то что хочу туда вписать
    public void fillTheInput(By inputName,String keys){

        driver.findElement(inputName).sendKeys(keys);
    }
    //метод заполнения поля станции метро
    public void fillMetroField(By metro, String metroValue) {
        driver.findElement(metro).sendKeys(metroValue, Keys.ARROW_DOWN, Keys.ENTER);
    }
    //Собрал в один метод заполнение всех инпутов и клик на кнопку Далее
    public void fillAllInputsAndPressNext(String name,String surname,String address, String phone,String metro){
        fillTheInput(nameField,name);
        fillTheInput(surnameField,surname);
        fillTheInput(addressField,address);
        fillMetroField(metroField,metro);
        fillTheInput(phoneNumber,phone);
        driver.findElement(buttonNext).click();

    }
}
