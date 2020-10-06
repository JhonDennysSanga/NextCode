package com.example.nextcode.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.nextcode.R;
import com.example.nextcode.models.Facturas;
import com.example.nextcode.models.PlanesUsuario;
import com.example.nextcode.models.Usuarios;
import com.example.nextcode.models.UsuariosRespuesta;
import com.example.nextcode.nextcodeApi.ApiService;
import com.example.nextcode.nextcodeApi.FacturasAdapter;
import com.example.nextcode.nextcodeApi.PlanesDeUsuariosAdapter;
import com.example.nextcode.nextcodeApi.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacturasActivity extends AppCompatActivity {

    private static final String TAG = "NextCode";

    private RecyclerView recyclerView;
    private FacturasAdapter listaFacturasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturas);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewFacturas);
        listaFacturasAdapter = new FacturasAdapter(this);
        recyclerView.setAdapter(listaFacturasAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        obtenerDatos();
    }

    private void obtenerDatos() {
        ApiService service = RetrofitClient.getRetrofit().create(ApiService.class);
        Call<UsuariosRespuesta> usuariosRespuestaCall = service.obtenerListaFacturas();

        usuariosRespuestaCall.enqueue(new Callback<UsuariosRespuesta>() {
            @Override
            public void onResponse(Call<UsuariosRespuesta> call, Response<UsuariosRespuesta> response) {

                if (response.isSuccessful()) {
                    UsuariosRespuesta usuariosRespuesta = response.body();
                    ArrayList<Usuarios> listaUsuarios = usuariosRespuesta.getData();
                    ArrayList<Facturas> listaFacturas = listaUsuarios.get(MainActivity.getUsuario_id() - 1).getFacturas();

                    listaFacturasAdapter.adicionarListaFacturas(listaFacturas);

                    for (Facturas f : listaFacturas) {
                        Log.i(TAG, "Facturas: id = " + f.getId() + ", serie= " + f.getSerie());
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
