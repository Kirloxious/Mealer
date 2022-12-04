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
 * Layout for the an order to be applied to a listview using this adapter
 * see layout_order_list.xml to customize the layout
 */
public class OrderLayout extends ArrayAdapter<DemandeAchat> {

    private Activity context;
    List<DemandeAchat> orderList;

    public OrderLayout(Activity context, List<DemandeAchat> orders){
        super(context, R.layout.layout_order_list, orders);
        this.context = context;
        this.orderList = orders;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View listViewItem = inflater.inflate(R.layout.layout_order_list, null, true);

        //get all view components
        TextView textViewOrderId = (TextView) listViewItem.findViewById(R.id.textViewOrderId);
        TextView textViewOrderStatus = (TextView) listViewItem.findViewById(R.id.textViewOrderStatus);
        TextView textViewMealName = (TextView) listViewItem.findViewById(R.id.textViewMealName);
        TextView textViewCookName = (TextView) listViewItem.findViewById(R.id.textViewCookName);
        TextView textViewClientName = (TextView) listViewItem.findViewById(R.id.textViewClientName);


        //get the order at the given position in the listview
        DemandeAchat order = orderList.get(position);
        //set the text view with info from the order
        textViewOrderId.append(order.getOrderId());
        textViewOrderStatus.append(order.getOrderStatus());
        textViewMealName.append(order.getMealId());
        textViewClientName.append(order.getClientEmail());
        textViewCookName.append(order.getCookEmail());


        return listViewItem;
    }
}
