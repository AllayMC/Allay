package org.allaymc.api.world.biome;

import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.annotation.NBT;

@NBT
public record BiomeData(
        float ash,
        float blue_spores,
        float depth,
        float downfall,
        float height,
        String name_hash,
        byte rain,
        float red_spores,
        NbtList<String> tags,
        float temperature,
        float waterColorA,
        float waterColorB,
        float waterColorG,
        float waterColorR,
        float waterTransparency,
        float white_ash
) {
}
