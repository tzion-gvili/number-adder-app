package com.example.numberadder;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber;
    private Button btnAdd;
    private Button btnClear;
    private LinearLayout itemsContainer;
    private TextView textViewCount;
    private TextView textViewTotal;
    
    private List<Double> numbersList;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextNumber = findViewById(R.id.editTextNumber);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        itemsContainer = findViewById(R.id.itemsContainer);
        textViewCount = findViewById(R.id.textViewCount);
        textViewTotal = findViewById(R.id.textViewTotal);

        // Initialize list and formatter
        numbersList = new ArrayList<>();
        decimalFormat = new DecimalFormat("#,##0.00");

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

    private void addNumber() {
        String input = editTextNumber.getText().toString().trim();

        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double number = Double.parseDouble(input);
            numbersList.add(number);
            
            // Add item to the list view
            addItemToView(number, numbersList.size());
            
            // Clear input field
            editTextNumber.setText("");
            editTextNumber.requestFocus();
            
            // Update summary
            updateSummary();
            
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
        }
    }

    private void addItemToView(double number, int position) {
        // Create a TextView for each item
        TextView itemView = new TextView(this);
        itemView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        
        itemView.setText(String.format("%d. %s", position, decimalFormat.format(number)));
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
        int count = numbersList.size();
        double total = 0.0;
        
        for (Double number : numbersList) {
            total += number;
        }
        
        textViewCount.setText(String.format("Total Items: %d", count));
        textViewTotal.setText(String.format("Total Sum: %s", decimalFormat.format(total)));
    }

    private void clearAll() {
        if (numbersList.isEmpty()) {
            Toast.makeText(this, "List is already empty", Toast.LENGTH_SHORT).show();
            return;
        }
        
        numbersList.clear();
        itemsContainer.removeAllViews();
        updateSummary();
        Toast.makeText(this, "All items cleared", Toast.LENGTH_SHORT).show();
    }
}

