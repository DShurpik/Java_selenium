package pageObjects;

public enum Navigation {

    /** Elements list items */
    ELEMENTS ("'Elements'"),
    TEXT_BOX ("'Text Box'"),
    CHECK_BOX ("'Check Box'"),
    RADIO_BUTTON ("'Radio Button'");

    private final String item;

    Navigation(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

}
