package rzd;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.ValueSources;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CsvFileTest {

  @Tag("Web")
  @CsvFileSource(resources = "/test_data/searchTerminal.csv")
  @ParameterizedTest(name="Для поискового запроса {0} должен отображаться результат {1}")
  void searchTerminalTest(String searchValue, String expectedResult){
    open("https://www.rzd.ru/");
    $("[aria-label='ИСКАТЬ']").click();
    $("input.menu-search-input.j-search-input").setValue(searchValue).pressEnter();
    $("a.search-results__heading").shouldHave(attributeMatching("href", expectedResult));
  }
}
