package com.example.personalproject.mainPackage.common;

import android.content.Context;
import android.os.Environment;

import com.example.personalproject.mainPackage.MyApplicationNew;
import com.example.personalproject.mainPackage.utilities.CalendarUtils;
import com.example.personalproject.mainPackage.utilities.LogUtils;
import com.example.personalproject.mainPackage.utilities.StringUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Creating Application Level Storage Directories
 * eg: storing images,caching data etc
 */
public class FilesStorage {
    public static String SDCARD_ROOT;

    public static String ROOT_DIR;
    public static String DOWNLOADS;

    public static String IMAGE_CACHE_DIR = "";

    public static String IMAGE_FAVOURITE_DIR;

    public static String TEMP_DIR;
    public static String BACK_UP;
    public static String EOT_BACK_UP;


    public static void CreateStorageDirs(Context context) {
        // Ensure that the directories exist.

        if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_REMOVED))
            SDCARD_ROOT = context.getFilesDir().toString() + "/";
        else
            SDCARD_ROOT = Environment.getExternalStorageDirectory().toString() + "/";

        ROOT_DIR = SDCARD_ROOT + "Blase/";
        IMAGE_CACHE_DIR = ROOT_DIR + "ImageCache/";
        IMAGE_FAVOURITE_DIR = ROOT_DIR + "Favourite/";
        TEMP_DIR = ROOT_DIR + "Temp/";

        DOWNLOADS = ROOT_DIR + "Downloads/";
        BACK_UP = ROOT_DIR + "Backup/";
        EOT_BACK_UP = ROOT_DIR + "EotbackUp/";

        new File(ROOT_DIR).mkdirs();
        new File(BACK_UP).mkdirs();
        new File(EOT_BACK_UP).mkdirs();
//		new File(IMAGE_CACHE_DIR).mkdirs();
        if (new File(DOWNLOADS).isDirectory()) {

        } else {
            new File(DOWNLOADS).mkdirs();
        }
    }

    public static void clearDir(String dirPath) {
        try {
            File delDir = new File(dirPath);

            File[] filesList = delDir.listFiles();
            for (int i = 0; i < filesList.length; i++) {
                try {
                    filesList[i].delete();
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
    }

    public static void copy(String source, String destination) throws IOException {

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
        FileOutputStream fos = new FileOutputStream(destination);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        byte[] byt = new byte[8192];
        int noBytes;
        while ((noBytes = bis.read(byt)) != -1)
            bos.write(byt, 0, noBytes);

        bos.flush();
        bos.close();
        fos.close();
        bis.close();
    }

    public static void deleteOldDatabse(String source) {
        File files = new File(source);
        final File[] filesFound = files.listFiles();
        if (filesFound != null) {
            for (File file : filesFound) {
                long diffrence = 0;
                String name = file.getName().split("_")[0];
                try {
                    diffrence = CalendarUtils.getCurrentTimeInMilli() - StringUtils.getLong(name);
                    if (diffrence > 0)
                        diffrence = (int) diffrence / (24 * 60 * 60 * 1000);
                    if (diffrence > 6 || diffrence == 0)
                        file.delete();
                    LogUtils.errorLog("diffrence", "" + diffrence);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getRootDirector() {
        if (Environment.getExternalStorageState().equalsIgnoreCase(
                Environment.MEDIA_REMOVED))
            SDCARD_ROOT = MyApplicationNew.mContext.getFilesDir().toString()
                    + "/";
        else if (MyApplicationNew.mContext.getExternalCacheDir() != null)
            SDCARD_ROOT = MyApplicationNew.mContext.getExternalCacheDir()
                    .toString() + "/";
        else
            SDCARD_ROOT = Environment.getExternalStorageDirectory()
                    .toString() + "/";
        ROOT_DIR = SDCARD_ROOT + "Unilever/";
        return ROOT_DIR;
    }

    public static String getTempDownload() {
        if (Environment.getExternalStorageState().equalsIgnoreCase(
                Environment.MEDIA_REMOVED))
            SDCARD_ROOT = MyApplicationNew.mContext.getFilesDir().toString()
                    + "/";
        else
            SDCARD_ROOT = MyApplicationNew.mContext.getExternalCacheDir()
                    .toString() + "/";
        ROOT_DIR = SDCARD_ROOT + "Unilever/";
        TEMP_DIR = ROOT_DIR + "TempDownload/";
        return TEMP_DIR;
    }

    public static String getImageCacheDirectory() {
        IMAGE_CACHE_DIR = getRootDirector() + "ImageCache/";
        return IMAGE_CACHE_DIR;
    }

    public static String getHttpCacheDirectory() {
        IMAGE_CACHE_DIR = getRootDirector() + "Http/";
        return IMAGE_CACHE_DIR;
    }
}
