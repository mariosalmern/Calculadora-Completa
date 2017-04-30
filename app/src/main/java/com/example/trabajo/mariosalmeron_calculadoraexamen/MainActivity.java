package com.example.trabajo.mariosalmeron_calculadoraexamen;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView pantalla;
    private String muestra = "";
    private String Operador = "";
    private String resultado = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pantalla = (TextView)findViewById(R.id.textView);
        pantalla.setText(muestra);
    }

    private void actualizarPantalla(){
        pantalla.setText(muestra);
    }

    public void onClickNumber(View v){
        if(resultado != ""){
            clear();
            actualizarPantalla();
        }
        Button b = (Button) v;
        muestra += b.getText();
        actualizarPantalla();
    }

    private boolean isOperator(char op){
        switch (op){
            case '+':
            case '-':
            case 'x':
            case '÷':return true;
            default: return false;
        }
    }

    public void onClickOperator(View v){
        if(muestra == "") return;

        Button b = (Button)v;

        if(resultado != ""){
            String _display = resultado;
            clear();
            muestra = _display;
        }

        if(Operador != ""){
            Log.d("CalcX", ""+ muestra.charAt(muestra.length()-1));
            if(isOperator(muestra.charAt(muestra.length()-1))){
                muestra = muestra.replace(muestra.charAt(muestra.length()-1), b.getText().charAt(0));
                actualizarPantalla();
                return;
            }else{
                getResultado();
                muestra = resultado;
                resultado = "";
            }
            Operador = b.getText().toString();
        }
        muestra += b.getText();
        Operador = b.getText().toString();
        actualizarPantalla();
    }

    private void clear(){
        muestra = "";
        Operador = "";
        resultado = "";
    }

    public void onClickClear(View v){
        clear();
        actualizarPantalla();
    }

    private double operar(String a, String b, String op){
        switch (op){
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-": return Double.valueOf(a) - Double.valueOf(b);
            case "x": return Double.valueOf(a) * Double.valueOf(b);
            case "÷": try{
                return Double.valueOf(a) / Double.valueOf(b);
            }catch (Exception e){
                Log.d("Calc", e.getMessage());
            }
            default: return (Double.valueOf(a)/Double.valueOf(b));
        }
    }

    private boolean getResultado(){
        if(Operador == "") return false;
        String[] operation = muestra.split(Pattern.quote(Operador));

        if(operation.length < 2) return false;
        resultado = String.valueOf(operar(operation[0], operation[1], Operador));
        return true;
    }

    public void onClickEqual(View v){
        if(muestra == "") return;
        if(!getResultado()) return;
        pantalla.setText(muestra + "\n" + String.valueOf(resultado));
    }
    public void intent_hora(View v) {
        Date hora = new Date();
        SimpleTimeZone tz = new SimpleTimeZone(3600000,
                "Europe/Paris",
                Calendar.MARCH, -1, Calendar.SUNDAY,
                3600000, SimpleTimeZone.UTC_TIME,
                Calendar.OCTOBER, -1, Calendar.SUNDAY,
                3600000, SimpleTimeZone.UTC_TIME,
                3600000);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        df.setTimeZone(tz);
        String dato = df.format(hora);
        Intent intent= new Intent(this,HoraActivity.class);
        intent.putExtra("hora", dato);

        startActivity(intent);

    }
}