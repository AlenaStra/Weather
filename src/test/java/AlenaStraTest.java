import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlenaStraTest {

    //    TC_1_1  - Тест кейс:
    //    //1. Открыть страницу https://openweathermap.org/
    //    //2. Набрать в строке поиска город Paris
    //    //3. Нажать пункт меню Search
    //    //4. Из выпадающего списка выбрать Paris, FR
    //    //5. Подтвердить, что заголовок изменился на "Paris, FR"


    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/alena/Documents/Alena/Chrome_Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();

        Thread.sleep(1000);
        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );

        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();
        Assert.assertEquals(actualResult,expectedResult);



        Thread.sleep(5000);


        driver.quit();

    }

    /* TC_11_01
    //1.  Открыть базовую ссылку
    //2.  Нажать на пункт меню Guide
    //3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide и что title этой
    // страницы OpenWeatherMap API guide - OpenWeatherMap
    */

    @Test
    public void testOpenWeatherMapAPIguide_OpenWeatherMap() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/alena/Documents/Alena/Chrome_Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String menuGuide = "Guide";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(10000);

        WebElement guideMenu = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[@href = '/guide']")
        );
        Thread.sleep(2000);
        guideMenu.click();

        String actualResult1 = driver.getCurrentUrl();
        String actualResult2 = driver.getTitle();
        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

        /* TC_11_02
        1.  Открыть базовую ссылку
        2.  Нажать на единицы измерения Imperial: °F, mph
        3.  Подтвердить, что температура для города показана в Фарингейтах
         */

        @Test
        public void testTempInFahrenheit() throws InterruptedException {
            System.setProperty("webdriver.chrome.driver", "C:/Users/alena/Documents/Alena/Chrome_Driver/chromedriver.exe");
            WebDriver driver = new ChromeDriver();

            String url = "https://openweathermap.org/";
            String expectedResult = "F";

            driver.get(url);
            Thread.sleep(10000);

            WebElement unitOfMeasureF = driver.findElement(
                    By.xpath("//div[@class = 'option'][contains(text(), 'Imperial: °F, mph')]")
//                    By.xpath("//div[@id = 'weather-widget']//div[@class = 'switch-container']/div[3]")
            );

            unitOfMeasureF.click();
            Thread.sleep(5000);

            WebElement currentCityTempInF = driver.findElement(
                    By.xpath("//div[@class = 'current-temp']//span[@class = 'heading']")
            );

            int length = currentCityTempInF.getText().length();
            String actualResult = String.valueOf(currentCityTempInF.getText().charAt(length - 1));

            Thread.sleep(2000);
            Assert.assertEquals(actualResult,expectedResult);

            driver.quit();
        }

        /* TC_11_07
            1.  Открыть базовую ссылку
            2.  Нажать на единицы измерения Imperial: °F, mph
            3.  Нажать на единицы измерения Metric: °C, m/s
            4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
         */

    @Test
    public void testTempSwitchFromFahrenheitToCelsius() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/alena/Documents/Alena/Chrome_Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "C";
        driver.get(url);
        Thread.sleep(6000);

        WebElement unitOfMeasureF = driver.findElement(
                By.xpath("//div[@class = 'option'][contains(text(), 'Imperial: °F, mph')]")
        );

        unitOfMeasureF.click();
        Thread.sleep(5000);

        WebElement unitOfMeasureC = driver.findElement(
                By.xpath("////div[@class = 'option'][contains(text(), 'Metric: °C, m/s')]")
        );

        unitOfMeasureC.click();
        Thread.sleep(5000);

        WebElement currentCityTemp = driver.findElement(
                By.xpath("//div[@class = 'current-temp']//span[@class = 'heading']")
        );

        int length = currentCityTemp.getText().length();
        String actualResult = String.valueOf(currentCityTemp.getText().charAt(length - 1));

        Thread.sleep(2000);
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /* TC_11_03
        1.  Открыть базовую ссылку
        2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to
        work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised.
        You can allow all cookies or manage them individually.”
        3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */

    @Test
    public void testVerifyCookiesAndTwoButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/alena/Documents/Alena/Chrome_Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "We use cookies which are essential for the site to " +
                "work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. " +
                "You can allow all cookies or manage them individually.";
        String expectedResult2 = "Allow all\nManage cookies";
        int expectedResult3 = 2;

        driver.get(url);
        Thread.sleep(10000);

        WebElement cookiesMessage = driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel__container']/p[@class = 'stick-footer-panel__description']")
        );
        Thread.sleep(1000);
        String actualResult1 = String.valueOf(cookiesMessage.getText());

        WebElement cookiesButtons = driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']")
        );
        Thread.sleep(2000);
        String actualResult2 = String.valueOf(cookiesButtons.getText());

        Assert.assertEquals(actualResult1,expectedResult1);
        Assert.assertEquals(driver.findElements(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*")
        ).size(), expectedResult3);
        Assert.assertEquals(actualResult2,expectedResult2);


        driver.quit();
    }

    /* TC_11_04
        1.  Открыть базовую ссылку
        2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
     */
    @Test
    public void testVerifySupportMenuHasThreeSubmenus() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/alena/Documents/Alena/Chrome_Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "FAQ\nHow to start\nAsk a question";

        driver.get(url);
        Thread.sleep(10000);
        driver.manage().window().maximize();
        Thread.sleep(1000);

        WebElement menuSupport = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']")
        );
        Thread.sleep(1000);
        menuSupport.click();

        Thread.sleep(1000);

        WebElement supportDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']")
        );

        String actualResult = String.valueOf(supportDropdownMenu.getText());
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /* TC_11_05
        1. Открыть базовую ссылку
        2. Нажать пункт меню Support → Ask a question
        3. Заполнить поля Email, Subject, Message
        4. Не подтвердив CAPTCHA, нажать кнопку Submit
        5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     */

    @Test
    public void testCaptchaErrorWhenSkippingCaptchaVerificationAtSupportAskAQestion() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/alena/Documents/Alena/Chrome_Driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "reCAPTCHA verification failed, please try again.";
        String email = "hello@a.com";
        String subject = "Other";
        String message = "What a nice day!";

        driver.get(url);
        Thread.sleep(5000);
        driver.manage().window().maximize();
        Thread.sleep(1000);

        WebElement menuSupport = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']")
        );
        Thread.sleep(1000);
        menuSupport.click();

        String originalWindow = driver.getWindowHandle();
        Thread.sleep(4000);
        WebElement askAQuestion = driver.findElement(By.xpath(
                "//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']"));
        askAQuestion.click();

        Thread.sleep(4500);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Thread.sleep(3000);

        WebElement findEmailField = driver.findElement(
                By.xpath("//input[@type = 'email']")
        );

        findEmailField.click();
        findEmailField.sendKeys(email);
        Thread.sleep(5000);

        WebElement enterSubject = driver.findElement(By.xpath(
                "//select[@class='form-control select required']"));
        enterSubject.click();
        enterSubject.sendKeys(subject);

        Thread.sleep(4000);

        WebElement enterMessage = driver.findElement(By.xpath(
                "//textarea[@class='form-control text required']"));
        enterMessage.click();
        enterMessage.sendKeys(message);

        Thread.sleep(5000);

        String window2 = driver.getWindowHandle();

//        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));
//
//        WebElement enterCaptcha = driver.findElement(By.xpath(
//                "//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked "
//                        + "rc-anchor-checkbox']"));
//        enterCaptcha.click();
//
//        Thread.sleep(10000);

        driver.switchTo().window(window2);

        WebElement pressSubmit = driver.findElement(By.xpath(
                "//input[@data-disable-with='Create Question form']"));
        pressSubmit.click();

        WebElement confirmErrorEmail = driver.findElement(By.xpath("//div[@class='help-block']"));

        String actualResult = confirmErrorEmail.getText();

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }











}
