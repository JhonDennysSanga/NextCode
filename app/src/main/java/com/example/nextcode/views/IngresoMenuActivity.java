package com.example.nextcode.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nextcode.R;
import com.example.nextcode.models.Facturas;
import com.example.nextcode.models.PlanesUsuario;
import com.example.nextcode.models.Usuarios;
import com.example.nextcode.models.UsuariosRespuesta;
import com.example.nextcode.nextcodeApi.ApiService;
import com.example.nextcode.nextcodeApi.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngresoMenuActivity extends AppCompatActivity {

    private static final String TAG = "NextCode";
    private static double subtotal;
    private Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_menu);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    //Método para ir a RegistroActivity
    public void contratar(View view){
        Intent intent = new Intent(this, PlanesActivity.class);
        startActivity(intent);
    }

    //Método para Ver mis Planes
    public void verMisPlanes(View view){
        Intent intent = new Intent(this, PlanesDeUsuariosActivity.class);
        startActivity(intent);
    }

    //Método para Ver mis Planes
    public void verMisFacturas(View view){
        Intent intent = new Intent(this, FacturasActivity.class);
        startActivity(intent);
    }

    //Método para Cerrar Sesion
    public void cerrarSesion(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Método para reccorer los planes a facturar
    public void planesFacturar(View view) {
        ApiService service = RetrofitClient.getRetrofit().create(ApiService.class);
        Call<UsuariosRespuesta> usuariosRespuestaCall = service.obtenerListaPlanesDeUsuarios();

        usuariosRespuestaCall.enqueue(new Callback<UsuariosRespuesta>() {
            @Override
            public void onResponse(Call<UsuariosRespuesta> call, Response<UsuariosRespuesta> response) {

                if (response.isSuccessful()) {
                    UsuariosRespuesta usuariosRespuesta = response.body();
                    ArrayList<Usuarios> listaUsuarios = usuariosRespuesta.getData();
                    ArrayList<PlanesUsuario> listaPlanesUsuario = listaUsuarios.get(MainActivity.getUsuario_id() - 1).getPlanes();

                    if (!listaPlanesUsuario.isEmpty()){
                        subtotal = 0;
                        double subtotalCadaPlan = 0;
                        for (PlanesUsuario p : listaPlanesUsuario) {
                            subtotalCadaPlan = Double.parseDouble(p.getPlan().getSubtotal());
                            subtotal += subtotalCadaPlan;
                            Log.i(TAG, "PlanesUsuario: " + p.getPlan().getNombre());
                        }
                        verFacturaGenerada(view);
                    }else{
                        Toast.makeText(context, "No tiene planes a Facturar", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "No Se pudo Facturar", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<UsuariosRespuesta> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    //Método para Ver Factura Creada por el usuario
    public void verFacturaGenerada(View view){
        Intent intent = new Intent(this, FacturaGeneradaActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Se Facturó exitosamente", Toast.LENGTH_SHORT).show();
    }

    public static double getSubtotal() {
        return subtotal;
    }

    public static void setSubtotal(double subtotal) {
        IngresoMenuActivity.subtotal = subtotal;
    }
}
