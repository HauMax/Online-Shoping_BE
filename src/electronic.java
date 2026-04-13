class electronic extends product {
    public electronic(String title, Double price, String description) {
        super(title, price, description);
        Catalog.incrementSubCategory();
    }
}