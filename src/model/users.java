package model;

/**
 * Class for users
 */
public class users {

    private int user_ID;
    private String user_Name;
    private String password;

    /**
     * constructor
     * @param user_ID user ID, primary key not directly used for login
     * @param user_Name user name, used for login verification
     * @param password user password, used for login verification
     */
    public users(int user_ID, String user_Name, String password) {
        this.user_ID = user_ID;
        this.user_Name = user_Name;
        this.password = password;
    }

    /**
     * getter
     * @return userID
     */
    public int getUser_ID() { return user_ID; }

    /**
     * getter
     * @return username
     */
    public String getUser_Name() { return user_Name; }

    /**
     * getter
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter
     * @param user_ID
     */
    public void setUser_ID(int user_ID) { this.user_ID = user_ID; }

    /**
     * setter
     * @param user_Name
     */
    public void setUser_Name(String user_Name) { this.user_Name = user_Name; }

    /**
     * setter
     * @param password
     */
     public void setPassword(String password) { this.password = password; }

    /**
     * this toString method overrides the default toString method, which returns user_ID instead
     * of a pointer reference to a hash code when customers are displayed in a combo box
     *
     * @return user_ID returns userID to be displayed as a string
     */
    @Override
    public String toString() {
        return (Integer.toString(user_ID));
    }

}

