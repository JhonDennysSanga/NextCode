package com.example.nextcode.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nextcode.R;
import com.example.nextcode.models.Planes;
import com.example.nextcode.models.PlanesUsuario;
import com.example.nextcode.models.Usuarios;
import com.example.nextcode.models.UsuariosRespuesta;
import com.example.nextcode.nextcodeApi.ApiService;
import com.example.nextcode.nextcodeApi.PlanesDeUsuariosAdapter;
import com.example.nextcode.nextcodeApi.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlanesDeUsuariosActivity extends AppCompatActivity {

    private static final String TAG = "POKEDEX";

    private RecyclerView recyclerView;
    private PlanesDeUsuariosAdapter listaPlanesDeUsuariosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planes_de_usuarios);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPlanUs);
        listaPlanesDeUsuariosAdapter = new PlanesDeUsuariosAdapter(this);
        recyclerView.setAdapter(listaPlanesDeUsuariosAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        obtenerDatos();
    }

    private void obtenerDatos() {
        ApiService service = RetrofitClient.getRetrofit().create(ApiService.class);
        Call<UsuariosRespuesta> usuariosRespuestaCall = service.obtenerListaPlanesDeUsuarios();

        usuariosRespuestaCall.enqueue(new Callback<UsuariosRespuesta>() {
            @Override
            public void onResponse(Call<UsuariosRespuesta> call, Response<UsuariosRespuesta> response) {

                if (response.isSuccessful()) {
                    UsuariosRespuesta usuariosRespuesta = response.body();
                    ArrayList<Usuarios> listaUsuarios = usuariosRespuesta.getData();
                    ArrayList<PlanesUsuario> listaPlanesUsuario = listaUsuarios.get(MainActivity.getUsuario_id() - 1).getPlanes();

                    listaPlanesDeUsuariosAdapter.adicionarListaPlanesDeUsuarios(listaPlanesUsuario);

                    for (PlanesUsuario p : listaPlanesUsuario) {
                        Log.i(TAG, "Pokemon: " + p.getPlan().getNombre());
                    }

                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<UsuariosRespuesta> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}


