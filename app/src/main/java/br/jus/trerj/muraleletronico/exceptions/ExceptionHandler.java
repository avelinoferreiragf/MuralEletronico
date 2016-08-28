package br.jus.trerj.muraleletronico.exceptions;

import android.app.Activity;
import android.app.AlertDialog;

import br.jus.trerj.muraleletronico.R;

/**
 * Created by avelinoferreiragf on 28/08/16.
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Activity activity;

    public ExceptionHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        String message = this.activity.getString(R.string.app_erro_padrao) + ex.getMessage();
        AlertDialog.Builder messageBox = new AlertDialog.Builder(this.activity);
        messageBox.setTitle(thread.getName());
        messageBox.setMessage(message);
        messageBox.setCancelable(false);
        messageBox.setNeutralButton("OK", null);
        messageBox.show();

    }
}
