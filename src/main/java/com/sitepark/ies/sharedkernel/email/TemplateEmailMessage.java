package com.sitepark.ies.sharedkernel.email;

import com.sitepark.ies.sharedkernel.base.MapBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import javax.annotation.concurrent.Immutable;
import org.jetbrains.annotations.NotNull;

/**
 * Template-based email message using Mustache templates.
 *
 * <p>This message type enables template-based email rendering with:
 *
 * <ul>
 *   <li><b>Message Types</b> - Reusable template definitions (e.g., "password-recovery")
 *   <li><b>Themes</b> - Visual styling with colors, typography, and logos
 *   <li><b>Multi-Language Support</b> - Language-specific templates and defaults
 *   <li><b>Variable Preprocessing</b> - Dynamic data with theme property access
 *   <li><b>Data Merging</b> - Deep merge of default data with runtime data
 * </ul>
 *
 * <p>The subject must be provided in the {@code data} map under the key "subject". This allows
 * language-specific subjects via default data and dynamic subject generation.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * TemplateEmailMessage message = TemplateEmailMessage.builder()
 *     .messageType("password-recovery")
 *     .theme("default")
 *     .lang("de")
 *     .data(configurer -> configurer
 *         .put("subject", "Passwort zurücksetzen")
 *         .put("code", "123456")
 *         .put("expiresAt", "14:30"))
 *     .build();
 * }</pre>
 *
 * <p>The rendering process:
 *
 * <ol>
 *   <li>Load template by message type
 *   <li>Merge language-specific default data with runtime data (deep merge)
 *   <li>Load theme (language-aware for localized styling)
 *   <li>Preprocess variables:
 *       <ul>
 *         <li>HTML: Convert line breaks (\n) to &lt;br&gt; tags
 *         <li>Plain: Keep line breaks unchanged
 *         <li>Both: Resolve Mustache expressions in variable values
 *       </ul>
 *   <li>Extract subject from preprocessed data.subject
 *   <li>Render HTML and text templates with preprocessed variables
 * </ol>
 */
@SuppressWarnings({"PMD.AvoidFieldNameMatchingMethodName"})
@Immutable
public final class TemplateEmailMessage implements EmailMessage {

  @NotNull private final EmailMessageTypeIdentifier messageType;
  private final EmailMessageThemeIdentifier theme;
  @NotNull private final Map<String, Object> data;
  private final String lang;

  @SuppressWarnings("PMD.LawOfDemeter")
  private TemplateEmailMessage(Builder builder) {
    this.messageType = builder.messageType;
    this.theme = builder.theme;
    this.data = Map.copyOf(builder.data);
    this.lang = builder.lang;
  }

  public EmailMessageTypeIdentifier messageType() {
    return this.messageType;
  }

  public EmailMessageThemeIdentifier theme() {
    return this.theme;
  }

  public Map<String, Object> data() {
    return Map.copyOf(this.data);
  }

  public String lang() {
    return this.lang;
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageType, theme, data, lang);
  }

  @Override
  public boolean equals(Object obj) {
    return (obj instanceof TemplateEmailMessage that)
        && Objects.equals(this.messageType, that.messageType)
        && Objects.equals(this.theme, that.theme)
        && Objects.equals(this.data, that.data)
        && Objects.equals(this.lang, that.lang);
  }

  @Override
  public String toString() {
    return "TemplateEmailMessage{"
        + "messageType='"
        + messageType
        + '\''
        + ", theme='"
        + theme
        + '\''
        + ", data="
        + data
        + ", lang='"
        + lang
        + '}';
  }

  public Builder toBuilder() {
    return new Builder(this);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("PMD.UseConcurrentHashMap")
  public static final class Builder {
    private EmailMessageTypeIdentifier messageType;
    private EmailMessageThemeIdentifier theme;
    private final Map<String, Object> data = new HashMap<>();
    private String lang;

    private Builder() {}

    @SuppressWarnings("PMD.LawOfDemeter")
    private Builder(TemplateEmailMessage templateEmailMessage) {
      this.messageType = templateEmailMessage.messageType;
      this.theme = templateEmailMessage.theme;
      this.data.putAll(Map.copyOf(templateEmailMessage.data));
      this.lang = templateEmailMessage.lang;
    }

    public Builder messageType(EmailMessageTypeIdentifier messageType) {
      Objects.requireNonNull(messageType, "messageType must not be null");
      this.messageType = messageType;
      return this;
    }

    public Builder theme(EmailMessageThemeIdentifier theme) {
      Objects.requireNonNull(theme, "theme must not be null");
      this.theme = theme;
      return this;
    }

    public Builder data(Consumer<MapBuilder<String, Object>> configurer) {
      MapBuilder<String, Object> mapBuilder = new MapBuilder<>();
      configurer.accept(mapBuilder);
      this.data.clear();
      this.data.putAll(mapBuilder.build());
      return this;
    }

    public Builder lang(String lang) {
      this.lang = lang;
      return this;
    }

    public TemplateEmailMessage build() {
      Objects.requireNonNull(messageType, "messageType must not be null");
      return new TemplateEmailMessage(this);
    }
  }
}
