package rzd;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import rzd.data.Language;
import utils.TestData;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Tag("SMOKE")
public class ParamPracticeTest {
  @BeforeEach
  void setUp() {
    Configuration.browserSize = "1920x1080";
    Configuration.pageLoadStrategy = "eager";
  }

  @ValueSource(strings = {

  })

  @DisplayName("Тест поиска билетов из Москвы в Санкт-Петербург и обратно")
  @ParameterizedTest
  @Tags({
    @Tag("SMOKE"),
    @Tag("Web"),

  })
  //@ParameterizedTest
  @ValueSource(strings = {
    "Москва",
    "Moscow"
  })


  public void paramPracticeTest(String currentCity) {
    TestData testData = new TestData();
    open("https://www.rzd.ru/");
    $(".highlight").shouldHave(text("КУПИТЬ БИЛЕТ "));
    $("#direction-from").click();
    $("[aria-label='Москва']").click();
    $("#direction-to").click();
    $("[aria-label='Санкт-Петербург']").click();
    //$("#datepicker-from").click();
    $("#datepicker-from").click();
    $(".rzd-datepicker-calendar").$("[data-week-day='5']").click();
    $("#datepicker-to").click();
    $("#datepicker-to").setValue("10012025");

    $("#rzd-search-widget").$(byText("Найти")).click();
    sleep(5000);
    $$("[data-testid='search-result-railway-card']").shouldBe(
      CollectionCondition.sizeGreaterThan(0));
    //sleep(7000);


  }

  @EnumSource(Language.class)
  @ParameterizedTest
  void rzdSiteShouldDisplayCorrectText(Language language, List<String> expectedButtons) {
    $(".locale-switch__trigger").click();
    $$("li.locale-switch__li").find(text(language.name())).click();
    $$(".menu-search-wrap").filter(visible)
      .shouldHave(texts(expectedButtons));
  }

  @AfterEach
  @Tag("SMOKE")
  void turnDown() {
    closeWebDriver();
//    Selenide.clearBrowserLocalStorage();
    Selenide.clearBrowserCookies();
  }

  @Test
  void successfulSearchTest() {


  }
}