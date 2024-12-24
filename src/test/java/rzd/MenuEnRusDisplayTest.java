package rzd;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import rzd.data.Language;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Tag("SMOKE")
@DisplayName("При смене языка Rus Eng, должно меняться верхнее меню")
public class MenuEnRusDisplayTest {
  @BeforeAll
  static void setUp() {
    Configuration.browserSize = "1920x1080";
    Configuration.pageLoadStrategy = "eager";
    open("https://www.rzd.ru/");
  }

  static Stream<Arguments> rzdSiteShouldDisplayCorrectText() {
    return Stream.of(
      Arguments.of(Language.Eng, List.of(
        "Passengers\n" +
          "Freight\n" +
          "The Company\n" +
          "Investor Relations\n" +
          "Contacts\n" +
          "Аccessibility version\n" +
          "Log in\n" +
          "Eng"
      )),
      Arguments.of(Language.Rus, List.of(
        "Пассажирам\n" +
          "Грузовые перевозки\n" +
          "Компания\n" +
          "Работа в РЖД\n" +
          "Контакты\n" +
          "Версия для слабовидящих\n" +
          "Вход\n" +
          "Rus"
      ))
    );
  }

  @MethodSource
  @ParameterizedTest
  void rzdSiteShouldDisplayCorrectText(Language language, List<String> expectedButtons) {
    $(".locale-switch__trigger").click();
    $$("li.locale-switch__li").find(text(language.name())).click();
    $$(".menu-search-wrap").filter(visible)
      .shouldHave(texts(expectedButtons));
  }


  @AfterAll
  @Tag("SMOKE")
  static void turnDown() {
    closeWebDriver();
  //  Selenide.clearBrowserLocalStorage();
    Selenide.clearBrowserCookies();
  }
}
