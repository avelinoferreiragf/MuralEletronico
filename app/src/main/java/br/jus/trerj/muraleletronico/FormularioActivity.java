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

public class FormularioActivity extends AppCompatActivity {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    private AdvogadoWS advogadoWS = new AdvogadoWS(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        //this.configurarDataPublicacaoPicker();

        PublicacaoFiltro filtro = PublicacaoFiltro.getInstance();

        this.setPropriedade(R.id.formulario_numero_processo, filtro.getNumeroProcesso());
        this.setPropriedade(R.id.formulario_numero_protocolo, filtro.getNumeroProtocolo());
        this.setPropriedade(R.id.formulario_data_publicacao, filtro.getDataPublicacao());
        this.setPropriedade(R.id.formulario_is_sjd, filtro.getSJD());

        EditText edtDataPublicacao = (EditText) this.findViewById(R.id.formulario_data_publicacao);

        edtDataPublicacao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Date dataPublicacao = FormularioActivity.this.getPropriedadeDate(s);
                FormularioActivity.this.advogadoWS.consultar(dataPublicacao);
            }

            @Override
            public void afterTextChanged(Editable s) {

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
/*
    private void configurarDataPublicacaoPicker() {
        PublicacaoFiltro filtro = PublicacaoFiltro.getInstance();
        Date hoje = new Date();
        if (filtro.getDataPublicacao() == null) {
            filtro.setDataPublicacao(new Date());
        }
        DatePicker datePickerDataPublicacao = (DatePicker) findViewById(R.id.formulario_data_publicacao);
        datePickerDataPublicacao.setMaxDate(hoje.getTime());

        Calendar c = Calendar.getInstance();
        c.setTime(filtro.getDataPublicacao());
        Integer dia = c.get(Calendar.DAY_OF_MONTH);
        Integer mes = c.get(Calendar.MONTH);
        Integer ano = c.get(Calendar.YEAR);

        datePickerDataPublicacao.updateDate(ano, mes, dia);
    }
*/
    private void informarParametrosDaConsulta() {
        PublicacaoFiltro filtro = PublicacaoFiltro.getInstance();

        Date dataPublicacao = this.getPropriedadeDate(R.id.formulario_data_publicacao);
        String numeroProcesso = this.getPropriedadeString(R.id.formulario_numero_processo);
        String numeroProtocolo = this.getPropriedadeString(R.id.formulario_numero_protocolo);

        filtro.setDataPublicacao(dataPublicacao);
        filtro.setNumeroProcesso(numeroProcesso);
        filtro.setNumeroProtocolo(numeroProtocolo);
        filtro.setSJD((((CheckBox) this.findViewById(R.id.formulario_is_sjd)).isChecked()));

        finish();
    }

    /*
private Date getDataPublicacaoDoDatePicker() {
    DatePicker datePickerDataPublicacao = (DatePicker) findViewById(R.id.formulario_data_publicacao);
    Integer dia = datePickerDataPublicacao.getDayOfMonth();
    Integer mes = datePickerDataPublicacao.getMonth();
    Integer ano = datePickerDataPublicacao.getYear();

    Calendar c = Calendar.getInstance();
    c.set(Calendar.DAY_OF_MONTH, dia);
    c.set(Calendar.MONTH, mes);
    c.set(Calendar.YEAR, ano);

    Date dataPublicacao = c.getTime();
    return dataPublicacao;
}
*/
    private String getPropriedadeString(Integer idComponente) {
        EditText editText = (EditText) this.findViewById(idComponente);
        return editText.getText().toString();
    }

    private void setPropriedade(Integer idComponente, Boolean isChecked) {
        CheckBox checkBox = (CheckBox) this.findViewById(idComponente);
        if (isChecked == null) {
            isChecked = false;
        }
        checkBox.setChecked(isChecked);
    }

    private void setPropriedade(Integer idComponente, String text) {
        EditText editText = (EditText) this.findViewById(idComponente);
        editText.setText(text);
    }

    private void setPropriedade(Integer idComponente, Date date) {
        String strDate = "";
        if (date != null) {
            strDate = SDF.format(date);
        }
        this.setPropriedade(idComponente, strDate);
    }

    private Date getPropriedadeDate(Integer idComponente) {
        String strDate = this.getPropriedadeString(idComponente);
        return this.getPropriedadeDate(strDate);
    }

    private Date getPropriedadeDate(String strDate) {
        try {
            if (strDate.trim().length() != 10) {
                return null;
            }
            return SDF.parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }
    private Date getPropriedadeDate(CharSequence strDate) {
        if (strDate == null) {
            return null;
        }
        return this.getPropriedadeDate(strDate.toString());
    }
}
