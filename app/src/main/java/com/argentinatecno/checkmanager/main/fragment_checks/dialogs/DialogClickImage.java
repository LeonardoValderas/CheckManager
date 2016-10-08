package com.argentinatecno.checkmanager.main.fragment_checks.dialogs;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.argentinatecno.checkmanager.R;
import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.lib.base.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class DialogClickImage {

    Context context;
    public ImageButton btnCerrar = null;
    public AlertDialog alertDialog;
    @Bind(R.id.imageAdapter)
    ImageView imageAdapter;
    @Bind(R.id.buttonCerrar)
    Button buttonCerrar;
    ImageLoader imageLoader;

    public DialogClickImage(Context context, ImageLoader imageLoader, Check check) {
        this.context = context;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.dialog_image_adapter, null);
        builder.setView(layout);
        ButterKnife.bind(this, layout);
        if(check != null)
        if(check.getPhoto() != null)
        imageLoader.loadDialog(imageAdapter, check.getPhoto());
        PhotoViewAttacher photoView = new PhotoViewAttacher(imageAdapter);
        photoView.update();
        buttonCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.getWindow().setLayout(550, 500);
        alertDialog.show();

    }
}
