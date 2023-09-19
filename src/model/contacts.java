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
}
