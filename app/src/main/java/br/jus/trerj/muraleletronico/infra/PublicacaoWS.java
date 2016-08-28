package br.jus.trerj.muraleletronico.infra;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.jus.trerj.muraleletronico.MainActivity;
import br.jus.trerj.muraleletronico.R;
import br.jus.trerj.muraleletronico.filter.PublicacaoFiltro;
import br.jus.trerj.muraleletronico.modelo.Municipio;
import br.jus.trerj.muraleletronico.modelo.Publicacao;
import cz.msebera.android.httpclient.Header;

/**
 * Created by avelinoferreiragf on 27/08/16.
 */
public class PublicacaoWS {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat SDF_DATA_PUBLICACAO = new SimpleDateFormat("yyyy-MM-dd");
    private static final String URL = "consulta.wsmural";

    private MainActivity activity;

    public PublicacaoWS(MainActivity activity) {
        this.activity = activity;
    }

    public void consultar() {
        PublicacaoFiltro filtro = PublicacaoFiltro.getInstance();

        String strDataPublicacao = "";
        String strIsSJD = "";
        if (filtro.getDataPublicacao() != null) {
           strDataPublicacao = SDF.format(filtro.getDataPublicacao());
        }

        if (filtro.getSJD() != null && filtro.getSJD()) {
            strIsSJD = "SJD";
        }
        RequestParams parametros = new RequestParams();
        parametros.add("dataPublicacao", strDataPublicacao);
        parametros.add("numeroProcesso", filtro.getNumeroProcesso());
        parametros.add("numeroProtocolo", filtro.getNumeroProtocolo());
        parametros.add("apenasSJD", strIsSJD);

        this.activity.findViewById(R.id.loading_panel).setVisibility(View.VISIBLE);
        MuralEletronicoRestClient.get(URL, parametros, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TRATAR POSSIVEL ERRO
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray publicacoesJson) {
                try {
                    if (publicacoesJson == null) {
                        return;
                    }
                    List<Publicacao> publicacoes = new ArrayList<Publicacao>();
                    for(int i = 0; i < publicacoesJson.length(); i++) {
                        JSONObject publicacaoJSON = publicacoesJson.getJSONObject(i);
                        String id = publicacaoJSON.getString("id");
                        String numeroProcesso = publicacaoJSON.getString("numeroProcesso");
                        String numeroProcessoCompleto = publicacaoJSON.getString("numeroProcessoCompleto");
                        String descricaoNumeroDoProcesso = publicacaoJSON.getString("descricaoNumeroDoProcesso");
                        String siglaClasseProcesso = publicacaoJSON.getString("siglaClasseProcesso");
                        Long numeroProtocolo = publicacaoJSON.getLong("numeroProtocolo");
                        String origem = publicacaoJSON.getString("origem");
                        Date dataPublicacao = SDF_DATA_PUBLICACAO.parse(publicacaoJSON.getString("dataPublicacao"));
                        Date dataAndamento = new Date(publicacaoJSON.getLong("dataAndamento"));
                        JSONObject municipioJSON = publicacaoJSON.getJSONObject("municipio");
                        Integer idMunicipio = municipioJSON.getInt("id");
                        String nomeMunicipio = municipioJSON.getString("nome");
                        String siglaUnidadePublicadora = publicacaoJSON.getString("siglaUnidadePublicadora");
                        Boolean isSJD = publicacaoJSON.getBoolean("isSJD");
                        String tipoPublicacao = publicacaoJSON.getString("tipoPublicacao");

                        Municipio municipio = new Municipio();
                        municipio.setId(idMunicipio);
                        municipio.setNome(nomeMunicipio);

                        Publicacao publicacao = new Publicacao();
                        publicacao.setId(id);
                        publicacao.setNumeroDoProcesso(numeroProcesso);
                        publicacao.setNumeroDoProcessoCompleto(numeroProcessoCompleto);
                        publicacao.setDescricaoNumeroProcesso(descricaoNumeroDoProcesso);
                        publicacao.setSiglaClasseProcesso(siglaClasseProcesso);
                        publicacao.setNumeroProtocolo(numeroProtocolo);
                        publicacao.setOrigem(origem);
                        publicacao.setDataPublicacao(dataPublicacao);
                        publicacao.setDataAndamento(dataAndamento);
                        publicacao.setMunicipio(municipio);
                        publicacao.setSiglaUnidadePublicadora(siglaUnidadePublicadora);
                        publicacao.setSJD(isSJD);
                        publicacao.setTipo(tipoPublicacao);

                        publicacoes.add(publicacao);
                        System.out.println(publicacaoJSON);
                    }
                    ArrayAdapter<Publicacao> adapter = new ArrayAdapter<Publicacao>(PublicacaoWS.this.activity, android.R.layout.simple_list_item_1, publicacoes);
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
