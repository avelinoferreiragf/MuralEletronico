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
import br.jus.trerj.muraleletronico.filter.PublicacaoFiltro;
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

    private FormularioActivity activity;
    private AdvogadoLoader loader = new AdvogadoLoader();

    public AdvogadoWS(FormularioActivity activity) {
        this.activity = activity;
    }

    public void consultar(Date dataPublicacao) {
        this.inicializarSpinnerAdvogados();

        String strDataPublicacao = "";
        if (dataPublicacao == null) {
            return;
        }
        strDataPublicacao = SDF.format(dataPublicacao);

        RequestParams parametros = new RequestParams();
        parametros.add("dataPublicacao", strDataPublicacao);

        this.activity.findViewById(R.id.loading_panel_formulario).setVisibility(View.VISIBLE);
        MuralEletronicoRestClient.get(URL, parametros, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TRATAR POSSIVEL ERRO
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray advogadosJson) {
                try {

                    Set<Advogado> advogados = AdvogadoWS.this.loader.carregar(advogadosJson);
                    List<String> toStringAdvogados = AdvogadoWS.this.loader.transformarAdvogadosParaListaDeNomesEOAB(advogados);

                    AdvogadoWS.this.setSpinner(toStringAdvogados);
                    AdvogadoWS.this.activity.findViewById(R.id.loading_panel_formulario).setVisibility(View.GONE);
                    Toast.makeText(AdvogadoWS.this.activity, advogados.size() + " advogados com publicações na data.", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void inicializarSpinnerAdvogados() {
        this.setSpinner(new ArrayList<String>());
    }
    private void setSpinner(List<String> values) {
        if (values == null) {
            return;
        }

        String[] valuesArray = new String[values.size()];
        valuesArray = values.toArray(valuesArray);
        Spinner spinner = (Spinner) this.activity.findViewById(R.id.formulario_advogados);

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this.activity, android.R.layout.simple_spinner_dropdown_item, (String[]) valuesArray);
        spinner.setAdapter(adapter);
    }
}
