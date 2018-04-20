package com.example.earthpatipon.recipeschef.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
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

    public void createDir(File dir) throws IOException {
        if (dir.exists()) {
            if (!dir.isDirectory()) {
                throw new IOException("Can't create directory, a file is in the way");
            }
        }
        else {
            dir.mkdirs();
            if (!dir.isDirectory()) {
                throw new IOException("Unable to create directory");
            }
        }
    }

    public String copyFileFromAssetManager(String assetDir, String destDir) throws IOException {

        String path = context.getFilesDir().getAbsolutePath()+"/"+destDir;
        File folder = new File(path);

        createDir(folder);

        Log.d("path",path);

        AssetManager assetManager = this.context.getAssets();
        String[] files = assetManager.list(assetDir);

        for (int i = 0; i < files.length; i++) {

            String absFilePath = addTrailingSlash(assetDir)  + files[i];
            Log.d("absFilePath", absFilePath);
            String subFiles[] = assetManager.list(absFilePath);

            if (subFiles.length == 0) {
                // It is a file
                String destFilePath = addTrailingSlash(assetDir) + files[i];
                Log.d("destFilePath", destFilePath);
                copyAssetFile(absFilePath, destFilePath);
            } else {
                // It is a sub directory
                copyFileFromAssetManager(absFilePath, addTrailingSlash(destDir) + files[i]);
            }
        }

        return path;
    }


    public void copyAssetFile(String assetFilePath, String destinationFilePath) throws IOException {
        InputStream in = this.context.getAssets().open(assetFilePath);
        OutputStream out = new FileOutputStream(destinationFilePath);

        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0)
            out.write(buf, 0, len);
        in.close();
        out.close();
    }

    public String addTrailingSlash(String path) {
        if (path.charAt(path.length() - 1) != '/') {
            path += "/";
        }
        return path;
    }

    public String addLeadingSlash(String path) {
        if (path.charAt(0) != '/') {
            path = "/" + path;
        }
        return path;
    }


}
