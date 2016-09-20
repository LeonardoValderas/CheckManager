package com.argentinatecno.checkmanager.lib.base;

import android.widget.ImageView;

public interface ImageLoader {
    void load(ImageView imageView, byte[] bytes);
    void loadDialog(ImageView imageView, byte[] bytes);
}
