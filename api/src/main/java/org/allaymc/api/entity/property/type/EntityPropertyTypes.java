package org.allaymc.api.entity.property.type;

import org.allaymc.api.annotation.MinecraftVersionSensitive;
import org.allaymc.api.entity.property.enums.ClimateVariant;

@MinecraftVersionSensitive
public interface EntityPropertyTypes {
    EnumPropertyType<ClimateVariant> CLIMATE_VARIANT = EnumPropertyType.of("minecraft:climate_variant", ClimateVariant.class, ClimateVariant.TEMPERATE);
}
