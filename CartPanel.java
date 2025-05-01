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
    
    public CartPanel() {
        setLayout(new BorderLayout());
        allItems = new ArrayList<>();
        
        // Create table
        String[] columns = {"Item Name", "Quantity", "Cost", "Extended Cost"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cartTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(cartTable);
        
        // Create receipt panel
        JPanel receiptPanel = new JPanel(new GridLayout(5, 2));
        receiptPanel.setBorder(BorderFactory.createTitledBorder("Receipt"));
        
        // Tip input
        receiptPanel.add(new JLabel("Tip Percentage:"));
        tipField = new JTextField();
        tipField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updateTotals(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updateTotals(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updateTotals(); }
        });
        receiptPanel.add(tipField);
        
        // Subtotal
        receiptPanel.add(new JLabel("Subtotal:"));
        subtotalField = new JTextField();
        subtotalField.setEditable(false);
        receiptPanel.add(subtotalField);
        
        // Tax
        receiptPanel.add(new JLabel("Tax (8.25%):"));
        taxField = new JTextField();
        taxField.setEditable(false);
        receiptPanel.add(taxField);
        
        // Tip amount
        receiptPanel.add(new JLabel("Tip Amount:"));
        tipAmountField = new JTextField();
        tipAmountField.setEditable(false);
        receiptPanel.add(tipAmountField);
        
        // Total
        receiptPanel.add(new JLabel("Total:"));
        totalField = new JTextField();
        totalField.setEditable(false);
        receiptPanel.add(totalField);
        
        // Add components to main panel
        add(tableScrollPane, BorderLayout.CENTER);
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