package br.jus.trerj.muraleletronico.loaders;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import br.jus.trerj.muraleletronico.exceptions.TRERJNonPresentableException;
import br.jus.trerj.muraleletronico.modelo.Advogado;

/**
 * Created by avelinoferreiragf on 30/08/16.
 */
public class JsonLoader<T extends Serializable> {
    private Class<T> classe;

    public JsonLoader(Class<T> classe) {
        this.classe = classe;
    }

    public List<T> carregar(JSONArray jsonArray) {
        SortedSet<T> returningList = new TreeSet<T>();
        if (jsonArray == null) {
            return new ArrayList<T>();
        }
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                T object = new Gson().fromJson(jsonObject.toString(), classe);
                returningList.add(object);
            }
            return new ArrayList<T>(returningList);
        } catch (Exception e) {
            throw new TRERJNonPresentableException(e);
        }
    }

}
