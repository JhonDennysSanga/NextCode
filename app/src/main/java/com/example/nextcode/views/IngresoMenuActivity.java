package com.example.nextcode.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nextcode.R;

public class IngresoMenuActivity extends AppCompatActivity {

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

    //Método para Cerrar Sesion
    public void cerrarSesion(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
