package br.jus.trerj.muraleletronico.loaders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import br.jus.trerj.muraleletronico.exceptions.TRERJNonPresentableException;
import br.jus.trerj.muraleletronico.modelo.Advogado;
import br.jus.trerj.muraleletronico.modelo.Municipio;

/**
 * Created by avelinoferreiragf on 30/08/16.
 */
public class JsonLoader<T extends Comparable<T>> {
    private Class<T> classe;
    private Type tipoDaListaDeObjetos;
    public JsonLoader(Class<T> classe, Type tipoDaListaDeObjetos) {
        this.classe = classe;
        this.tipoDaListaDeObjetos = tipoDaListaDeObjetos;
    }

    public List<T> carregar(JSONArray jsonArray) {
        SortedSet<T> returningList = new TreeSet<T>();
        if (jsonArray == null) {
            return new ArrayList<T>();
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        Gson gson = gsonBuilder.create();
        List<T> listaDeObjetos = gson.fromJson(jsonArray.toString(), tipoDaListaDeObjetos);
        try {
            Collections.sort(listaDeObjetos);
            return  listaDeObjetos;
        } catch (Exception e) {
            throw new TRERJNonPresentableException(e);
        }
    }

}
