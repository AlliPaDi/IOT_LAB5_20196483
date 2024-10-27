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
import com.example.calorimetro.databinding.ActivityLunchBinding;
import com.example.calorimetro.model.Food;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LunchActivity extends AppCompatActivity {

    ActivityLunchBinding binding;
    FoodAdapter foodAdapter;
    List<Food> foodList;

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

        // Verificar si ya se ha guardado una selección de almuerzo
        SharedPreferences sharedPref = getSharedPreferences("CaloriasComidas", Context.MODE_PRIVATE);
        boolean almuerzoGuardado = sharedPref.getBoolean("almuerzoGuardado", false);

        if (almuerzoGuardado) {
            // Recuperar la lista de alimentos seleccionados
            Set<String> selectedFoodsSet = sharedPref.getStringSet("almuerzoSelectedFoods", new HashSet<>());
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

        SharedPreferences sharedPref = getSharedPreferences("CaloriasComidas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("almuerzo", totalCalories);
        editor.putStringSet("almuerzoSelectedFoods", selectedFoodsSet);
        editor.putBoolean("almuerzoGuardado", true);
        editor.apply();

        Toast.makeText(this, "Calorías del almuerzo guardadas: " + totalCalories, Toast.LENGTH_SHORT).show();
    }
}