package com.example.mealer24.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.mealer24.R;

import java.util.List;

public class OrderList extends ArrayAdapter<DemandeAchat> {

    private Activity context;
    List<DemandeAchat> orderList;

    public OrderList(Activity context, List<DemandeAchat> orders){
        super(context, R.layout.layout_order_list, orders);
        this.context = context;
        this.orderList = orders;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View listViewItem = inflater.inflate(R.layout.layout_order_list, null, true);

        //get all view components


        //get the order at the given position in the listview
        DemandeAchat order = orderList.get(position);

        return listViewItem;
    }
}
