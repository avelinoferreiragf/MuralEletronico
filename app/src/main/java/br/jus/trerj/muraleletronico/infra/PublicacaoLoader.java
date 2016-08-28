package br.jus.trerj.muraleletronico.infra;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.jus.trerj.muraleletronico.exceptions.TRERJNonPresentableException;
import br.jus.trerj.muraleletronico.modelo.Municipio;
import br.jus.trerj.muraleletronico.modelo.Publicacao;

/**
 * Created by avelinoferreiragf on 28/08/16.
 */
public class PublicacaoLoader {
    private static final SimpleDateFormat SDF_DATA_PUBLICACAO = new SimpleDateFormat("yyyy-MM-dd");

    public List<Publicacao> carregar(JSONArray publicacoesJson) {
        List<Publicacao> publicacoes = new ArrayList<Publicacao>();

        if (publicacoesJson == null) {
            return publicacoes;
        }
        try {
            for(int i = 0; i < publicacoesJson.length(); i++) {
                JSONObject publicacaoJSON = publicacoesJson.getJSONObject(i);
                String id = publicacaoJSON.getString("id");
                String numeroProcesso = publicacaoJSON.getString("numeroProcesso");
                String numeroProcessoCompleto = publicacaoJSON.getString("numeroProcessoCompleto");
                String descricaoNumeroDoProcesso = publicacaoJSON.getString("descricaoNumeroDoProcesso");
                String siglaClasseProcesso = publicacaoJSON.getString("siglaClasseProcesso");
                Long numeroProtocolo = publicacaoJSON.getLong("numeroProtocolo");
                String origem = publicacaoJSON.getString("origem");
                Date dataPublicacao = SDF_DATA_PUBLICACAO.parse(publicacaoJSON.getString("dataPublicacao"));
                Date dataAndamento = new Date(publicacaoJSON.getLong("dataAndamento"));
                JSONObject municipioJSON = publicacaoJSON.getJSONObject("municipio");
                Integer idMunicipio = municipioJSON.getInt("id");
                String nomeMunicipio = municipioJSON.getString("nome");
                String siglaUnidadePublicadora = publicacaoJSON.getString("siglaUnidadePublicadora");
                Boolean isSJD = publicacaoJSON.getBoolean("isSJD");
                String tipoPublicacao = publicacaoJSON.getString("tipoPublicacao");

                Municipio municipio = new Municipio();
                municipio.setId(idMunicipio);
                municipio.setNome(nomeMunicipio);

                Publicacao publicacao = new Publicacao();
                publicacao.setId(id);
                publicacao.setNumeroDoProcesso(numeroProcesso);
                publicacao.setNumeroDoProcessoCompleto(numeroProcessoCompleto);
                publicacao.setDescricaoNumeroProcesso(descricaoNumeroDoProcesso);
                publicacao.setSiglaClasseProcesso(siglaClasseProcesso);
                publicacao.setNumeroProtocolo(numeroProtocolo);
                publicacao.setOrigem(origem);
                publicacao.setDataPublicacao(dataPublicacao);
                publicacao.setDataAndamento(dataAndamento);
                publicacao.setMunicipio(municipio);
                publicacao.setSiglaUnidadePublicadora(siglaUnidadePublicadora);
                publicacao.setSJD(isSJD);
                publicacao.setTipo(tipoPublicacao);

                publicacoes.add(publicacao);
            }
            return publicacoes;
        } catch (Exception e) {
            throw new TRERJNonPresentableException(e);
        }

    }
}
