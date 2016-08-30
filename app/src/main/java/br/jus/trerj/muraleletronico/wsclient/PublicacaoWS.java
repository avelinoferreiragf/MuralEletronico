package br.jus.trerj.muraleletronico.wsclient;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

import br.jus.trerj.muraleletronico.MainActivity;
import br.jus.trerj.muraleletronico.filter.PublicacaoFiltro;
import br.jus.trerj.muraleletronico.helpers.PublicacaoHelper;
import br.jus.trerj.muraleletronico.loaders.PublicacaoLoader;
import br.jus.trerj.muraleletronico.modelo.Publicacao;
import cz.msebera.android.httpclient.Header;

/**
 * Created by avelinoferreiragf on 27/08/16.
 */
public class PublicacaoWS {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    private static final String URL = "consulta.wsmural";

    private PublicacaoHelper helper;
    private PublicacaoLoader loader = new PublicacaoLoader();


    public PublicacaoWS(MainActivity activity) {
        this.helper = new PublicacaoHelper(activity);
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
            strIdAdvogado = filtro.getAdvogado().getIdAdvogado().toString();
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

        this.helper.avisarUsuarioDoInicioDoCarregamentoAssincrono();
        MuralEletronicoRestClient.get(URL, parametros, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TRATAR POSSIVEL ERRO
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray publicacoesJson) {
                List<Publicacao> publicacoes = PublicacaoWS.this.loader.carregar(publicacoesJson);
                PublicacaoWS.this.helper.carregar(publicacoes);
                PublicacaoWS.this.helper.avisarUsuarioDoFinalDoCarregamentoAssincrono(publicacoes);
            }
        });
    }
}
