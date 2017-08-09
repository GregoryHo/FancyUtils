package com.ns.greg.library.android_utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Gregory on 2016/4/6.
 */
public class PreferenceUtils {

  /**
   * Gets the String from {@link SharedPreferences}
   * with specific key SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
   *
   * @param context application context
   * @param key specific key
   * @param defaultValue default value
   */
  public String getString(Context context, String key, String defaultValue) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    return sharedPreferences.getString(key, defaultValue);
  }

  /**
   * Sets the String into {@link SharedPreferences}
   *
   * @param context application context
   * @param key specific key
   * @param value stored value
   */
  public void setString(Context context, String key, String value) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    sharedPreferences.edit().putString(key, value).apply();
  }

  /**
   * Gets the boolean form {@link SharedPreferences}
   * with specific key
   *
   * @param context application context
   * @param key specific key
   * @param defaultValue default value
   */
  public boolean getBoolean(Context context, String key, boolean defaultValue) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    return sharedPreferences.getBoolean(key, defaultValue);
  }

  /**
   * Sets the boolean into {@link SharedPreferences}
   *
   * @param context application context
   * @param key specific key
   * @param value stored value
   */
  public void setBoolean(Context context, String key, boolean value) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    sharedPreferences.edit().putBoolean(key, value).apply();
  }

  /**
   * Gets the Integer form {@link SharedPreferences}
   * with specific key
   *
   * @param context application context
   * @param key specific key
   * @param defaultValue default value
   */
  public int getInt(Context context, String key, int defaultValue) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    return sharedPreferences.getInt(key, defaultValue);
  }

  /**
   * Sets the Integer into {@link SharedPreferences}
   *
   * @param context application context
   * @param key specific key
   * @param value stored value
   */
  public void setInt(Context context, String key, int value) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    sharedPreferences.edit().putInt(key, value).apply();
  }

  /**
   * Gets the Float from {@link SharedPreferences}
   * with specific key
   *
   * @param context application context
   * @param key specific key
   * @param defaultValue default value
   */
  public float getFloat(Context context, String key, float defaultValue) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    return sharedPreferences.getFloat(key, defaultValue);
  }

  /**
   * Sets the Float into {@link SharedPreferences}
   *
   * @param context application context
   * @param key specific key
   * @param value stored value
   */
  public void setFloat(Context context, String key, float value) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    sharedPreferences.edit().putFloat(key, value).apply();
  }

  /**
   * Gets the Long from {@link SharedPreferences}
   * with specific key
   *
   * @param context application context
   * @param key specific key
   * @param defaultValue default value
   */
  public long getLong(Context context, String key, long defaultValue) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    return sharedPreferences.getLong(key, defaultValue);
  }

  /**
   * Sets the Long into {@link SharedPreferences}
   *
   * @param context application context
   * @param key specific key
   * @param value stored value
   */
  public void setLong(Context context, String key, long value) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    sharedPreferences.edit().putLong(key, value).apply();
  }

  /**
   * Checks if {@link SharedPreferences} contains the specific key
   *
   * @param context application context
   * @param key specific key
   */
  public boolean hasKey(Context context, String key) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    return sharedPreferences.contains(key);
  }

  /**
   * Clears {@link SharedPreferences}
   *
   * @param context application context
   */
  public void clears(Context context) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    sharedPreferences.edit().clear().apply();
  }
}
