package com.ns.greg.library.android_utils;

import android.app.ActivityManager;
import android.content.Context;

/**
 * @author Gregory
 * @since 2017/11/30
 */

public class SystemUtils {

  public static int getMemorySize(Context context) {
    ActivityManager activityManager =
        (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    if (activityManager != null) {
      return activityManager.getMemoryClass() * 1024 * 1024;
    }

    return (int) (Runtime.getRuntime().maxMemory());
  }
}
