package com.argentinatecno.checkmanager.lib.di;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.argentinatecno.checkmanager.lib.base.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class GlideImageLoader implements ImageLoader {
    private RequestManager glideRequestManager;

    public void setLoaderContext(Fragment fragment) {
        if (fragment != null)
            this.glideRequestManager = Glide.with(fragment);
    }

    public void setLoaderContext(Activity activity) {
        if (activity != null)
            this.glideRequestManager = Glide.with(activity);
    }

    @Override
    public void load(ImageView imageView, byte[] bytes) {
        glideRequestManager
                .load(bytes).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(imageView);
    }

    @Override
    public void loadDialog(ImageView imageView, byte[] bytes) {
        glideRequestManager
                .load(bytes).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(imageView);
    }
}
