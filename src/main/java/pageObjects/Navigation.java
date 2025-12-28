package pageObjects;

public enum Navigation {

    /** Elements list items */
    ELEMENTS ("'Elements'"),
    ALERTS ("'Alerts, Frame & Windows'"),
    FORMS ("'Forms'"),
    TEXT_BOX ("'Text Box'"),
    BROWSER_WINDOWS ("'Browser Windows'"),
    ALERTS_MENU ("'Alerts'"),
    CHECK_BOX ("'Check Box'"),
    RADIO_BUTTON ("'Radio Button'"),
    WEB_TABLES ("'Web Tables'"),
    BUTTONS ("'Buttons'"),
    LINKS ("'Links'"),
    BROKEN_LINKS ("'Broken Links - Images'"),
    DYNAMIC_PROPERTIES ("'Dynamic Properties'"),
    PRACTICE_FORM ("'Practice Form'"),
    FRAMES ("'Frames'"),
    MODAL_DIALOGS ("'Modal Dialogs'"),
    ACCORDION ("'Accordian'"),
    WIDGETS ("'Widgets'"),
    AUTO_COMPLETE ("'Auto Complete'"),
    DATE_PICKER ("'Date Picker'"),;

    private final String item;

    Navigation(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

}
