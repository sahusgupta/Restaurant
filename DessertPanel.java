import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DessertPanel extends MenuPanel {
    private List<MenuItem> desserts;
    
    public DessertPanel() {
        super("Desserts");
        initializeDesserts();
        displayDesserts();
    }
    
    private void initializeDesserts() {
        desserts = new ArrayList<>();
        // Add 4 unique desserts
        desserts.add(new MenuItem("Chocolate Lava Cake", "Warm chocolate cake with vanilla ice cream", 8.99, "images/lava_cake.jpg"));
        desserts.add(new MenuItem("New York Cheesecake", "Classic cheesecake with berry compote", 7.99, "images/cheesecake.jpg"));
        desserts.add(new MenuItem("Tiramisu", "Italian coffee-flavored dessert", 8.99, "images/tiramisu.jpg"));
        desserts.add(new MenuItem("Crème Brûlée", "Vanilla custard with caramelized sugar", 7.99, "images/creme_brulee.jpg"));
    }
    
    private void displayDesserts() {
        Color[] colors = {new Color(240, 240, 240), new Color(220, 220, 220)};
        int colorIndex = 0;
        
        for (MenuItem item : desserts) {
            itemsPanel.add(createItemPanel(item, colors[colorIndex]));
            colorIndex = (colorIndex + 1) % colors.length;
        }
    }
} 