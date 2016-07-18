package com.jofre.managercheck.lib;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jofre.managercheck.R;
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
    public void load(ImageView imageView, byte[] bytes) {
        glideRequestManager
                .load(bytes).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(200, 200)
                .placeholder(R.drawable.border_rect_linear)
                .centerCrop()
                .into(imageView);
    }
}
