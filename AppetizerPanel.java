import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AppetizerPanel extends MenuPanel {
    private List<MenuItem> appetizers;
    
    public AppetizerPanel() {
        super("Appetizers");
        initializeAppetizers();
        displayAppetizers();
    }
    
    private void initializeAppetizers() {
        appetizers = new ArrayList<>();
        // Add 6 unique appetizers
        appetizers.add(new MenuItem("Bruschetta", "Toasted bread with tomatoes, garlic, and basil", 8.99, "images/bruschetta.jpg"));
        appetizers.add(new MenuItem("Calamari", "Crispy fried squid with marinara sauce", 12.99, "images/calamari.jpg"));
        appetizers.add(new MenuItem("Spinach Artichoke Dip", "Creamy dip with tortilla chips", 9.99, "images/spinach_dip.jpg"));
        appetizers.add(new MenuItem("Mozzarella Sticks", "Breaded mozzarella with marinara sauce", 7.99, "images/mozzarella_sticks.jpg"));
        appetizers.add(new MenuItem("Chicken Wings", "Buffalo wings with ranch dressing", 11.99, "images/wings.jpg"));
        appetizers.add(new MenuItem("Shrimp Cocktail", "Chilled shrimp with cocktail sauce", 14.99, "images/shrimp_cocktail.jpg"));
    }
    
    private void displayAppetizers() {
        Color[] colors = {new Color(240, 240, 240), new Color(220, 220, 220)};
        int colorIndex = 0;
        
        for (MenuItem item : appetizers) {
            itemsPanel.add(createItemPanel(item, colors[colorIndex]));
            colorIndex = (colorIndex + 1) % colors.length;
        }
    }
} 