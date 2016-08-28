package br.jus.trerj.muraleletronico.infra;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by avelinoferreiragf on 27/08/16.
 */
public class MuralEletronicoRestClient {
    private static final String BASE_URL = "http://www.tre-rj.jus.br/muraleletronico/rest/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        String url = BASE_URL + relativeUrl;
        return url;
    }
}
