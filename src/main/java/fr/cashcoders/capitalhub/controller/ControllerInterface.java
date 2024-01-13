package fr.cashcoders.capitalhub.controller;

/**
 * Interface representing a generic controller.
 * Any controller implementing this interface must provide a refresh method.
 */
public interface ControllerInterface {

    /**
     * Method to refresh data or the associated view for the controller.
     */
    void refresh();
}
