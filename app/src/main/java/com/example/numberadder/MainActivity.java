package com.example.numberadder;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber;
    private Button btnAdd;
    private Button btnClear;
    private Spinner spinnerProducts;
    private LinearLayout itemsContainer;
    private TextView textViewCount;
    private TextView textViewTotal;
    
    private List<Item> itemsList;
    private List<Product> productsList;
    private DecimalFormat decimalFormat;
    private boolean isSpinnerInitialized = false;
    private boolean isResettingSpinner = false;
    
    // Product class to hold product data
    private static class Product {
        String barcode;
        String productname;
        double productprice;
        
        Product(String barcode, String productname, double productprice) {
            this.barcode = barcode;
            this.productname = productname;
            this.productprice = productprice;
        }
        
        @Override
        public String toString() {
            return productname;
        }
    }
    
    // Item class to hold either a product or a number
    private static class Item {
        String productName;
        double price;
        boolean isProduct;
        
        Item(String productName, double price, boolean isProduct) {
            this.productName = productName;
            this.price = price;
            this.isProduct = isProduct;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextNumber = findViewById(R.id.editTextNumber);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        spinnerProducts = findViewById(R.id.spinnerProducts);
        itemsContainer = findViewById(R.id.itemsContainer);
        textViewCount = findViewById(R.id.textViewCount);
        textViewTotal = findViewById(R.id.textViewTotal);

        // Initialize lists and formatter
        itemsList = new ArrayList<>();
        productsList = new ArrayList<>();
        decimalFormat = new DecimalFormat("#,##0.00");
        
        // Load products from JSON
        loadProductsFromJson();
        
        // Setup spinner
        setupProductSpinner();
        
        // Mark spinner as initialized after setup
        isSpinnerInitialized = true;

        // Add button click listener
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber();
            }
        });

        // Clear button click listener
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });

        // Allow Enter key to add number
        editTextNumber.setOnEditorActionListener((v, actionId, event) -> {
            addNumber();
            return true;
        });

        updateSummary();
    }
    
    private void loadProductsFromJson() {
        try {
            InputStream is = getAssets().open("products.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject productObj = jsonArray.getJSONObject(i);
                String barcode = productObj.getString("barcode");
                String productname = productObj.getString("productname");
                double productprice = productObj.getDouble("productprice");
                
                productsList.add(new Product(barcode, productname, productprice));
            }
            
            // Debug: Show how many products were loaded
            android.util.Log.d("MainActivity", "Loaded " + productsList.size() + " products from JSON");
            
            // Show toast with product count for debugging
            runOnUiThread(() -> {
                if (productsList.isEmpty()) {
                    Toast.makeText(this, "⚠️ No products found in JSON", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "✅ Loaded " + productsList.size() + " products", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            String errorMsg = "Error loading products: " + e.getMessage();
            android.util.Log.e("MainActivity", errorMsg, e);
            e.printStackTrace();
            
            // Show error on UI thread
            runOnUiThread(() -> {
                Toast.makeText(this, "❌ Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            });
        }
    }
    
    private void setupProductSpinner() {
        // Check if products were loaded
        if (productsList.isEmpty()) {
            android.util.Log.w("MainActivity", "Products list is empty, cannot setup spinner");
            Toast.makeText(this, "No products available. Check JSON file.", Toast.LENGTH_LONG).show();
            
            // Create empty adapter with placeholder
            List<String> emptyList = new ArrayList<>();
            emptyList.add("No products available");
            ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_spinner_item, emptyList);
            emptyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerProducts.setAdapter(emptyAdapter);
            spinnerProducts.setEnabled(false);
            return;
        }
        
        // Create a simple list of product names for the spinner
        List<String> productNames = new ArrayList<>();
        productNames.add("-- Select Product --"); // Placeholder at position 0
        for (Product product : productsList) {
            productNames.add(product.productname);
        }
        
        // Create adapter with product names
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 
            android.R.layout.simple_spinner_item, productNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProducts.setAdapter(adapter);
        spinnerProducts.setSelection(0); // Select placeholder by default
        
        android.util.Log.d("MainActivity", "Spinner setup with " + productsList.size() + " products");
        
        // Handle product selection
        spinnerProducts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Skip placeholder (position 0) and only add if spinner is initialized
                if (isSpinnerInitialized && !isResettingSpinner && position > 0 && position <= productsList.size()) {
                    Product selectedProduct = productsList.get(position - 1); // -1 because of placeholder
                    // Automatically add product to the list
                    addProductToList(selectedProduct);
                }
            }
            
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }
    
    private void addProductToList(Product product) {
        // Create item with product name and price
        Item item = new Item(product.productname, product.productprice, true);
        itemsList.add(item);
        
        // Add item to the view
        addItemToView(item, itemsList.size());
        
        // Update summary
        updateSummary();
        
        // Show feedback
        Toast.makeText(this, "Added: " + product.productname, Toast.LENGTH_SHORT).show();
        
        // Reset spinner to placeholder (position 0) to allow selecting the same product again
        isResettingSpinner = true;
        spinnerProducts.setSelection(0);
        isResettingSpinner = false;
    }

    private void addNumber() {
        String input = editTextNumber.getText().toString().trim();

        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double number = Double.parseDouble(input);
            // Create item with null product name (free number)
            Item item = new Item(null, number, false);
            itemsList.add(item);
            
            // Add item to the list view
            addItemToView(item, itemsList.size());
            
            // Clear input field
            editTextNumber.setText("");
            editTextNumber.requestFocus();
            
            // Update summary
            updateSummary();
            
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
        }
    }

    private void addItemToView(Item item, int position) {
        // Create a TextView for each item
        TextView itemView = new TextView(this);
        itemView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        
        // Format text based on whether it's a product or free number
        String displayText;
        if (item.isProduct && item.productName != null) {
            // Product: show name and price
            displayText = String.format("%d. %s - %s", position, item.productName, decimalFormat.format(item.price));
        } else {
            // Free number: show just the number
            displayText = String.format("%d. %s", position, decimalFormat.format(item.price));
        }
        
        itemView.setText(displayText);
        itemView.setTextSize(16);
        itemView.setPadding(16, 12, 16, 12);
        itemView.setBackgroundResource(android.R.drawable.list_selector_background);
        
        // Alternate background colors for better visibility
        if (position % 2 == 0) {
            itemView.setBackgroundColor(0xFFF5F5F5);
        } else {
            itemView.setBackgroundColor(0xFFFFFFFF);
        }
        
        itemsContainer.addView(itemView);
    }

    private void updateSummary() {
        int count = itemsList.size();
        double total = 0.0;
        
        for (Item item : itemsList) {
            total += item.price;
        }
        
        textViewCount.setText(String.format("Total Items: %d", count));
        textViewTotal.setText(String.format("Total Sum: %s", decimalFormat.format(total)));
    }

    private void clearAll() {
        if (itemsList.isEmpty()) {
            Toast.makeText(this, "List is already empty", Toast.LENGTH_SHORT).show();
            return;
        }
        
        itemsList.clear();
        itemsContainer.removeAllViews();
        updateSummary();
        Toast.makeText(this, "All items cleared", Toast.LENGTH_SHORT).show();
    }
}

