package com.example.nextcode.nextcodeApi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nextcode.R;
import com.example.nextcode.models.Facturas;
import com.example.nextcode.models.PlanesUsuario;

import java.util.ArrayList;

//Adaptador para colocar los datos en el RecyclerView de Ver Facturas
public class FacturasAdapter extends RecyclerView.Adapter<FacturasAdapter.ViewHolder>{

    private ArrayList<Facturas> dataset;
    private Context context;

    private static final String TAG = "NextCode";

    public FacturasAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public FacturasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_facturas,parent,false);
        return new FacturasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacturasAdapter.ViewHolder holder, int position) {
        Facturas f = dataset.get(position);

        //Asignamos los datos a cada item respectivamente.
        holder.textViewSerie.setText(f.getSerie());
        holder.textViewFechaEmision.setText("Fecha de Emisión: " + f.getFecha_emision());
        holder.textViewSubtotal.setText("Subtotal: " + f.getSubtotal());
        holder.textViewIva.setText("Iva: " + f.getIva());
        holder.textViewTotal.setText("Total: " + f.getTotal());
        holder.textViewEstadoPago.setText("Estado: " + f.getEstado_pago());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaFacturas(ArrayList<Facturas> listaFacturas) {
        dataset.addAll(listaFacturas);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewSerie;
        private TextView textViewFechaEmision;
        private TextView textViewSubtotal;
        private TextView textViewIva;
        private TextView textViewTotal;
        private TextView textViewEstadoPago;

        public ViewHolder(View itemView){
            super(itemView);
            textViewSerie =(TextView) itemView.findViewById(R.id.textViewSerie);
            textViewFechaEmision =(TextView) itemView.findViewById(R.id.textViewFechaEmision);
            textViewSubtotal =(TextView) itemView.findViewById(R.id.textViewSubtotal);
            textViewIva =(TextView) itemView.findViewById(R.id.textViewIva);
            textViewTotal =(TextView) itemView.findViewById(R.id.textViewTotal);
            textViewEstadoPago =(TextView) itemView.findViewById(R.id.textViewEstadoPago);
        }

    }


}

