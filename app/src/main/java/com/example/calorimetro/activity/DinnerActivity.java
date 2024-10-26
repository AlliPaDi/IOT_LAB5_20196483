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
import com.example.calorimetro.databinding.ActivityDinnerBinding;
import com.example.calorimetro.model.Food;

import java.util.ArrayList;
import java.util.List;

public class DinnerActivity extends AppCompatActivity {

    ActivityDinnerBinding binding;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDinnerBinding.inflate(getLayoutInflater());
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
        foodList.add(new Food("Salmon a la parilla", 210));
        foodList.add(new Food("Pizza", 300));
        foodList.add(new Food("Quinoa cocida", 240));
        foodList.add(new Food("Hamburguesa", 400));
        foodList.add(new Food("Pasta con salsa de crema", 500));

        // Configurar el Adapter
        foodAdapter = new FoodAdapter(this, foodList);
        binding.recyclerView.setAdapter(foodAdapter);
    }
}