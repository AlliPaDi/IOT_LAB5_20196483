package com.example.calorimetro.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calorimetro.R;
import com.example.calorimetro.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // funciones de botones
        binding.btnProfile.setOnClickListener(v->{
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        });

        binding.btnNotifications.setOnClickListener(v->{
            Intent intent = new Intent(this, NotificationsActivity.class);
            startActivity(intent);
        });

        binding.btnMeals.setOnClickListener(v->{
            Intent intent = new Intent(this, MealsActivity.class);
            startActivity(intent);
        });

        binding.btnTraining.setOnClickListener(v->{
            Intent intent = new Intent(this, TrainingActivity.class);
            startActivity(intent);
        });

        // Mostrar las calorías
        showCaloriesGoal();
        showCaloriesConsumed();

    }
    private void showCaloriesGoal() {
        // Recuperar el valor de las calorías calculadas
        SharedPreferences sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        float caloriasDiarias = sharedPref.getFloat("caloriasDiarias", 0);

        // Actualizar el TextView con el valor de las calorías
        binding.tvCaloriesGoal.setText(String.format("de %.2f kcal", caloriasDiarias));
    }

    private void showCaloriesConsumed(){
        SharedPreferences sharedPref = getSharedPreferences("CaloriasComidas", Context.MODE_PRIVATE);
        int desayuno = sharedPref.getInt("desayuno", 0);
        int almuerzo = sharedPref.getInt("almuerzo", 0);
        int cena = sharedPref.getInt("cena", 0);

        int totalCalorias = desayuno + almuerzo + cena;

        // Mostrar el total de calorías en un TextView
        binding.tvCalConsumed.setText(totalCalorias + " kcal");

    }

}