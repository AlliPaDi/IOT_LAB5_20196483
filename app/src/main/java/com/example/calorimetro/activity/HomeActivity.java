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
import com.example.calorimetro.model.ActividadFisica;

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
        updateConsumedCalories();
        showCaloriesFinal();
        showCaloriesFaltantes();

    }

    private void updateCaloriesBurned() {
        SharedPreferences sharedPref = getSharedPreferences("CaloriasQuemadas", Context.MODE_PRIVATE);
        float totalCalorias = sharedPref.getFloat("total_calorias", 0f);
        String caloriesText = String.format("%.2f kcal", totalCalorias);
        binding.tvCalBurned.setText(caloriesText);
    }

    // Función para obtener las calorías diarias (Goal)
    private float getCaloriasDiarias() {
        SharedPreferences sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        return sharedPref.getFloat("caloriasDiarias", 0);
    }

    // Función para obtener las calorías quemadas
    private float getCaloriasQuemadas() {
        SharedPreferences sharedPref = getSharedPreferences("CaloriasQuemadas", Context.MODE_PRIVATE);
        return sharedPref.getFloat("total_calorias", 0);
    }

    // Función para obtener el total de calorías consumidas (desayuno, almuerzo, cena)
    private int getCaloriasConsumidas() {
        SharedPreferences sharedPref = getSharedPreferences("CaloriasComidas", Context.MODE_PRIVATE);
        int desayuno = sharedPref.getInt("desayuno", 0);
        int almuerzo = sharedPref.getInt("almuerzo", 0);
        int cena = sharedPref.getInt("cena", 0);
        return desayuno + almuerzo + cena;
    }

    // Mostrar calorías necesarias (Goal)
    private void showCaloriesGoal() {
        float caloriasDiarias = getCaloriasDiarias();
        binding.tvCaloriesGoal.setText(String.format("Calorías necesarias según TMB %.2f kcal", caloriasDiarias));
    }

    // Actualizar y mostrar las calorías consumidas
    private void updateConsumedCalories() {
        int totalConsumidas = getCaloriasConsumidas();
        binding.tvCalConsumed.setText(totalConsumidas + " kcal");
        binding.tvACtCalories.setText(totalConsumidas + " kcal");
    }

    // Mostrar la diferencia entre calorías diarias y calorías quemadas
    private void showCaloriesFinal() {
        float caloriasDiarias = getCaloriasDiarias();
        float caloriasQuemadas = getCaloriasQuemadas();
        float caloriasRestantes = caloriasDiarias - caloriasQuemadas;
        binding.tvCaloriesFinal.setText(String.format("de %.2f kcal", caloriasRestantes));
    }

    // Mostrar las calorías faltantes (calorías restantes - calorías consumidas)
    private void showCaloriesFaltantes() {
        float caloriasDiarias = getCaloriasDiarias();
        float caloriasQuemadas = getCaloriasQuemadas();
        int caloriasConsumidas = getCaloriasConsumidas();

        float caloriasRestantes = caloriasDiarias - caloriasQuemadas;
        float caloriasFaltantes = caloriasRestantes - caloriasConsumidas;

        binding.tvMsgGoal.setText(String.format("Te faltan %.2f kcal para la meta", caloriasFaltantes));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateConsumedCalories();
        showCaloriesFinal();
        showCaloriesFaltantes();
        updateCaloriesBurned();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}