package fr.cashcoders.capitalhub.model;

import fr.cashcoders.capitalhub.view.Observer;

public interface Observable {
    void addObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}