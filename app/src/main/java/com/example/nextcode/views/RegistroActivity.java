package com.example.nextcode.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class RegistroActivity extends AppCompatActivity {

    private static final String TAG = "NextCode";
    private EditText editTextCedula, editTextContrasenia, editTextNombres, editTextApellidos, editTextCorreo;
    private String cedula, contrasenia, nombres, apellidos, correo, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    //Realiza el procedimiento Post y verifica que se ingrese los campos para el registro.
    public void verificarRegistro(View view){

        ApiService service = RetrofitClient.getRetrofit().create(ApiService.class);

        editTextCedula = findViewById(R.id.editTextCedula);
        editTextContrasenia = findViewById(R.id.editTextContraseniaReg);
        editTextNombres = findViewById(R.id.editTextNombre);
        editTextApellidos = findViewById(R.id.editTextApellido);
        editTextCorreo = findViewById(R.id.editTextCorreoReg);

        cedula = editTextCedula.getText().toString();
        contrasenia = editTextContrasenia.getText().toString();
        nombres = editTextNombres.getText().toString();
        apellidos = editTextApellidos.getText().toString();
        correo = editTextCorreo.getText().toString();
        status = "A";


        if(!TextUtils.isEmpty(cedula) && !TextUtils.isEmpty(contrasenia) && !TextUtils.isEmpty(nombres)
           && !TextUtils.isEmpty(apellidos) && !TextUtils.isEmpty(correo)) {

                status = "A";
                service.registrarUsuario(cedula, contrasenia, nombres, apellidos, correo, status)
                    .enqueue(new Callback<Usuarios>() {

                    @Override
                    public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {

                        if(response.isSuccessful()) {
                            Log.i(TAG, "Usuario Registrado." + response.body().toString());
                        }
                        registrado(view);
                    }

                    @Override
                    public void onFailure(Call<Usuarios> call, Throwable t) {
                        Log.e(TAG, "No se pudo registrar el usuario.");
                    }
                });
        }else{
            Toast.makeText(this, "Ingrese todo los campos de Registro" , Toast.LENGTH_SHORT).show();
        }

    }

    //Método para regresar a LogIn después de haber Registrado
    public void registrado(View view){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Registro Exitoso" , Toast.LENGTH_SHORT).show();
    }

}
