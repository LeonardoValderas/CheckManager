package com.jofre.managercheck.lib.base;

import java.io.File;

/**
 * Created by LEO on 5/7/2016.
 */
public interface ImageStorage {
    String getImageUrl(String id);
    void upload(File file, String id, ImageStorageFinishedListener listener);
}