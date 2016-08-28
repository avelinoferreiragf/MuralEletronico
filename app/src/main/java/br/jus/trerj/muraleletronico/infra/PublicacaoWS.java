package br.jus.trerj.muraleletronico.infra;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

import br.jus.trerj.muraleletronico.MainActivity;
import br.jus.trerj.muraleletronico.R;
import br.jus.trerj.muraleletronico.adapters.PublicacaoAdapter;
import br.jus.trerj.muraleletronico.filter.PublicacaoFiltro;
import br.jus.trerj.muraleletronico.modelo.Publicacao;
import cz.msebera.android.httpclient.Header;

/**
 * Created by avelinoferreiragf on 27/08/16.
 */
public class PublicacaoWS {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    private static final String URL = "consulta.wsmural";

    private MainActivity activity;
    private PublicacaoLoader loader = new PublicacaoLoader();

    public PublicacaoWS(MainActivity activity) {
        this.activity = activity;
    }

    public void consultar() {
        PublicacaoFiltro filtro = PublicacaoFiltro.getInstance();

        String strDataPublicacao = "";
        String strIdAdvogado = "";
        String strIsSJD = "";

        if (filtro.getDataPublicacao() != null) {
           strDataPublicacao = SDF.format(filtro.getDataPublicacao());
        }

        if (filtro.getAdvogado() != null) {
            strIdAdvogado = filtro.getAdvogado().getId().toString();
        }

        if (filtro.getSJD() != null && filtro.getSJD()) {
            strIsSJD = "SJD";
        }

        RequestParams parametros = new RequestParams();
        parametros.add("dataPublicacao", strDataPublicacao);
        parametros.add("numeroProcesso", filtro.getNumeroProcesso());
        parametros.add("numeroProtocolo", filtro.getNumeroProtocolo());
        parametros.add("apenasSJD", strIsSJD);
        parametros.add("idAdvogado", strIdAdvogado);

        this.activity.findViewById(R.id.loading_panel).setVisibility(View.VISIBLE);
        MuralEletronicoRestClient.get(URL, parametros, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TRATAR POSSIVEL ERRO
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray publicacoesJson) {
                try {

                    List<Publicacao> publicacoes = PublicacaoWS.this.loader.carregar(publicacoesJson);
                    PublicacaoAdapter adapter = new PublicacaoAdapter(PublicacaoWS.this.activity, R.layout.lista_publicacoes_item, publicacoes);
                    ListView listaPublicacoes = (ListView) PublicacaoWS.this.activity.findViewById(R.id.lista_publicacoes);
                    listaPublicacoes.setAdapter(adapter);
                    PublicacaoWS.this.activity.findViewById(R.id.loading_panel).setVisibility(View.GONE);
                    Toast.makeText(PublicacaoWS.this.activity, "Foram carregadas " + publicacoes.size() + " publicações do Mural Eletrônico", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
