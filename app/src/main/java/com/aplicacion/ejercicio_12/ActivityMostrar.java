package com.aplicacion.ejercicio_12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import modelos.Persona;

public class ActivityMostrar extends AppCompatActivity {

    TextView txtnombres, txtapellidos, txtedad, txtcorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);


        txtnombres = (TextView) findViewById(R.id.txtmostrarnombres);
        txtapellidos = (TextView) findViewById(R.id.txtmostrarapellidos);
        txtedad = (TextView) findViewById(R.id.txtmostraredad);
        txtcorreo = (TextView) findViewById(R.id.txtmostrarcorreo);

        Bundle objEnviado = getIntent().getExtras();

        Persona persona = null;

        if(objEnviado != null){
            persona = (Persona) objEnviado.getSerializable("persona");

            txtnombres.setText(persona.getNombres());
            txtapellidos.setText(persona.getApellidos());
            txtedad.setText(persona.getEdad()+"");
            txtcorreo.setText(persona.getCorreo());
        }
    }
}