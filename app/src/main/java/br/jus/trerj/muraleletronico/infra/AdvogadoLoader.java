package br.jus.trerj.muraleletronico.infra;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import br.jus.trerj.muraleletronico.exceptions.TRERJNonPresentableException;
import br.jus.trerj.muraleletronico.modelo.Advogado;

/**
 * Created by avelinoferreiragf on 28/08/16.
 */
public class AdvogadoLoader {

    public List<Advogado> carregar(JSONArray advogadosJson) {
        SortedSet<Advogado> advogados = new TreeSet<Advogado>();
        if (advogadosJson == null) {
            return new ArrayList<Advogado>();
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
            return new ArrayList<Advogado>(advogados);
        } catch (Exception e) {
            throw new TRERJNonPresentableException(e);
        }
    }
}
