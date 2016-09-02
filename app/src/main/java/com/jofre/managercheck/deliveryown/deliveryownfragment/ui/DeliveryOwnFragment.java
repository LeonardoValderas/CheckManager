package com.jofre.managercheck.deliveryown.deliveryownfragment.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.jofre.managercheck.ManagerCheckApp;
import com.jofre.managercheck.R;
import com.jofre.managercheck.auxiliary.AuxiliaryGeneral;
import com.jofre.managercheck.deliveryown.deliveryownmainactivity.Communicator;
import com.jofre.managercheck.deliveryown.deliveryownfragment.DeliveryOwnFragmentPresenter;
import com.jofre.managercheck.deliveryown.deliveryownfragment.di.DeliveryOwnFragmentComponent;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeliveryOwnFragment extends Fragment implements DeliveryOwnFragmentView {
    @Bind(R.id.editTextNumber)
    EditText editTextNumber;
    @Bind(R.id.editTextAmount)
    EditText editTextAmount;
    @Bind(R.id.editTextOrigin)
    EditText editTextOrigin;
    @Bind(R.id.linearPhoto)
    LinearLayout linearPhoto;
    @Bind(R.id.imgReceiver)
    ImageView imgDelivery;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.relativeContent)
    RelativeLayout relativeContent;
    @Bind(R.id.daysExpiration)
    EditText daysExpiration;
    @Bind(R.id.monthsExpiration)
    Spinner monthsExpiration;
    @Bind(R.id.yearsExpiration)
    Spinner yearsExpiration;

    private Communicator communicator;
    private ImageLoader imageLoader;
    private String photoPath;
    private static final int REQUEST_PICTURE = 0;
    private DatePickerDialog.OnDateSetListener d;
    private boolean update = false;
    private AuxiliaryGeneral auxiliaryGeneral = AuxiliaryGeneral.getInstance(getActivity());
    DeliveryOwnFragmentPresenter presenter;
    Check check = new Check();
    //    private Calendar calendar = Calendar.getInstance();
//    private SimpleDateFormat formate = new SimpleDateFormat(
//            "dd-MM-yyyy");
//    private Date date = new Date();
    private DeliveryOwnFragmentComponent component;
    private ArrayAdapter<String> monthsAdapter;
    private ArrayAdapter<String> yearsAdapter;
    private int iMonth;
    private int iYear;

    public DeliveryOwnFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communicator = (Communicator) getActivity();
        setupInjection();
        //  OnClickdatePicker();
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
        View view = inflater.inflate(R.layout.fragment_own_delivery, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void isUpdate() {
        update = getActivity().getIntent().getBooleanExtra("update", false);
        if (update) {
            check.setId_check(getActivity().getIntent().getIntExtra("id", 0));
            check.setNumber(getActivity().getIntent().getStringExtra("number"));
            check.setAmount(getActivity().getIntent().getStringExtra("amount"));
            check.setOrigin(getActivity().getIntent().getStringExtra("origin"));
            byte[] b = getActivity().getIntent().getByteArrayExtra("photo");
            check.setPhoto(b);
            check.setExpiration(getActivity().getIntent().getStringExtra("expiration"));
            presenter.siUpdate(check);
        }
    }

    private void setupInjection() {
        ManagerCheckApp app = (ManagerCheckApp) getActivity().getApplication();
        component = app.getDeliveryOwnFragmentComponent(this, this);
        imageLoader = component.getImageLoader();
        presenter = component.getPresenter();
    }

    private void fillSpinnerDate() {
        monthsAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, getResources().getStringArray(R.array.months));
        monthsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthsExpiration.setAdapter(monthsAdapter);

        yearsAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, auxiliaryGeneral.getYearsSpinner());
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearsExpiration.setAdapter(yearsAdapter);
        yearsExpiration.setSelection(1);
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
            Bitmap bitmap;

            boolean isCamera = (data == null ||
                    data.getData() == null);
            if (isCamera) {
                bitmap = auxiliaryGeneral.SelectImage(photoPath);
                //if(bitmap == null) {
                //    addPicToGallery();
                //photoPath = getRealPathFromURI(data.getData());
                //bitmap = BitmapFactory.decodeFile(photoPath);
                // }
            } else {
                //     photoPath = getRealPathFromURI(data.getData());
                bitmap = auxiliaryGeneral.SelectImage(data, getActivity());

                // photoPath = getRealPathFromURI(data.getData());
                //    bitmap= BitmapFactory.decodeFile(photoPath);

                //setImageView(photoPath);
            }
            //  if(bitmap != null)
            setImageView(bitmap);
        }
    }

    private void setImageView(Bitmap bitmap) {

//        if(bitmap == null)
//        bitmap = BitmapFactory.decodeFile(photoPath);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        if (bitmap != null) {
            byte[] byteArray = bytes.toByteArray();
            imageLoader.load(imgDelivery, byteArray);
            //   check.setPhoto(new Blob((byteArray)));
            check.setPhoto(byteArray);
        }
    }

