package com.sitepark.ies.sharedkernel.base;

import com.sitepark.ies.sharedkernel.anchor.Anchor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/** Collects identifiers; every method ignores {@code null}, so optional values can be passed as is. */
@SuppressWarnings("PMD.TooManyMethods")
public class IdentifierListBuilder {

  @NonNull private final List<Identifier> identifiers = new ArrayList<>();
  private boolean changed;

  public IdentifierListBuilder set(String @Nullable ... identifiers) {
    if (identifiers == null) {
      return this;
    }
    this.identifiers.clear();
    for (String identifier : identifiers) {
      this.add(identifier);
    }
    this.changed = true;
    return this;
  }

  public IdentifierListBuilder set(@Nullable Collection<String> identifiers) {
    if (identifiers == null) {
      return this;
    }
    this.identifiers.clear();
    for (String identifier : identifiers) {
      this.add(identifier);
    }
    this.changed = true;
    return this;
  }

  public IdentifierListBuilder add(@Nullable String identifier) {
    if (identifier == null) {
      return this;
    }
    this.identifiers.add(Identifier.ofString(identifier));
    this.changed = true;
    return this;
  }

  public IdentifierListBuilder identifiers(Identifier @Nullable ... identifiers) {
    if (identifiers == null) {
      return this;
    }
    this.identifiers.clear();
    for (Identifier identifier : identifiers) {
      this.identifier(identifier);
    }
    this.changed = true;
    return this;
  }

  public IdentifierListBuilder identifiers(@Nullable Collection<Identifier> identifiers) {
    if (identifiers == null) {
      return this;
    }
    this.identifiers.clear();
    for (Identifier identifier : identifiers) {
      this.identifier(identifier);
    }
    this.changed = true;
    return this;
  }

  public IdentifierListBuilder identifier(@Nullable Identifier identifier) {
    if (identifier == null) {
      return this;
    }
    this.identifiers.add(identifier);
    this.changed = true;
    return this;
  }

  public IdentifierListBuilder ids(String @Nullable ... ids) {
    if (ids == null) {
      return this;
    }
    this.identifiers.clear();
    for (String id : ids) {
      this.id(id);
    }
    this.changed = true;
    return this;
  }

  public IdentifierListBuilder ids(@Nullable Collection<String> ids) {
    if (ids == null) {
      return this;
    }
    this.identifiers.clear();
    for (String id : ids) {
      this.id(id);
    }
    this.changed = true;
    return this;
  }

  public IdentifierListBuilder id(@Nullable String id) {
    if (id == null || id.isBlank()) {
      return this;
    }
    this.identifiers.add(Identifier.ofId(id));
    this.changed = true;
    return this;
  }

  public IdentifierListBuilder anchors(Anchor @Nullable ... anchors) {
    if (anchors == null) {
      return this;
    }
    this.identifiers.clear();
    for (Anchor anchor : anchors) {
      this.anchor(anchor);
    }
    this.changed = true;
    return this;
  }

  public IdentifierListBuilder anchors(@Nullable Collection<Anchor> anchors) {
    if (anchors == null) {
      return this;
    }
    this.identifiers.clear();
    for (Anchor anchor : anchors) {
      this.anchor(anchor);
    }
    this.changed = true;
    return this;
  }

  public IdentifierListBuilder anchor(@Nullable Anchor anchor) {
    if (anchor == null) {
      return this;
    }
    this.identifiers.add(Identifier.ofAnchor(anchor));
    this.changed = true;
    return this;
  }

  public boolean changed() {
    return this.changed;
  }

  public List<Identifier> build() {
    return List.copyOf(this.identifiers);
  }
}
