public class MenuItem {
    private String name;
    private String description;
    private double cost;
    private String imagePath;
    private int quantity;
    
    public MenuItem(String name, String description, double cost, String imagePath) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.imagePath = imagePath;
        this.quantity = 0;
    }
    
    // Getters and setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getCost() { return cost; }
    public String getImagePath() { return imagePath; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
} 