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
import com.example.calorimetro.databinding.ActivityBreakfastBinding;
import com.example.calorimetro.model.Food;

import java.util.ArrayList;
import java.util.List;

public class BreakfastActivity extends AppCompatActivity {

    ActivityBreakfastBinding binding;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBreakfastBinding.inflate(getLayoutInflater());
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
        foodList.add(new Food("Tostada de pan integral", 75));
        foodList.add(new Food("Huevo cocido", 75));
        foodList.add(new Food("Yogurt natural", 200));
        foodList.add(new Food("Plátano", 100));
        foodList.add(new Food("Avena", 300));

        // Configurar el Adapter
        foodAdapter = new FoodAdapter(this, foodList);
        binding.recyclerView.setAdapter(foodAdapter);
    }
}