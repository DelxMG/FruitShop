public class Fruit {

    private int id;
    private String name;
    private double pricePerKg;
    private int stockKg;

    public Fruit() {}

    public Fruit(int id, String name, double pricePerKg, int stockKg) {
        this.id = id;
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.stockKg = stockKg;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPricePerKg() { return pricePerKg; }
    public int getStockKg() { return stockKg; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPricePerKg(double pricePerKg) { this.pricePerKg = pricePerKg; }
    public void setStockKg(int stockKg) { this.stockKg = stockKg; }

    @Override
    public String toString() {
        return this.id + " | " + this.name + " | " + this.pricePerKg + "€ | " + this.stockKg + "kg";

    }
}
