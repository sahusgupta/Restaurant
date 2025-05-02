import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DessertPanel extends MenuPanel {
    private List<MenuItem> desserts;
    
    public DessertPanel(CartPanel cartPanel) {
        super("Desserts", cartPanel);
        initializeDesserts();
        displayDesserts();
    }
    
    private void initializeDesserts() {
        desserts = new ArrayList<>();
        // Add 4 unique desserts
        desserts.add(new MenuItem("Chocolate Lava Cake", "Warm chocolate cake with vanilla ice cream", 8.99, "lava_cake.jpg"));
        desserts.add(new MenuItem("Ice Cream Sandwich", "A classic ice cream sandwich with your choice of flavor", 7.99, "ice_cream_sandwich.jpg"));
        desserts.add(new MenuItem("Chocolate Chip Cookie", "A warm chocolate chip cookie served with vanilla ice cream", 8.99, "cookie.jpg"));
        desserts.add(new MenuItem("Fudge Heaven", "A decadent fudge with a rich chocolate flavor", 7.99, "fudge.jpg"));
    }
    
    private void displayDesserts() {
        displayItems(desserts);
    }
} 