package pageObjects;

public enum Navigation {

    /** Elements list items */
    ELEMENTS ("'Elements'"),
    TEXT_BOX ("'Text Box'"),
    CHECK_BOX ("'Check Box'"),
    RADIO_BUTTON ("'Radio Button'"),
    WEB_TABLES ("'Web Tables'"),
    BUTTONS ("'Buttons'"),
    LINKS ("'Links'");

    private final String item;

    Navigation(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

}
