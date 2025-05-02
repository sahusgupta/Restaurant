import javax.swing.*;
import java.awt.*;

public class Restaurant {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private CartPanel cartPanel;
    
    public Restaurant() {
        frame = new JFrame("Restaurant Ordering System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Create cart panel first
        cartPanel = new CartPanel();
        
        // Create and add different panels
        mainPanel.add(new AppetizerPanel(cartPanel), "APPETIZERS");
        mainPanel.add(new EntreePanel(cartPanel), "ENTREES");
        mainPanel.add(new DessertPanel(cartPanel), "DESSERTS");
        mainPanel.add(cartPanel, "CART");
        
        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        
        JMenuItem appetizerItem = new JMenuItem("Appetizers");
        JMenuItem entreeItem = new JMenuItem("Entrees");
        JMenuItem dessertItem = new JMenuItem("Desserts");
        JMenuItem cartItem = new JMenuItem("View Cart");
        
        // Add action listeners to menu items
        appetizerItem.addActionListener(e -> cardLayout.show(mainPanel, "APPETIZERS"));
        entreeItem.addActionListener(e -> cardLayout.show(mainPanel, "ENTREES"));
        dessertItem.addActionListener(e -> cardLayout.show(mainPanel, "DESSERTS"));
        cartItem.addActionListener(e -> cardLayout.show(mainPanel, "CART"));
        
        menu.add(appetizerItem);
        menu.add(entreeItem);
        menu.add(dessertItem);
        menu.add(cartItem);
        menuBar.add(menu);
        
        frame.setJMenuBar(menuBar);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Restaurant());
    }
} 