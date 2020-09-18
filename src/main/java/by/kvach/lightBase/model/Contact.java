package by.kvach.lightBase.model;

public class Contact {

    public Contact(ContactType type, String value, boolean primary) {
        this.type = type;
        this.value = value;
        this.primary = primary;
    }

    public Contact(ContactType type, String value) {
        this.type = type;
        this.value = value;
        this.primary = true;
    }

    private ContactType type;
    private String value;
    private boolean primary;

    public String getType() {
        return type.getContactType();
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

}
