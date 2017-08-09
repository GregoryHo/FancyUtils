package com.ns.greg.library.android_utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import java.util.ArrayList;

/**
 * Created by Gregory on 2017/2/14.
 */
public class BroadcastUtils {

  private BroadcastUtils() {
    throw new AssertionError("No instances");
  }

  public static void registerBroadcastReceiver(Context context, BroadcastReceiver receiver,
      IntentFilter intentFilter) {
    LocalBroadcastManager.getInstance(context).registerReceiver(receiver, intentFilter);
  }

  public static void unRegisterBroadcastReceiver(Context context, BroadcastReceiver receiver) {
    LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
  }

  public static BroadcastHelper onBroadcast(@NonNull String action) {
    return new BroadcastHelper(action);
  }

  public static final class BroadcastHelper {

    private Intent intent;

    private BroadcastHelper(@NonNull String action) {
      intent = new Intent(action);
    }

    public BroadcastHelper putExtra(@NonNull String key, byte value) {
      intent.putExtra(key, value);

      return this;
    }

    public BroadcastHelper putExtra(@NonNull String key, char value) {
      intent.putExtra(key, value);

      return this;
    }

    public BroadcastHelper putExtra(@NonNull String key, short value) {
      intent.putExtra(key, value);

      return this;
    }

    public BroadcastHelper putExtra(@NonNull String key, int value) {
      intent.putExtra(key, value);

      return this;
    }

    public BroadcastHelper putExtra(@NonNull String key, long value) {
      intent.putExtra(key, value);

      return this;
    }

    public BroadcastHelper putExtra(@NonNull String key, float value) {
      intent.putExtra(key, value);

      return this;
    }

    public BroadcastHelper putExtra(@NonNull String key, double value) {
      intent.putExtra(key, value);

      return this;
    }

    public BroadcastHelper putExtra(@NonNull String key, String value) {
      intent.putExtra(key, value);

      return this;
    }

    public BroadcastHelper putExtra(@NonNull String key, CharSequence value) {
      intent.putExtra(key, value);

      return this;
    }

    public BroadcastHelper putExtra(@NonNull String key, Parcelable value) {
      intent.putExtra(key, value);

      return this;
    }

    public BroadcastHelper putExtra(@NonNull String key, Parcelable[] value) {
      intent.putExtra(key, value);

      return this;
    }

    public BroadcastHelper putExtra(@NonNull String key, ArrayList<? extends Parcelable> value) {
      intent.putExtra(key, value);

      return this;
    }

    public void broacast(Context context) {
      LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override public String toString() {
      return "Action : " + intent.getAction() + " broadcast";
    }
  }
}
