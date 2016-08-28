package br.jus.trerj.muraleletronico.helpers;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.jus.trerj.muraleletronico.FormularioActivity;
import br.jus.trerj.muraleletronico.R;
import br.jus.trerj.muraleletronico.modelo.Advogado;

/**
 * Created by avelinoferreiragf on 28/08/16.
 */
public class AdvogadoHelper {

    private List<Advogado> advogadosDisponiveis;
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
        this.advogadosDisponiveis = advogados;
        List<String> toStringAdvogados = new ArrayList<String>();
        for(Advogado advogado : advogados) {
            toStringAdvogados.add(advogado.toString());
        }
        if (toStringAdvogados.size() == 0) {
            toStringAdvogados.add("Nenhum advogado encontrado");
        } else {
            toStringAdvogados.set(0, "Caso deseje, escolha um advogado.");
        }
        this.setSpinnerValues(toStringAdvogados);
    }

    public void avisarUsuarioDoFinalDoCarregamentoAssincrono() {
        this.activity.findViewById(R.id.loading_panel_formulario).setVisibility(View.GONE);
        if (this.advogadosDisponiveis == null || this.advogadosDisponiveis.size() == 0) {
            Toast.makeText(this.activity, this.activity.getString(R.string.formulario_advogados_sem_advogados), Toast.LENGTH_SHORT).show();
        } else if (advogadosDisponiveis.size() == 1) {
            Toast.makeText(this.activity, this.activity.getString(R.string.formulario_advogados_com_advogado), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.activity, advogadosDisponiveis.size() + this.activity.getString(R.string.formulario_advogados_com_advogados), Toast.LENGTH_SHORT).show();
        }
   }

    private void setSpinnerValues(List<String> values) {
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
