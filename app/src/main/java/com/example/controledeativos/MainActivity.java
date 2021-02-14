package com.example.controledeativos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextQuantidade, editTextPrcoAcao, editTextTaxaImposto, editTextPorcentagemRetorno;
    Button btnCalcularTotal, btnCalculaRetorno;
    TextView textViewTotal, textViewPrecoVenda, textViewRetornoTotal, textViewGanhoCapital;

    AutoCompleteTextView autoCompleteTextView;
    String [] ticker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();

        ticker = getResources().getStringArray(R.array.tickers);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ticker);

        autoCompleteTextView.setAdapter(adapter);

        setBtnCalcularTotal();

    }
    public void inicializarComponentes (){

        editTextQuantidade = findViewById(R.id.editTextQuantidade);
        editTextPrcoAcao = findViewById(R.id.editTextPrcoAcao);
        editTextTaxaImposto = findViewById(R.id.editTextTaxaImposto);
        editTextPorcentagemRetorno = findViewById(R.id.editTextPorcentagemRetorno);
        btnCalcularTotal = findViewById(R.id.btnCalcularTotal);
        btnCalculaRetorno = findViewById(R.id.btnCalculaRetorno);
        textViewTotal = findViewById(R.id.textViewTotal);
        textViewRetornoTotal = findViewById(R.id.textViewRetornoTotal);
        textViewGanhoCapital = findViewById(R.id.textViewGanhoCapital);
        textViewPrecoVenda = findViewById(R.id.textViewPrecoVenda);
        autoCompleteTextView = findViewById(R.id.autoComplete);

    }
    public void setBtnCalcularTotal (){
        btnCalcularTotal.setOnClickListener(v -> {

            String autoComplete = autoCompleteTextView.getText().toString();
            int qtdAcoes = Integer.parseInt(editTextQuantidade.getText().toString());
            double precoAcao = Double.parseDouble(editTextPrcoAcao.getText().toString());
            double taxaImposto = Double.parseDouble(editTextTaxaImposto.getText().toString());
            double valorTotal = (precoAcao * qtdAcoes) + taxaImposto;



            textViewTotal.setText("Valor total: R$" + (float)valorTotal);

        });

        btnCalculaRetorno.setOnClickListener(v -> {

            int qtdAcoes = Integer.parseInt(editTextQuantidade.getText().toString());
            double precoAcao = Double.parseDouble(editTextPrcoAcao.getText().toString());
            double taxaImposto = Double.parseDouble(editTextTaxaImposto.getText().toString());
            double valorTotal = (precoAcao * qtdAcoes) + taxaImposto;

            double taxaDeRetorno = Double.parseDouble(editTextPorcentagemRetorno.getText().toString());
            double retornoTotal = (valorTotal * taxaDeRetorno) / 100;
            double valorAcumulado = valorTotal + retornoTotal;

            textViewPrecoVenda.setText("Preço alvo de venda: " + (float)(valorAcumulado / qtdAcoes));
            textViewRetornoTotal.setText("Valor total do retorno: " + (float)(valorAcumulado));
            textViewGanhoCapital.setText("Valor de ganho de Capital: " + (float)(retornoTotal));
        });
    }
    public void alertaErro (String erro){
        Toast.makeText(this, erro, Toast.LENGTH_SHORT).show();
    }

}