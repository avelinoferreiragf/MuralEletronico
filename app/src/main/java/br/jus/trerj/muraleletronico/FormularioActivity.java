package br.jus.trerj.muraleletronico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.jus.trerj.muraleletronico.filter.PublicacaoFiltro;
import br.jus.trerj.muraleletronico.infra.AdvogadoWS;
import br.jus.trerj.muraleletronico.modelo.Publicacao;
import br.jus.trerj.muraleletronico.util.PropriedadesFormularioUtil;

public class FormularioActivity extends AppCompatActivity {


    private AdvogadoWS advogadoWS = new AdvogadoWS(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        PublicacaoFiltro filtro = PublicacaoFiltro.getInstance();

        this.advogadoWS.consultar(filtro.getDataPublicacao());
        PropriedadesFormularioUtil.setPropriedade(this, R.id.formulario_numero_processo, filtro.getNumeroProcesso());
        PropriedadesFormularioUtil.setPropriedade(this, R.id.formulario_numero_protocolo, filtro.getNumeroProtocolo());
        PropriedadesFormularioUtil.setPropriedade(this, R.id.formulario_data_publicacao, filtro.getDataPublicacao());
        PropriedadesFormularioUtil.setPropriedade(this, R.id.formulario_is_sjd, filtro.getSJD());

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
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
