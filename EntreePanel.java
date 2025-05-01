import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntreePanel extends MenuPanel {
    private List<MenuItem> entrees;
    
    public EntreePanel() {
        super("Entrees");
        initializeEntrees();
        displayEntrees();
    }
    
    private void initializeEntrees() {
        entrees = new ArrayList<>();
        // Add 12 unique entrees
        entrees.add(new MenuItem("Grilled Salmon", "Fresh Atlantic salmon with lemon butter sauce", 24.99, "images/salmon.jpg"));
        entrees.add(new MenuItem("Filet Mignon", "8oz prime beef with red wine reduction", 32.99, "images/filet.jpg"));
        entrees.add(new MenuItem("Chicken Parmesan", "Breaded chicken with marinara and mozzarella", 18.99, "images/chicken_parm.jpg"));
        entrees.add(new MenuItem("Shrimp Scampi", "Shrimp in garlic butter sauce over linguine", 22.99, "images/scampi.jpg"));
        entrees.add(new MenuItem("Vegetable Stir Fry", "Fresh vegetables with tofu in teriyaki sauce", 16.99, "images/stir_fry.jpg"));
        entrees.add(new MenuItem("Ribeye Steak", "12oz prime ribeye with herb butter", 29.99, "images/ribeye.jpg"));
        entrees.add(new MenuItem("Lobster Tail", "8oz Maine lobster with drawn butter", 34.99, "images/lobster.jpg"));
        entrees.add(new MenuItem("Chicken Alfredo", "Fettuccine with creamy alfredo sauce", 19.99, "images/alfredo.jpg"));
        entrees.add(new MenuItem("Beef Wellington", "Tenderloin wrapped in puff pastry", 36.99, "images/wellington.jpg"));
        entrees.add(new MenuItem("Pork Chop", "Grilled pork chop with apple chutney", 21.99, "images/pork_chop.jpg"));
        entrees.add(new MenuItem("Seafood Paella", "Spanish rice with mixed seafood", 26.99, "images/paella.jpg"));
        entrees.add(new MenuItem("Mushroom Risotto", "Creamy arborio rice with wild mushrooms", 17.99, "images/risotto.jpg"));
    }
    
    private void displayEntrees() {
        Color[] colors = {new Color(240, 240, 240), new Color(220, 220, 220)};
        int colorIndex = 0;
        
        for (MenuItem item : entrees) {
            itemsPanel.add(createItemPanel(item, colors[colorIndex]));
            colorIndex = (colorIndex + 1) % colors.length;
        }
    }
} 