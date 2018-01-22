package com.ns.greg.library.android_utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.support.annotation.NonNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author Gregory
 * @since 2017/10/19
 */

public class FileUtils {

  public static boolean saveBitmap(String appName, Context context, String fileName,
      @NonNull Bitmap bitmap) {
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED)) {
      return false;
    }

    File storageDirectory = Environment.getExternalStorageDirectory();
    String path = storageDirectory.getParent() + "/" + storageDirectory.getName();
    File file = new File(path, "DCIM");
    if (!file.exists() && !file.mkdir()) {
      return false;
    }

    file = new File(file.getPath(), appName);
    if (!file.exists() && !file.mkdir()) {
      return false;
    }

    file = new File(file.getAbsolutePath(), fileName + ".jpg");
    if (file.exists()) {
      file.delete();
    }

    OutputStream out = null;
    try {
      out = new FileOutputStream(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    if (out != null) {
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
      MediaScannerConnection.scanFile(context, new String[] { file.toString() }, null, null);
      return true;
    }

    return false;
  }
}
