package br.com.livrospockframework.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TelaInicial extends AppCompatActivity {

    private EditText editNum1;
    private EditText editNum2;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        this.editNum1 = findViewById(R.id.editNum1);
        this.editNum2 = findViewById(R.id.editNum2);
        this.tvResultado = findViewById(R.id.tvResultado);
    }

    public void dividir(View botao) {

        Double numero1 = Double.valueOf(this.editNum1.getText().toString());
        Double numero2 = Double.valueOf(this.editNum2.getText().toString());

        this.tvResultado.setText(
            (numero2 == 0) ? "Não sei dividir por 0" : (numero1 / numero2)+""
        );

    }
}
