package com.jofre.managercheck.receiver.receiverlistfragment.ui.alerts;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.jofre.managercheck.R;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LEO on 16/7/2016.
 */
public class ReceiverAddListImageAdapter {

    Context context;
    public ImageButton btnCerrar = null;
    public AlertDialog alertDialog;
    @Bind(R.id.imageAdapter)
    ImageView imageAdapter;
    @Bind(R.id.buttonCerrar)
    Button buttonCerrar;
    ImageLoader imageLoader;

    public ReceiverAddListImageAdapter(Context context, ImageLoader imageLoader, Check check) {
        this.context = context;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.alert_image_adapter, null);
        builder.setView(layout);
        ButterKnife.bind(this, layout);
        if(check != null)
        if(check.getPhoto() != null)
        imageLoader.load(imageAdapter, check.getPhoto());
        buttonCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.getWindow().setLayout(550, 680);
        alertDialog.show();

    }
}
