package model;

public class contacts {

    private int contactID;
    private String contactName;
    private String contactEmail;

    public contacts(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    // Getters
    public int getContactID() { return contactID; }
    public String getContactName() { return contactName; }
    public String getContactEmail() { return contactEmail; }

    // Setters
    public void setContactID(int contactID) { this.contactID = contactID; }
    public void setContactName(String contactName) { this.contactName = contactName; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    /**
     * this toString method overrides the default toString method, which returns contact name instead
     * of a pointer reference to a hash code when contacts are displayed in a combo box
     *
     * @return contactName
     */
    @Override
    public String toString() {
        return (contactName);
    }
}
