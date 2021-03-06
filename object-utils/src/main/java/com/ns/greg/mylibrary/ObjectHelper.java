package com.ns.greg.mylibrary;

import android.support.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * @author Gregory
 * @since 2017/2/14
 */
public class ObjectHelper {

  private ObjectHelper() {
    throw new AssertionError("No instances");
  }

  /**
   * Verifies if the object is not null and returns it or throws a NullPointerException
   *
   * @param <T> the value type
   * @param object the object to verify
   * @return the object itself
   * @throws NullPointerException if object is null
   */
  public static <T> T checkNotNull(T object) {
    if (object == null) {
      throw new NullPointerException();
    }

    return object;
  }

  /**
   * Verifies if the object is not null and returns it or throws a NullPointerException
   * with the given message.
   *
   * @param <T> the value type
   * @param object the object to verify
   * @param message the message to use with the NullPointerException
   * @return the object itself
   * @throws NullPointerException if object is null
   */
  public static <T> T checkNotNull(T object, @Nullable Object message) {
    if (object == null) {
      throw new NullPointerException(String.valueOf(message));
    }

    return object;
  }

  public static boolean checkIsNull(Object reference) {
    return reference == null;
  }

  /**
   * Compares two potentially null objects with each other using Object.equals.
   *
   * @param o1 the first object
   * @param o2 the second object
   * @return the comparison result
   */
  public static boolean equals(Object o1, Object o2) {
    return o1 == o2 || (o1 != null && o1.equals(o2));
  }

  /**
   * Compares two integer values similar to Integer.compare.
   *
   * @param v1 the first value
   * @param v2 the second value
   * @return the comparison result
   */
  public static int compare(int v1, int v2) {
    return v1 < v2 ? -1 : (v1 > v2 ? 1 : 0);
  }

  /**
   * Compares two integer values similar to Long.compare.
   *
   * @param v1 the first value
   * @param v2 the second value
   * @return the comparison result
   */
  public static int compare(long v1, long v2) {
    return v1 < v2 ? -1 : (v1 > v2 ? 1 : 0);
  }

  /**
   * Generates a hash code for multiple values. The hash code is generated by calling
   * {@link Arrays#hashCode(Object[])}. Note that array arguments to this method, with the exception
   * of a single Object array, do not get any special handling; their hash codes are based on
   * identity and not contents.
   *
   * <p>This is useful for implementing {@link Object#hashCode()}. For example, in an object that
   * has three properties, {@code x}, {@code y}, and {@code z}, one could write:
   *
   * <pre>   {@code
   *   public int hashCode() {
   *     return Objects.hashCode(getX(), getY(), getZ());
   *   }}</pre>
   *
   * <p><b>Warning:</b> When a single object is supplied, the returned hash code does not equal the
   * hash code of that object.
   *
   * <p><b>Note for Java 7 and later:</b> This method should be treated as deprecated; use
   * {@link java.util.Objects#hash} instead.
   */
  public static int hashCode(@Nullable Object... objects) {
    return Arrays.hashCode(objects);
  }

  /**
   * Creates an instance of {@link ToStringHelper}.
   *
   * <p>This is helpful for implementing {@link Object#toString()}. Specification by example:
   *
   * <pre>   {@code
   *   // Returns "ClassName{}"
   *   MoreObjects.toStringHelper(this)
   *       .toString();
   *
   *   // Returns "ClassName{x=1}"
   *   MoreObjects.toStringHelper(this)
   *       .add("x", 1)
   *       .toString();
   *
   *   // Returns "MyObject{x=1}"
   *   MoreObjects.toStringHelper("MyObject")
   *       .add("x", 1)
   *       .toString();
   *
   *   // Returns "ClassName{x=1, y=foo}"
   *   MoreObjects.toStringHelper(this)
   *       .add("x", 1)
   *       .add("y", "foo")
   *       .toString();
   *
   *   // Returns "ClassName{x=1}"
   *   MoreObjects.toStringHelper(this)
   *       .omitNullValues()
   *       .add("x", 1)
   *       .add("y", null)
   *       .toString();
   *   }}</pre>
   *
   * <p>Note that in GWT, class names are often obfuscated.
   *
   * @param self the object to generate the string for (typically {@code this}), used only for its
   * class name
   * @since 18.0 (since 2.0 as {@code Objects.toStringHelper()}).
   */
  public static ToStringHelper toStringHelper(Object self) {
    return new ToStringHelper(self.getClass().getSimpleName());
  }

