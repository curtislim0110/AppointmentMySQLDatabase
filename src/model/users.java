package model;

public class users {

    public int user_ID;
    public String user_Name;
    public String password;

    public users(int user_ID, String user_Name, String password) {
        this.user_ID = user_ID;
        this.user_Name = user_Name;
        this.password = password;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public String getPassword() {
        return password;
    }

}

