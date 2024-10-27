package com.example.calorimetro.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calorimetro.R;
import com.example.calorimetro.databinding.ActivityAddTrainingBinding;
import com.example.calorimetro.model.ActividadFisica;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AddTrainingActivity extends AppCompatActivity {

    ActivityAddTrainingBinding binding;
    List<ActividadFisica> actividadesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTrainingBinding.inflate(getLayoutInflater());
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

        // Cargar la lista de actividades desde SharedPreferences
        loadActivities();

        // Validación al guardar act fisica
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    Toast.makeText(AddTrainingActivity.this, "Se agregó la activdad exitosamente", Toast.LENGTH_SHORT).show();
                    saveActivity();
                } else {
                    Toast.makeText(AddTrainingActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateFields() {
        boolean isValid = true;

        // Validación de la act fisica
        if (binding.etActFisica.getText().toString().trim().isEmpty()) {
            binding.etActFisicaLayout.setError("Complete la actividad física realizada");
            isValid = false;
        } else {
            binding.etActFisicaLayout.setError(null); // Limpiar el error si es válido
        }

        // Validación de la cant de calorías
        if (binding.etCalQuemadas.getText().toString().trim().isEmpty()) {
            binding.etCalQuemadasLayout.setError("Complete las calorías quemadas");
            isValid = false;
        } else {
            binding.etCalQuemadasLayout.setError(null);
        }
        return isValid;
    }

    private void saveActivity() {
        try {
            String actividad = binding.etActFisica.getText().toString().trim();
            float caloriasQuemadas = Float.parseFloat(binding.etCalQuemadas.getText().toString().trim());

            // Actualizar el total de calorías
            SharedPreferences sharedPref = getSharedPreferences("CaloriasQuemadas", Context.MODE_PRIVATE);
            float totalCalorias = sharedPref.getFloat("total_calorias", 0f);
            totalCalorias += caloriasQuemadas;

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putFloat("total_calorias", totalCalorias);
            editor.apply();

            ActividadFisica nuevaActividad = new ActividadFisica(actividad, caloriasQuemadas);

            if (actividadesList == null) {
                actividadesList = new ArrayList<>();
            }

            actividadesList.add(nuevaActividad);
            saveActivities();

            finish();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar la actividad", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadActivities() {
        try {
            SharedPreferences sharedPref = getSharedPreferences("ActividadesFisicas", Context.MODE_PRIVATE);
            String json = sharedPref.getString("actividades", null);

            if (json != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<ActividadFisica>>() {}.getType();
                actividadesList = gson.fromJson(json, type);
            }

            if (actividadesList == null) {
                actividadesList = new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            actividadesList = new ArrayList<>();
        }
    }

    private void saveActivities() {
        try {
            SharedPreferences sharedPref = getSharedPreferences("ActividadesFisicas", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            Gson gson = new Gson();
            String json = gson.toJson(actividadesList);
            editor.putString("actividades", json);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}

// se uso claude para la actualización constante de la lista