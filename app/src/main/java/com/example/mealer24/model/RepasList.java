package com.example.mealer24.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mealer24.R;

import java.util.List;

/**
 * This class allows us to customize the list view of repas
 * see layout_repas_list.xml to customize the layout
 */
public class RepasList extends ArrayAdapter<Repas> {


    private Activity context;
            List<Repas> repas;

    public RepasList(Activity context, List<Repas> repas){
        super(context, R.layout.layout_repas_list, repas);
        this.context = context;
        this.repas = repas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View listViewItem = inflater.inflate(R.layout.layout_repas_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice);
        TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.textViewDescription);


        Repas r = repas.get(position);
        textViewName.setText(r.getNomDuRepas());
        textViewPrice.setText(Double.toString(r.getPrix())+"$");
        textViewDescription.setText(r.getDescription());
        return listViewItem;
    }


}
