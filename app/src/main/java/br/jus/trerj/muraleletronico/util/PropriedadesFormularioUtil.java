package br.jus.trerj.muraleletronico.util;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by avelinoferreiragf on 28/08/16.
 */
public final class PropriedadesFormularioUtil {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    private PropriedadesFormularioUtil() {
    }

    public static String getPropriedadeString(Activity activity, Integer idComponente) {
        EditText editText = (EditText) activity.findViewById(idComponente);
        return editText.getText().toString();
    }

    public static void setPropriedade(Activity activity, Integer idComponente, Boolean isChecked) {
        CheckBox checkBox = (CheckBox) activity.findViewById(idComponente);
        if (isChecked == null) {
            isChecked = false;
        }
        checkBox.setChecked(isChecked);
    }

    public static void setPropriedade(Activity activity, Integer idComponente, String text) {
        EditText editText = (EditText) activity.findViewById(idComponente);
        editText.setText(text);
    }

    public static void setPropriedade(Activity activity, Integer idComponente, Date date) {
        String strDate = "";
        if (date != null) {
            strDate = SDF.format(date);
        }
        setPropriedade(activity, idComponente, strDate);
    }

    public static Date getPropriedadeDate(Activity activity, Integer idComponente) {
        String strDate = getPropriedadeString(activity, idComponente);
        return getPropriedadeDate(strDate);
    }

    public static Date getPropriedadeDate(CharSequence strDate) {
        if (strDate == null) {
            return null;
        }
        return getPropriedadeDate(strDate.toString());
    }

    public static Date getPropriedadeDate(String strDate) {
        try {
            if (strDate.trim().length() != 10) {
                return null;
            }
            return SDF.parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }
}