//    private String getRealPathFromURI(Uri contentURI) {
//        String result = null;
//        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
//        if (cursor == null) {
//            result = contentURI.getPath();
//        } else {
//            if (contentURI.toString().contains("mediaKey")) {
//                cursor.close();
//
//                try {
//                    File file = File.createTempFile("tempImg", ".jpg", getActivity().getCacheDir());
//                    InputStream input = getActivity().getContentResolver().openInputStream(contentURI);
//                    OutputStream output = new FileOutputStream(file);
//                    try {
//                        byte[] buffer = new byte[4 * 1024];
//                        int read;
//                        while ((read = input.read(buffer)) != -1) {
//                            output.write(buffer, 0, read);
//                        }
//                        output.flush();
//                        result = file.getAbsolutePath();
//                    } finally {
//                        output.close();
//                        input.close();
//                    }
//                } catch (Exception e) {
//                }
//            } else {
//                cursor.moveToFirst();
//                int dataColumn = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//                result = cursor.getString(dataColumn);
//                cursor.close();
//            }
//        }
//        return result;
//    }

//    private void addPicToGallery() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//
//        File f = new File(photoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        getActivity().sendBroadcast(mediaScanIntent);
//    }

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
        imgDelivery.setImageResource(android.R.drawable.ic_menu_camera);
    }

    @Override
    @OnClick(R.id.fab)
    public void saveCheck() {
        check.setType(1);//0=other 1=own
        check.setNumber(editTextNumber.getText().toString());
        check.setAmount(editTextAmount.getText().toString());
        check.setOrigin(editTextOrigin.getText().toString());
        if (auxiliaryGeneral.getExpitationDate(iYear, iMonth, daysExpiration.getText().toString()))
            check.setExpiration(auxiliaryGeneral.validateLengthMountOrDay(daysExpiration.getText().toString()) + "/" + auxiliaryGeneral.validateLengthMountOrDay(String.valueOf(iMonth + 1)) + "/" + String.valueOf(iYear));
        else
            check.setExpiration(null);

        if (!update)
            presenter.saveCheck(check, getActivity());
        else
            presenter.updateCheck(check, getActivity());

        update = false;
    }

//    public boolean getExpitationDate() {
//        Calendar mycal = new GregorianCalendar(iYear, iMonth, 1);
//        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
//        if (!daysExpiration.getText().toString().isEmpty() && daysExpiration.getText() != null) {
//            if (Integer.parseInt(daysExpiration.getText().toString()) <= daysInMonth)
//                return true;
//            else
//                return false;
//
//        } else {
//            return false;
//        }
//    }

    @Override
    public void isUpdateIUElemente(Check check) {
        editTextNumber.setText(check.getNumber().toString());
        editTextAmount.setText(check.getAmount().toString());
        if (check.getExpiration() != null && !check.getExpiration().isEmpty()) {
            daysExpiration.setText(check.getExpiration().substring(0, 2));
            monthsExpiration.setSelection(Integer.parseInt(check.getExpiration().substring(3, 5)) - 1);
            yearsExpiration.setSelection(auxiliaryGeneral.getPositionSpinnerYear(check.getExpiration().substring(6, 10)));
        }
        editTextOrigin.setText(check.getOrigin().toString());

        if (check.getPhoto() != null)
            imageLoader.load(imgDelivery, check.getPhoto());
        else
            imgDelivery.setImageResource(android.R.drawable.ic_menu_camera);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void enableComponent(boolean isEnable) {
        relativeContent.setEnabled(isEnable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //  @OnClick(R.id.daysExpiration)
//    public void getDate() {
//        new DatePickerDialog(getActivity(), d, calendar.get(Calendar.YEAR),
//                calendar.get(Calendar.MONTH),
//                calendar.get(Calendar.DAY_OF_MONTH)).show();
//    }
//
//    public void OnClickdatePicker() {
//        d = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                  int dayOfMonth) {
//                calendar.set(Calendar.YEAR, year);
//                calendar.set(Calendar.MONTH, monthOfYear);
//                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                setDate();
//            }
//        };
//    }
//
//    public void setDate() {
//        date = calendar.getTime();
//        if (date != null) {
//            String dateStg = formate.format(calendar.getTime());
//            daysExpiration.setText(dateStg);
//            check.setExpiration(dateStg);
//        }
//    }

//    public int getPositionSpinnerYear(String year) {
//
//        int index = 0;
//        for (int i = 0; i < auxiliaryGeneral.getYearsSpinner().size(); i++) {
//            if (auxiliaryGeneral.getYearsSpinner().get(i).toString().equals(year)) {
//                index = i;
//            }
//        }
//        return index;
//    }

    public void initOnClickEditText(View v) {
        auxiliaryGeneral.initOnClickEditText(v);
    }

}
