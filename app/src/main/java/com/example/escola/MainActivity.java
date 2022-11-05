package com.example.escola;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editN1, editN2;
    TextView txtM, txtSit;
    LinearLayout layResult;
    ImageView imgSit;

//    1º comando para ocultar o teclado
    InputMethodManager methodManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Ligando codigo aos componentes da view
        editN1 = findViewById(R.id.editN1Id);
        editN2 = findViewById(R.id.editN2Id);
        txtM = findViewById(R.id.txtMId);
        txtSit = findViewById(R.id.txtSitId);
        layResult = findViewById(R.id.layResultId);
        imgSit = findViewById(R.id.imgSitId);

        //    2º comando para ocultar o teclado
        methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        ocultando componentes
        layResult.setVisibility(View.INVISIBLE);
    }

    public void calcular(View view) {
//        Vaidando os dados
        boolean ok = true;
        if (editN1.getText().toString().trim().isEmpty()) {
            ok = false;
            editN1.setError(getText(R.string.msgError));
        }
        if (editN2.getText().toString().trim().isEmpty()) {
            ok = false;
            editN2.setError(getText(R.string.msgError));
        }
        if (ok) {
            //    3º comando para ocultar o teclado
methodManager.hideSoftInputFromWindow(editN1.getWindowToken(), 0);

//            mostrando conteudo oculto
            layResult.setVisibility(View.VISIBLE);

//        Fazendo a soma
            double n1 = Double.parseDouble(editN1.getText().toString());
            double n2 = Double.parseDouble(editN2.getText().toString());
            double m = (n1 + n2) / 2;

//        Resultado da media
            txtM.setText(String.format("%.1f", m));

//        Resultado em situação
            if (m < 5) {
//            reprovado
                txtSit.setText(getString(R.string.strSitRp));
//                Formas de colocar cor 1
                txtSit.setTextColor(Color.RED);

//                Mensagem Toast
                Toast.makeText(getApplicationContext(), getString(R.string.strMsgRp), Toast.LENGTH_SHORT).show();

//                Mudando imagem
                imgSit.setImageResource(R.drawable.emojireprovado);
            } else if (m < 7) {
//            recuperação
                txtSit.setText(getString(R.string.strSitRc));
                //                Formas de colocar cor 2

                txtSit.setTextColor(Color.parseColor("#21219c"));
//                Mensagem Toast
                Toast.makeText(getApplicationContext(), getString(R.string.strMsgRc), Toast.LENGTH_SHORT).show();

                //                Mudando imagem
                imgSit.setImageResource(R.drawable.emojirecuperacao);
            } else {
//            aprovado
                txtSit.setText(getString(R.string.strSitAp));
                //                Formas de colocar cor 3
                txtSit.setTextColor(getResources().getColor(R.color.colorAprovado));

//                Mensagem Toast
                Toast.makeText(getApplicationContext(), getString(R.string.strMsgAp), Toast.LENGTH_SHORT).show();

                //                Mudando imagem
                imgSit.setImageResource(R.drawable.emojiaprovado);
            }
        }
    }
}