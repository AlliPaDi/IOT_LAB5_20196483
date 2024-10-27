package com.example.calorimetro.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BreakfastActivity extends AppCompatActivity {

    ActivityBreakfastBinding binding;
    FoodAdapter foodAdapter;
    List<Food> foodList;

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

        // Verificar si ya se ha guardado una selección de desayuno
        SharedPreferences sharedPref = getSharedPreferences("CaloriasComidas", Context.MODE_PRIVATE);
        boolean desayunoGuardado = sharedPref.getBoolean("desayunoGuardado", false);

        if (desayunoGuardado) {
            // Recuperar la lista de alimentos seleccionados
            Set<String> selectedFoodsSet = sharedPref.getStringSet("desayunoSelectedFoods", new HashSet<>());
            foodAdapter.setSelectedFoods(selectedFoodsSet);
        }

        // Guardar calorías seleccionadas
        binding.btnSave.setOnClickListener(v -> saveSelectedCalories());
    }

    private void saveSelectedCalories() {
        // Obtener las calorías totales seleccionadas
        int totalCalories = 0;
        List<Food> selectedFoods = foodAdapter.getSelectedFoods();
        Set<String> selectedFoodsSet = new HashSet<>();

        for (Food food : selectedFoods) {
            totalCalories += food.getCalories();
            selectedFoodsSet.add(food.getName());
        }

        // Guardar las calorías totales y los alimentos seleccionados
        SharedPreferences sharedPref = getSharedPreferences("CaloriasComidas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("desayuno", totalCalories);
        editor.putStringSet("desayunoSelectedFoods", selectedFoodsSet);
        editor.putBoolean("desayunoGuardado", true);
        editor.apply();

        Toast.makeText(this, "Calorías del desayuno guardadas: " + totalCalories, Toast.LENGTH_SHORT).show();

    }
}