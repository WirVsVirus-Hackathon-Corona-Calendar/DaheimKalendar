package de.garritfra.daheimkalender;

import android.media.Image;
import android.os.AsyncTask;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class ImageCache {

    private static ImageCache sharedInstance = new ImageCache();

    public HashMap<String, Image> images;

    public static ImageCache getInstance() {
        return sharedInstance;
    }

    public ImageCache() {
        images = new HashMap<String, Image>();
    }

    public void downloadImages() {
    }
}