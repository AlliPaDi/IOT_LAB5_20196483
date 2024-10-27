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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.calorimetro.R;
import com.example.calorimetro.adapter.TrainingAdapter;
import com.example.calorimetro.databinding.ActivityTrainingBinding;
import com.example.calorimetro.model.ActividadFisica;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TrainingActivity extends AppCompatActivity {

    ActivityTrainingBinding binding;
    TrainingAdapter trainingAdapter;
    List<ActividadFisica> actividadesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrainingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupButtons();
        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadActivities(); // Cargar actividades cuando la actividad se reanude
    }

    private void setupButtons() {
        binding.btnBack.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });

        binding.btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, AddTrainingActivity.class));
        });
    }

    private void setupRecyclerView() {
        actividadesList = new ArrayList<>();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        trainingAdapter = new TrainingAdapter(this, actividadesList);
        binding.recyclerView.setAdapter(trainingAdapter);
    }


    private void loadActivities() {
        try {
            SharedPreferences sharedPref = getSharedPreferences("ActividadesFisicas", Context.MODE_PRIVATE);
            String json = sharedPref.getString("actividades", null);

            if (json != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<ActividadFisica>>() {}.getType();
                List<ActividadFisica> loadedList = gson.fromJson(json, type);

                if (loadedList != null) {
                    actividadesList.clear();
                    actividadesList.addAll(loadedList);
                    trainingAdapter.notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}