package com.example.nextcode.views;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nextcode.R;
import com.example.nextcode.models.Planes;
import com.example.nextcode.models.PlanesRespuesta;
import com.example.nextcode.models.Usuarios;
import com.example.nextcode.models.UsuariosRespuesta;
import com.example.nextcode.nextcodeApi.ApiService;
import com.example.nextcode.nextcodeApi.PlanesAdapter;
import com.example.nextcode.nextcodeApi.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanesActivity extends AppCompatActivity {

    private static final String TAG = "NextCode";

    private RecyclerView recyclerView;
    private PlanesAdapter planesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planes);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPlan);
        planesAdapter = new PlanesAdapter(this);
        recyclerView.setAdapter(planesAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        obtenerDatos();
    }

    private void obtenerDatos() {

        ApiService service = RetrofitClient.getRetrofit().create(ApiService.class);
        Call<PlanesRespuesta> planesRespuestaCall = service.obtenerListaPlanes();

        planesRespuestaCall.enqueue(new Callback<PlanesRespuesta>() {
            @Override
            public void onResponse(Call<PlanesRespuesta> call, Response<PlanesRespuesta> response) {

                if(response.isSuccessful()){
                    PlanesRespuesta planesRespuesta = response.body();
                    ArrayList<Planes> listaPlanes = planesRespuesta.getData();

                    for (Planes p: listaPlanes) {

                        System.out.println(p.getId() + " " + p.getNombre());

                    }

                    //Envíamos la lista que obtuvimos al adaptador.
                    planesAdapter.adicionarListaPlanes(listaPlanes);

                }else{
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PlanesRespuesta> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }




}
