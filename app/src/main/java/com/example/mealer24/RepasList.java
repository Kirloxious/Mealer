package com.example.mealer24;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

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
        TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.textViewPrice);

        Repas r = repas.get(position);
        textViewName.setText(r.getNomDuRepas());
        textViewDescription.setText(r.getDescription());
        return listViewItem;
    }

}
