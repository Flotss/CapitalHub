package fr.cashcoders.capitalhub.model;

public enum TransactionType {
    BUY, SELL;

    public static TransactionType fromString(String type) {
        if (type.equals("BUY")) {
            return BUY;
        } else if (type.equals("SELL")) {
            return SELL;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return this.name();
    }
}
