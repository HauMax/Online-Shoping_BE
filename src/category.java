public abstract class category {
    private static int idCon = 0;
    private int id;
    private String title;
    private Double price;
    private String description;

    public category(String Title, Double Price, String Description) {
        idCon++;
        this.id = idCon;
        this.title = Title;
        this.price = Price;
        this.description = Description;
    }

    // Абстрактный метод
    public abstract void showInfo();

    // Геттеры
    public int get_id() { return id; }
    public String get_Title() { return title; }
    public Double get_Price() { return price; }
    public String get_Description() { return description; }
}
