package br.usjt.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.usjt.R;
import br.usjt.data.DataSource;

public class LocalizacoesActivity extends AppCompatActivity {

    private static final String INTENT_EXTRA = "intent-extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacoes);
        DataSource dataSource = (DataSource) getIntent().getSerializableExtra(INTENT_EXTRA);


        RecyclerView localizacoesRecyclerView = findViewById(R.id.localizacoes_list);
        localizacoesRecyclerView.setLayoutManager(
                new LinearLayoutManager(
                        this));


        localizacoesRecyclerView.setNestedScrollingEnabled(false);

        localizacoesRecyclerView.setAdapter(
                new LocalizacoesAdapter(
                        dataSource.localizacoes));
    }

    public static void invocaActivity(Context context, DataSource dataSource) {
        Intent intent = new Intent(context, LocalizacoesActivity.class);
        intent.putExtra(INTENT_EXTRA, dataSource);

        context.startActivity(intent);
    }
}
