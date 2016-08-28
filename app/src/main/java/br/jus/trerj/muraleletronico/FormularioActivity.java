package br.jus.trerj.muraleletronico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.jus.trerj.muraleletronico.filter.PublicacaoFiltro;
import br.jus.trerj.muraleletronico.helpers.AdvogadoHelper;
import br.jus.trerj.muraleletronico.infra.AdvogadoWS;
import br.jus.trerj.muraleletronico.modelo.Advogado;
import br.jus.trerj.muraleletronico.modelo.Publicacao;
import br.jus.trerj.muraleletronico.util.PropriedadesFormularioUtil;

public class FormularioActivity extends AppCompatActivity {


    private AdvogadoHelper advogadoHelper = new AdvogadoHelper(this);
    private AdvogadoWS advogadoWS = new AdvogadoWS(advogadoHelper);
    private List<Advogado> advogadosDisponiveis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        this.advogadosDisponiveis = new ArrayList<Advogado>();

        PublicacaoFiltro filtro = PublicacaoFiltro.getInstance();

        this.advogadoWS.consultar(filtro.getDataPublicacao());
        PropriedadesFormularioUtil.setPropriedade(this, R.id.formulario_numero_processo, filtro.getNumeroProcesso());
        PropriedadesFormularioUtil.setPropriedade(this, R.id.formulario_numero_protocolo, filtro.getNumeroProtocolo());
        PropriedadesFormularioUtil.setPropriedade(this, R.id.formulario_data_publicacao, filtro.getDataPublicacao());
        PropriedadesFormularioUtil.setPropriedade(this, R.id.formulario_is_sjd, filtro.getSJD());

        this.setDataPublicacaoListner();
        this.setAdvogadoListner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setDataPublicacaoListner() {
        EditText edtDataPublicacao = (EditText) this.findViewById(R.id.formulario_data_publicacao);

        edtDataPublicacao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Date dataPublicacao = PropriedadesFormularioUtil.getPropriedadeDate(s);
                FormularioActivity.this.advogadoWS.consultar(dataPublicacao);
            }
        });

    }

    private void setAdvogadoListner() {
        Spinner advogadosSpinner = (Spinner) this.findViewById(R.id.formulario_advogados);

        advogadosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                this.informarParametrosDaConsulta();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void informarParametrosDaConsulta() {
        PublicacaoFiltro filtro = PublicacaoFiltro.getInstance();

        Date dataPublicacao = PropriedadesFormularioUtil.getPropriedadeDate(this, R.id.formulario_data_publicacao);
        String numeroProcesso = PropriedadesFormularioUtil.getPropriedadeString(this, R.id.formulario_numero_processo);
        String numeroProtocolo = PropriedadesFormularioUtil.getPropriedadeString(this, R.id.formulario_numero_protocolo);

        filtro.setDataPublicacao(dataPublicacao);
        filtro.setNumeroProcesso(numeroProcesso);
        filtro.setNumeroProtocolo(numeroProtocolo);
        filtro.setSJD((((CheckBox) this.findViewById(R.id.formulario_is_sjd)).isChecked()));

        finish();
    }


}
