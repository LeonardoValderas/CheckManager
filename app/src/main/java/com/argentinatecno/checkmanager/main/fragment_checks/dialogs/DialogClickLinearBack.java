package com.argentinatecno.checkmanager.main.fragment_checks.dialogs;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.argentinatecno.checkmanager.R;
import com.argentinatecno.checkmanager.entities.Check;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DialogClickLinearBack {

    Context context;
    //public ImageButton btnCerrar = null;
    public AlertDialog alertDialog;
    @Bind(R.id.buttonAcceptDestiny)
    public Button buttonAcceptDestiny;
    @Bind(R.id.buttonCancelDestiny)
    public Button buttonCancelDestiny;

    public DialogClickLinearBack(Context context, final Check check) {
        this.context = context;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.dialog_destiny_back, null);
        ButterKnife.bind(this, layout);

        builder.setView(layout);
        alertDialog = builder.create();
        //   alertDialog.getWindow().setLayout(550, 680);
        alertDialog.show();

    }
}
