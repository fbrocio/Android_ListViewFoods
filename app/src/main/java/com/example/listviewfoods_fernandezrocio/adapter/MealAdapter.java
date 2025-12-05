package com.example.listviewfoods_fernandezrocio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.listviewfoods_fernandezrocio.R;

import java.util.List;

public class MealAdapter extends ArrayAdapter<String> {
    //variable privada, constante_Va a contener como objeto la pantalla donde se va a usar el adaptador
    private final Context context;

    private final List<String> mealList;

    private int selectedPosition = -1;
    //Constructor
    public MealAdapter(Context context, List<String> mealList){
        super(context, 0, mealList);
        this.context = context;
        this.mealList = mealList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        //Si no existe esa vista, se crea
        if (convertView == null){
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_food, parent, false);
        }

        //Referencias a los elementos del layout (despues de convertir items en una lista)
        ImageView imgFood = convertView.findViewById(R.id.tvImage);
        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvCategory = convertView.findViewById(R.id.tvCategory);
        RadioButton radioButton = convertView.findViewById(R.id.radioButton);

        //Obtener el elemento final de la lista
        String mealItem = mealList.get(position);

        //Separar el texto por - para obtener título, descripción, categoría
        String[] parts = mealItem.split("-");

        //Asignar valores a nuestros TextView
        String title = parts[0];
        String description = parts.length > 1? parts[1]: "Sin descripción";

        tvTitle.setText(title);
        tvCategory.setText(description);

        imgFood.setImageResource(getImageForMeal(title));

        //Marcar el RadioButton si corresponde a la posición seleccionada
        radioButton.setChecked(position == selectedPosition);

        //Listener para que solo se marque un RadioButton
        radioButton.setOnClickListener(v-> {
            selectedPosition = position;
            notifyDataSetChanged();
        });

        return convertView;
    }

    private int getImageForMeal(String title){
        switch(title){
            case "sushi ": return R.drawable.sushi;
            case "bun cha ": return R.drawable.buncha;
            case "spring rolls ": return R.drawable.springrolls;
            case "green curry ": return R.drawable.greencurry;
            case "nasi goreng ": return R.drawable.nasigoreng;
            case "pad thai ": return R.drawable.padthai;
            case "pho ": return R.drawable.pho;
            case "kimchi ": return R.drawable.kimchi;
            case "gyozas ": return R.drawable.gyozas;
            case "ramen ": return R.drawable.ramen;
            default: return R.drawable.placeholder_food;
        }
    }

    //Devuelve el elemento seleccionado
    public String getSelectedMeal(){

        return selectedPosition!= -1 ? mealList.get(selectedPosition) : null;
    }

    //Desmarcar cualquier selección
    public void clearSelection(int position){
        selectedPosition = position;
        notifyDataSetChanged();
    }
}