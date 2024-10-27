package com.example.calorimetro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calorimetro.R;
import com.example.calorimetro.model.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private final List<Food> foodList;
    private final Context context;
    private List<Food> selectedFoods = new ArrayList<>();

    public FoodAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.tvFoodName.setText(food.getName());
        holder.tvCalories.setText(String.format("%d kcal", food.getCalories()));

        // Manejar la selección del checkbox
        holder.checkBox.setOnCheckedChangeListener(null); // Limpiar el listener previo
        holder.checkBox.setChecked(selectedFoods.contains(food)); // Marcar el checkbox si ya está seleccionado


        holder.checkBox.setEnabled(true);
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedFoods.add(food); // Añadir alimento seleccionado
            } else {
                selectedFoods.remove(food); // Remover alimento deseleccionado
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public List<Food> getSelectedFoods() {
        return selectedFoods; // Retorna la lista de alimentos seleccionados
    }

    public void setSelectedFoods(Set<String> selectedFoodsSet) {
        for (Food food : foodList) {
            if (selectedFoodsSet.contains(food.getName())) {
                selectedFoods.add(food); // Marcar como seleccionado
            }
        }
        notifyDataSetChanged();
    }



    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView tvFoodName, tvCalories;
        CheckBox checkBox;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodName = itemView.findViewById(R.id.tvFoodName);
            tvCalories = itemView.findViewById(R.id.tvCalories);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}