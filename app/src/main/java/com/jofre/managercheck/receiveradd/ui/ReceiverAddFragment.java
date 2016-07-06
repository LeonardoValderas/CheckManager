package com.jofre.managercheck.receiveradd.ui;


import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.jofre.managercheck.ManagerCheckApp;
import com.jofre.managercheck.R;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.receiveradd.ReceiverAddPresenter;
import com.raizlabs.android.dbflow.data.Blob;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiverAddFragment extends Fragment implements ReceiverAddView {


    @Bind(R.id.editTextNumber)
    EditText editTextNumber;
    @Bind(R.id.imgButtonMaturities)
    ImageButton imgButtonMaturities;
    @Bind(R.id.editTextAmount)
    EditText editTextAmount;
    @Bind(R.id.editTextOrigin)
    EditText editTextOrigin;
    @Bind(R.id.linearPhoto)
    LinearLayout linearPhoto;
    @Bind(R.id.imgReceiver)
    ImageView imgReceiver;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.relativeContent)
    RelativeLayout relativeContent;

    private String photoPath;
    private static final int REQUEST_PICTURE = 0;
    @Inject
    ReceiverAddPresenter presenter;

    Check check = new Check();

    public ReceiverAddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_receiver, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupInjection();
        presenter.onCreate();
    }

    // @OnClick(R.id.fab)
    @OnClick(R.id.imgReceiver)
    public void takePicture() {
        Intent chooserIntent = null;

        List<Intent> intentList = new ArrayList<>();

        Intent pickIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoIntent.putExtra("return-data", true);
        File photoFile = getFile();

        if (photoFile != null) {
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            if (takePhotoIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                intentList = addIntentsToList(intentList, takePhotoIntent);
            }
        }

        if (pickIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            intentList = addIntentsToList(intentList, pickIntent);
        }

        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
                    getString(R.string.main_message_picture_source));
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }

        startActivityForResult(chooserIntent, REQUEST_PICTURE);
    }

    private List<Intent> addIntentsToList(List<Intent> list, Intent intent) {
        List<ResolveInfo> resInfo = getActivity().getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent targetedIntent = new Intent(intent);
            targetedIntent.setPackage(packageName);
            list.add(targetedIntent);
        }
        return list;
    }

    private File getFile() {
        File photoFile = null;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            photoFile = File.createTempFile(imageFileName, ".jpg", storageDir);
            photoPath = photoFile.getAbsolutePath();
        } catch (IOException ex) {
            showSnackbar(getString(R.string.main_error_dispatch_camera));
        }
        return photoFile;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getActivity().RESULT_OK && requestCode == REQUEST_PICTURE) {
            boolean isCamera = (data == null ||
                    data.getData() == null);

            if (isCamera) {
                addPicToGallery();
            } else {
                photoPath = getRealPathFromURI(data.getData());
            }

            setImageView(photoPath);
            //presenter.uploadPhoto(lastLocation, photoPath);
        }
    }

    private void setImageView(String photoPath) {
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        if (bitmap != null) {
            imgReceiver.setImageBitmap(bitmap);
            byte[] byteArray = bytes.toByteArray();

            check.setPhoto(new Blob((byteArray)));
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result = null;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            if (contentURI.toString().contains("mediaKey")) {
                cursor.close();

                try {
                    File file = File.createTempFile("tempImg", ".jpg", getActivity().getCacheDir());
                    InputStream input = getActivity().getContentResolver().openInputStream(contentURI);
                    OutputStream output = new FileOutputStream(file);

                    try {
                        byte[] buffer = new byte[4 * 1024];
                        int read;

                        while ((read = input.read(buffer)) != -1) {
                            output.write(buffer, 0, read);
                        }
                        output.flush();
                        result = file.getAbsolutePath();
                    } finally {
                        output.close();
                        input.close();
                    }

                } catch (Exception e) {
                }
            } else {
                cursor.moveToFirst();
                int dataColumn = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                result = cursor.getString(dataColumn);
                cursor.close();
            }

        }
        return result;
    }

    private void addPicToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

        File f = new File(photoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

    @Override
    public void onAddInit() {
        showSnackbar(getString(R.string.add_init));
    }

    @Override
    public void onAddComplete() {
        showSnackbar(getString(R.string.add_complete));
    }

    @Override
    public void onAddError(String error) {
        showSnackbar(error);
    }

    public void showSnackbar(String msg) {
        Snackbar.make(mainContent, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void hideUIComponent() {
        relativeContent.setVisibility(View.GONE);
    }

    @Override
    public void showUIComponent() {
        relativeContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    @OnClick(R.id.fab)
    public void saveCheck() {
        check.setNumber(editTextNumber.getText().toString());
        check.setAmount(editTextAmount.getText().toString());
        check.setExpiration(getDate());
        check.setOrigin(editTextOrigin.getText().toString());
        presenter.saveCheck(check);
    }

    //pasar a util
    public Date getDate() {
        Date currentDate;
        return currentDate = new Date(System.currentTimeMillis());
    }

    @Override
    public void onDestroy() {
        //presenter.unsubscribe();
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        ManagerCheckApp app = (ManagerCheckApp) getActivity().getApplication();

        app.getReceiverAddComponent(this).inject(this);
    }
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }
}
