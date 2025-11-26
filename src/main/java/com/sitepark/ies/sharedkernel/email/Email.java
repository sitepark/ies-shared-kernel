package com.sitepark.ies.sharedkernel.email;

import com.sitepark.ies.sharedkernel.base.ListBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import javax.annotation.concurrent.Immutable;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({
  "PMD.AvoidFieldNameMatchingMethodName",
  "PMD.LawOfDemeter",
  "PMD.TooManyMethods"
})
@Immutable
public final class Email {

  @NotNull private final EmailAddress from;
  @NotNull private final List<EmailAddress> replyTo;
  @NotNull private final List<EmailAddress> to;
  @NotNull private final List<EmailAddress> cc;
  @NotNull private final List<EmailAddress> bcc;
  @NotNull private final EmailMessage message;

  private Email(Builder builder) {
    this.from = builder.from;
    this.replyTo = builder.replyTo;
    this.to = List.copyOf(builder.to);
    this.cc = List.copyOf(builder.cc);
    this.bcc = List.copyOf(builder.bcc);
    this.message = builder.message;
  }

  public EmailAddress from() {
    return this.from;
  }

  public List<EmailAddress> replyTo() {
    return List.copyOf(this.replyTo);
  }

  public List<EmailAddress> to() {
    return List.copyOf(this.to);
  }

  public List<EmailAddress> cc() {
    return List.copyOf(this.cc);
  }

  public List<EmailAddress> bcc() {
    return List.copyOf(this.bcc);
  }

  public EmailMessage message() {
    return this.message;
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, replyTo, to, cc, bcc, message);
  }

  @Override
  public boolean equals(Object obj) {
    return (obj instanceof Email that)
        && Objects.equals(this.from, that.from)
        && Objects.equals(this.replyTo, that.replyTo)
        && Objects.equals(this.to, that.to)
        && Objects.equals(this.cc, that.cc)
        && Objects.equals(this.bcc, that.bcc)
        && Objects.equals(this.message, that.message);
  }

  @Override
  public String toString() {
    return "Email{"
        + "from="
        + from
        + ", replyTo="
        + replyTo
        + ", to="
        + to
        + ", cc="
        + cc
        + ", bcc="
        + bcc
        + ", message="
        + message
        + '}';
  }

  public Builder toBuilder() {
    return new Builder(this);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private EmailAddress from;
    private final List<EmailAddress> replyTo = new ArrayList<>();
    private final List<EmailAddress> to = new ArrayList<>();
    private final List<EmailAddress> cc = new ArrayList<>();
    private final List<EmailAddress> bcc = new ArrayList<>();
    private EmailMessage message;

    private Builder() {}

    private Builder(Email emailAddress) {
      this.from = emailAddress.from;
      this.replyTo.addAll(emailAddress.replyTo);
      this.to.addAll(emailAddress.to);
      this.cc.addAll(emailAddress.cc);
      this.bcc.addAll(emailAddress.bcc);
      this.message = emailAddress.message;
    }

    public Builder from(EmailAddress from) {
      Objects.requireNonNull(from, "from must not be null");
      this.from = from;
      return this;
    }

    public Builder replyTo(Consumer<ListBuilder<EmailAddress>> configurer) {
      ListBuilder<EmailAddress> listBuilder = new ListBuilder<>();
      configurer.accept(listBuilder);
      this.replyTo.clear();
      this.replyTo.addAll(listBuilder.build());
      return this;
    }

    public Builder to(Consumer<ListBuilder<EmailAddress>> configurer) {
      ListBuilder<EmailAddress> listBuilder = new ListBuilder<>();
      configurer.accept(listBuilder);
      this.to.clear();
      this.to.addAll(listBuilder.build());
      return this;
    }

    public Builder cc(Consumer<ListBuilder<EmailAddress>> configurer) {
      ListBuilder<EmailAddress> listBuilder = new ListBuilder<>();
      configurer.accept(listBuilder);
      this.cc.clear();
      this.cc.addAll(listBuilder.build());
      return this;
    }

    public Builder bcc(Consumer<ListBuilder<EmailAddress>> configurer) {
      ListBuilder<EmailAddress> listBuilder = new ListBuilder<>();
      configurer.accept(listBuilder);
      this.bcc.clear();
      this.bcc.addAll(listBuilder.build());
      return this;
    }

    public Builder message(EmailMessage message) {
      Objects.requireNonNull(message, "message must not be null");
      this.message = message;
      return this;
    }

    public Email build() {
      Objects.requireNonNull(from, "from must not be null");
      if (this.replyTo.isEmpty()) {
        this.replyTo.add(from);
      }
      if (this.to.isEmpty()) {
        throw new IllegalArgumentException("to must not be empty");
      }
      Objects.requireNonNull(message, "message must not be null");
      return new Email(this);
    }
  }
}
