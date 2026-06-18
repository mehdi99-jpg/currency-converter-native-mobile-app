package com.example.devise;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override   
    protected void onCreate(Bundle savedInstanceState) {        
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.activity_main);
        
        // 1. Reference XML UI components
        EditText editTextAmount = findViewById(R.id.editTextAmount);        
        Button btnCompute = findViewById(R.id.btnCompute);        
        TextView textViewResult = findViewById(R.id.textViewResult);        
        ListView listViewResult = findViewById(R.id.listViewResults);        
        
        // 2. Setup History Log list and adapter
        List<String> data = new ArrayList<>();        
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listViewResult.setAdapter(stringArrayAdapter);
        
        // 3. Set the Click Listener to handle calculations
        btnCompute.setOnClickListener((view) -> {           
            String amountStr = editTextAmount.getText().toString().trim();
            
            if (amountStr.isEmpty()) {
                Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                return;
            }
            
            try {
                double amount = Double.parseDouble(amountStr);
                double result = amount * 11.0; // Conversion factor (1 € = 11 MAD)          
                
                // Update results display
                textViewResult.setText(String.format("%.2f MAD", result));           
                
                // Add to list and notify UI list view to refresh
                data.add(String.format("%.2f € => %.2f MAD", amount, result));         
                stringArrayAdapter.notifyDataSetChanged();          
                
                // Clear input field for next conversion
                editTextAmount.setText("");
                
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid number entered", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
