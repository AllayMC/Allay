package org.allaymc.api.entity.property.type;

import org.allaymc.api.annotation.MinecraftVersionSensitive;
import org.allaymc.api.entity.property.enums.ClimateVariant;

/**
 * {@code EntityPropertyTypes} supplies the shared entity property definitions
 * used by built-in entity types. The climate variant definition names the
 * client-visible property and its enum choices, allowing entity initialization
 * and NBT loading to use the same default and serialized names.
 */
@MinecraftVersionSensitive
public interface EntityPropertyTypes {
    /**
     * {@code CLIMATE_VARIANT} defines the climate-sensitive appearance
     * property. Its default is {@code ClimateVariant.TEMPERATE}, and enum
     * names provide the serialized choices used by entity persistence and
     * client synchronization.
     */
    EnumPropertyType<ClimateVariant> CLIMATE_VARIANT = EnumPropertyType.of("minecraft:climate_variant", ClimateVariant.class, ClimateVariant.TEMPERATE);
}
