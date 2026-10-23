package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

/**
 * {@code Dropdown} represents a custom-form selection with display text, an option list, and a
 * default option index. Construction retains the option list, and server-settings synchronization
 * replaces the default index by parsing the corresponding response token.
 *
 * @author daoge_cmd
 */
public final class Dropdown implements CustomFormElement {

    /**
     * Serialized form element discriminator.
     */
    @SuppressWarnings("unused")
    private final String type = "dropdown";

    /**
     * Displayed form element text.
     */
    @Getter
    private final String text;
    /**
     * {@code options} retains the caller's mutable list; its generated getter exposes the same
     * list, and later serialization observes list changes.
     */
    @Getter
    private final List<String> options;
    /**
     * Zero-based initially selected option index.
     */
    @Getter
    @SerializedName("default")
    private int defaultOptionIndex;
    /**
     * Optional control hover text.
     */
    @Getter
    private final String tooltip;

    /**
     * Creates a dropdown retaining the supplied option list and default index. The constructor does
     * not validate index bounds, and a {@code null} tooltip omits optional tooltip content during
     * serialization.
     */
    public Dropdown(String text, List<String> options, int defaultOption, String tooltip) {
        this.text = text;
        this.options = options;
        this.defaultOptionIndex = defaultOption;
        this.tooltip = tooltip;
    }

    /**
     * Parses the response as an integer and stores it as the next default index. Parsing failure
     * leaves the old default unchanged; successful parsing does not validate the index against the
     * option list.
     */
    @Override
    public void syncDefaultValueToResponse(String response) {
        this.defaultOptionIndex = Integer.parseInt(response);
    }
}
