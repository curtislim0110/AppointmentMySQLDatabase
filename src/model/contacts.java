package model;

/**
 * class for contacts, each appointment has at least one contact who is a person
 */
public class contacts {

    private int contactID;
    private String contactName;
    private String contactEmail;

    /**
     * constructor
     * @param contactID contactID
     * @param contactName contact name
     * @param contactEmail contact email
     */
    public contacts(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * getter
     * @return
     */
    public int getContactID() { return contactID; }

    /**
     * getter
     * @return
     */
    public String getContactName() { return contactName; }

    /**
     * getter
     * @return
     */
    public String getContactEmail() { return contactEmail; }

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
