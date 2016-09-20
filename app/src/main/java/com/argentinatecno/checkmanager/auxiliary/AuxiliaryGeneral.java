package com.argentinatecno.checkmanager.auxiliary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.argentinatecno.checkmanager.R;
import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.entities.Share;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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

//    public Bitmap SelectImage(Intent data, Context context) {
//
//        Bitmap bRect = null;
//        Cursor cursor = null;
//        String path = null;
//        try {
//            UtilityImage.uri = data.getData();
//            if (UtilityImage.uri != null) {
//                cursor = context.getContentResolver().query(
//                        UtilityImage.uri, null, null, null, null);
//
//                cursor.moveToFirst();
//                String document_id = cursor.getString(0);
//                document_id = document_id.substring(document_id
//                        .lastIndexOf(":") + 1);
//
//                cursor = context
//                        .getContentResolver()
//                        .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                                null, MediaStore.Images.Media._ID + " = ? ",
//                                new String[]{document_id}, null);
//                cursor.moveToFirst();
//                path = cursor.getString(cursor
//                        .getColumnIndex(MediaStore.Images.Media.DATA));
//                cursor.close();
//                //asigamnos el string directorio
//                UtilityImage.Default_DIR = new File(path);
//                //creamos el nuevo directorio
//                UtilityImage.Create_MY_IMAGES_DIR();
//                // Copiamos la imagen
//                UtilityImage.copyFile(UtilityImage.Default_DIR,
//                        UtilityImage.MY_IMG_DIR);
//                //tomamos la imagen y la codificamos
//                bRect = UtilityImage
//                        .decodeFile(UtilityImage.Paste_Target_Location);
//                // use new copied path and use anywhere
//                String valid_photo = UtilityImage.Paste_Target_Location
//                        .toString();
//
//                // bRect = Bitmap.createScaledBitmap(bRect, 160, 160, true);
//
//                cursor.close();
//            } else {
//                bRect = null;
//            }
//        } catch (Exception e) {
//
//        }
//        return bRect;
//    }

