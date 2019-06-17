package br.com.rodrigoamad.primeiratelachatbotcris;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "br.com.rodrigoamad.primeiratelachatbotcris";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cancelar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        final EditText editTextNome = (EditText) findViewById(R.id.nome);
        final EditText editTextTelefone = (EditText) findViewById(R.id.telefone);
        final EditText editTextEmail = (EditText) findViewById(R.id.email);
        editTextNome.setText("");
        editTextTelefone.setText("");
        editTextEmail.setText("");
    }

    public void cadastrar(View view){
        EditText editTextNome = (EditText) findViewById(R.id.nome);
        EditText editTextTelefone = (EditText) findViewById(R.id.telefone);
        EditText editTextEmail = (EditText) findViewById(R.id.email);

        if (editTextNome.getText().toString().length() == 0) {
           editTextNome.setError("Digite seu nome!");
        }else if (editTextTelefone.getText().toString().length() < 15) { //fiz o tratamento do campo e coloquei "< 15" p/ não passar mesmo faltando um número
            editTextTelefone.setError("Digite seu celular!");
        }else if (editTextEmail.getText().toString().length() == 0){
           editTextEmail.setError("Digite seu e-mail!");
        }else{
            Intent intent = new Intent(this, SegundaTelaChatBotCris.class);
            startActivity(intent);
        }
    }
}