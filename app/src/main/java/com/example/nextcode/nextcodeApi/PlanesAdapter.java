package com.example.nextcode.nextcodeApi;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nextcode.R;
import com.example.nextcode.models.Planes;
import com.example.nextcode.models.PlanesUsuario;
import com.example.nextcode.models.Usuarios;
import com.example.nextcode.views.IngresoMenuActivity;
import com.example.nextcode.views.MainActivity;
import com.example.nextcode.views.PlanesActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Adaptador para colocar los datos en el RecyclerView
public class PlanesAdapter extends RecyclerView.Adapter<PlanesAdapter.ViewHolder>{
    private ArrayList<Planes> dataset;
    private Context context;

    private static final String TAG = "NextCode";

    public PlanesAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_planes,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Planes p = dataset.get(position);

        //Asignamos los datos a cada item respectivamente.
        holder.textViewNombre.setText(p.getNombre());
        holder.textViewTipo.setText("TIPO: " + p.getTipo());
        holder.textViewValor.setText("Costo(inc. iva): " +p.getTotal());

        holder.buttonContratar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("id: "+ p.getId() + ", nombre: " + p.getNombre());
                System.out.println("idUsuario: "+ MainActivity.getUsuario_id());
                contratarPlan(MainActivity.getUsuario_id(), p.getId(), "A");
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPlanes(ArrayList<Planes> listaPlanes) {
        dataset.addAll(listaPlanes);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewNombre;
        private TextView textViewTipo;
        private TextView textViewValor;
        private Button buttonContratar;

        public ViewHolder(View itemView){
            super(itemView);
            textViewNombre =(TextView) itemView.findViewById(R.id.textViewNombre);
            textViewTipo =(TextView) itemView.findViewById(R.id.textViewTipo);
            textViewValor =(TextView) itemView.findViewById(R.id.textViewValor);
            buttonContratar =(Button) itemView.findViewById(R.id.buttonContratar);
        }

    }

    //Realiza el Post al contratar un PLAN.
    public void contratarPlan(int usuario_id, int plan_id, String status){

        ApiService service = RetrofitClient.getRetrofit().create(ApiService.class);

        if(usuario_id != 0 && plan_id != 0 && !TextUtils.isEmpty(status) ) {

            status = "A";
            service.contratarPlan(usuario_id, plan_id, status)
                    .enqueue(new Callback<PlanesUsuario>() {

                        @Override
                        public void onResponse(Call<PlanesUsuario> call, Response<PlanesUsuario> response) {

                            if(response.isSuccessful()) {
                                Log.i(TAG, "Plan Contratado." + response.body().toString());
                                Toast.makeText(context, "Plan Contratado." , Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "No se pudo contratar el Plan." , Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<PlanesUsuario> call, Throwable t) {
                            Log.e(TAG, "No se pudo Contratar el Plan.");
                        }
                    });
        }else{
            Toast.makeText(context, "No se pudo contratar el Plan." , Toast.LENGTH_SHORT).show();
        }

    }

}

