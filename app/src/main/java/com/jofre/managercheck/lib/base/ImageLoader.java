package com.jofre.managercheck.lib.base;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by LEO on 5/7/2016.
 */
public interface ImageLoader {
    void load(ImageView imageView, byte[] bytes);
}
