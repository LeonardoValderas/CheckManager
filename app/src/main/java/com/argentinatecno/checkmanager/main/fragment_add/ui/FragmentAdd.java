package com.argentinatecno.checkmanager.main.fragment_add.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ResolveInfo;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.argentinatecno.checkmanager.CheckManagerApp;
import com.argentinatecno.checkmanager.R;
import com.argentinatecno.checkmanager.auxiliary.AuxiliaryGeneral;
import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.lib.base.ImageLoader;
import com.argentinatecno.checkmanager.main.activity.Communicator;
import com.argentinatecno.checkmanager.main.fragment_add.FragmentAddPresenter;
import com.argentinatecno.checkmanager.main.fragment_add.di.FragmentAddComponent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentAdd extends Fragment implements FragmentAddView {

    @Bind(R.id.editTextNumber)
    EditText editTextNumber;
    @Bind(R.id.editTextAmount)
    EditText editTextAmount;
    @Bind(R.id.editTextOrigin)
    EditText editTextOrigin;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.daysExpiration)
    EditText daysExpiration;
    @Bind(R.id.monthsExpiration)
    Spinner monthsExpiration;
    @Bind(R.id.yearsExpiration)
    Spinner yearsExpiration;
    @Bind(R.id.spinnerTypeChecks)
    Spinner spinnerTypeChecks;
    @Bind(R.id.imageAddPhoto)
    ImageView imageAddPhoto;
    @Bind(R.id.editTextDestiny)
    EditText editTextDestiny;
    @Bind(R.id.imageAddShow)
    ImageView imageAddShow;

    private Communicator communicator;
    private ImageLoader imageLoader;
    private String photoPath;
    private static final int REQUEST_PICTURE = 0;
    private DatePickerDialog.OnDateSetListener d;
    private boolean update = false;
    private AuxiliaryGeneral auxiliaryGeneral = AuxiliaryGeneral.getInstance(getActivity());

    FragmentAddPresenter presenter;
    Check check = new Check();
    private FragmentAddComponent component;
    private ArrayAdapter<String> monthsAdapter;
    private ArrayAdapter<String> yearsAdapter;
    private ArrayAdapter<String> typeCheckAdapter;
    private int iMonth;
    private int iYear;
    private int mDstWidth;
    private int mDstHeight;


    public FragmentAdd() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communicator = (Communicator) getActivity();
        setupInjection();
        presenter.onCreate();
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        fillSpinnerDate();
        isUpdate();
        initOnClickEditText(daysExpiration);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void isUpdate() {
        update = getActivity().getIntent().getBooleanExtra("update", false);
        if (update) {
            check.setId_check(getActivity().getIntent().getIntExtra("id", 0));
            check.setType(getActivity().getIntent().getIntExtra("type", 0));
            check.setNumber(getActivity().getIntent().getStringExtra("number"));
            check.setAmount(getActivity().getIntent().getStringExtra("amount"));
            if (check.getType() == 0) //0 =  other  1= Own
                check.setOrigin(getActivity().getIntent().getStringExtra("origin"));

            byte[] b = getActivity().getIntent().getByteArrayExtra("photo");
            check.setPhoto(b);
            check.setDestiny(getActivity().getIntent().getStringExtra("destiny"));
            check.setDestinyDate(getActivity().getIntent().getStringExtra("destiny_date"));
            check.setExpiration(getActivity().getIntent().getStringExtra("expiration"));

            presenter.isUpdate(check);
        }
    }

    private void setupInjection() {
        CheckManagerApp app = (CheckManagerApp) getActivity().getApplication();
        component = app.getFragmentAddComponent(this, this);
        imageLoader = component.getImageLoader();
        presenter = component.getPresenter();
    }

    private void fillSpinnerDate() {
        mDstWidth = getResources().getDimensionPixelSize(R.dimen.destination_width);
        mDstHeight = getResources().getDimensionPixelSize(R.dimen.destination_height);

        typeCheckAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, getResources().getStringArray(R.array.type_checks));
        typeCheckAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeChecks.setAdapter(typeCheckAdapter);

        monthsAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, getResources().getStringArray(R.array.months));
        monthsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthsExpiration.setAdapter(monthsAdapter);

        yearsAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, auxiliaryGeneral.getYearsSpinner());
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearsExpiration.setAdapter(yearsAdapter);
        yearsExpiration.setSelection(1);

        spinnerTypeChecks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1)
                    editTextOrigin.setVisibility(View.GONE);
                else
                    editTextOrigin.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        monthsExpiration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iMonth = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                iMonth = 0;
            }
        });
        yearsExpiration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iYear = Integer.parseInt(yearsExpiration.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                iYear = Integer.parseInt(yearsExpiration.getSelectedItem().toString());
            }
        });
    }

    @OnClick(R.id.imageAddPhoto)
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
            Bitmap bitmap;

            boolean isCamera = (data == null ||
                    data.getData() == null);
            if (isCamera) {

                bitmap = auxiliaryGeneral.getResizedBitmap(BitmapFactory.decodeFile(photoPath),1024,1024);
             //   bitmap = auxiliaryGeneral.getBitmapForPath(photoPath, mDstWidth, mDstHeight);
            } else {
                photoPath = auxiliaryGeneral.selectImageForData(data, getActivity());
               // bitmap = auxiliaryGeneral.getBitmapForPath(photoPath, mDstWidth, mDstHeight);
                bitmap = auxiliaryGeneral.getResizedBitmap(BitmapFactory.decodeFile(photoPath),1024,1024);
            }
            setImageView(bitmap);
        }
    }

    private void setImageView(Bitmap bitmap) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        if (bitmap != null) {
            byte[] byteArray = bytes.toByteArray();
            imageLoader.load(imageAddShow, byteArray);
            check.setPhoto(byteArray);
        }
    }

    @Override
    public void onAddComplete() {
        showSnackbar(getString(R.string.add_complete));
        communicator.refresh();
    }

    @Override
    public void onAddError(String error) {
        showSnackbar(error);
    }

    public void showSnackbar(String msg) {
        Snackbar.make(mainContent, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void enableUIComponent() {
        enableComponent(true);
    }

    @Override
    public void unableUIComponent() {
        enableComponent(false);
    }

    @Override
    public void cleanUIComponent() {
        check = new Check();
        editTextNumber.setText("");
        editTextAmount.setText("");
        daysExpiration.setText("");
        editTextOrigin.setText("");
        editTextDestiny.setText("");
        imageAddShow.setImageResource(android.R.drawable.ic_menu_gallery);
    }

    @Override
    @OnClick(R.id.fab)
    public void saveCheck() {
        int typePosition = spinnerTypeChecks.getSelectedItemPosition();
        check.setType(typePosition);//0=other 1=own
        check.setNumber(editTextNumber.getText().toString());
        check.setAmount(editTextAmount.getText().toString());

        if (auxiliaryGeneral.getExpitationDate(iYear, iMonth, daysExpiration.getText().toString()))
            check.setExpiration(auxiliaryGeneral.validateLengthMountOrDay(daysExpiration.getText().toString()) + "/" + auxiliaryGeneral.validateLengthMountOrDay(String.valueOf(iMonth + 1)) + "/" + String.valueOf(iYear));
        else
            check.setExpiration(null);
        if (typePosition == 0) {
            check.setOrigin(editTextOrigin.getText().toString());
        }
        check.setDestiny(editTextDestiny.getText().toString());
        check.setDestinyDate(auxiliaryGeneral.dateNowShot());
        if (!update)
            presenter.saveCheck(check, getActivity());
        else
            presenter.updateCheck(check, getActivity());

        update = false;
    }

    @Override
    public void isUpdateIUElemente(Check check) {
        spinnerTypeChecks.setSelection(check.getType());
        editTextNumber.setText(check.getNumber().toString());
        editTextAmount.setText(check.getAmount().toString());
        if (check.getExpiration() != null)
            if (!check.getExpiration().isEmpty()) {
                daysExpiration.setText(check.getExpiration().substring(0, 2));
                monthsExpiration.setSelection(Integer.parseInt(check.getExpiration().substring(3, 5)) - 1);
                yearsExpiration.setSelection(auxiliaryGeneral.getPositionSpinnerYear(check.getExpiration().substring(6, 10)));

            }
        if (check.getType() == 0)
            editTextOrigin.setText(check.getOrigin().toString());

        editTextDestiny.setText(check.getDestiny().toString());

        //  .setText(check.getDestiny().toString());

        if (check.getPhoto() != null)
            imageLoader.load(imageAddShow, check.getPhoto());
        else
            imageAddShow.setImageResource(android.R.drawable.ic_menu_camera);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void enableComponent(boolean isEnable) {
        // .setEnabled(isEnable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void initOnClickEditText(View v) {
        auxiliaryGeneral.initOnClickEditText(v);
    }
}
