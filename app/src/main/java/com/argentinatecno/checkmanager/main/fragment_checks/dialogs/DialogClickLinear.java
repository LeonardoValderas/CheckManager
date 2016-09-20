package com.argentinatecno.checkmanager.main.fragment_checks.dialogs;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.argentinatecno.checkmanager.R;
import com.argentinatecno.checkmanager.entities.Check;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LEO on 16/7/2016.
 */
public class DialogClickLinear {

    Context context;
    public AlertDialog alertDialog;
    @Bind(R.id.textReceiverNumber)
    TextView textReceiverNumber;
    @Bind(R.id.textReceiverAmount)
    TextView textReceiverAmount;
    @Bind(R.id.textReceiverExpirate)
    TextView textReceiverExpirate;
    @Bind(R.id.textReceiverOrigin)
    TextView textReceiverOrigin;
    @Bind(R.id.textReceiverDestiny)
    public EditText textReceiverDestiny;
    @Bind(R.id.linearDataReceiver)
    LinearLayout linearDataReceiver;
    @Bind(R.id.buttonSaveDestiny)
    public Button buttonSaveDestiny;
    @Bind(R.id.textEmpty)
    public TextView textEmpty;


    public DialogClickLinear(Context context, final Check check) {
        this.context = context;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.dialog_destiny, null);
        ButterKnife.bind(this, layout);

        if (check != null) {
            textReceiverNumber.setText(check.getNumber());
            textReceiverAmount.setText("$ " + check.getAmount());
            linearDataReceiver.setBackgroundResource(R.drawable.border_rect_linear);
            textReceiverExpirate.setText(check.getExpiration());
            textReceiverOrigin.setText(check.getOrigin());
        }
        builder.setView(layout);
        alertDialog = builder.create();
     //   alertDialog.getWindow().setLayout(550, 680);
        alertDialog.show();

    }
}
