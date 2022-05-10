package filippova.anna;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

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

    @Disabled
    @ParameterizedTest(name = "Заполнение формы Text Box для {0} с помощью аннотации CsvSource")
    void fillFormTestCsvSource(String name, String email, String currentAddress, String permanentAddress) {

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

    @ValueSource (strings = {
            "Anna",
            "Leonid"
    })

    @Disabled
    @ParameterizedTest(name= "Заполнение формы Text Box для {0} с помощью аннотации ValueSource")
    void fillFormTestValueSource(String name) {
        open("/text-box");

        //Заполнение формы
        $("#userName").setValue(name);
        $("#userEmail").setValue("email@mail.ru");
        $("#currentAddress").setValue("Current address");
        $("#permanentAddress").setValue("permanent Address");
        $("#submit").click();

        // Asserts
        $("#userForm").shouldHave(text(name), text("email@mail.ru"),
                text("Current address"), text("permanent Address"));
    }

    static Stream<Arguments> fillFormTestMethodSource() {
        return Stream.of(
                Arguments.of("Anna", "anna@yandex.ru", "Moscow, Michurinsky prospekt 12", "Moscow, proezd Shokalskogo"),
                Arguments.of("Leonid", "leonid@mail.ru", "Ufa, Zhukova 2/3 street", "Moscow Region, Mytishchi, Lyotnaya 30 street")
        );
    }

    @MethodSource("fillFormTestMethodSource")
    @ParameterizedTest(name = "Заполнение формы Text Box для {0} с помощью аннотации MethodSource")
    void fillFormTestValueSource(String name, String email, String currentAddress, String permanentAddress) {
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


