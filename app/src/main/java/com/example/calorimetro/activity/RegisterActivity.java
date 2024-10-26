package com.example.calorimetro.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calorimetro.R;
import com.example.calorimetro.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vincular el AutoCompleteTextView para genero
        ArrayAdapter<String> adapterGenero = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.genero_options));
        binding.autoCompleteGenero.setAdapter(adapterGenero);

        // Vincular el AutoCompleteTextView para nivel fisico
        ArrayAdapter<String> adapterNivFisico = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.niv_fisico_options));
        binding.autoCompleteNivFisico.setAdapter(adapterNivFisico);

        // Vincular el AutoCompleteTextView para objetivo
        ArrayAdapter<String> adapterObjetivo = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.objetivo_options));
        binding.autoCompleteObjetivo.setAdapter(adapterObjetivo);

        // Validación al hacer clic en el botón de registrar
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    saveUserData();
                    Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateFields() {
        boolean isValid = true;

        // Validación del campo de Peso
        if (binding.etPeso.getText().toString().trim().isEmpty()) {
            binding.etPesoLayout.setError("Ingresa tu peso");
            isValid = false;
        } else {
            binding.etPesoLayout.setError(null); // Limpiar el error si es válido
        }

        // Validación del campo de Altura
        if (binding.etAltura.getText().toString().trim().isEmpty()) {
            binding.etAlturaLayout.setError("Ingresa tu altura");
            isValid = false;
        } else {
            binding.etAlturaLayout.setError(null);
        }

        // Validación del campo de Edad
        if (binding.etEdad.getText().toString().trim().isEmpty()) {
            binding.etEdadLayout.setError("Ingresa tu edad");
            isValid = false;
        } else {
            binding.etEdadLayout.setError(null);
        }

        // Validación del campo Género
        if (binding.autoCompleteGenero.getText().toString().trim().isEmpty()) {
            binding.autoCompleteGeneroLayout.setError("Selecciona el género");
            isValid = false;
        } else {
            binding.autoCompleteGeneroLayout.setError(null);
        }

        // Validación del campo Nivel Físico
        if (binding.autoCompleteNivFisico.getText().toString().trim().isEmpty()) {
            binding.autoCompleteNivFisicoLayout.setError("Selecciona el nivel físico");
            isValid = false;
        } else {
            binding.autoCompleteNivFisicoLayout.setError(null);
        }

        // Validación del campo Objetivo
        if (binding.autoCompleteObjetivo.getText().toString().trim().isEmpty()) {
            binding.autoCompleteObjetivoLayout.setError("Selecciona el objetivo");
            isValid = false;
        } else {
            binding.autoCompleteObjetivoLayout.setError(null);
        }

        return isValid;
    }

    private void saveUserData() {
        // Obtener los valores ingresados por el usuario
        String peso = binding.etPeso.getText().toString().trim();
        String altura = binding.etAltura.getText().toString().trim();
        String edad = binding.etEdad.getText().toString().trim();
        String genero = binding.autoCompleteGenero.getText().toString().trim();
        String nivelFisico = binding.autoCompleteNivFisico.getText().toString().trim();
        String objetivo = binding.autoCompleteObjetivo.getText().toString().trim();

        // Guardar la información en SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("peso", peso);
        editor.putString("altura", altura);
        editor.putString("edad", edad);
        editor.putString("genero", genero);
        editor.putString("nivelFisico", nivelFisico);
        editor.putString("objetivo", objetivo);

        // Confirmar que se han guardado los datos
        editor.apply();
    }
}