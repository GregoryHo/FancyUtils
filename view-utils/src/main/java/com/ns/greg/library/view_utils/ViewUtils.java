package com.ns.greg.library.view_utils;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Gregory
 * @since 2016/3/24
 */
public class ViewUtils {

  /**
   * View property field
   */
  public static class PropertyName {

    public static final String ALPHA = "alpha";
    public static final String PIVOT_X = "pivotX";
    public static final String PIVOT_Y = "pivotY";
    public static final String TRANSLATION_X = "translationX";
    public static final String TRANSLATION_Y = "translationY";
    public static final String ROTATION = "rotation";
    public static final String ROTATION_X = "rotationX";
    public static final String ROTATION_Y = "rotationY";
    public static final String SCALE_X = "scaleX";
    public static final String SCALE_Y = "scaleY";
    public static final String SCROLL_X = "scrollX";
    public static final String SCROLL_Y = "scrollY";
    public static final String X = "x";
    public static final String Y = "y";
  }

  private ViewUtils() {
    throw new AssertionError("No instance");
  }

  /**
   * Takes current view screen shot.
   *
   * @param view current view
   * @param quality quality
   */
  public static Bitmap takeScreenShot(View view, int quality) {
    view.setDrawingCacheEnabled(true);
    view.setDrawingCacheQuality(quality);
    view.buildDrawingCache();
    if (view.getDrawingCache() == null) {
      return null;
    }

    Bitmap snapshot = Bitmap.createBitmap(view.getDrawingCache());
    view.setDrawingCacheEnabled(false);
    view.destroyDrawingCache();
    return snapshot;
  }

  /**
   * Inflates view
   */
  public static View inflateView(Context context, ViewGroup viewGroup, int layoutId,
      boolean attachToRoot) {
    return LayoutInflater.from(context).inflate(layoutId, viewGroup, attachToRoot);
  }

  public static String getTextViewString(@NonNull TextView textView) {
    try {
      return textView.getText().toString();
    } catch (NullPointerException e) {
      return "";
    }
  }

  public static String getTextViewString(@NonNull TextView textView, String defaultValue) {
    try {
      return textView.getText().toString();
    } catch (NullPointerException e) {
      return defaultValue;
    }
  }

  public static String getEditTextString(@NonNull EditText editText) {
    try {
      return editText.getText().toString();
    } catch (NullPointerException e) {
      return "";
    }
  }

  public static TextView cast2TextView(ViewGroup parent, @IdRes int resId) {
    return (TextView) parent.findViewById(resId);
  }

  public static ImageView cast2ImageView(ViewGroup parent, @IdRes int resId) {
    return (ImageView) parent.findViewById(resId);
  }

  public static int getRelativeTop(View view) {
    if (view.getParent() == view.getRootView()) {
      return view.getTop();
    }

    return view.getTop() + getRelativeTop((View) view.getParent());
  }

  /**
   * Sets Evaluator in ValueAnimator
   */
  public static ValueAnimator setTypeEvaluator(ValueAnimator valueAnimator,
      TypeEvaluator typeEvaluator) {
    valueAnimator.setEvaluator(typeEvaluator);
    return valueAnimator;
  }

  public static DisplayMetrics getDisplayMetrics(Context context) {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    if (windowManager != null) {
      windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
    }

    return displayMetrics;
  }

  /**
   * Returns the screen width.
   *
   * @param context application context
   * @return width size in pixel
   */
  public static int getWidth(Context context) {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    if (windowManager != null) {
      windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
    }

    return displayMetrics.widthPixels;
  }

  /**
   * Returns the screen height.
   *
   * @param context application context
   * @return height size in pixel
   */
  public static int getHeight(Context context) {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    if (windowManager != null) {
      windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
    }

    return displayMetrics.heightPixels;
  }

  /**
   * Returns the screen density
   * <p>
   * 120dpi = 0.75
   * 160dpi = 1 (default)
   * 240dpi = 1.5
   * 320dpi = 2
   * 400dpi = 2.5
   *
   * @param context application context
   * @return Device density
   */
  public static float getDensity(Context context) {
    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
    return metrics.density;
  }

  /**
   * Returns the screen ScaledDensity
   *
   * @param context application context
   * @return Device scaled density
   */
  public static float getScaledDensity(Context context) {
    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
    return metrics.scaledDensity;
  }

  /**
   * Coverts px to dp.
   *
   * @param px pixel
   * @param context application context
   * @return dp
   */
  public static int convertPixel2Dp(float px, Context context) {
    return (int) (px / getDensity(context) + 0.5f);
  }

  /**
   * Coverts dp to px.
   *
   * @param dp dp
   * @param context application context
   * @return pixel
   */
  public static int convertDp2Pixel(float dp, Context context) {
    return (int) (dp * getDensity(context) + 0.5f);
  }

  /**
   * Converts px to sp.
   *
   * @param px pixel
   * @param context application context
   * @return sp
   */
  public static int convertPixel2Sp(float px, Context context) {
    return (int) (px / getScaledDensity(context) + 0.5f);
  }

  /**
   * Converts sp to px.
   *
   * @param dp sp
   * @param context application context
   * @return dp
   */
  public static int convertSp2Pixel(float dp, Context context) {
    return (int) (dp * getScaledDensity(context) + 0.5d);
  }

  /**
   * Returns the status bar height
   *
   * @param activity activity context
   * @return status bar height in pixel
   */
  public static int getStatusBarHeight(Activity activity) {
    Rect rectangle = new Rect();
    Window window = activity.getWindow();
    window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
    return rectangle.top;
  }
}
