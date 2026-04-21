import java.util.Objects;

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

    public abstract void showInfo();

    // Геттеры
    public int get_id() { return id; }
    public String get_Title() { return title; }
    public Double get_Price() { return price; }
    public String get_Description() { return description; }

    @Override
    public String toString() {
        return String.format("[%s] ID: %d, Название: %s, Цена: %.2f",
                getClass().getSimpleName(), id, title, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof category)) return false;
        category category = (category) o;
        return Objects.equals(title, category.title) && Objects.equals(price, category.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price);
    }
}