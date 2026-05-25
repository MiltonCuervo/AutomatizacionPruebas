package co.edu.udea.certificacion.advshop.modulocompra.userinterfaces;


import net.serenitybdd.screenplay.targets.Target;

public class HomePage {

    public static final Target BTN_USER_PROFILE = Target.the("button to access user profile").locatedBy("//*[@id='menuUser']");
    public static final Target BTN_CREATE_NEW_ACCOUNT = Target.the("button to create a new account").locatedBy("//a[contains(text(), 'CREATE NEW ACCOUNT')]");
    public static final Target USERNAME_TEXT = Target.the("text username").locatedBy("//*[@id=\"menuUserLink\"]/span");

    public static Target categoryImage(String category) {
        return Target.the(category + " section image")
                .locatedBy("//*[@id='" + category.toLowerCase() + "Img']");
    }
}
    /*
    public static final Target IMAGE_SPEAKERS = Target.the("Speakers Section Image").locatedBy("//*[@id='speakersImg']");
    public static final Target IMAGE_HEADPHONES = Target.the("Headphones Section Image").locatedBy("//*[@id='headphonesImg']");
    public static final Target IMAGE_MICE = Target.the("Mice Section Image").locatedBy("//*[@id='miceImg']");
    public static final Target IMAGE_TABLETS = Target.the("Tablets Section Image").locatedBy("//*[@id='tabletsImg']");
    public static final Target IMAGE_LAPTOPS = Target.the("Laptops Section Image").locatedBy("//*[@id='laptopsImg']");
    */
