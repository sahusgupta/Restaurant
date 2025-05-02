import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntreePanel extends MenuPanel {
    private List<MenuItem> entrees;
    
    public EntreePanel(CartPanel cartPanel) {
        super("Entrees", cartPanel);
        initializeEntrees();
        displayEntrees();
    }
    
    private void initializeEntrees() {
        entrees = new ArrayList<>();
        // Add 12 unique entrees
        entrees.add(new MenuItem("Big as Yo Face Burrito", "Its in the name, your choice of protein, veggies, and sauce", 15.99, "big_as_yo_face_burrito.jpg"));
        entrees.add(new MenuItem("Filet Mignon", "8oz prime beef with red wine reduction", 32.99, "filet_mignon.jpg"));
        entrees.add(new MenuItem("Chicken Parmesan", "Breaded chicken with marinara and mozzarella", 18.99, "chicken_parm.jpg"));
        entrees.add(new MenuItem("Fettucine Alfredo", "Authentic fettuccine with creamy alfredo sauce", 19.99, "fettucine_alfredo.jpg"));
        entrees.add(new MenuItem("Vegetable Stir Fry", "Fresh vegetables with marinated cottage cheese in szechwan sauce", 16.99, "stir_fry.jpg"));
        entrees.add(new MenuItem("Lamb Chops", "12oz prime lamb chops with herb butter and sauteed vegetables", 29.99, "lamb_chops.jpg"));
        entrees.add(new MenuItem("Lobster", "8oz Maine lobster with drawn butter", 34.99, "lobster.jpg"));
        entrees.add(new MenuItem("Tour of Italy", "Your choice of pasta, sauce, and protein. Served with beef lasagna and chicken parmesan", 48.99, "tour_of_italy.jpg"));
        entrees.add(new MenuItem("Beef Wellington", "Gordon Ramsay's classic, cooked by the master chef himself", 39.99, "wellington.jpg"));
        entrees.add(new MenuItem("Pork Chop", "Grilled pork chop with apple chutney", 21.99, "pork_chop.jpg"));
        entrees.add(new MenuItem("Seafood Tower", "Your choice of our selection of seafood stacked in a tower as tall as you.", 45.99, "seafood_tower.jpg"));
        entrees.add(new MenuItem("Chicken Tikka Masala", "Creamy chicken with a blend of spices", 17.99, "tikka_masala.jpg"));
    }
    
    private void displayEntrees() {
        displayItems(entrees);
    }
} 