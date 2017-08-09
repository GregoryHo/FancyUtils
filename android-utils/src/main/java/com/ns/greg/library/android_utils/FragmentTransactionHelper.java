package com.ns.greg.library.android_utils;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.AnimatorRes;
import android.support.annotation.IdRes;

/**
 * Created by Gregory on 2017/3/9.
 */
public class FragmentTransactionHelper {

  private FragmentTransactionHelper() {
    throw new AssertionError("No instance");
  }

  public static Builder beginTransaction(@IdRes int rootResId, FragmentManager fragmentManager) {
    return new Builder(rootResId, fragmentManager);
  }

  @SuppressWarnings("unchecked")
  public static <T extends Fragment> T getFragment(FragmentManager fragmentManager,
      Class<T> fragmentClass) {
    return (T) fragmentManager.findFragmentByTag(fragmentClass.getName());
  }

  public static final class Builder {

    private int rootResId;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    @SuppressLint("CommitTransaction")
    private Builder(@IdRes int rootResId, FragmentManager fragmentManager) {
      this.rootResId = rootResId;
      this.fragmentManager = fragmentManager;
      this.fragmentTransaction = fragmentManager.beginTransaction();
    }

    public Builder setCustomAnimations(@AnimatorRes int enter, @AnimatorRes int exit) {
      fragmentTransaction.setCustomAnimations(enter, exit);

      return this;
    }

    public <T extends Fragment> Builder addFragment(Class<T> fragmentClass) {
      if (getFragment(fragmentManager, fragmentClass) == null) {
        Fragment fragment = null;
        try {
          fragment = fragmentClass.newInstance();
        } catch (InstantiationException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }

        if (fragment != null) {
          fragmentTransaction.add(rootResId, fragment, fragmentClass.getName());
          fragmentTransaction.show(fragment);
        }
      }

      return this;
    }

    public <T extends Fragment> Builder showFragment(Class<T> fragmentClass) {
      Fragment fragment = getFragment(fragmentManager, fragmentClass);
      if (fragment != null) {
        fragmentTransaction.show(fragment);
      }

      return this;
    }

    public <T extends Fragment> Builder removeFragment(Class<T> fragmentClass) {
      Fragment fragment = getFragment(fragmentManager, fragmentClass);
      if (fragment != null) {
        fragmentTransaction.remove(fragment);
      }

      return this;
    }

    public <T extends Fragment> Builder hideFragment(Class<T> fragmentClass) {
      Fragment fragment = getFragment(fragmentManager, fragmentClass);
      if (fragment != null) {
        fragmentTransaction.hide(fragment);
      }

      return this;
    }

    public void commit() {
      try {
        fragmentTransaction.commit();
      } catch (IllegalStateException e) {
        e.printStackTrace();
      }
    }

    public void commitAllowingStateLoss() {
      try {
        fragmentTransaction.commitAllowingStateLoss();
      } catch (IllegalStateException e) {
        e.printStackTrace();
      }
    }
  }
}
