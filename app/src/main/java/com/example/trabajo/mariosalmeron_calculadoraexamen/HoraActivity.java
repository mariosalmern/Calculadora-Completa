package com.example.trabajo.mariosalmeron_calculadoraexamen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class HoraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora);

        String hora=getIntent().getStringExtra("hora");
        TextView t = (TextView) findViewById(R.id.hora);
        t.setText(t.getText() + " " + hora);
    }
    public void volver(View v){
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
