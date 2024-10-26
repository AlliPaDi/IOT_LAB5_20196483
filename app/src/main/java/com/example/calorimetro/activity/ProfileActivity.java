package com.example.calorimetro.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calorimetro.R;
import com.example.calorimetro.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
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

        // Mostrar la información del usuario
        displayUserData();
    }
    private void displayUserData() {
        // Recuperar la información de SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        String peso = sharedPref.getString("peso", "N/A");
        String altura = sharedPref.getString("altura", "N/A");
        String edad = sharedPref.getString("edad", "N/A");
        String genero = sharedPref.getString("genero", "N/A");
        String nivelFisico = sharedPref.getString("nivelFisico", "N/A");
        String objetivo = sharedPref.getString("objetivo", "N/A");

        // Asignar los valores recuperados a los TextViews
        binding.tvPeso.setText(peso + " kg");
        binding.tvAltura.setText(altura + " m");
        binding.tvEdad.setText(edad + " años");
        binding.tvGenero.setText(genero);
        binding.tvNivFisico.setText(nivelFisico);
        binding.tvObjetivo.setText(objetivo);
    }
}