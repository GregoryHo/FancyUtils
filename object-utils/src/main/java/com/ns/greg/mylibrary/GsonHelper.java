package com.ns.greg.mylibrary;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.io.Reader;
import java.util.Map;

/**
 * @author Gregory
 * @since 2016/5/19
 */
public class GsonHelper {

  @NonNull public static String create(@NonNull Object object) {
    return create(false, true, object);
  }

  @NonNull public static String create(boolean excludeWithoutExpose, boolean serializeNull,
      @NonNull Object object) {
    return gsonBuild(excludeWithoutExpose, serializeNull, null).toJson(object);
  }

  @Nullable
  public static String create(@NonNull Object object, Class<?> aClass, TypeAdapter typeAdapter) {
    return create(false, true, object, aClass, typeAdapter);
  }

  @Nullable public static String create(boolean excludeWithoutExpose, boolean serializeNull,
      @NonNull Object object, Class<?> aClass, TypeAdapter typeAdapter) {
    return gsonBuild(excludeWithoutExpose, serializeNull,
        new TypeAdapterOptions(aClass, typeAdapter)).toJson(object);
  }

  @Nullable public static <T> T parse(@Nullable String json, @NonNull Class<T> aClass)
      throws JsonSyntaxException {
    return parse(false, true, json, aClass);
  }

  @Nullable public static <T> T parse(boolean excludeWithoutExpose, boolean serializeNulls,
      @Nullable String json, @NonNull Class<T> aClass) throws JsonSyntaxException {
    if (json == null || json.isEmpty()) {
      return null;
    }

    try {
      return gsonBuild(excludeWithoutExpose, serializeNulls, null).fromJson(json, aClass);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Nullable public static <T> T parse(@Nullable Reader reader, @NonNull Class<T> aClass)
      throws JsonSyntaxException {
    return parse(false, true, reader, aClass);
  }

  @Nullable public static <T> T parse(boolean excludeWithoutExpose, boolean serializeNulls,
      @Nullable Reader reader, @NonNull Class<T> aClass) throws JsonSyntaxException {
    if (reader == null) {
      return null;
    }

    try {
      return gsonBuild(excludeWithoutExpose, serializeNulls, null).fromJson(reader, aClass);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Nullable public static <T> Map<String, T> parseWithDynamicKey(@Nullable String json) {
    return parseWithDynamicKey(false, true, json);
  }

  @Nullable public static <T> Map<String, T> parseWithDynamicKey(boolean excludeWithoutExpose,
      boolean serializeNulls, @Nullable String json) {
    if (json == null || json.isEmpty()) {
      return null;
    }

    return gsonBuild(excludeWithoutExpose, serializeNulls, null).fromJson(json,
        new TypeToken<Map<String, T>>() {
        }.getType());
  }

  @Nullable
  public static <T> T parseWithTypeAdapter(@Nullable String json, @NonNull Class<T> aClass,
      TypeAdapter typeAdapter) {
    return parseWithTypeAdapter(false, true, json, aClass, typeAdapter);
  }

  @Nullable
  public static <T> T parseWithTypeAdapter(boolean excludeWithoutExpose, boolean serializeNulls,
      @Nullable String json, @NonNull Class<T> aClass, TypeAdapter typeAdapter) {
    if (json == null || json.isEmpty()) {
      return null;
    }

    try {
      return gsonBuild(excludeWithoutExpose, serializeNulls,
          new TypeAdapterOptions(aClass, typeAdapter)).fromJson(json, aClass);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Gson gsonBuild(boolean excludeWithoutExpose, boolean serializeNulls,
      TypeAdapterOptions typeAdapterOptions) {
    GsonBuilder gsonBuilder = new GsonBuilder();
    if (excludeWithoutExpose) {
      gsonBuilder.excludeFieldsWithoutExposeAnnotation();
    }

    if (serializeNulls) {
      gsonBuilder.serializeNulls();
    }

    if (typeAdapterOptions != null) {
      gsonBuilder.registerTypeAdapter(typeAdapterOptions.aClass, typeAdapterOptions.typeAdapter);
    }

    return gsonBuilder.create();
  }

  private static class TypeAdapterOptions {

    private Class aClass;
    private TypeAdapter typeAdapter;

    TypeAdapterOptions(Class aClass, TypeAdapter typeAdapter) {
      this.aClass = aClass;
      this.typeAdapter = typeAdapter;
    }
  }
}
