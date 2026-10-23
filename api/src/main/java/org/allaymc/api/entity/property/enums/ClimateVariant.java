package org.allaymc.api.entity.property.enums;

/**
 * {@code ClimateVariant} identifies the temperate, warm, or cold appearance
 * category of a climate-sensitive entity. The shared entity property uses
 * these enum values for its serialized names and defaults to {@code TEMPERATE}
 * when no alternate variant has been selected.
 *
 * @author daoge_cmd
 */
public enum ClimateVariant {
    /**
     * Temperate climate appearance category for the entity.
     */
    TEMPERATE,
    /**
     * Warm climate appearance category for the entity.
     */
    WARM,
    /**
     * Cold climate appearance category for the entity.
     */
    COLD
}
