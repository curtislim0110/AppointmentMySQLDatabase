package model;

public class users {

    private int user_ID;
    private String user_Name;
    private String password;

    public users(int user_ID, String user_Name, String password) {
        this.user_ID = user_ID;
        this.user_Name = user_Name;
        this.password = password;
    }

    // Getters
    public int getUser_ID() {
        return user_ID;
    }
    public String getUser_Name() {
        return user_Name;
    }
    public String getPassword() {
        return password;
    }

    // Setters
    public void setUser_ID(int user_ID) { this.user_ID = user_ID; }
    public void setUser_Name(String user_Name) { this.user_Name = user_Name; }
    public void setPassword(String password) { this.password = password; }

    /**
     * this toString method overrides the default toString method, which returns user_ID instead
     * of a pointer reference to a hash code when customers are displayed in a combo box
     *
     * @return user_ID
     */
    @Override
    public String toString() {
        return (Integer.toString(user_ID));
    }

}

