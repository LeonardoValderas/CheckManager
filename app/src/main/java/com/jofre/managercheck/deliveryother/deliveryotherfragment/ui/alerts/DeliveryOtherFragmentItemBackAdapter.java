package com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.alerts;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jofre.managercheck.R;
import com.jofre.managercheck.db.CheckController;
import com.jofre.managercheck.entities.Check;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LEO on 16/7/2016.
 */
public class DeliveryOtherFragmentItemBackAdapter {

    Context context;
    //public ImageButton btnCerrar = null;
    public AlertDialog alertDialog;
    @Bind(R.id.buttonAcceptDestiny)
    public Button buttonAcceptDestiny;
    @Bind(R.id.buttonCancelDestiny)
    public Button buttonCancelDestiny;

    public DeliveryOtherFragmentItemBackAdapter(Context context, final Check check) {
        this.context = context;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.delivery_dialog_destiny_back, null);
        ButterKnife.bind(this, layout);

        builder.setView(layout);
        alertDialog = builder.create();
        //   alertDialog.getWindow().setLayout(550, 680);
        alertDialog.show();

    }
}
