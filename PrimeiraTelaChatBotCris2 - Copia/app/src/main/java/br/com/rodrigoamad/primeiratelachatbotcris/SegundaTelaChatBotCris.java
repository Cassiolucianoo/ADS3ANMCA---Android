package br.com.rodrigoamad.primeiratelachatbotcris;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.rodrigoamad.primeiratelachatbotcris.R;

public class SegundaTelaChatBotCris extends AppCompatActivity {
    TextView messagesTextView;
    EditText inputEditText;
    Button sendButton;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_tela_chat_bot_cris);

        context = this;
        messagesTextView = findViewById(R.id.messagesTextView);
        inputEditText = findViewById(R.id.inputEditText);
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = inputEditText.getText().toString();
                messagesTextView.append(Html.fromHtml("<p><b>EU:</b> " + input + "</p>"));
                inputEditText.setText("");

                getResponse(input);
            }
        });

    }

    private void getResponse(String input) {

        String workspaceId = "8de0e9c8-0d0c-4654-a5a2-8b6feaf92e12";
        String urlAssistant = "https://gateway.watsonplatform.net/assistant/api/v1/workspaces/"+workspaceId+"/message?version=2019-02-28";
        String authentication = "YXBpa2V5OjU0Z0J6cHhKMjlkcUZaV3VLSHk1VXB3ZzJOaVVKVDVXU0dwc0FmOFg2QUl3";

        //creo la estructura json de input del usuario
        JSONObject inputJsonObject = new JSONObject();
        try {
            inputJsonObject.put("text",input);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("input", inputJsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(urlAssistant)
                .addHeaders("Content-Type","application/json")
                .addHeaders("Authorization","Basic " + authentication)
                .addJSONObjectBody(jsonBody)
                .setPriority(Priority.HIGH)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String outputAssistant = "";


                        try {
                            String outputJsonObject = response.getJSONObject("output").getJSONArray("text").getString(0);
                            messagesTextView.append(Html.fromHtml("<p><b>CRIS:</b> " + outputJsonObject + "</p>"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context,"SEM ACESSO A INTERNET", Toast.LENGTH_LONG).show();
                    }
                });
    }
}





