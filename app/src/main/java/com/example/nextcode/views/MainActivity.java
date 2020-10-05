package com.example.nextcode.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nextcode.R;
import com.example.nextcode.models.Usuarios;
import com.example.nextcode.models.UsuariosRespuesta;
import com.example.nextcode.nextcodeApi.ApiService;
import com.example.nextcode.nextcodeApi.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //MainActivity
    //ramaJhon

    private static final String TAG = "NextCode";
    private EditText editTextcorreo, editTextContrasenia;
    private String correo, contrasenia;
    private boolean verificado = false;
    private static int usuario_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Método para ir a RegistroActivity
    public void registrar(View view){
            Intent intent = new Intent(this, RegistroActivity.class);
            startActivity(intent);
    }

    //Obtiene el listado de usuarios y verifica si el usuario ingresado es valido
    public void verificar(View view){

        ApiService service = RetrofitClient.getRetrofit().create(ApiService.class);
        Call<UsuariosRespuesta> usuariosRespuestaCall = service.obtenerListaUsuarios();

        editTextcorreo = findViewById(R.id.editTextCorreo);
        editTextContrasenia = findViewById(R.id.editTextContrasenia);
        correo = editTextcorreo.getText().toString();
        contrasenia = editTextContrasenia.getText().toString();

        usuariosRespuestaCall.enqueue(new Callback<UsuariosRespuesta>() {
            @Override
            public void onResponse(Call<UsuariosRespuesta> call, Response<UsuariosRespuesta> response) {

                if(response.isSuccessful()){
                    UsuariosRespuesta usuariosRespuesta = response.body();
                    ArrayList<Usuarios> listaUsuarios = usuariosRespuesta.getData();

                    verificado=false;

                    for (Usuarios u : listaUsuarios) {


                        if(u.getCorreo().equals(correo) && u.getContrasenia().equals(contrasenia)
                                && !correo.isEmpty() && !contrasenia.isEmpty()) {

                            usuario_id = u.getId();
                            verificado = true;

                        }
                    }
                    ingresar(view);

                }else{
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<UsuariosRespuesta> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    //Método para ir a Menu despues de haber iniciado sesión.
    public void ingresar(View view){

        if(verificado){
            Intent intent = new Intent(this, IngresoMenuActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Ingreso Exitoso" , Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Credenciales No Válidas" , Toast.LENGTH_SHORT).show();
        }

    }

    public static int getUsuario_id() {
        return usuario_id;
    }
}
