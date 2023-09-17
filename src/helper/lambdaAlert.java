package helper;

/**
 * Interface for Lambda Expression used in CustomersController.java
 * <p> This Lambda Expression reduces code clutter for error messages, and could be implemented
 * in multiple classes as needed for different types of errors</p>
 * @param errornumber ia used to select between different types of input errors
 */
public interface lambdaAlert {
    void lambdaAlertMethod(int alertNumber);
}
