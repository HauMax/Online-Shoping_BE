class MobileDevice extends electronic {
    public MobileDevice(String title, Double price, String description) {
        super(title, price, description);
        Catalog.incrementSubCategory();
    }
}
