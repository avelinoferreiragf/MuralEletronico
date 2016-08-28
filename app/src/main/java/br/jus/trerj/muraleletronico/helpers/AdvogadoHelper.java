package br.jus.trerj.muraleletronico.helpers;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.jus.trerj.muraleletronico.FormularioActivity;
import br.jus.trerj.muraleletronico.R;
import br.jus.trerj.muraleletronico.filter.PublicacaoFiltro;
import br.jus.trerj.muraleletronico.modelo.Advogado;

/**
 * O Advogado Helper recebe informações do AdvogadoWS e "intermedeia" a comunicação com a Activity
 * do formulário. Ele manipula o Advogados Spinner.
 * Created by avelinoferreiragf on 28/08/16.
 */
public class AdvogadoHelper {

    private FormularioActivity activity;

    public AdvogadoHelper(FormularioActivity activity) {
        this.activity = activity;
    }

    public void iniciar() {
        this.setSpinnerValues(new ArrayList<String>());
    }

    public void avisarUsuarioDoInicioDoCarregamentoAssincrono() {
        this.activity.findViewById(R.id.loading_panel_formulario).setVisibility(View.VISIBLE);
    }

    public void carregarAdvogadosDisponiveis(List<Advogado> advogados) {
        PublicacaoFiltro.getInstance().setAdvogadosDisponiveis(advogados);
        if (advogados == null) {
            return;
        }
        List<String> toStringAdvogados = new ArrayList<String>();
        for(Advogado advogado : advogados) {
            toStringAdvogados.add(advogado.toString());
        }
        if (toStringAdvogados.size() == 0) {
            toStringAdvogados.add(this.activity.getString(R.string.formulario_advogados_sem_advogados_short));
        } else {
            toStringAdvogados.add(0, this.activity.getString(R.string.formulario_advogados_selecione));
        }
        this.setSpinnerValues(toStringAdvogados);

    }
    public void selecionarAdvogadoSelecionado() {
        PublicacaoFiltro publicacaoFiltro = PublicacaoFiltro.getInstance();
        Advogado advogadoSelecionado = publicacaoFiltro.getAdvogado();
        if (advogadoSelecionado == null) {
            return;
        }
        Integer pos = publicacaoFiltro.getAdvogadosDisponiveis().indexOf(advogadoSelecionado);
        if (pos < 0) {
            return;
        }
        Spinner spinner = (Spinner) this.activity.findViewById(R.id.formulario_advogados);
        spinner.setSelection(pos);

    }


    public void avisarUsuarioDoFinalDoCarregamentoAssincrono() {
        PublicacaoFiltro publicacaoFiltro = PublicacaoFiltro.getInstance();
        this.activity.findViewById(R.id.loading_panel_formulario).setVisibility(View.GONE);
        if (!publicacaoFiltro.hasAdvogados()) {
            Toast.makeText(this.activity, this.activity.getString(R.string.formulario_advogados_sem_advogados), Toast.LENGTH_SHORT).show();
        } else if (publicacaoFiltro.getQuantidadeAdvogadosDisponives() == 1) {
            Toast.makeText(this.activity, this.activity.getString(R.string.formulario_advogados_com_advogado), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.activity, publicacaoFiltro.getQuantidadeAdvogadosDisponives() + " " + this.activity.getString(R.string.formulario_advogados_com_advogados), Toast.LENGTH_SHORT).show();
        }
    }

    public void avisarUsuarioDoFinalDoCarregamentoAssincrono(Exception e) {
        this.activity.findViewById(R.id.loading_panel_formulario).setVisibility(View.GONE);
        Toast.makeText(this.activity, PublicacaoFiltro.getInstance().getQuantidadeAdvogadosDisponives() + this.activity.getString(R.string.formulario_advogados_com_advogados), Toast.LENGTH_SHORT).show();
    }

    private void setSpinnerValues(List<String> values) {
        if (values == null) {
            return;
        }

        String[] valuesArray = new String[values.size()];
        valuesArray = values.toArray(valuesArray);
        Spinner spinner = (Spinner) this.activity.findViewById(R.id.formulario_advogados);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.activity, android.R.layout.simple_spinner_dropdown_item, (String[]) valuesArray);
        spinner.setAdapter(adapter);
    }
}