//    public Bitmap SelectImage(String path) {
//        Bitmap bRect = null;
//        Cursor cursor = null;
//        try {
//            if (path != null) {
//                //asigamnos el string directorio
//                UtilityImage.Default_DIR = new File(path);
//                //creamos el nuevo directorio
//                UtilityImage.Create_MY_IMAGES_DIR();
//                // Copiamos la imagen
//                UtilityImage.copyFile(UtilityImage.Default_DIR,
//                        UtilityImage.MY_IMG_DIR);
//                //tomamos la imagen y la codificamos
//                bRect = UtilityImage
//                        .decodeFile(UtilityImage.Paste_Target_Location);
//                // use new copied path and use anywhere
//                String valid_photo = UtilityImage.Paste_Target_Location
//                        .toString();
//                bRect = Bitmap.createScaledBitmap(bRect, 1512, 1512, true);
//
//                //cursor.close();
//            } else {
//                bRect = null;
//            }
//        } catch (Exception e) {
//
//        }
//        return bRect;
//    }

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
        if (!monthOrDay.isEmpty() && monthOrDay != null) {
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

    public String getDateWeek() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dd = new SimpleDateFormat("dd");
        int year = cal.get(Calendar.YEAR);
        cal.set(Calendar.DAY_OF_WEEK, cal.MONDAY);
        String firstWkDay = df.format(cal.getTime());
        String onlyDay = dd.format(cal.getTime());
        int day = Integer.parseInt(onlyDay);
        firstWkDay = firstWkDay.substring(0, 6);
        day = day - 2;
        firstWkDay = firstWkDay+day;
        cal.set(Calendar.DAY_OF_WEEK, 6);
        String lastWkDay = df.format(cal.getTime());

        return firstWkDay + lastWkDay;

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

    public String dateNowShot() {
        String formattedDate = null;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return formattedDate = df.format(c.getTime());
    }

    public void initOnClickEditText(View view) {
        final EditText daysExpiration = (EditText) view;
        daysExpiration.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                daysExpiration.setHint("");
                return false;
            }

        });

        daysExpiration.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && daysExpiration != null) {
                    daysExpiration.setHint("01");
                }
            }
        });
    }


    public Bitmap getBitmapForPath(String photoPath, int mDstWidth, int mDstHeight) {
        // Part 1: Decode image
        Bitmap unscaledBitmap = BitmapFactory.decodeFile(photoPath);
        // Part 2: Scale image
   //     Bitmap scaledBitmap = Bitmap
     //           .createScaledBitmap(unscaledBitmap, mDstWidth, mDstHeight, true);
        unscaledBitmap.recycle();
        return unscaledBitmap;
       // return scaledBitmap;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    public String selectImageForData(Intent data, Context context) {

        Cursor cursor = null;
        String photoPath = null;
        try {
            Uri uri = data.getData();
            if (uri != null) {
                cursor = context.getContentResolver().query(
                        uri, null, null, null, null);

                cursor.moveToFirst();
                String document_id = cursor.getString(0);
                document_id = document_id.substring(document_id
                        .lastIndexOf(":") + 1);

                cursor = context
                        .getContentResolver()
                        .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                null, MediaStore.Images.Media._ID + " = ? ",
                                new String[]{document_id}, null);
                cursor.moveToFirst();
                photoPath = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
                cursor.close();
            }
        } catch (Exception e) {
            photoPath = null;
        }
        return photoPath;
    }

    public void share(List<Check> checks, Context context) {
        String text = null;
        Bitmap bitmap = null;
        Uri photo = null;
        byte[] bytePhoto = null;
        String[] st = new String[]{"a", "b"};
//        File shareFile = null;
        Share share;
        int i = 0;
        ArrayList<Share> shares = new ArrayList<>();


        ArrayList<Uri> uris = new ArrayList<>();
        ArrayList<String> texts = new ArrayList<>();


        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.setPackage("com.whatsapp");

        /**
         * Select whatsapp
         */
//        PackageManager pm = context.getPackageManager();
//        List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
//        for (final ResolveInfo app : activityList) {
//            if ((app.activityInfo.name).contains("com.whatsapp")) {
//                final ActivityInfo activity = app.activityInfo;
//                final ComponentName name = new ComponentName(
//                        activity.applicationInfo.packageName, activity.name);
//                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//                shareIntent.setComponent(name);
//            }
//        }
        text = null;

        for (Check check : checks) {

            i++;
            if (text == null)
                text = "Número: " + check.getNumber() + " ";
            else
                text += "Número: " + check.getNumber() + " ";


            text += "Monto: " + check.getAmount() + "\n";
            text += "Vencimiento: " + check.getExpiration() + "\n";
            text += "Entregado a: " + check.getDestiny() + "\n";
            text += "Fecha de entrega: " + check.getDestinyDate() + "\n";
            if (check.getOrigin() != null)
                text += "Recibido de: " + check.getOrigin() + "\n";
            text += "--------------------------------------------" + "\n";


            //  bytePhoto = check.getPhoto();

            if (check.getPhoto() != null)
                bitmap = setPhoto(check.getPhoto());

            else {
                Drawable d = ContextCompat.getDrawable(context, android.R.drawable.ic_menu_camera);
                bitmap = drawableToBitmap(d);
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                bytePhoto = stream.toByteArray();
            }

            if (bitmap != null) {
                try {
                    File cache = context.getApplicationContext().getExternalCacheDir();
                    File shareFile = new File(cache, "cheque" + i + ".png");
                    FileOutputStream out = new FileOutputStream(shareFile);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                    photo = null;
                    photo = Uri.parse("file://" + shareFile);
                    //    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

//            share = new Share();
//            share.setFile(photo);
//            share.setText(text);
//            shares.add(share);
            uris.add(photo);
            texts.add(text);

        }


        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        //shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        context.startActivity(shareIntent);


        // context.startActivity(Intent.createChooser(shareIntent, "Share images..."));
    }

    public Bitmap setPhoto(byte[] photo) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0,
                photo.length);
        bitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
        return bitmap;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


}