  /**
   * Creates an instance of {@link ToStringHelper} in the same manner as
   * {@link #toStringHelper(Object)}, but using the simple name of {@code clazz} instead of using an
   * instance's {@link Object#getClass()}.
   *
   * <p>Note that in GWT, class names are often obfuscated.
   *
   * @param clazz the {@link Class} of the instance
   * @since 18.0 (since 7.0 as {@code Objects.toStringHelper()}).
   */
  public static ToStringHelper toStringHelper(Class<?> clazz) {
    return new ToStringHelper(clazz.getSimpleName());
  }

  /**
   * Creates an instance of {@link ToStringHelper} in the same manner as
   * {@link #toStringHelper(Object)}, but using {@code className} instead of using an instance's
   * {@link Object#getClass()}.
   *
   * @param className the name of the instance type
   * @since 18.0 (since 7.0 as {@code Objects.toStringHelper()}).
   */
  public static ToStringHelper toStringHelper(String className) {
    return new ToStringHelper(className);
  }

  @Target({ METHOD, TYPE }) @Retention(CLASS) public @interface CanIgnoreReturnValue {
  }

  /**
   * Support class for {@link ObjectHelper#toStringHelper}.
   *
   * @author Jason Lee
   * @since 18.0 (since 2.0 as {@code Objects.ToStringHelper}).
   */
  public static final class ToStringHelper {
    private final String className;
    private final ValueHolder holderHead = new ValueHolder();
    private ValueHolder holderTail = holderHead;
    private boolean omitNullValues = false;

    /**
     * Use {@link ObjectHelper#toStringHelper(Object)} to create an instance.
     */
    private ToStringHelper(String className) {
      this.className = checkNotNull(className);
    }

    /**
     * Configures the {@link ToStringHelper} so {@link #toString()} will ignore properties with null
     * value. The order of calling this method, relative to the {@code add()}/{@code addValue()}
     * methods, is not significant.
     *
     * @since 18.0 (since 12.0 as {@code Objects.ToStringHelper.omitNullValues()}).
     */
    @CanIgnoreReturnValue public ToStringHelper omitNullValues() {
      omitNullValues = true;
      return this;
    }

    /**
     * Adds a name/value pair to the formatted output in {@code name=value} format. If {@code value}
     * is {@code null}, the string {@code "null"} is used, unless {@link #omitNullValues()} is
     * called, in which case this name/value pair will not be added.
     */
    @CanIgnoreReturnValue public ToStringHelper add(String name, @Nullable Object value) {
      return addHolder(name, value);
    }

    /**
     * Adds a name/value pair to the formatted output in {@code name=value} format.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
     */
    @CanIgnoreReturnValue public ToStringHelper add(String name, boolean value) {
      return addHolder(name, String.valueOf(value));
    }

    /**
     * Adds a name/value pair to the formatted output in {@code name=value} format.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
     */
    @CanIgnoreReturnValue public ToStringHelper add(String name, char value) {
      return addHolder(name, String.valueOf(value));
    }

    /**
     * Adds a name/value pair to the formatted output in {@code name=value} format.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
     */
    @CanIgnoreReturnValue public ToStringHelper add(String name, double value) {
      return addHolder(name, String.valueOf(value));
    }

    /**
     * Adds a name/value pair to the formatted output in {@code name=value} format.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
     */
    @CanIgnoreReturnValue public ToStringHelper add(String name, float value) {
      return addHolder(name, String.valueOf(value));
    }

    /**
     * Adds a name/value pair to the formatted output in {@code name=value} format.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
     */
    @CanIgnoreReturnValue public ToStringHelper add(String name, int value) {
      return addHolder(name, String.valueOf(value));
    }

    /**
     * Adds a name/value pair to the formatted output in {@code name=value} format.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
     */
    @CanIgnoreReturnValue public ToStringHelper add(String name, long value) {
      return addHolder(name, String.valueOf(value));
    }

