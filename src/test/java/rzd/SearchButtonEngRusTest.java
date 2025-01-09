package rzd;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import rzd.data.Language;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SearchButtonEngRusTest {
  @Tag("SMOKE")
  @DisplayName("При смене языка Rus Eng, должно меняться верхнее меню")
  public static class ParamEnRusDisplayTest extends TestBase {


    @EnumSource(Language.class)
    @ParameterizedTest
    void rzdSiteShouldDisplayCorrectButton(Language language) {
      $(".locale-switch__trigger").click();
      $$("li.locale-switch__li").find(text(language.name())).click();
      $$("#rzd-search-widget").filter(visible)
        .shouldHave(texts(language.s));
    }
  }
}
