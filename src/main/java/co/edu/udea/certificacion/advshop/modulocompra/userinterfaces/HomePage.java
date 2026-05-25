package co.edu.udea.certificacion.advshop.modulocompra.userinterfaces;


import net.serenitybdd.screenplay.targets.Target;

public class HomePage {

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
