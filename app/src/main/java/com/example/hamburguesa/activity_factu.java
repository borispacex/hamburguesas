package com.example.hamburguesa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity_factu extends AppCompatActivity {

    private String thambur;
    private String tcantidad;
    private String tsuma;
    private double ttotal;

    private EditText ecalculado;
    private String estado;
    private TextView testado;

    private TextView hambur;
    private TextView cantidad;
    private TextView suma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factu);


        ecalculado = (EditText) findViewById(R.id.calculado);
        testado = (TextView) findViewById(R.id.estado);

        hambur = (TextView) findViewById(R.id.hambur);
        cantidad = (TextView) findViewById(R.id.cantidad);
        suma = (TextView) findViewById(R.id.suma);

        Bundle b = getIntent().getExtras();
        thambur = b.getString("totalhamburguesas");
        tcantidad = b.getString("totalcantidad");
        tsuma = b.getString("totalsuma");
        ttotal = b.getDouble("total");


        // Toast toast = Toast.makeText(this, "THAMBUR: "+thambur+" - TCANTIDAD: "+tcantidad+" - TSUMA:"+tsuma+" - TOTAL: "+ttotal, Toast.LENGTH_SHORT);
        // toast.show();

        hambur.setText(thambur);
        cantidad.setText(tcantidad);
        suma.setText(tsuma);
        ecalculado.setText(ttotal+"");

    }

    public void retornar(View view) {
        finish();
        hambur.setText("0");
        cantidad.setText("0");
        suma.setText("0");
        ecalculado.setText("0");

        thambur = "";
        tcantidad = "";
        tsuma = "";
        ttotal = 0;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
