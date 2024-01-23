package model;

import javafx.beans.property.IntegerProperty;

/**
 * The AppointmentReport class represents a report on appointments, including information about the month, type, and total number of appointments.
 */
public class AppointmentReport {
    /**
     * The month for which the report is generated.
     */
    private String month;

    /**
     * The type of appointments included in the report.
     */
    private String type;

    /**
     * The total number of appointments for the specified month and type.
     */
    private IntegerProperty totalAppointments;

    /**
     * Constructs an AppointmentReport with the specified month, type, and total number of appointments.
     *
     * @param month             The month for which the report is generated.
     * @param type              The type of appointments included in the report.
     * @param totalAppointments The total number of appointments for the specified month and type.
     */
    public AppointmentReport(String month, String type, IntegerProperty totalAppointments) {
        this.month = month;
        this.type = type;
        this.totalAppointments = totalAppointments;
    }
    /**
     * Gets the month for which the report is generated.
     *
     * @return The month of the report.
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets the month for the report.
     *
     * @param month The month to set.
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * Gets the type of appointments included in the report.
     *
     * @return The type of appointments.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of appointments for the report.
     *
     * @param type The type of appointments to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the total number of appointments for the specified month and type.
     *
     * @return The total number of appointments.
     */
    public IntegerProperty getTotalAppointments() {
        return totalAppointments;
    }

    /**
     * Sets the total number of appointments for the specified month and type.
     *
     * @param totalAppointments The total number of appointments to set.
     */
    public void setTotalAppointments(IntegerProperty totalAppointments) {
        this.totalAppointments = totalAppointments;
    }


}
