package com.jofre.managercheck.receiveraddmain.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jofre.managercheck.R;
import com.jofre.managercheck.receiveradd.ui.ReceiverAddFragment;
import com.jofre.managercheck.receiveraddlist.ui.ReceiverAddListFragment;
import com.jofre.managercheck.receiveraddmain.Communicator;
import com.jofre.managercheck.receiveraddmain.ReceiverMainPresenter;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.receiveraddmain.ui.adapters.ReceiverSectionsPagerAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReceiverMainActivity extends AppCompatActivity implements Communicator {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
//    @Bind(R.id.fab)
//    FloatingActionButton fab;
    @Inject
    ReceiverMainPresenter presenter;
    @Inject
    ReceiverSectionsPagerAdapter adapter;

    private static final int REQUEST_PICTURE = 0;
    private String photoPath;
    private Check check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_receive);
        ButterKnife.bind(this);
        setupInjection();
        setupNavigation();

//        presenter.onCreate();
    }

//    @Override
//    protected void onDestroy() {
//        presenter.onDestroy();
//        super.onDestroy();
//    }

    private void setupNavigation() {
        //  PhotoFeedApp app = (PhotoFeedApp) getApplication();
         toolbar.setTitle(getString(R.string.receiver_name_add));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }

    private void setupInjection() {
        String[] titles = new String[]{getString(R.string.receiver_title_add),
                getString(R.string.receiver_title_list)};

        Fragment[] fragments = new Fragment[]{new ReceiverAddFragment(),
                new ReceiverAddListFragment()};
        adapter = new ReceiverSectionsPagerAdapter(getSupportFragmentManager(), titles, fragments);
//        PhotoFeedApp app = (PhotoFeedApp) getApplication();
//        app.getMainComponent(this, getSupportFragmentManager(), fragments, titles).inject(this);
    }

    public void refresh() {
        FragmentManager manager = getSupportFragmentManager();
        ReceiverAddListFragment fragment = (ReceiverAddListFragment) manager
                .findFragmentByTag("android:switcher:" + viewPager.getId()
                        + ":" + 1);

        fragment.getChecks();

    }
//    @OnClick(R.id.fab)
//    public void takePicture() {
//        Intent chooserIntent = null;
//
//        List<Intent> intentList = new ArrayList<>();
//
//        Intent pickIntent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        takePhotoIntent.putExtra("return-data", true);
//        File photoFile = getFile();
//
//        if (photoFile != null) {
//            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
//            if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {
//                intentList = addIntentsToList(intentList, takePhotoIntent);
//            }
//        }
//
//        if (pickIntent.resolveActivity(getPackageManager()) != null) {
//            intentList = addIntentsToList(intentList, pickIntent);
//        }
//
//        if (intentList.size() > 0) {
//            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
//                    getString(R.string.main_message_picture_source));
//            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
//        }
//
//        startActivityForResult(chooserIntent, REQUEST_PICTURE);
//    }
//
//    private List<Intent> addIntentsToList(List<Intent> list, Intent intent) {
//        List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(intent, 0);
//        for (ResolveInfo resolveInfo : resInfo) {
//            String packageName = resolveInfo.activityInfo.packageName;
//            Intent targetedIntent = new Intent(intent);
//            targetedIntent.setPackage(packageName);
//            list.add(targetedIntent);
//        }
//        return list;
//    }
//
//    private File getFile() {
//        File photoFile = null;
//        try {
//            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//            String imageFileName = "JPEG_" + timeStamp + "_";
//            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//            photoFile = File.createTempFile(imageFileName, ".jpg", storageDir);
//            photoPath = photoFile.getAbsolutePath();
//        } catch (IOException ex) {
//            showSnackbar(getString(R.string.main_error_dispatch_camera));
//        }
//        return photoFile;
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK && requestCode == REQUEST_PICTURE) {
//            boolean isCamera = (data == null ||
//                    data.getData() == null);
//
//            if (isCamera) {
//                addPicToGallery();
//            } else {
//                photoPath = getRealPathFromURI(data.getData());
//            }
//
//
//            //presenter.uploadPhoto(lastLocation, photoPath);
//        }
//    }
//
//    private String getRealPathFromURI(Uri contentURI) {
//        String result = null;
//        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
//        if (cursor == null) {
//            result = contentURI.getPath();
//        } else {
//            if (contentURI.toString().contains("mediaKey")) {
//                cursor.close();
//
//                try {
//                    File file = File.createTempFile("tempImg", ".jpg", getCacheDir());
//                    InputStream input = getContentResolver().openInputStream(contentURI);
//                    OutputStream output = new FileOutputStream(file);
//
//                    try {
//                        byte[] buffer = new byte[4 * 1024];
//                        int read;
//
//                        while ((read = input.read(buffer)) != -1) {
//                            output.write(buffer, 0, read);
//                        }
//                        output.flush();
//                        result = file.getAbsolutePath();
//                    } finally {
//                        output.close();
//                        input.close();
//                    }
//
//                } catch (Exception e) {
//                }
//            } else {
//                cursor.moveToFirst();
//                int dataColumn = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//                result = cursor.getString(dataColumn);
//                cursor.close();
//            }
//
//        }
//        return result;
//    }
//
//    private void addPicToGallery() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(photoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }
//
//    @Override
//    public void onAddInit() {
//        showSnackbar(getString(R.string.add_init));
//    }
//
//    @Override
//    public void onAddComplete() {
//        showSnackbar(getString(R.string.add_complete));
//    }
//
//    @Override
//    public void onAddError(String error) {
//        showSnackbar(error);
//    }
//
//    public void showSnackbar(String msg) {
//        Snackbar.make(viewPager, msg, Snackbar.LENGTH_SHORT).show();
//    }
}
