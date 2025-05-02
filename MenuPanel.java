import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class MenuPanel extends JPanel {
    protected JPanel itemsPanel;
    protected JScrollPane scrollPane;
    protected CartPanel cartPanel;
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color ITEM_BACKGROUND = new Color(255, 255, 255);
    private static final Color ACCENT_COLOR = new Color(100, 100, 100);
    private static final Color TITLE_COLOR = new Color(80, 80, 80);
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font ITEM_NAME_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font DESCRIPTION_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font PRICE_FONT = new Font("Segoe UI", Font.BOLD, 14);
    
    public MenuPanel(String title, CartPanel cartPanel) {
        this.cartPanel = cartPanel;
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
        
        // Create title panel with gradient
        JPanel titlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(100, 100, 100);
                Color color2 = new Color(150, 150, 150);
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(0, 60));
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);
        
        // Create scrollable panel for items
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(BACKGROUND_COLOR);
        
        scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    protected JPanel createItemPanel(MenuItem item, Color bgColor) {
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.setBackground(bgColor);
        itemPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Create image panel with padding and shadow effect
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(bgColor);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        
        // Add image with rounded corners
        try {
            String imagePath = item.getImagePath();
            // Check if the path already includes "images/"
            if (!imagePath.startsWith("images/")) {
                imagePath = "images/" + imagePath;
            }
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                // Try to read the image with different formats
                BufferedImage originalImage = null;
                try {
                    originalImage = ImageIO.read(imageFile);
                } catch (Exception e) {
                    System.err.println("Failed to read image with default format: " + imagePath);
                    // Try alternative formats
                    String[] formats = {"jpg", "jpeg", "png", "gif"};
                    for (String format : formats) {
                        try {
                            originalImage = ImageIO.read(new File(imagePath.replace(".jpg", "." + format)));
                            if (originalImage != null) break;
                        } catch (Exception ex) {
                            // Continue to next format
                        }
                    }
                }
                
                if (originalImage != null) {
                    Image scaledImage = originalImage.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(scaledImage);
                    JLabel imageLabel = new JLabel(icon) {
                        @Override
                        protected void paintComponent(Graphics g) {
                            Graphics2D g2 = (Graphics2D) g;
                            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                            g2.setColor(getBackground());
                            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                            super.paintComponent(g);
                        }
                    };
                    imageLabel.setOpaque(false);
                    imagePanel.add(imageLabel, BorderLayout.CENTER);
                } else {
                    throw new IOException("Failed to read image file in any supported format: " + imagePath);
                }
            } else {
                throw new IOException("Image file not found: " + imagePath);
            }
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            // Create a placeholder panel with a grey background
            JPanel placeholderPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(240, 240, 240));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                }
            };
            placeholderPanel.setPreferredSize(new Dimension(70, 70));
            placeholderPanel.setBackground(bgColor);
            JLabel placeholder = new JLabel("No Image");
            placeholder.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            placeholder.setForeground(new Color(150, 150, 150));
            placeholder.setHorizontalAlignment(SwingConstants.CENTER);
            placeholderPanel.add(placeholder);
            imagePanel.add(placeholderPanel, BorderLayout.CENTER);
        }
        
        itemPanel.add(imagePanel, BorderLayout.WEST);
        
        // Create center panel for text
        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        centerPanel.setBackground(bgColor);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        
        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(ITEM_NAME_FONT);
        nameLabel.setForeground(new Color(50, 50, 50));
        nameLabel.setBackground(bgColor);
        
        JLabel descLabel = new JLabel(item.getDescription());
        descLabel.setFont(DESCRIPTION_FONT);
        descLabel.setForeground(new Color(100, 100, 100));
        descLabel.setBackground(bgColor);
        
        JLabel costLabel = new JLabel(String.format("$%.2f", item.getCost()));
        costLabel.setFont(PRICE_FONT);
        costLabel.setForeground(ACCENT_COLOR);
        costLabel.setBackground(bgColor);
        
        centerPanel.add(nameLabel);
        centerPanel.add(descLabel);
        centerPanel.add(costLabel);
        
        itemPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Create right panel for quantity controls
        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
        rightPanel.setBackground(bgColor);
        
        JLabel quantityLabel = new JLabel("Quantity: " + item.getQuantity());
        quantityLabel.setFont(DESCRIPTION_FONT);
        quantityLabel.setForeground(new Color(100, 100, 100));
        quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quantityLabel.setBackground(bgColor);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bgColor);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        
        JButton addButton = new JButton("+");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        addButton.setBackground(ACCENT_COLOR);
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);
        addButton.setPreferredSize(new Dimension(50, 35));
        addButton.setMinimumSize(new Dimension(50, 35));
        addButton.setMaximumSize(new Dimension(50, 35));
        
        JButton removeButton = new JButton("-");
        removeButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        removeButton.setBackground(new Color(200, 200, 200));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFocusPainted(false);
        removeButton.setBorderPainted(false);
        removeButton.setPreferredSize(new Dimension(50, 35));
        removeButton.setMinimumSize(new Dimension(50, 35));
        removeButton.setMaximumSize(new Dimension(50, 35));
        removeButton.setEnabled(false);
        
        addButton.addActionListener(e -> {
            item.setQuantity(item.getQuantity() + 1);
            quantityLabel.setText("Quantity: " + item.getQuantity());
            removeButton.setEnabled(true);
            cartPanel.addItem(item);
        });
        
        removeButton.addActionListener(e -> {
            if (item.getQuantity() > 0) {
                item.setQuantity(item.getQuantity() - 1);
                quantityLabel.setText("Quantity: " + item.getQuantity());
                removeButton.setEnabled(item.getQuantity() > 0);
                cartPanel.addItem(item);
            }
        });
        
        buttonPanel.add(removeButton);
        buttonPanel.add(addButton);
        
        rightPanel.add(quantityLabel);
        rightPanel.add(buttonPanel);
        
        itemPanel.add(rightPanel, BorderLayout.EAST);
        
        return itemPanel;
    }

    protected void displayItems(List<MenuItem> items) {
        Color[] colors = {Color.WHITE, new Color(245, 245, 245)};
        int colorIndex = 0;
        
        for (MenuItem item : items) {
            JPanel itemPanel = createItemPanel(item, colors[colorIndex]);
            itemPanel.setBackground(colors[colorIndex]);
            itemsPanel.add(itemPanel);
            colorIndex = (colorIndex + 1) % colors.length;
        }
    }
} 