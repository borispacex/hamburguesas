package com.example.hamburguesa;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;
// import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int indice = 0;
    int imagenes[] = {
            R.drawable.hnormal,
            R.drawable.hmediokilo,
            R.drawable.hconhuevo,
            R.drawable.hconfideitos,
            R.drawable.hdoble,
            R.drawable.hgemelas,
            R.drawable.hmariajuana,
            R.drawable.hvegetal,
            R.drawable.hpollo
    };
    String textos[] = {
            "H. Normal",
            "H. 1/2 Kilo",
            "H.c/huevo",
            "H.c/Fideitos",
            "H. Doble",
            "H. Gemelas",
            "H. MariaJuana",
            "H.c/vegetales",
            "H. Pollo c/papas"
    };
    private ImageView pimg;
    private TextView nomImg;
    private TextView tprecios;

    private EditText epedido;
    private EditText epagar;

    private Button btnDevolver;
    private Button btnComprar;


    double precio[] = {
            12, 18, 13.5, 15 ,18, 21, 25, 15, 12
    };

    int pedido[] = {
            0, 0, 0, 0, 0, 0, 0, 0, 0
    };

    double apagar[] = {
            0, 0, 0, 0, 0, 0, 0, 0, 0
    };

    double suma = 0;
    int cantidad = 0;

    String hamburguesas = "";
    String cantidades = "";
    String sumas = "";
    double totales = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pimg = (ImageView) findViewById(R.id.imageView);
        nomImg = (TextView) findViewById(R.id.textView );
        tprecios = (TextView) findViewById(R.id.textViewPrecio);
        epedido = (EditText) findViewById(R.id.inputpedido);
        epagar = (EditText) findViewById(R.id.inputpagar);
        btnComprar = (Button) findViewById(R.id.comprar);
        btnDevolver = (Button) findViewById(R.id.devolver);

        hamburguesas = "";
        cantidades = "";
        sumas = "";
        totales = 0;
    }


    public void antes(View vista) {
        indice--;
        if (indice<0) indice=8;
        nomImg.setText(textos[indice]+" ");
        pimg.setImageResource(imagenes[indice]);
        tprecios.setText(precio[indice]+"");

        epagar.setText(suma+"");
        epedido.setText(pedido[indice]+"");

        if (pedido[indice] == 0) {
            btnDevolver.setEnabled(false);
        }else{
            btnDevolver.setEnabled(true);
        }
    }

    public void siguiente(View vista) {
        indice++;
        if (indice>8) indice=0;
        nomImg.setText(textos[indice]+" ");
        pimg.setImageResource(imagenes[indice]);
        tprecios.setText(precio[indice]+"");

        epagar.setText(suma+"");
        epedido.setText(pedido[indice]+"");

        if (pedido[indice] == 0) {
            btnDevolver.setEnabled(false);
        }else{
            btnDevolver.setEnabled(true);
        }
    }

    public void devolver(View view) {
        if (pedido[indice] > 0) {

            pedido[indice] = pedido[indice] - 1;
            apagar[indice] = apagar[indice] - precio[indice];

            suma = suma - precio[indice];
            cantidad = cantidad - 1;

            epagar.setText(suma+"");
            epedido.setText(pedido[indice]+"");
        }
        if (pedido[indice] == 0) btnDevolver.setEnabled(false);
    }

    public void f(View view) {
        Toast toast = Toast.makeText(this, "Pedido total: "+cantidad+"   -   "+"A pagar total: "+suma, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void c(View view) {
        for (int i=0; i< 9; i++) {
            pedido[i] = 0;
            apagar[i] = 0;
        }
        epagar.setText("0");
        epedido.setText("0");
        suma = 0;
        cantidad = 0;
        btnDevolver.setEnabled(false);

        hamburguesas = "";
        cantidades = "";
        sumas = "";
    }

    public void comprar(View view) {
        pedido[indice] = pedido[indice] + 1;
        apagar[indice] = apagar[indice] + precio[indice];

        suma = suma + precio[indice];
        cantidad = cantidad + 1;

        epagar.setText(suma+"");
        epedido.setText(pedido[indice]+"");

        btnDevolver.setEnabled(true);
    }

    public void calcular(View view) {

        Intent vd = new Intent(this, activity_factu.class);

        totales = 0;
        for (int i=0; i< 9; i++) {
            if (pedido[i] != 0) {
                hamburguesas = hamburguesas + textos[i] + "\n";
                cantidades = cantidades + pedido[i] + "\n";
                sumas = sumas + apagar[i] + "\n";
                totales = totales + apagar[i];
            }
        }

        vd.putExtra("totalhamburguesas", hamburguesas);
        vd.putExtra("totalcantidad", cantidades);
        vd.putExtra("totalsuma", sumas);
        vd.putExtra("total", totales);

        hamburguesas = "";
        cantidades = "";
        sumas = "";
        totales = 0;
        startActivity(vd);
    }

    public void fin(View view) {
        finish();
    }
}
