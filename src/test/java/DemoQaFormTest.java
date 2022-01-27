import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class DemoQaFormTest extends TestBase {

    String url = "https://demoqa.com/automation-practice-form",
            name = "Jee",
            lastName = "Doe",
            email = "jee.doe@gmail.com",
            gender = "Other",
            mobile = "1234567890",
            month = "May",
            year = "1990",
            day = "30",
            subject1 = "Commerce",
            subject2 = "Arts",
            hobby = "Music",
            path = "img/1.jpg",
            picture = "1.jpg",
            address = "Roof, 5",
            state = "Rajasthan",
            city = "Jaipur";


    @Test
    void fillFormTest() {

        // Open URL
        open(url);

        // Set the contact info and gender
        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(mobile);

        // Set date of birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();

        // Set subjects and hobbies
        $("#subjectsInput").click();
        $("#subjectsInput").setValue(subject1).pressEnter();
        $("#subjectsInput").setValue(subject2).pressEnter();
        $("#hobbiesWrapper").findElement(byText(hobby)).click();

        // Upload picture
        //  $("#uploadPicture").uploadFromClasspath(path);

        // Set address, state and city info
        $("#currentAddress").setValue(address);

        $("#state").click();
        $(byText(state)).scrollTo().click();

        $("#city").click();
        $(byText(city)).scrollTo().click();

        // Submit the form
        $("#submit").click();

        // Check the final form
        $(".table-responsive").shouldHave(text(name), text(lastName), text(email), text(gender), text(mobile),
                text(month), text(year), text(day),
                text(subject1), text(subject2), text(hobby),
                /*text(picture),*/ text(address), text(state), text(city));
    }


}