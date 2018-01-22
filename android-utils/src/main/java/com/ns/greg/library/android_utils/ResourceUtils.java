package com.ns.greg.library.android_utils;

import android.content.Context;

/**
 * @author Gregory
 * @since 2018/1/11
 */

public class ResourceUtils {

  private ResourceUtils() {
    throw new AssertionError("No instance.");
  }

  /**
   * Getting resource id with identifier
   *
   * @param context context
   * @param defType layout, id, string... etc.
   * @param name the resource name
   * @return id of resource
   */
  public static int getResource(Context context, String defType, String name) {
    return context.getResources().getIdentifier(name, defType, context.getPackageName());
  }
}
