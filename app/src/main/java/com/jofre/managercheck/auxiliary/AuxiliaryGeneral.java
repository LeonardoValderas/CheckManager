package com.jofre.managercheck.auxiliary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by LEO on 17/7/2016.
 */
public class AuxiliaryGeneral {

    private Context context;
    private static AuxiliaryGeneral auxiliaryGeneral = null;

    public AuxiliaryGeneral(Context context) {
        this.context = context;
    }

    public static AuxiliaryGeneral getInstance(Context context) {
        if (auxiliaryGeneral == null)
            auxiliaryGeneral = new AuxiliaryGeneral(context);

        return auxiliaryGeneral;
    }

    public Bitmap SeleccionarImagen(Intent data, String path, boolean isCamera) {

        Bitmap bRect = null;
        Cursor cursor = null;
        //String path = null;
        try {
            if (!isCamera) {
                UtilityImage.uri = data.getData();
                if (UtilityImage.uri != null) {
                    cursor = context.getContentResolver().query(
                            UtilityImage.uri, null, null, null, null);

                    cursor.moveToFirst();
                    String document_id = cursor.getString(0);
                    document_id = document_id.substring(document_id
                            .lastIndexOf(":") + 1);

                    cursor = context
                            .getContentResolver()
                            .query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    null, MediaStore.Images.Media._ID + " = ? ",
                                    new String[]{document_id}, null);
                    cursor.moveToFirst();
                    path = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    cursor.close();
                } else {
                    bRect = null;
                }
            }
                //asigamnos el string directorio
                UtilityImage.Default_DIR = new File(path);
                //creamos el nuevo directorio
                UtilityImage.Create_MY_IMAGES_DIR();
                // Copiamos la imagen
                UtilityImage.copyFile(UtilityImage.Default_DIR,
                        UtilityImage.MY_IMG_DIR);
                //tomamos la imagen y la codificamos
                bRect = UtilityImage
                        .decodeFile(UtilityImage.Paste_Target_Location);
                // use new copied path and use anywhere
                String valid_photo = UtilityImage.Paste_Target_Location
                        .toString();
                bRect = Bitmap.createScaledBitmap(bRect, 0, 0, true);

                cursor.close();
        } catch (Exception e) {

        }
        return bRect;
    }


}
