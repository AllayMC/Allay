package org.allaymc.api.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * {@code Slider} represents a numeric custom-form control with bounds, a step, and a default value.
 * The client receives those values through form serialization, while server-settings responses can
 * replace the stored default for the next display.
 *
 * <p>
 * For finite inputs, construction raises {@code min} to zero when needed and selects {@code max}
 * against that adjusted lower bound, keeping the serialized endpoints ordered. The supplied
 * {@code step} and {@code defaultValue} remain unchanged, so neither is validated against the
 * normalized range. Floating-point {@code NaN} can survive bound normalization, and default
 * synchronization parses floats without clamping them to either endpoint.
 *
 * @author daoge_cmd
 */
public final class Slider implements CustomFormElement {

    /**
     * Serialized form element discriminator.
     */
    @SuppressWarnings("unused")
    private final String type = "slider";

    /**
     * Displayed form element text.
     */
    @Getter
    private final String text;
    /**
     * Normalized lower numeric bound.
     */
    @Getter
    private final float min;
    /**
     * Normalized upper numeric bound.
     */
    @Getter
    private final float max;
    /**
     * Numeric interval between slider positions.
     */
    @Getter
    private final int step;
    /**
     * Value used for the next display.
     */
    @Getter
    @SerializedName("default")
    private float defaultValue;
    /**
     * Optional control hover text.
     */
    @Getter
    private final String tooltip;

    /**
     * Creates a slider using the supplied label, bounds, step, default, and optional tooltip. For
     * finite inputs, the lower bound becomes at least zero and the upper bound becomes at least
     * that normalized lower bound.
     *
     * <p>
     * The upper-bound calculation uses {@code this.min}, preserving endpoint ordering when the
     * requested lower bound was negative. The supplied step and default remain unvalidated and
     * unclamped; {@code NaN} bounds retain floating-point comparison behavior rather than becoming
     * a usable finite range.
     */
    public Slider(String text, float min, float max, int step, float defaultValue, String tooltip) {
        this.text = text;
        // Select the upper bound against the normalized lower bound so finite
        // endpoints remain ordered in the serialized control configuration.
        this.min = Math.max(min, 0f);
        this.max = Math.max(max, this.min);
        this.step = step;
        this.defaultValue = defaultValue;
        this.tooltip = tooltip;
    }

    /**
     * Parses the response as a float and stores it as the next default value. Parsing failure
     * leaves the old default unchanged; successful parsing does not clamp the result to the
     * configured range.
     */
    @Override
    public void syncDefaultValueToResponse(String response) {
        this.defaultValue = Float.parseFloat(response);
    }
}
