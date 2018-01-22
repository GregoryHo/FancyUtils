package com.ns.greg.library.android_utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.Telephony;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Gregory
 * @since 2017/10/23
 */

public class ShareIntentUtils {

  private static final List<String> DEFAULT_SHARE_PACKAGES =
      Arrays.asList("mail", "whatsapp", "android.gm", "android.email", "Skype", "facebook",
          "naver.line", "kakao");

  public static List<String> getDefaultSharePackages() {
    return new ArrayList<>(DEFAULT_SHARE_PACKAGES);
  }

  public static void shareText() {

  }

  public static void shareImage(Activity activity, String title, String message,
      String bitmapPath) {
    shareImage(activity, DEFAULT_SHARE_PACKAGES, title, message, bitmapPath);
  }

  public static void shareImage(Activity activity, List<String> targetPackages, String title,
      String message, String bitmapPath) {
    if (targetPackages == null || bitmapPath == null) {
      return;
    }

    Intent share = new Intent();
    share.setAction(Intent.ACTION_SEND);
    share.setType("image/*");
    List<ResolveInfo> resolveInfoList = activity.getPackageManager()
        .queryIntentActivities(share, PackageManager.MATCH_DEFAULT_ONLY);
    if (!resolveInfoList.isEmpty()) {
      List<Intent> targetIntentList = new ArrayList<Intent>();
      for (ResolveInfo resolveInfo : resolveInfoList) {
        String packageName = resolveInfo.activityInfo.packageName;
        for (String target : targetPackages) {
          if (packageName.contains(target)) {
            Intent sharedTarget = new Intent(Intent.ACTION_SEND);
            sharedTarget.setType("image/*");
            Uri bitmapUri = Uri.parse(bitmapPath);
            sharedTarget.putExtra(Intent.EXTRA_TITLE, title);
            sharedTarget.putExtra(Intent.EXTRA_TEXT, message);
            sharedTarget.putExtra(Intent.EXTRA_STREAM, bitmapUri);
            sharedTarget.setComponent(new ComponentName(packageName, resolveInfo.activityInfo.name));
            sharedTarget.setPackage(packageName);
            targetIntentList.add(sharedTarget);
            break;
          }
        }
      }

      if (!targetIntentList.isEmpty()) {
        Intent chooserIntent =
            Intent.createChooser(targetIntentList.remove(0), "Please select app to share");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
            targetIntentList.toArray(new Parcelable[] {}));
        activity.startActivity(chooserIntent);
      } else {
        Toast.makeText(activity.getApplicationContext(), "No app to share", Toast.LENGTH_SHORT)
            .show();
      }
    }
  }

  public static String getDefaultSmsAppPackageName(Context context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      return Telephony.Sms.getDefaultSmsPackage(context);
    } else {
      Intent intent = new Intent(Intent.ACTION_VIEW).addCategory(Intent.CATEGORY_DEFAULT)
          .setType("vnd.android-dir/mms-sms");
      final List<ResolveInfo> resolveInfoList =
          context.getPackageManager().queryIntentActivities(intent, 0);
      if (resolveInfoList != null && !resolveInfoList.isEmpty()) {
        return resolveInfoList.get(0).activityInfo.packageName;
      }

      return null;
    }
  }
}
