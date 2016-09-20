package com.argentinatecno.checkmanager.main.activity_maturities.adapters;

import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.lib.base.ImageLoader;

public interface OnItemClickListener {
    void onShowImageClick(ImageLoader imageLoader, Check check);
}
