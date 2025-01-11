package rzd;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {
  @BeforeAll
  static void setUp() {
    Configuration.browserSize = "1920x1080";
    Configuration.pageLoadStrategy = "eager";

  }
  @BeforeEach
  void openStarPage() {
    open("https://www.rzd.ru/");
  }
  @AfterEach
  void turnDown() {
    closeWebDriver();
  }

  @AfterAll
  static void clearCookies() {

    //  Selenide.clearBrowserLocalStorage();
    Selenide.clearBrowserCookies();
  }
}
