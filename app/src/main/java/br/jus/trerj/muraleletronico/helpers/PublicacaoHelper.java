package br.jus.trerj.muraleletronico.helpers;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.jus.trerj.muraleletronico.MainActivity;
import br.jus.trerj.muraleletronico.R;
import br.jus.trerj.muraleletronico.adapters.PublicacaoAdapter;
import br.jus.trerj.muraleletronico.modelo.Publicacao;

/**
 * Created by avelinoferreiragf on 29/08/16.
 */
public class PublicacaoHelper {
    private MainActivity activity;

    public PublicacaoHelper(MainActivity activity) {
        this.activity = activity;
    }

    public void carregar(List<Publicacao> publicacoes) {
        PublicacaoAdapter adapter = new PublicacaoAdapter(this.activity, R.layout.lista_publicacoes_item, publicacoes);
        ListView listaPublicacoes = (ListView) this.activity.findViewById(R.id.lista_publicacoes);
        listaPublicacoes.setAdapter(adapter);
    }

    public void avisarUsuarioDoInicioDoCarregamentoAssincrono() {
        this.activity.findViewById(R.id.loading_panel).setVisibility(View.VISIBLE);
    }

    public void avisarUsuarioDoFinalDoCarregamentoAssincrono(List<Publicacao> publicacoes) {
        this.activity.findViewById(R.id.loading_panel).setVisibility(View.GONE);
        if (publicacoes == null || publicacoes.size() == 0) {
            Toast.makeText(this.activity, R.string.lista_publicacao_sem_publicacoes, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this.activity, "Foram carregadas " + publicacoes.size() + " publicações do Mural Eletrônico", Toast.LENGTH_SHORT).show();
        }
    }
}