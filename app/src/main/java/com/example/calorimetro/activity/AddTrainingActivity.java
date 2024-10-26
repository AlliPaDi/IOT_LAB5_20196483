package com.example.calorimetro.activity;

import android.content.Intent;
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

public class AddTrainingActivity extends AppCompatActivity {

    ActivityAddTrainingBinding binding;

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

        // Validación al guardar act fisica
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    Toast.makeText(AddTrainingActivity.this, "Se agregó la activdad exitosamente", Toast.LENGTH_SHORT).show();
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
}