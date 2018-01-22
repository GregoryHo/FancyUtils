package com.ns.greg.library.android_utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author Gregory
 * @since 2016/4/6
 */
public class PreferenceUtils {

  /**
   * get the String from {@link SharedPreferences}
   */
  public static String getPrefString(Context context, String key, String defaultValue) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    return sharedPreferences.getString(key, defaultValue);
  }

  /**
   * set the String into {@link SharedPreferences}
   */
  public static void setPrefString(Context context, String key, String value) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    sharedPreferences.edit().putString(key, value).apply();
  }

  /**
   * get the boolean form {@link SharedPreferences}
   */
  public static boolean getPrefBoolean(Context context, String key, boolean defaultValue) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    return sharedPreferences.getBoolean(key, defaultValue);
  }

  /**
   * set the boolean into {@link SharedPreferences}
   */
  public static void setPrefBoolean(Context context, String key, boolean value) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    sharedPreferences.edit().putBoolean(key, value).apply();
  }

  /**
   * get the Integer form {@link SharedPreferences}
   */
  public static int getPrefInt(Context context, String key, int defaultValue) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    return sharedPreferences.getInt(key, defaultValue);
  }

  /**
   * set the Integer into {@link SharedPreferences}
   */
  public static void setPrefInt(Context context, String key, int value) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    sharedPreferences.edit().putInt(key, value).apply();
  }

  /**
   * get the Float from {@link SharedPreferences}
   */
  public static float getPrefFloat(Context context, String key, float defaultValue) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    return sharedPreferences.getFloat(key, defaultValue);
  }

  /**
   * set the Float into {@link SharedPreferences}
   */
  public static void setPrefFloat(Context context, String key, float value) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    sharedPreferences.edit().putFloat(key, value).apply();
  }

  /**
   * get the Long from {@link SharedPreferences}
   */
  public static long getPrefLong(Context context, String key, long defaultValue) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    return sharedPreferences.getLong(key, defaultValue);
  }

  /**
   * set the Long into {@link SharedPreferences}
   */
  public static void setSettingLong(Context context, String key, long value) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    sharedPreferences.edit().putLong(key, value).apply();
  }

  /**
   * check if {@link SharedPreferences} contains the key
   */
  public static boolean hasKey(Context context, String key) {
    return PreferenceManager.getDefaultSharedPreferences(context).contains(key);
  }

  /**
   * clear {@link SharedPreferences}
   */
  public static void clearPreference(Context context, SharedPreferences p) {
    SharedPreferences.Editor editor = p.edit();
    editor.clear();
    editor.apply();
  }
}
