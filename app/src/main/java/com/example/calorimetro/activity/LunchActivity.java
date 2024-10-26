package com.example.calorimetro.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.calorimetro.R;
import com.example.calorimetro.adapter.FoodAdapter;
import com.example.calorimetro.databinding.ActivityLunchBinding;
import com.example.calorimetro.model.Food;

import java.util.ArrayList;
import java.util.List;

public class LunchActivity extends AppCompatActivity {

    ActivityLunchBinding binding;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLunchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // funciones de botones
        binding.btnBack.setOnClickListener(view -> {
            finish();
        });

        // Configurar el RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear lista de alimentos
        foodList = new ArrayList<>();
        foodList.add(new Food("Pechuga de pollo", 165));
        foodList.add(new Food("Arroz blanco cocido", 200));
        foodList.add(new Food("Ensalada mixta", 50));
        foodList.add(new Food("Frijoles negros", 120));
        foodList.add(new Food("Queso cheddar", 300));

        // Configurar el Adapter
        foodAdapter = new FoodAdapter(this, foodList);
        binding.recyclerView.setAdapter(foodAdapter);
    }
}