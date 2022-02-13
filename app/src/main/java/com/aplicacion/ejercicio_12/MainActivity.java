package com.aplicacion.ejercicio_12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelos.Persona;

public class MainActivity extends AppCompatActivity {

    Button btnenviar;
    EditText txtnombres, txtapellidos, txtedad, txtcorreo;
    String nombres, apellidos, correo;
    int edad;
    Persona persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombres = (EditText) findViewById(R.id.txtnombres);
        txtapellidos = (EditText) findViewById(R.id.txtapellidos);
        txtedad = (EditText) findViewById(R.id.txtedad);
        txtcorreo = (EditText) findViewById(R.id.txtcorreo);




        btnenviar = (Button) findViewById(R.id.btnenviar);

        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(permitirEnviar()){
                    try {

                        nombres = txtnombres.getText().toString();
                        apellidos = txtapellidos.getText().toString();
                        correo = txtcorreo.getText().toString();
                        edad = Integer.parseInt(txtedad.getText().toString());

                        persona = new Persona(nombres, apellidos, edad, correo);

                        enviarDatos();
                    }catch (Exception e){

                        Toast.makeText(getApplicationContext(), "Error al procesar datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void enviarDatos() {
        Intent intent = new Intent(getApplicationContext(), ActivityMostrar.class);

        Bundle bundle = new Bundle();

        bundle.putSerializable("persona", persona);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    private boolean permitirEnviar(){
        String mensaje = "";

        String nombres = txtnombres.getText().toString();
        String apellidos = txtapellidos.getText().toString();
        String correo = txtcorreo.getText().toString();
        String edad = txtedad.getText().toString();

        if(isTextEmpty(nombres)) mensaje = "El campo nombres esta vacio";
        else if(isTextEmpty(apellidos)) mensaje = "El campo apellidos esta vacio";
        else if(isTextEmpty(edad)) mensaje = "El campo edad esta vacio";
        else if(isTextEmpty(correo)) mensaje = "El campo correo esta vacio";
        else if(!isNumeric(edad)) mensaje = "La edad debe ser numerica";
        else if(!validarCorreo(correo)) mensaje = "Correo no valido";
        else if(!isText(nombres)) mensaje = "Los nombres no son validos: Solo deben ser letras";
        else if(!isText(apellidos)) mensaje = "Los apellidos no son validos: Solo deben ser letras";


        if(mensaje.length() != 0){
            Toast.makeText(getApplicationContext(), "Error al procesar datos: " + mensaje, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }



    /******************************************************************/

    //Si el correo es valido
    private boolean validarCorreo(String email){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        // El email a validar
//        String email = txtcorreo.getText().toString();

        Matcher mather = pattern.matcher(email);

        if(mather.find()) {
            return true;
        }else{
//            Toast.makeText(this, "El correo ingresado no es valido: " + email, Toast.LENGTH_SHORT).show();
            return false;
        }

    }
    private static boolean isNumeric(String cadena){
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    private static boolean isText(String text){

        // Validando un texto que solo acepte letras sin importar tamaño
        Pattern pat = Pattern.compile("^[a-zA-ZáéíóúÁÉÓÚÍ ]+$");
        Matcher mat = pat.matcher(text);

        return (mat.matches());
//        return (mat.matches())?true:false;

    }


    //Si el texto esta vacio
    private static boolean isTextEmpty(String text){

        return (text.length()==0)?true:false;
    }


}