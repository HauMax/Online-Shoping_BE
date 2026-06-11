public enum PaymentStatus {
    ORDERED("Заказ не сделан"),
    IN_PROGRESS("В процессе"),
    RECEIVED("Куплено");

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}