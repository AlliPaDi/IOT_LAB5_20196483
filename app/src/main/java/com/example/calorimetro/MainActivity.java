package com.example.calorimetro;

import android.content.Intent;
import android.os.Bundle;

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

        // funciones de botones
        binding.btnHome.setOnClickListener(v->{
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });

        binding.btnRegister.setOnClickListener(v->{
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}