package com.jofre.managercheck.lib;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jofre.managercheck.lib.base.ImageLoader;

/**
 * Created by LEO on 5/7/2016.
 */
public class GlideImageLoader implements ImageLoader {
    private RequestManager glideRequestManager;

    public void setLoaderContext(Fragment fragment) {
        this.glideRequestManager = Glide.with(fragment);
    }

    @Override
    public void load(ImageView imageView) {
        glideRequestManager
                .load(Uri.EMPTY)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(800, 800)
                .centerCrop()
                .into(imageView);
    }
}
