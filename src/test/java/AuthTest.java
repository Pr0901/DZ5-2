import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class AuthTest {


    @Test
    @DisplayName("Login with active user")
    void loginWithActiveUser() {
        open("http://localhost:9999/");
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id=login] input").setValue(registeredUser.getLogin());
        $("[data-test-id=password] input").setValue(registeredUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("h2").shouldHave(Condition.exactText("Личный кабинет")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Login with inactive user")
    void loginWithInactiveUser() {
        open("http://localhost:9999/");
        var inactiveUser = DataGenerator.Registration.getUser("active");
        $("[data-test-id=login] input").setValue(inactiveUser.getLogin());
        $("[data-test-id=password] input").setValue(inactiveUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(Condition.exactText("Ошибка!  Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Login with random login")
    void loginWithRandomLogin() {
        open("http://localhost:9999/");
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        var randomLogin = DataGenerator.getRandomLogin();
        $("[data-test-id=login] input").setValue(randomLogin);
        $("[data-test-id=password] input").setValue(registeredUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(Condition.exactText("Ошибка!  Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Login with random password")
    void loginWithRandomPassword() {
        open("http://localhost:9999/");
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        var randomPassword = DataGenerator.getRandomLogin();
        $("[data-test-id=login] input").setValue(registeredUser.getLogin());
        $("[data-test-id=password] input").setValue(randomPassword);
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(Condition.exactText("Ошибка!  Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }


    @Test
    @DisplayName("Login with blocked user")
    void loginWithBlockedUser() {
        open("http://localhost:9999/");
        var registeredUser = DataGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id=login] input").setValue(registeredUser.getLogin());
        $("[data-test-id=password] input").setValue(registeredUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(Condition.exactText("Ошибка! Пользователь заблокирован")).shouldBe(Condition.visible);
    }
}
