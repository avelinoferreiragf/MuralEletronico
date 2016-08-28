package br.jus.trerj.muraleletronico.infra;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.jus.trerj.muraleletronico.FormularioActivity;
import br.jus.trerj.muraleletronico.MainActivity;
import br.jus.trerj.muraleletronico.R;
import br.jus.trerj.muraleletronico.exceptions.TRERJNonPresentableException;
import br.jus.trerj.muraleletronico.filter.PublicacaoFiltro;
import br.jus.trerj.muraleletronico.helpers.AdvogadoHelper;
import br.jus.trerj.muraleletronico.modelo.Advogado;
import br.jus.trerj.muraleletronico.modelo.Municipio;
import br.jus.trerj.muraleletronico.modelo.Publicacao;
import cz.msebera.android.httpclient.Header;

/**
 * Created by avelinoferreiragf on 27/08/16.
 */
public class AdvogadoWS {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    private static final String URL = "advogados.wsmural";

    private AdvogadoHelper helper;
    private AdvogadoLoader loader = new AdvogadoLoader();

    public AdvogadoWS(AdvogadoHelper helper) {
        this.helper = helper;
    }

    private List<Advogado> advogados;

    public List<Advogado> getAdvogados() {
        return advogados;
    }

    public void consultar(Date dataPublicacao) {
        this.helper.iniciar();
        this.advogados = new ArrayList<Advogado>();

        String strDataPublicacao = "";
        if (dataPublicacao == null) {
            return;
        }
        strDataPublicacao = SDF.format(dataPublicacao);

        RequestParams parametros = new RequestParams();
        parametros.add("dataPublicacao", strDataPublicacao);

        this.helper.avisarUsuarioDoInicioDoCarregamentoAssincrono();
        MuralEletronicoRestClient.get(URL, parametros, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TRATAR POSSIVEL ERRO
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray advogadosJson) {
                try {

                    List<Advogado> advogados = AdvogadoWS.this.loader.carregar(advogadosJson);
                    AdvogadoWS.this.helper.carregarAdvogadosDisponiveis(advogados);

                    AdvogadoWS.this.helper.avisarUsuarioDoFinalDoCarregamentoAssincrono();

                } catch (Exception e) {
                    throw new TRERJNonPresentableException(e);
                }
            }
        });
    }
}
