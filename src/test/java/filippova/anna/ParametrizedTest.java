package filippova.anna;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParametrizedTest {
    @BeforeAll
    static void setBrowserSize () {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @CsvSource(value = {
            "Anna | anna@yandex.ru | Moscow, Michurinsky prospekt 12 | Moscow, proezd Shokalskogo",
            "Leonid | leonid@mail.ru | Ufa, Zhukova 2/3 street | Moscow Region, Mytishchi, Lyotnaya 30 street"
    },
    delimiter = '|'
    )

    @ParameterizedTest(name = "Заполнение формы Text Box для {0}")
    void fillFormTest(String name, String email, String currentAddress, String permanentAddress) {

        open("/text-box");

        //Заполнение формы
        $("#userName").setValue(name);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();

        // Asserts
        $("#userForm").shouldHave(text(name), text(email),
                text(currentAddress), text(permanentAddress));
    }
}
