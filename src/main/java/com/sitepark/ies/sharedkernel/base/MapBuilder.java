package com.sitepark.ies.sharedkernel.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("PMD.UseConcurrentHashMap")
public final class MapBuilder<K, V> {

  @NotNull private final Map<K, V> map = new HashMap<>();
  private boolean changed;

  @SafeVarargs
  public final MapBuilder<K, V> set(Entry<K, V>... entries) {
    if (entries == null) {
      return this;
    }
    this.map.clear();
    for (Entry<K, V> entry : entries) {
      if (entry == null) {
        continue;
      }
      this.map.put(entry.getKey(), entry.getValue());
    }
    this.changed = true;
    return this;
  }

  public MapBuilder<K, V> set(Map<K, V> map) {
    if (map == null) {
      return this;
    }
    this.map.clear();
    this.map.putAll(map);
    this.changed = true;
    return this;
  }

  public MapBuilder<K, V> putAll(Map<K, V> map) {
    if (map == null) {
      return this;
    }
    this.map.putAll(map);
    this.changed = true;
    return this;
  }

  public MapBuilder<K, V> put(K key, V value) {
    if (value == null) {
      return this;
    }
    this.map.put(key, value);
    this.changed = true;
    return this;
  }

  public boolean changed() {
    return this.changed;
  }

  public Map<K, V> build() {
    return Map.copyOf(this.map);
  }
}
