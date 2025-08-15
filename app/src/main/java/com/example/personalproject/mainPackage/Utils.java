package com.example.personalproject.mainPackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

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
