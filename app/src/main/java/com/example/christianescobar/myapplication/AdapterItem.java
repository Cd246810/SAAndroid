package com.example.christianescobar.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chris on 11/10/17.
 */

public class AdapterItem extends BaseAdapter{
    protected Activity activity;
    protected ArrayList<Vehiculo> items;

    public AdapterItem (Activity activity, ArrayList<Vehiculo> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Vehiculo> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_category, null);
        }

        Vehiculo dir = items.get(position);

        TextView id_Vehiculo = (TextView) v.findViewById(R.id.txtIdVehiculo);
        TextView marca = (TextView) v.findViewById(R.id.txtMarca);
        TextView linea = (TextView) v.findViewById(R.id.txtLinea);
        TextView modelo = (TextView) v.findViewById(R.id.txtModelo);
        TextView pais_Origen = (TextView) v.findViewById(R.id.txtPaisOrigen);
        TextView precio_Vehiculo = (TextView) v.findViewById(R.id.txtPrecioVehiculo);

        /*TextView title = (TextView) v.findViewById(R.id.category);
        title.setText(dir.getTittle());

        TextView description = (TextView) v.findViewById(R.id.texto);
        description.setText(dir.getDescription());

        ImageView imagen = (ImageView) v.findViewById(R.id.imageView);
        imagen.setImageDrawable(dir.getImage());*/

        return v;
    }
}
