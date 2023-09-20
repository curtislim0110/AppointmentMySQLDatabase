package model;

public class reportsorted {

    private String month;
    private String type;
    private int appointmentcount;

    public reportsorted(String month, String type, int appointmentcount) {
        this.month = month;
        this.type = type;
        this.appointmentcount = appointmentcount;
    }

    // Getters
    public String getMonth() { return month; }
    public String getType() { return type; }
    public int getAppointmentcount() { return appointmentcount; }

    // Setters
    public void setMonth(String month) { this.month = month; }
    public void setType(String type) { this.type = type; }
    public void setAppointmentcount(int appointmentcount) { this.appointmentcount = appointmentcount; }
}
