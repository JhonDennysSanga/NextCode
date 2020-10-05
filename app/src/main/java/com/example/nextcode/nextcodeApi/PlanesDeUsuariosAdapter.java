package com.example.nextcode.nextcodeApi;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nextcode.R;
import com.example.nextcode.models.Planes;
import com.example.nextcode.models.PlanesUsuario;
import com.example.nextcode.models.Usuarios;
import com.example.nextcode.views.MainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Adaptador para colocar los datos en el RecyclerView
public class PlanesDeUsuariosAdapter extends RecyclerView.Adapter<PlanesDeUsuariosAdapter.ViewHolder>{

    private ArrayList<PlanesUsuario> dataset;
    private Context context;

    private static final String TAG = "NextCode";

    public PlanesDeUsuariosAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public PlanesDeUsuariosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_planes_de_usuario,parent,false);
        return new PlanesDeUsuariosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanesDeUsuariosAdapter.ViewHolder holder, int position) {
        PlanesUsuario p = dataset.get(position);

        //Asignamos los datos a cada item respectivamente.
        holder.textViewNombrePlanUs.setText(p.getPlan().getNombre());
        holder.textViewTipoPlanUs.setText("TIPO: " + p.getPlan().getTipo());
        holder.textViewValorPlanUs.setText("Costo(inc. iva): " + p.getPlan().getTotal());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPlanesDeUsuarios(ArrayList<PlanesUsuario> listaPlanesDeUsuarios) {
        dataset.addAll(listaPlanesDeUsuarios);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewNombrePlanUs;
        private TextView textViewTipoPlanUs;
        private TextView textViewValorPlanUs;

        public ViewHolder(View itemView){
            super(itemView);
            textViewNombrePlanUs =(TextView) itemView.findViewById(R.id.textViewNombrePlanUs);
            textViewTipoPlanUs =(TextView) itemView.findViewById(R.id.textViewTipoPlanUs);
            textViewValorPlanUs =(TextView) itemView.findViewById(R.id.textViewValorPlanUs);
        }

    }


}







