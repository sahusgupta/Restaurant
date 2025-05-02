import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartPanel extends JPanel {
    private JTable cartTable;
    private DefaultTableModel tableModel;
    private JTextField tipField;
    private JTextField subtotalField;
    private JTextField taxField;
    private JTextField tipAmountField;
    private JTextField totalField;
    private List<MenuItem> allItems;
    
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color ACCENT_COLOR = new Color(100, 100, 100);
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font VALUE_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    
    public CartPanel() {
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
        allItems = new ArrayList<>();
        
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
        
        JLabel titleLabel = new JLabel("Shopping Cart", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);
        
        // Create table
        String[] columns = {"Item Name", "Quantity", "Cost", "Extended Cost"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cartTable = new JTable(tableModel);
        cartTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cartTable.setRowHeight(30);
        cartTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        cartTable.getTableHeader().setBackground(ACCENT_COLOR);
        cartTable.getTableHeader().setForeground(Color.WHITE);
        cartTable.setGridColor(new Color(220, 220, 220));
        cartTable.setShowGrid(true);
        
        JScrollPane tableScrollPane = new JScrollPane(cartTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        tableScrollPane.getViewport().setBackground(Color.WHITE);
        add(tableScrollPane, BorderLayout.CENTER);
        
        // Create receipt panel
        JPanel receiptPanel = new JPanel(new GridLayout(5, 2));
        receiptPanel.setBackground(Color.WHITE);
        receiptPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Tip input
        JLabel tipLabel = new JLabel("Tip Percentage:");
        tipLabel.setFont(LABEL_FONT);
        tipLabel.setForeground(new Color(50, 50, 50));
        receiptPanel.add(tipLabel);
        
        tipField = new JTextField();
        tipField.setFont(VALUE_FONT);
        tipField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        tipField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updateTotals(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updateTotals(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updateTotals(); }
        });
        ((javax.swing.text.AbstractDocument)tipField.getDocument()).setDocumentFilter(new javax.swing.text.DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
                if (isValidTip(newText)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws javax.swing.text.BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (isValidTip(newText)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            private boolean isValidTip(String text) {
                if (text.isEmpty()) return true;
                try {
                    double value = Double.parseDouble(text);
                    return value >= 0;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        });
        receiptPanel.add(tipField);
        
        // Subtotal
        JLabel subtotalLabel = new JLabel("Subtotal:");
        subtotalLabel.setFont(LABEL_FONT);
        subtotalLabel.setForeground(new Color(50, 50, 50));
        receiptPanel.add(subtotalLabel);
        
        subtotalField = new JTextField();
        subtotalField.setFont(VALUE_FONT);
        subtotalField.setEditable(false);
        subtotalField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        subtotalField.setBackground(Color.WHITE);
        receiptPanel.add(subtotalField);
        
        // Tax
        JLabel taxLabel = new JLabel("Tax (8.25%):");
        taxLabel.setFont(LABEL_FONT);
        taxLabel.setForeground(new Color(50, 50, 50));
        receiptPanel.add(taxLabel);
        
        taxField = new JTextField();
        taxField.setFont(VALUE_FONT);
        taxField.setEditable(false);
        taxField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        taxField.setBackground(Color.WHITE);
        receiptPanel.add(taxField);
        
        // Tip amount
        JLabel tipAmountLabel = new JLabel("Tip Amount:");
        tipAmountLabel.setFont(LABEL_FONT);
        tipAmountLabel.setForeground(new Color(50, 50, 50));
        receiptPanel.add(tipAmountLabel);
        
        tipAmountField = new JTextField();
        tipAmountField.setFont(VALUE_FONT);
        tipAmountField.setEditable(false);
        tipAmountField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tipAmountField.setBackground(Color.WHITE);
        receiptPanel.add(tipAmountField);
        
        // Total
        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setFont(LABEL_FONT);
        totalLabel.setForeground(new Color(50, 50, 50));
        receiptPanel.add(totalLabel);
        
        totalField = new JTextField();
        totalField.setFont(VALUE_FONT);
        totalField.setEditable(false);
        totalField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        totalField.setBackground(Color.WHITE);
        receiptPanel.add(totalField);
        
        // Add components to main panel
        add(receiptPanel, BorderLayout.SOUTH);
    }
    
    public void addItem(MenuItem item) {
        allItems.add(item);
        updateTable();
    }
    
    private void updateTable() {
        tableModel.setRowCount(0);
        DecimalFormat df = new DecimalFormat("#.00");
        
        for (MenuItem item : allItems) {
            if (item.getQuantity() > 0) {
                double extendedCost = item.getCost() * item.getQuantity();
                tableModel.addRow(new Object[]{
                    item.getName(),
                    item.getQuantity(),
                    "$" + df.format(item.getCost()),
                    "$" + df.format(extendedCost)
                });
            }
        }
        updateTotals();
    }
    
    private void updateTotals() {
        DecimalFormat df = new DecimalFormat("#.00");
        double subtotal = 0.0;
        
        // Calculate subtotal
        for (MenuItem item : allItems) {
            subtotal += item.getCost() * item.getQuantity();
        }
        
        // Calculate tax
        double tax = subtotal * 0.0825;
        
        // Calculate tip
        double tipPercentage = 0.0;
        try {
            String tipText = tipField.getText().trim();
            if (!tipText.isEmpty()) {
                tipPercentage = Double.parseDouble(tipText) / 100.0;
            }
        } catch (NumberFormatException e) {
            // Invalid tip percentage, treat as 0
        }
        double tipAmount = subtotal * tipPercentage;
        
        // Calculate total
        double total = subtotal + tax + tipAmount;
        
        // Update fields
        subtotalField.setText("$" + df.format(subtotal));
        taxField.setText("$" + df.format(tax));
        tipAmountField.setText("$" + df.format(tipAmount));
        totalField.setText("$" + df.format(total));
    }
}