package fr.cashcoders.capitalhub.view;

/**
 * Interface representing an observer pattern for updating objects.
 * Classes implementing this interface are expected to provide an 'update' method.
 */
public interface Observer {

    /**
     * Method for updating the implementing object.
     */
    void update();
}
