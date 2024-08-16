package com.app.taskmanagementsystem.helpers;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class Validator {
  private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
  private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

  private Validator() {}

  public static boolean isEmailValid(String email) {
    return email != null && EMAIL_PATTERN.matcher(email).matches();
  }

  public static boolean isEmpty(Object obj) {
    if (obj == null) {
      return true;
    }
    if (obj instanceof String) {
      return ((String) obj).trim().isEmpty();
    }
    if (obj instanceof Collection<?>) {
      return ((Collection<?>) obj).isEmpty();
    }
    if (obj instanceof Map<?, ?>) {
      return ((Map<?, ?>) obj).isEmpty();
    }
    if (obj instanceof Optional) {
      return ((Optional<?>) obj).isEmpty();
    }
    if (obj.getClass().isArray()) {
      return ((Object[]) obj).length == 0;
    }
    return false;
  }
}
