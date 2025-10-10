package com.sitepark.ies.sharedkernel.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class ListBuilder<T> {

  @NotNull private final List<T> list = new ArrayList<>();

  @SafeVarargs
  public final ListBuilder<T> set(T... items) {
    if (items == null) {
      return this;
    }
    this.list.clear();
    for (T item : items) {
      this.add(item);
    }
    return this;
  }

  public ListBuilder<T> set(Collection<T> items) {
    if (items == null) {
      return this;
    }
    this.list.clear();
    for (T item : items) {
      this.add(item);
    }
    return this;
  }

  @SafeVarargs
  public final ListBuilder<T> addAll(T... items) {
    if (items == null) {
      return this;
    }
    for (T item : items) {
      this.add(item);
    }
    return this;
  }

  public ListBuilder<T> addAll(Collection<T> items) {
    if (items == null) {
      return this;
    }
    for (T item : items) {
      this.add(item);
    }
    return this;
  }

  public ListBuilder<T> add(T item) {
    if (item == null) {
      return this;
    }
    this.list.add(item);
    return this;
  }

  public List<T> build() {
    return List.copyOf(this.list);
  }
}
