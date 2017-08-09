package com.ns.greg.library.view_utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Gregory on 2016/5/30.
 */
public class BitmapUtils {

  public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
      int reqHeight) {
    // Raw height and width of image
    int width = options.outWidth;
    int height = options.outHeight;
    int inSampleSize = 1;

    if (width > reqWidth || height > reqHeight) {
      width = width / 2;
      height = height / 2;

      // Calculate the largest inSampleSize value that is a power of 2 and keeps both
      // height and width larger than the requested height and width.
      while ((width / inSampleSize) > reqWidth && (height / inSampleSize) > reqHeight) {
        inSampleSize *= 2;
      }
    }

    return inSampleSize;
  }

  public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth,
      int reqHeight, boolean filter) {

    final BitmapFactory.Options options = new BitmapFactory.Options();

    // First decode with inJustDecodeBounds = true to check dimensions
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(res, resId, options);

    // Calculate inSampleSize
    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
    options.inPreferredConfig = Bitmap.Config.RGB_565;

    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false;
    Bitmap out = BitmapFactory.decodeResource(res, resId, options);
    return createScaleBitmap(out, reqWidth, reqHeight, filter);
  }

  public static Bitmap createScaleBitmap(Bitmap src, int reqWidth, int reqHeight, boolean filter) {
    Bitmap dst = Bitmap.createScaledBitmap(src, reqWidth, reqHeight, filter);
    if (dst != src) {
      src.recycle();
    }

    return dst;
  }
}
