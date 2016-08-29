package br.jus.trerj.muraleletronico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AndamentoActivity extends AppCompatActivity {

    private static final String URL_ANDAMENTO = "http://inter03.tse.jus.br/sadpPush/ExibirDadosProcesso.do?nproc=<NUMERO_PROCESSO />&sgcla=<SIGLA_PROCESSO />&nprot=<NUMERO_PROTOCOLO />&comboTribunal=rj&tipoProcesso=J";
    private WebView webView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ver_andamento, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andamento);

        setTitle(R.string.web_andamento_titulo);

        webView = (WebView) findViewById(R.id.webview_andamento);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        String numeroProcesso = getIntent().getStringExtra("numeroProcesso");
        String siglaProcesso = getIntent().getStringExtra("siglaClasseProcesso");
        String numeroProtocolo = getIntent().getStringExtra("numeroProtocolo");

        String url = URL_ANDAMENTO
                .replace("<NUMERO_PROCESSO />", numeroProcesso)
                .replace("<SIGLA_PROCESSO />", siglaProcesso)
                .replace("<NUMERO_PROTOCOLO />", numeroProtocolo);
        webView.loadUrl(url);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_fechar_andamento:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
