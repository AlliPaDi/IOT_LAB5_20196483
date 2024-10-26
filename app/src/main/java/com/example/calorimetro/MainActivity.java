package com.example.calorimetro;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calorimetro.activity.HomeActivity;
import com.example.calorimetro.activity.RegisterActivity;
import com.example.calorimetro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Verificar si los datos del usuario ya están guardados
        SharedPreferences sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        if (sharedPref.contains("peso") && sharedPref.contains("altura") && sharedPref.contains("edad") && sharedPref.contains("genero") && sharedPref.contains("nivelFisico") && sharedPref.contains("objetivo")) {
            // Si los datos están guardados, ir directamente a la pantalla principal
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Si no hay datos guardados, ir a la pantalla de registro
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }
}