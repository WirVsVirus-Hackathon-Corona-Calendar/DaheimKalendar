package de.garritfra.daheimkalender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import de.garritfra.daheimkalender.util.NameUtil;
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

    public String storeChallengeImage(Bitmap bitmap, Context context) {
        String path = NameUtil.getRandomImageName();
        storeImage(path, bitmap, context);
        return path;
    }

    private void storeImage(String url, Bitmap bitmap, Context context) {
        String filename = NameUtil.getResourceImageName(url);
        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Log.d(getClass().getCanonicalName(), "Saving image " + filename + " from url " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Boolean isImageStored(String url, Context context) {
        String filename = NameUtil.getResourceImageName(url);
        File file = new File(context.getFilesDir(), filename);
        return file.exists();
    }

    private Bitmap loadImage(String url, Context context) {
        String filename = NameUtil.getResourceImageName(url);
        File file = new File(context.getFilesDir(), filename);
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    public void getImage(String url, ImageStorageListener listener, Context context) {
        Log.d(getClass().getCanonicalName(), "Getting image: " + url);
        Bitmap bitmap = images.get(url);
        if (bitmap != null) {
            Log.d(getClass().getCanonicalName(), "Image from cache: " + url);
            if (listener != null) {
                listener.onImageLoaded(bitmap);
            };
        } else if (isImageStored(url, context)) {
            Log.d(getClass().getCanonicalName(), "Loaded image from storage: " + url);
            bitmap = loadImage(url, context);
            listener.onImageLoaded(bitmap);
        } else {
            Log.d(getClass().getCanonicalName(), "Downloading from cache: " + url);
            downloadImage(url, listener, context);
        }
    }

    public void getImages(final String[] urls, final ImageLoadingListener listener, Context context) {
        final List<String> finishedLoads = new LinkedList<String>();
        for (int i = 0; i < urls.length; i++) {
            final String url = urls[i];
            getImage(url, new ImageStorageListener() {
                @Override
                public void onImageLoaded(Bitmap bitMap) {
                    finishedLoads.add(url);
                    int loaded = finishedLoads.size();
                    listener.onElementLoaded( loaded,loaded == urls.length);
                }
            }, context);
        }
    }

    private void downloadImage(final String url, final ImageStorageListener listener, final Context context) {

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
                            storeImage(url, bitmap, context);
                            listener.onImageLoaded(bitmap);
                        }
                    });
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public interface ImageStorageListener {
        public void onImageLoaded(Bitmap bitMap);
    }

    public interface ImageLoadingListener {
        public void onElementLoaded(int loadedImages, Boolean isFinal);
    }
}