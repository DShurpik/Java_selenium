import basePages.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.TextBoxPage;

import static pageObjects.Navigation.ELEMENTS;
import static pageObjects.Navigation.TEXT_BOX;

public class ElementsTests extends BaseTest {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test(description = "Have used ordinary data for filling the user's data")
    public void fillingTextForm() {
        String fullName = "D S";
        String email = "ds@gmail.com";
        String currentAddress = "Minsk";
        String permanentAddress = "Minsk";

        textBoxPage.open("http://85.192.34.140:8081/");
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillFullNameField(fullName)
                .fillEmailField(email)
                .fillCurrentAddressField(currentAddress)
                .fillPermanentAddressField(permanentAddress);
        textBoxPage.clickSubmitBtn();

        Assert.assertEquals(fullName, textBoxPage.getResult().get("Name"));
        Assert.assertEquals(email, textBoxPage.getResult().get("Email"));
        Assert.assertEquals(currentAddress, textBoxPage.getResult().get("Current Address"));
        Assert.assertEquals(permanentAddress, textBoxPage.getResult().get("Permananet Address"));
    }

    @Test(description = "Have used a faker for filling the user's data")
    public void fillFieldByFaker() {
        Generator user = new Generator();

        textBoxPage.open("http://85.192.34.140:8081/");
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillFullNameField(user.getFullName())
                .fillEmailField(user.getEmail())
                .fillCurrentAddressField(user.getCurrentAddress())
                .fillPermanentAddressField(user.getPermanentAddress());
        textBoxPage.clickSubmitBtn();

        Assert.assertEquals(user.getFullName(), textBoxPage.getResult().get("Name"));
        Assert.assertEquals(user.getEmail(), textBoxPage.getResult().get("Email"));
        Assert.assertEquals(user.getCurrentAddress(), textBoxPage.getResult().get("Current Address"));
        Assert.assertEquals(user.getPermanentAddress(), textBoxPage.getResult().get("Permananet Address"));
    }
}
