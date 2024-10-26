package com.example.calorimetro.activity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calorimetro.MainActivity;
import com.example.calorimetro.R;
import com.example.calorimetro.databinding.ActivityNotificationsBinding;

import java.util.Calendar;

public class NotificationsActivity extends AppCompatActivity {

    ActivityNotificationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
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

        // Inicializar el TimePicker cuando se hace clic en el campo de hora
        binding.etNotificationTimeBreakfast.setOnClickListener(view -> showTimePickerDialog(1));
        binding.etNotificationTimeLunch.setOnClickListener(view -> showTimePickerDialog(2));
        binding.etNotificationTimeDinner.setOnClickListener(view -> showTimePickerDialog(3));
    }


    // Se usó chat gpt para desarrolar el TimePickerDialog
    // Método para mostrar el TimePickerDialog
    private void showTimePickerDialog(final int notificationIndex) {
        // Obtener la hora actual para establecerla como predeterminada
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Crear el TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (TimePicker timePicker, int selectedHour, int selectedMinute) -> {
                    // Llamar al método que actualiza el campo correspondiente
                    setNotificationTime(notificationIndex, selectedHour, selectedMinute);
                },
                hour, minute, DateFormat.is24HourFormat(this)  // Determina si usa formato de 24 horas o no
        );

        // Mostrar el diálogo de selección de hora
        timePickerDialog.show();
    }

    // Método para actualizar el campo correcto según el índice
    private void setNotificationTime(int notificationIndex, int hour, int minute) {
        String time = formatTime(hour, minute);  // Formatear la hora seleccionada
        switch (notificationIndex) {
            case 1:
                binding.etNotificationTimeBreakfast.setText(time);
                break;
            case 2:
                binding.etNotificationTimeLunch.setText(time);
                break;
            case 3:
                binding.etNotificationTimeDinner.setText(time);
                break;
        }
    }

    // Método para formatear la hora seleccionada
    private String formatTime(int hour, int minute) {
        return String.format("%02d:%02d", hour, minute);  // Retorna en formato 00:00
    }
}