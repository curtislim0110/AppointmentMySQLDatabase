package model;

/**
 * this class is used for created a sorted list of customer appointments by month and type
 */
public class reportsorted {

    private String month;
    private String type;
    private int appointmentcount;

    /**
     * constructor
     * @param month month as whole word
     * @param type type of appointment
     * @param appointmentcount count of appointments by month and type from the database
     */
    public reportsorted(String month, String type, int appointmentcount) {
        this.month = month;
        this.type = type;
        this.appointmentcount = appointmentcount;
    }

    /**
     * getter
     * @return
     */
    public String getMonth() { return month; }

    /**
     * getter
     * @return
     */
    public String getType() { return type; }

    /**
     * getter
     * @return
     */
    public int getAppointmentcount() { return appointmentcount; }

    /**
     * setter
     * @param month
     */
    public void setMonth(String month) { this.month = month; }

    /**
     * setter
     * @param type
     */
    public void setType(String type) { this.type = type; }

    /**
     * setter
     * @param appointmentcount
     */
    public void setAppointmentcount(int appointmentcount) { this.appointmentcount = appointmentcount; }
}
