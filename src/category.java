import java.util.Objects;

public abstract class category implements Comparable<category> {
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

    @Override
    public int compareTo(category o) {
        return Double.compare(this.price, o.price);
    }

    public int get_id() { return id; }
    public String get_Title() { return title; }
    public Double get_Price() { return price; }
    public String get_Description() { return description; }

    @Override
    public String toString() {
        return String.format("[%s] ID: %d, Название: %s, Цена: %.2f руб.",
                getClass().getSimpleName(), id, title, price);
    }
}
