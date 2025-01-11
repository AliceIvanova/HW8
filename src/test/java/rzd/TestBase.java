package rzd;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {
  @BeforeAll
  static void setUp() {
    Configuration.browserSize = "1920x1080";
    Configuration.pageLoadStrategy = "eager";
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
