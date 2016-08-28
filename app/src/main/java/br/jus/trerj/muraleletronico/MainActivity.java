package br.jus.trerj.muraleletronico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.jus.trerj.muraleletronico.filter.PublicacaoFiltro;
import br.jus.trerj.muraleletronico.infra.AdvogadoWS;
import br.jus.trerj.muraleletronico.infra.PublicacaoWS;

public class MainActivity extends AppCompatActivity {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    private PublicacaoWS publicacaoWS = new PublicacaoWS(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PublicacaoFiltro.getInstance().setDataPublicacao(new Date());
        Button novoAluno = (Button) findViewById(R.id.btn_pesquisa_publicacoes);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.consultar();
    }

    public void consultar() {
        this.publicacaoWS.consultar();
    }
}
