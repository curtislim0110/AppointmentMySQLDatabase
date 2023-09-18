package helper;

/**
 * Interface for Lambda Expression used in CustomersController.java
 * <p> This Lambda Expression reduces code clutter for error messages, and could be implemented
 * in multiple classes as needed for different types of alerts and errors</p>
 * @param alertNumber ia used to select between different types of alerts
 */
public interface lambdaAlert {
    void lambdaAlertMethod(int alertNumber);
}
