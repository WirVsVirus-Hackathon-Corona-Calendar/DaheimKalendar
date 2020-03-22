package de.garritfra.daheimkalender;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.garritfra.daheimkalender.model.Challenge;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImageStorage {

    private static ImageStorage sharedInstance = new ImageStorage();

    private HashMap<String, Bitmap> images;

    public static ImageStorage getInstance() {
        return sharedInstance;
    }

    private ImageStorage() {
        images = new HashMap<String, Bitmap>();
    }

    private void storeImage(String url, Bitmap bitmap) {
        
    }

    public void getImage(String url, ImageStorageListener listener) {
        Bitmap bitmap = images.get(url);
        if (bitmap == null) {
            downloadImage(url, listener);
        } else if (listener != null) {
            listener.onImageLoaded(bitmap);
        }
    }

    public void getImages(final String[] urls, final ImageStorageListener listener) {
        final List<String> finishedLoads = new LinkedList<String>();
        for (int i = 0; i < urls.length; i++) {
            final String url = urls[i];
            getImage(url, new ImageStorageListener() {
                @Override
                public void onImageLoaded(Bitmap bitMap) {
                    finishedLoads.add(url);
                    if (finishedLoads.size() == urls.length) {
                        listener.onImageLoaded(null);
                    }
                }
            });
        }
    }

    private void downloadImage(final String url, final ImageStorageListener listener) {

        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("E", String.valueOf(e.getStackTrace()));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                try {
                    byte[] bytes = response.body().bytes();
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                    new Handler(Looper.getMainLooper()).post(new Runnable() { // <-- if you are not on UI thread and want to go there
                        @Override
                        public void run() {
                            images.put(url, bitmap);
                            listener.onImageLoaded(bitmap);
                        }
                    });
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public interface ImageStorageListener {
        public void onImageLoaded(Bitmap bitMap);
    }
}