package com.argentinatecno.checkmanager.auxiliary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.entities.Share;

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
        String dayStg = String.valueOf(day);
        if (dayStg.length() < 2) {
            dayStg = "0" + dayStg;
        }
        firstWkDay = firstWkDay + dayStg;
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
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
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

    public Bitmap getResizedBitmap(String path, int newWidth, int newHeight) {

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / newWidth, photoH / newHeight);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);

        return bitmap;
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
        Share share;
        int i = 0;
        ArrayList<Share> shares = new ArrayList<>();


        ArrayList<Uri> uris = new ArrayList<>();
        ArrayList<String> texts = new ArrayList<>();


        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.setPackage("com.whatsapp");

        text = null;


        for (Check check : checks) {
            i++;
            if (text == null)
                text = setTextConcatenate(check);
            else
                text += setTextConcatenate(check);

            if (check.getPhoto() != null)
                bitmap = setPhoto(check.getPhoto());
            else {
                Drawable d = ContextCompat.getDrawable(context, android.R.drawable.ic_menu_camera);
                bitmap = drawableToBitmap(d);
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
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            uris.add(photo);
            texts.add(text);

        }
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(shareIntent);
    }


    public boolean email(List<Check> checks, Context context) {
        String text = null;

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, "");
        i.putExtra(Intent.EXTRA_SUBJECT, "Mis Cheques Backup");
        for (Check check : checks) {
            if (text == null)
                text = setTextConcatenate(check);
            else
                text += setTextConcatenate(check);
        }
        i.putExtra(Intent.EXTRA_TEXT, text);
        try {
            context.startActivity(Intent.createChooser(i, "Enviando email..."));
            return true;
        } catch (android.content.ActivityNotFoundException ex) {
            return false;
        }
    }

    public String setTextConcatenate(Check check) {

        String text = null;

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
        return text;
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

    public static class CurrencyFormat implements TextWatcher {

        public void onTextChanged(CharSequence arg0, int start, int arg2, int arg3) {
        }

        public void beforeTextChanged(CharSequence arg0, int start, int arg2, int arg3) {
        }

        public void afterTextChanged(Editable arg0) {
            int length = arg0.length();
            if (length > 0) {
                if (nrOfDecimal(arg0.toString()) > 2)
                    arg0.delete(length - 1, length);
            }
        }

        private int nrOfDecimal(String nr) {
            int len = nr.length();
            int pos = len;
            for (int i = 0; i < len; i++) {
                if (nr.charAt(i) == '.') {
                    pos = i + 1;
                    break;
                }
            }
            return len - pos;
        }
    }
}
