import javax.swing.*;
import java.awt.*;

public abstract class MenuPanel extends JPanel {
    protected JPanel itemsPanel;
    protected JScrollPane scrollPane;
    
    public MenuPanel(String title) {
        setLayout(new BorderLayout());
        
        // Create title
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        
        // Create scrollable panel for items
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        
        scrollPane = new JScrollPane(itemsPanel);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    protected JPanel createItemPanel(MenuItem item, Color bgColor) {
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.setBackground(bgColor);
        itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add image
        ImageIcon icon = new ImageIcon(item.getImagePath());
        JLabel imageLabel = new JLabel(icon);
        itemPanel.add(imageLabel, BorderLayout.WEST);
        
        // Create center panel for text
        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        centerPanel.setBackground(bgColor);
        
        JLabel nameLabel = new JLabel(item.getName());
        JLabel descLabel = new JLabel(item.getDescription());
        JLabel costLabel = new JLabel(String.format("$%.2f", item.getCost()));
        
        centerPanel.add(nameLabel);
        centerPanel.add(descLabel);
        centerPanel.add(costLabel);
        
        itemPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Create right panel for quantity controls
        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
        rightPanel.setBackground(bgColor);
        
        JLabel quantityLabel = new JLabel("Quantity: " + item.getQuantity());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bgColor);
        
        JButton addButton = new JButton("+");
        JButton removeButton = new JButton("-");
        removeButton.setEnabled(false);
        
        addButton.addActionListener(e -> {
            item.setQuantity(item.getQuantity() + 1);
            quantityLabel.setText("Quantity: " + item.getQuantity());
            removeButton.setEnabled(true);
        });
        
        removeButton.addActionListener(e -> {
            if (item.getQuantity() > 0) {
                item.setQuantity(item.getQuantity() - 1);
                quantityLabel.setText("Quantity: " + item.getQuantity());
                removeButton.setEnabled(item.getQuantity() > 0);
            }
        });
        
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        
        rightPanel.add(quantityLabel);
        rightPanel.add(buttonPanel);
        
        itemPanel.add(rightPanel, BorderLayout.EAST);
        
        return itemPanel;
    }
} 