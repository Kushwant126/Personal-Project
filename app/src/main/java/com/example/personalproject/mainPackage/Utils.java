package com.example.personalproject.mainPackage;

import static android.content.Context.BATTERY_SERVICE;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings.Secure;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

//import com.google.firebase.messaging.FirebaseMessaging;
//import com.haier.hmisempower.activity.Main2Activity;
//import com.haier.hmisempower.database.SharedpreferenceUtility;
//import com.haier.hmisempower.database.UserPref;
//import com.haier.hmisempower.model.Network;
//import com.haier.hmisempower.model.User;
//import com.haier.hmisempower.winit.LatLagDO;
//import com.watermark.androidwm_light.Watermark;
//import com.watermark.androidwm_light.WatermarkBuilder;
//import com.watermark.androidwm_light.bean.WatermarkText;
//import okhttp3.OkHttpClient;

/**
 * Created by 6607 on 10-29-2017.
 */

public class Utils {
    public static Bitmap getBitmap(Context context, Uri uri) {
        Bitmap bitmap = null;

        try {
            int REQUIRED_SIZE = 250;
            InputStream inputStream = context.getContentResolver().openInputStream(uri);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();

            int scale = 1;
            while (options.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    options.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            inputStream = context.getContentResolver().openInputStream(uri);
            options.inJustDecodeBounds = false;
            options.inSampleSize = scale;
            bitmap = BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();
        } catch (Exception e) {
        }
        return bitmap;
    }
    /**
     * Get Base64 encoded string from bitmap.
     *
     * @param bitmap
     * @return Base64 encoded string
     */
    public static String getBase64EncodedString(Bitmap bitmap) {
        try {
//            int quality = UserPref.i().getUserType().equalsIgnoreCase(Constants.USER_ISD) ? 100 : 50;
            int quality = 100;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
            byte[] byteFormat = stream.toByteArray();
            return Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        } catch (Exception e) {
            return null;
        }
    }

}
