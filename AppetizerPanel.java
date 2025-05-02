import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AppetizerPanel extends MenuPanel {
    private List<MenuItem> appetizers;
    
    public AppetizerPanel(CartPanel cartPanel) {
        super("Appetizers", cartPanel);
        initializeAppetizers();
        displayAppetizers();
    }
    
    private void initializeAppetizers() {
        appetizers = new ArrayList<>();
        // Add 6 unique appetizers
        appetizers.add(new MenuItem("Nachos", "Tortilla chips topped with melted cheese, refried beans, salsa, jalapenos, corn. Served with a side of guacamole.", 8.99, "nachos.jpg"));
        appetizers.add(new MenuItem("Panchos", "The XL version of our world famous nachos. Served with a side of guacamole.", 12.99, "panchos.jpg"));
        appetizers.add(new MenuItem("Breadsticks", "Crispy breadsticks with garlic butter", 6.99, "breadsticks.jpg"));
        appetizers.add(new MenuItem("Cheese Sticks", "Breaded cheese with marinara sauce", 5.99, "cheese_sticks.jpg"));
        appetizers.add(new MenuItem("Boneless Chicken Wings", "Your choice of 8 marinated wings with ranch dressing", 11.99, "wings.jpg"));
        appetizers.add(new MenuItem("Seafood Platter", "An assortment of shrimp, scallops, and calamari", 25.99, "seafood_platter.jpg"));
    }
    
    private void displayAppetizers() {
        displayItems(appetizers);
    }
} 