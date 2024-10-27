package com.example.calorimetro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.calorimetro.R;
import com.example.calorimetro.model.ActividadFisica;

import java.util.ArrayList;
import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder> {

    private Context context;
    private List<ActividadFisica> trainingList;

    public TrainingAdapter(Context context, List<ActividadFisica> trainingList) {
        this.context = context;
        this.trainingList = trainingList;
    }

    @Override
    public TrainingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_training, parent, false);
        return new TrainingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrainingViewHolder holder, int position) {
        ActividadFisica actividad = trainingList.get(position);
        holder.tvActName.setText(actividad.getNombre());
        holder.tvCaloriesQuemadas.setText(String.format("%.2f kcal", actividad.getCaloriasQuemadas()));

    }

    @Override
    public int getItemCount() {
        return trainingList == null ? 0 : trainingList.size();
    }


    public void setActivities(List<ActividadFisica> activities) {
        if (activities != null) {
            this.trainingList = new ArrayList<>(activities); // Crear una copia de la lista
            notifyDataSetChanged();
        }
    }

    public class TrainingViewHolder extends RecyclerView.ViewHolder {
        TextView tvActName, tvCaloriesQuemadas;

        public TrainingViewHolder(View itemView) {
            super(itemView);
            tvActName = itemView.findViewById(R.id.tvActName);
            tvCaloriesQuemadas = itemView.findViewById(R.id.tvCaloriesQuemadas);
        }
    }
}