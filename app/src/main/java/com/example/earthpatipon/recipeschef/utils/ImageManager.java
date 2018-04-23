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

    public void copyStream(InputStream is, OutputStream os) throws IOException {
        byte[] buffer = new byte[5120];
        int length = is.read(buffer);
        while (length > 0) {
            os.write(buffer, 0, length);
            length = is.read(buffer);
        }
    }

    public void copyAssetsFromFolder(String folderName) throws IOException {
        AssetManager assetManager = context.getAssets();
        String assets[];
        String internalStorage = context.getFilesDir().getPath() + File.separator + folderName;
        File checkFolder = new File(internalStorage);
        if (!checkFolder.exists())
            checkFolder.mkdir();
        try {
            assets = assetManager.list(folderName);
            for (String asset : assets) {
                InputStream is = assetManager.open(folderName + File.separator + asset);
                OutputStream os = new FileOutputStream(internalStorage + File.separator + asset);
                copyStream(is, os);
                os.flush();
                os.close();
                is.close();
            }
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage());
        }
    }
}
