package com.sitepark.ies.sharedkernel.base;

import com.sitepark.ies.sharedkernel.anchor.Anchor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("PMD.TooManyMethods")
public class IdentifierListBuilder {

  @NotNull private final List<Identifier> identifiers = new ArrayList<>();

  public IdentifierListBuilder set(String... identifiers) {
    if (identifiers == null) {
      return this;
    }
    this.identifiers.clear();
    for (String identifier : identifiers) {
      this.add(identifier);
    }
    return this;
  }

  public IdentifierListBuilder set(Collection<String> identifiers) {
    if (identifiers == null) {
      return this;
    }
    this.identifiers.clear();
    for (String identifier : identifiers) {
      this.add(identifier);
    }
    return this;
  }

  public IdentifierListBuilder add(String identifier) {
    if (identifier == null) {
      return this;
    }
    this.identifiers.add(Identifier.ofString(identifier));
    return this;
  }

  public IdentifierListBuilder identifiers(Identifier... identifiers) {
    if (identifiers == null) {
      return this;
    }
    this.identifiers.clear();
    for (Identifier identifier : identifiers) {
      this.identifier(identifier);
    }
    return this;
  }

  public IdentifierListBuilder identifiers(Collection<Identifier> identifiers) {
    if (identifiers == null) {
      return this;
    }
    this.identifiers.clear();
    for (Identifier identifier : identifiers) {
      this.identifier(identifier);
    }
    return this;
  }

  public IdentifierListBuilder identifier(Identifier identifier) {
    if (identifier == null) {
      return this;
    }
    this.identifiers.add(identifier);
    return this;
  }

  public IdentifierListBuilder ids(String... ids) {
    if (ids == null) {
      return this;
    }
    this.identifiers.clear();
    for (String id : ids) {
      this.id(id);
    }
    return this;
  }

  public IdentifierListBuilder ids(Collection<String> ids) {
    if (ids == null) {
      return this;
    }
    this.identifiers.clear();
    for (String id : ids) {
      this.id(id);
    }
    return this;
  }

  public IdentifierListBuilder id(String id) {
    if (id == null || id.isBlank()) {
      return this;
    }
    this.identifiers.add(Identifier.ofId(id));
    return this;
  }

  public IdentifierListBuilder anchors(Anchor... anchors) {
    if (anchors == null) {
      return this;
    }
    this.identifiers.clear();
    for (Anchor anchor : anchors) {
      this.anchor(anchor);
    }
    return this;
  }

  public IdentifierListBuilder anchors(Collection<Anchor> anchors) {
    if (anchors == null) {
      return this;
    }
    this.identifiers.clear();
    for (Anchor anchor : anchors) {
      this.anchor(anchor);
    }
    return this;
  }

  public IdentifierListBuilder anchor(Anchor anchor) {
    if (anchor == null) {
      return this;
    }
    this.identifiers.add(Identifier.ofAnchor(anchor));
    return this;
  }

  public List<Identifier> build() {
    return List.copyOf(this.identifiers);
  }
}
