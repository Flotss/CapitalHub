package fr.cashcoders.capitalhub.view;

import fr.cashcoders.capitalhub.model.Observable;

public interface Observer {
    void update(Observable o, Object arg);
}