    /**
     * Adds an unnamed value to the formatted output.
     *
     * <p>It is strongly encouraged to use {@link #add(String, Object)} instead and give value a
     * readable name.
     */
    @CanIgnoreReturnValue public ToStringHelper addValue(@Nullable Object value) {
      return addHolder(value);
    }

    /**
     * Adds an unnamed value to the formatted output.
     *
     * <p>It is strongly encouraged to use {@link #add(String, boolean)} instead and give value a
     * readable name.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.addValue()}).
     */
    @CanIgnoreReturnValue public ToStringHelper addValue(boolean value) {
      return addHolder(String.valueOf(value));
    }

    /**
     * Adds an unnamed value to the formatted output.
     *
     * <p>It is strongly encouraged to use {@link #add(String, char)} instead and give value a
     * readable name.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.addValue()}).
     */
    @CanIgnoreReturnValue public ToStringHelper addValue(char value) {
      return addHolder(String.valueOf(value));
    }

    /**
     * Adds an unnamed value to the formatted output.
     *
     * <p>It is strongly encouraged to use {@link #add(String, double)} instead and give value a
     * readable name.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.addValue()}).
     */
    @CanIgnoreReturnValue public ToStringHelper addValue(double value) {
      return addHolder(String.valueOf(value));
    }

    /**
     * Adds an unnamed value to the formatted output.
     *
     * <p>It is strongly encouraged to use {@link #add(String, float)} instead and give value a
     * readable name.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.addValue()}).
     */
    @CanIgnoreReturnValue public ToStringHelper addValue(float value) {
      return addHolder(String.valueOf(value));
    }

    /**
     * Adds an unnamed value to the formatted output.
     *
     * <p>It is strongly encouraged to use {@link #add(String, int)} instead and give value a
     * readable name.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.addValue()}).
     */
    @CanIgnoreReturnValue public ToStringHelper addValue(int value) {
      return addHolder(String.valueOf(value));
    }

    /**
     * Adds an unnamed value to the formatted output.
     *
     * <p>It is strongly encouraged to use {@link #add(String, long)} instead and give value a
     * readable name.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.addValue()}).
     */
    @CanIgnoreReturnValue public ToStringHelper addValue(long value) {
      return addHolder(String.valueOf(value));
    }

    /**
     * Returns a string in the format specified by {@link ObjectHelper#toStringHelper(Object)}.
     *
     * <p>After calling this method, you can keep adding more properties to later call toString()
     * again and get a more complete representation of the same object; but properties cannot be
     * removed, so this only allows limited reuse of the helper instance. The helper allows
     * duplication of properties (multiple name/value pairs with the same name can be added).
     */
    @Override public String toString() {
      // create a copy to keep it consistent in case value changes
      boolean omitNullValuesSnapshot = omitNullValues;
      String nextSeparator = "";
      StringBuilder builder = new StringBuilder().append('{')
          .append("\"")
          .append(className)
          .append("\"")
          .append(':')
          .append('{');

      for (ValueHolder valueHolder = holderHead.next; valueHolder != null;
          valueHolder = valueHolder.next) {
        Object value = valueHolder.value;
        if (!omitNullValuesSnapshot || value != null) {
          builder.append(nextSeparator);
          nextSeparator = ", ";

          if (valueHolder.name != null) {
            builder.append("\"").append(valueHolder.name).append("\"").append(':');
          }

          if (value != null && value.getClass().isArray()) {
            Object[] objectArray = { value };
            String arrayString = Arrays.deepToString(objectArray);
            builder.append(arrayString, 1, arrayString.length() - 1);
          } else {
            builder.append(value);
          }
        }
      }

      return builder.append('}').append('}').toString();
    }

    private ValueHolder addHolder() {
      ValueHolder valueHolder = new ValueHolder();
      holderTail = holderTail.next = valueHolder;
      return valueHolder;
    }

    private ToStringHelper addHolder(@Nullable Object value) {
      ValueHolder valueHolder = addHolder();
      valueHolder.value = value;
      return this;
    }

    private ToStringHelper addHolder(String name, @Nullable Object value) {
      ValueHolder valueHolder = addHolder();
      valueHolder.value = value;
      valueHolder.name = checkNotNull(name);
      return this;
    }

    private static final class ValueHolder {
      String name;
      Object value;
      ValueHolder next;
    }
  }
}
