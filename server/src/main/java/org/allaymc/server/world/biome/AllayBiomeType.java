package org.allaymc.server.world.biome;

import lombok.Getter;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.biome.BiomeData;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.server.registry.InternalRegistries;

/**
 * @author daoge_cmd
 */
@Getter
public class AllayBiomeType implements BiomeType {
    protected final Identifier identifier;
    protected final int id;
    protected final BiomeData biomeData;

    public AllayBiomeType(BiomeId biomeId) {
        this.identifier = biomeId.getIdentifier();
        this.id = biomeId.getId();
        this.biomeData = InternalRegistries.BIOME_DATA.get(biomeId);
        Registries.BIOMES.register(this.id, this.identifier, this);
    }

    /**
     * Create a custom biome type with auto-allocated ID.
     * <p>
     * The ID is automatically allocated by {@link CustomBiomeIdAllocator} and
     * persisted to {@code biome_ids.yml}. The biome is automatically registered
     * in {@link Registries#BIOMES}.
     *
     * @param identifier the biome identifier
     * @param biomeData  the biome data
     */
    public AllayBiomeType(Identifier identifier, BiomeData biomeData) {
        this.identifier = identifier;
        this.id = CustomBiomeIdAllocator.getInstance().allocateId(identifier);
        this.biomeData = biomeData;
        Registries.BIOMES.register(this.id, this.identifier, this);
    }
}
