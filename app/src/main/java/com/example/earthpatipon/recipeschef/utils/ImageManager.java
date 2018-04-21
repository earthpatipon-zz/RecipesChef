package com.example.earthpatipon.recipeschef.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageManager {

    private Context context;

    public ImageManager(Context context) {
        this.context = context;
    }

    public void copyFileOrDir(String path) {
        AssetManager assetManager = context.getAssets();
        String assets[];
        try {
            assets = assetManager.list(path);
            if (assets.length == 0) {
                copyFile(path);
            } else {
                String fullPath = "/data/data/" + context.getPackageName() + "/" + path;
                File dir = new File(fullPath);
                if (!dir.exists())
                    dir.mkdir();
                for (int i = 0; i < assets.length; ++i) {
                    //Log.d("path+asset", path+"/"+assets[i]);
                    copyFileOrDir(path + "/" + assets[i]);
                }
            }
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    private void copyFile(String filename) {
        AssetManager assetManager = context.getAssets();

        InputStream in;
        OutputStream out;
        try {
            in = assetManager.open(filename);
            String newFileName = "/data/data/" + context.getPackageName() + "/" + filename;
            //Log.d("newFileName", newFileName);
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }
}
