package com.example.listviewfoods_fernandezrocio.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listviewfoods_fernandezrocio.R;
import com.example.listviewfoods_fernandezrocio.adapter.MealAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListMealActivity extends AppCompatActivity {

    private ListView listViewMeal;
    private MealAdapter adapter;
    private final List<String> mealList = new ArrayList<>(Arrays.asList(
            "sushi - Rollitos de arroz con pescado y vegetales frescos",
            "ramen - Fideos en caldo intenso con toppings variados",
            "pad thai - Fideos salteados con tamarindo, soja y cacahuetes",
            "spring rolls - Rollitos crujientes rellenos de verduras y carne",
            "pho - Sopa ligera de fideos de arroz y carne",
            "nasi goreng - Arroz frito especiado con huevo y verduras",
            "gyozas - Empanadillas fritas rellenas de carne y vegetales",
            "green curry - Curry verde cremoso con coco y pollo",
            "kimchi - Col fermantada picante",
            "bun cha - Cerdo a la parrilla con fideos y hierbas"
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meal);

        listViewMeal = findViewById(R.id.listViewMeal);

        loadFoodList();

        Button btnDetails = findViewById(R.id.btnDetails);
        btnDetails.setOnClickListener(v-> {
            String selectedMeal = adapter.getSelectedMeal();

            if (selectedMeal == null){
                Toast.makeText(ListMealActivity.this,
                        "Selecciona una comida",
                        Toast.LENGTH_SHORT).show();
            }

            String [] parts = selectedMeal.split("-");
            String title = parts.length > 0 ? parts[0] : "Sin título";
            String description = parts.length >1 ? parts [1] : "Sin descripción";
            String category = parts.length > 2 ? parts[2] : "Sin categoría";

            String message = "Título: " + title + "\n"
                    + "Descripción: " + description + "\n"
                    + "Categoría: " + category;
            Toast.makeText(ListMealActivity.this, message, Toast.LENGTH_LONG).show();

        });
    }

    private void loadFoodList(){
        adapter = new MealAdapter(this, mealList);
        listViewMeal.setAdapter(adapter);
    }

}