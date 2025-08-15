package com.example.personalproject.mainPackage.utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;

import com.google.android.material.color.utilities.Scheme;

public class FileUtils {

    public interface DownloadListner {
        void onProgrss(int count);

        void onComplete();

        void onError();
    }

    public static synchronized String downloadSQLITE(String sUrl, String directory, Context context, String sqliteName, DownloadListner downloadListener) {
        if (!sUrl.contains(".zip"))
            sUrl = sUrl.replace("sqlite", "zip");
        System.gc();

        if (sUrl == null || sUrl.length() <= 0)
            return null;

        String sqliteFilePath = sqliteName + ".zip";

        File file = new File(directory, sqliteFilePath);

        if (file.exists())
            file.delete();
        //		File masterFiles =  new File(directory);
        //		if(masterFiles.isDirectory() && masterFiles.exists())
        //		{
        //
        //			LogUtils.defaultLog("downloadSQLITE", "file directory");
        //			for(File f : masterFiles.listFiles())
        //			{
        //				LogUtils.defaultLog("downloadSQLITE", "filename ="+f.getName());
        //				f.delete();
        //			}
        //		}
        //
        String localFilePath = directory + sqliteFilePath;

        try {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
            sUrl = sUrl.replace(" ", "%20");

            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;

            try {

                URL url = new URL(sUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }


                int fileLength = connection.getContentLength();

                input = connection.getInputStream();
                output = new FileOutputStream(localFilePath);

                byte[] data = new byte[1024];
                long total = 0;
                int count;
                LogUtils.defaultLog("downloadSQLITE", "filelength=" + fileLength);
                while ((count = input.read(data)) != -1) {
                    if (NetworkUtility.isNetworkConnectionAvailable(context)) {
                        LogUtils.defaultLog("downloadSQLITE", "count-start =" + count);
                        total += count;
                        if (fileLength > 0)
                            downloadListener.onProgrss((int) (total * 100 / fileLength));
                        LogUtils.defaultLog("downloadSQLITE", "count-end =" + total);
                        output.write(data, 0, count);
                    } else {
                        try {
                            if (output != null)
                                output.close();
                            if (input != null)
                                input.close();
                        } catch (IOException ignored) {
                        }

                        if (connection != null)
                            connection.disconnect();
                        return null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }

            ZipUtils.upZipFile(file, directory);

            count = 0;
            downloadListener.onComplete();
            return localFilePath;
        } catch (Exception e) {
            file.delete();

            if (count < 3) {
                count++;
                e.printStackTrace();
                return downloadSQLITE(sUrl, directory, context, sqliteName, downloadListener);
            } else {
                count = 0;
                downloadListener.onError();
                return null;
            }
        }
    }

    public static synchronized String downloadSQLITESalesHistory(String sUrl, String directory, Context context, String sqliteName, DownloadListner downloadListener) {
        if (!sUrl.contains(".zip"))
            sUrl = sUrl.replace("sqlite", "zip");
        System.gc();

        if (sUrl == null || sUrl.length() <= 0)
            return null;

        String sqliteFilePath = sqliteName + ".zip";

        File file = new File(directory, sqliteFilePath);

        if (file.exists())
            file.delete();
        //		File masterFiles =  new File(directory);
        //		if(masterFiles.isDirectory() && masterFiles.exists())
        //		{
        //
        //			LogUtils.defaultLog("downloadSQLITE", "file directory");
        //			for(File f : masterFiles.listFiles())
        //			{
        //				LogUtils.defaultLog("downloadSQLITE", "filename ="+f.getName());
        //				f.delete();
        //			}
        //		}
        //
        String localFilePath = directory + sqliteFilePath;

        try {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
            sUrl = sUrl.replace(" ", "%20");

            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;

            try {

                URL url = new URL(sUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }


                int fileLength = connection.getContentLength();

                input = connection.getInputStream();
                output = new FileOutputStream(localFilePath);

                byte[] data = new byte[1024];
                long total = 0;
                int count;
                LogUtils.defaultLog("downloadSQLITE", "filelength=" + fileLength);
                while ((count = input.read(data)) != -1) {
                    if (NetworkUtility.isNetworkConnectionAvailable(context)) {
                        LogUtils.defaultLog("downloadSQLITE", "count-start =" + count);
                        total += count;
                        if (fileLength > 0)
                            downloadListener.onProgrss((int) (total * 100 / fileLength));
                        LogUtils.defaultLog("downloadSQLITE", "count-end =" + total);
                        output.write(data, 0, count);
                    } else {
                        try {
                            if (output != null)
                                output.close();
                            if (input != null)
                                input.close();
                        } catch (IOException ignored) {
                        }

                        if (connection != null)
                            connection.disconnect();
                        return null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }

            ZipUtils.upZipFileForSalesHistory(file, directory);

            count = 0;
            downloadListener.onComplete();
            return localFilePath;
        } catch (Exception e) {
            file.delete();

            if (count < 3) {
                count++;
                e.printStackTrace();
                return downloadSQLITESalesHistory(sUrl, directory, context, sqliteName, downloadListener);
            } else {
                count = 0;
                downloadListener.onError();
                return null;
            }
        }
    }

    static int count = 0;

    private static boolean isTaskInterrupted = false;
    public static boolean isCancel = false;

    public static void interruptBackgroundTask() {
        isTaskInterrupted = true;
        if (httpConn != null) {
            httpConn.disconnect();
        }
    }

    public static void startBackgroundTask() {
        isCancel = false;
        isTaskInterrupted = false;
        if (httpConn != null) {
            httpConn.disconnect();
        }
    }


    private static HttpURLConnection httpConn;

    public static int downloadFile(DownloadListner listner, String path, File file) {

        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        int count, downloadPercentage = 0;

        try {
            path = path.replace(" ", "%20");
            // path = "http://maven.apache.org/archives/maven-1.x/maven.pdf";
            URL url = new URL(path);
            // URLConnection conection = url.openConnection();
            httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();
            if (responseCode == 200) {
                httpConn.connect();
                // getting file length
                int lenghtOfFile = httpConn.getContentLength();
                // InputStream input = new BufferedInputStream(url.openStream(),
                // 8192);
                InputStream input = httpConn.getInputStream();
                FileOutputStream output = new FileOutputStream(file);

                byte[] data = new byte[1024];

                long total = 0;
                int c = 0;

                Log.d("rohit", "Size of file=" + lenghtOfFile);
                while ((count = input.read(data)) != -1) {
                    total += count;
                    if (isTaskInterrupted) {
                        if (isCancel)
                            return 1;
                        break;
                    } else {
                        output.write(data, 0, count);
                        downloadPercentage = (int) ((total * 100) / lenghtOfFile);
                        Log.d("rohit", "progress" + downloadPercentage);
                        listner.onProgrss(downloadPercentage);
                    }

                }
                output.flush();
                output.close();
                input.close();

                listner.onComplete();

            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            listner.onError();
            if (isCancel)
                return 1;
            return 0;

        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return 2;

    }

    public static String getFileNameFromPath(String filePath) {
        String fileName = null;
        try {
            fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public static String SaveInputStreamAsFile(Context ctx, String SdcardPath, String source, String fileName) {
        try {
            File myFile = new File(SdcardPath, "Themes.xml");

            myFile.createNewFile();

            FileOutputStream fOut = new FileOutputStream(myFile);

            OutputStreamWriter myOutWriter =

                    new OutputStreamWriter(fOut);

            myOutWriter.append(source);

            myOutWriter.close();

            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void inputStream2File(InputStream inputStream, String fileName, String SdcardPath) {
        try {
            File themeFile = new File(SdcardPath);
            if (!themeFile.exists()) {
                new File(SdcardPath).mkdirs();
            }
            File file = new File(SdcardPath + fileName);
            if (file.exists()) {
                file.delete();
            }

            BufferedInputStream bis = new BufferedInputStream(inputStream);
            FileOutputStream fos = new FileOutputStream(SdcardPath + fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            byte[] byt = new byte[1024];
            int noBytes;
            while ((noBytes = bis.read(byt)) != -1)
                bos.write(byt, 0, noBytes);
            bos.flush();
            bos.close();
            fos.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static InputStream getFileFromSDcard(String SDcardpath, String fileName) {
        InputStream is = null;
        try {
            File myFile = new File(SDcardpath, fileName);
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow + "\n";
            }
            //				txtData.setText(aBuffer);
            is = new ByteArrayInputStream(aBuffer.getBytes());
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }


    public static void copyDirectory(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }

            String[] children = sourceLocation.list();
            for (int i = 0; i < children.length; i++) {
                copyDirectory(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {

            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }

    private static void acquireWifi(Context context, PowerManager.WakeLock mWifiLock) {
        mWifiLock.acquire();
        Log.e("acquire", "DONE");
    }


    public static class productDO {
        String firstValue;
        String SecondValue;
        String Start;
        String End;
    }

    public static File getOutputImageFile(String folder) {

//		File captureImagesStorageDir = new File(Environment.getExternalStorageDirectory()+"/KR/"+folder);
        File captureImagesStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/KR/" + folder);

        if (!captureImagesStorageDir.exists()) {
            if (!captureImagesStorageDir.mkdirs()) {
                Log.d("Unilever", "Oops! Failed create ");
                return null;
            }
        }

        String timestamp = System.currentTimeMillis() + "";
        File imageFile = new File(captureImagesStorageDir.getPath() + File.separator + "CAPTURE_" + timestamp + ".jpg");
        return imageFile;
    }

    public static File getOutputAudioFile(String folder) {

        File captureImagesStorageDir = new File(Environment.getExternalStorageDirectory() + "/KR/" + folder);

        if (!captureImagesStorageDir.exists()) {
            if (!captureImagesStorageDir.mkdirs()) {
                Log.d("Unilever", "Oops! Failed create ");
                return null;
            }
        }

        String timestamp = System.currentTimeMillis() + "";
        File imageFile = new File(captureImagesStorageDir.getPath() + File.separator
                + "CAPTURE_" + timestamp + ".mp3");


        return imageFile;
    }

    public static File getOutputVideoFile(String folder) {

        File captureImagesStorageDir = new File(Environment.getExternalStorageDirectory() + "/KR/" + folder);

        if (!captureImagesStorageDir.exists()) {
            if (!captureImagesStorageDir.mkdirs()) {
                Log.d("Unilever", "Oops! Failed create ");
                return null;
            }
        }

        String timestamp = System.currentTimeMillis() + "";
        File imageFile = new File(captureImagesStorageDir.getPath() + File.separator
                + "CAPTURE_" + timestamp + ".mp4");


        return imageFile;
    }

    public static File getApkFilePath(String folder) {

        File captureImagesStorageDir = new File(Environment.getExternalStorageDirectory() + "/AlRashed/" + folder);

        if (!captureImagesStorageDir.exists()) {
            if (!captureImagesStorageDir.mkdirs()) {
                Log.d("AlRashed", "Oops! Failed create ");
                return null;
            }
        }

        String timestamp = System.currentTimeMillis() + "";
        File imageFile = new File(captureImagesStorageDir.getPath() + File.separator
                + "ALSEER_SFA_" + timestamp + ".apk");


        return imageFile;
    }


}
