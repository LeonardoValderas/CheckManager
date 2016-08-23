package com.jofre.managercheck.auxiliary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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

    public Bitmap SelectImage(Intent data, Context context) {

        Bitmap bRect = null;
        Cursor cursor = null;
        String path = null;
        try {
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

               // bRect = Bitmap.createScaledBitmap(bRect, 160, 160, true);

                cursor.close();
            } else {
                bRect = null;
            }
        } catch (Exception e) {

        }
        return bRect;
    }

    public Bitmap SelectImage(String path) {
        Bitmap bRect = null;
        Cursor cursor = null;
        try {
            if (path != null) {
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
                bRect = Bitmap.createScaledBitmap(bRect, 1512, 1512, true);

                //cursor.close();
            } else {
                bRect = null;
            }
        } catch (Exception e) {

        }
        return bRect;
    }

    public List<String> getYearsSpinner() {
        List<String> years = new ArrayList<String>();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        years.add(String.valueOf(year - 1));
        years.add(String.valueOf(year));
        for (int i = 1; i < 5; i++) {
            years.add(String.valueOf(year + i));
        }

        return years;
    }

    public String validateLengthMountOrDay(String monthOrDay) {
      if(!monthOrDay.isEmpty() && monthOrDay != null) {
          int length = 0;
          for (int i = 0; i < monthOrDay.length(); i++) {
              length++;
          }

          if (length == 1)
              monthOrDay = "0" + monthOrDay;
      }
        return monthOrDay;
    }

    public boolean getExpitationDate(int iYear, int iMonth, String day) {
        Calendar mycal = new GregorianCalendar(iYear, iMonth, 1);
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (!day.isEmpty() && day != null) {
            if (Integer.parseInt(day) <= daysInMonth)
                return true;
            else
                return false;

        } else {
            return false;
        }
    }
    public int getPositionSpinnerYear(String year) {

        int index = 0;
        for (int i = 0; i < getYearsSpinner().size(); i++) {
            if (getYearsSpinner().get(i).toString().equals(year)) {
                index = i;
            }
        }
        return index;
    }


}
