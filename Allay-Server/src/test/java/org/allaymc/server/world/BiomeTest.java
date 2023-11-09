package org.allaymc.server.world;

import org.allaymc.api.data.VanillaBiomeId;
import org.allaymc.api.world.biome.BiomeData;
import org.allaymc.api.world.biome.BiomeTypeRegistry;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AllayTestExtension.class)
public class BiomeTest {
    @Test
    void readBiome() {
        BiomeData biomeData = BiomeTypeRegistry.getRegistry().get(VanillaBiomeId.BASALT_DELTAS);
        NbtList<String> list = new NbtList<>(NbtType.STRING,
                "nether",
                "soulsand_valley",
                "spawn_endermen",
                "spawn_ghast");
        Assertions.assertEquals(new BiomeData(0.05f, 0f, 0.1f, 0f,
                        0.2f, "soulsand_valley", (byte) 0, 0,
                        list, 2, 0.65f, 0.34117648f, 0.34901962f,
                        0.5647059f, 0.65f, 0)
                , biomeData);
    }
}
