package br.jus.trerj.muraleletronico.infra;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.jus.trerj.muraleletronico.exceptions.TRERJNonPresentableException;
import br.jus.trerj.muraleletronico.modelo.Advogado;

/**
 * Created by avelinoferreiragf on 28/08/16.
 */
public class AdvogadoLoader {

    public Set<Advogado> carregar(JSONArray advogadosJson) {
        Set<Advogado> advogados = new HashSet<Advogado>();
        if (advogadosJson == null) {
            return advogados;
        }
        try {
            for (int i = 0; i < advogadosJson.length(); i++) {
                JSONObject advogadoJSON = advogadosJson.getJSONObject(i);
                Long id = advogadoJSON.getLong("idAdvogado");
                String nomeAdvogado = advogadoJSON.getString("nomeAdvogado");
                String numeroOAB = advogadoJSON.getString("numeroOAB");

                Advogado advogado = new Advogado();
                advogado.setId(id);
                advogado.setNome(nomeAdvogado);
                advogado.setNumeroOAB(numeroOAB);

                advogados.add(advogado);
            }
            return advogados;
        } catch (Exception e) {
            throw new TRERJNonPresentableException(e);
        }
    }

    public List<String> transformarAdvogadosParaListaDeNomesEOAB(Set<Advogado> advogados) {
        List<String> toStringAdvogados = new ArrayList<String>();
        for(Advogado advogado : advogados) {
            toStringAdvogados.add(advogado.toString());
        }
        if (toStringAdvogados.size() == 0) {
            toStringAdvogados.add("Nenhum advogado encontrado");
        } else {
            toStringAdvogados.set(0, "Caso deseje, escolha um advogado.");
        }
        return toStringAdvogados;
    }
}
