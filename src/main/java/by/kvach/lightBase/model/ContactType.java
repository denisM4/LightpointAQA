package by.kvach.lightBase.model;

public enum ContactType {

    PHONE("Телефон"),
    EMAIL("Email"),
    SKYPE("Skype"),
    LINKEDIN("LinkedIn"),
    TELEGRAM("Telegram");

    private String contactType;

    ContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactType() {
        return contactType;
    }

}
