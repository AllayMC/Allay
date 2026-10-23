package org.allaymc.server.world.biome;

import lombok.Getter;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.biome.BiomeData;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.server.registry.InternalRegistries;

/**
 * {@code AllayBiomeType} represents a registered biome with numeric and namespaced
 * identifiers and metadata for gameplay and encoding. Its constructors register
 * each instance in the biome registry. Built-in instances use predefined IDs and
 * loaded metadata, while the custom constructor obtains an allocated ID before
 * registration.
 *
 * @author daoge_cmd
 */
@Getter
public class AllayBiomeType implements BiomeType {
    /**
     * Namespaced registered biome identifier.
     */
    protected final Identifier identifier;
    /**
     * Numeric biome palette identifier.
     */
    protected final int id;
    /**
     * {@code biomeData} retains the supplied metadata and exposes it through the
     * generated getter. Mutable tag lists inside the record remain shared with
     * consumers.
     */
    protected final BiomeData biomeData;

    /**
     * Creates and immediately registers a built-in biome using its predefined
     * identity and loaded metadata. Registry initialization must already be
     * available; missing metadata is retained as {@code null} rather than rejected
     * here.
     */
    public AllayBiomeType(BiomeId biomeId) {
        this.identifier = biomeId.getIdentifier();
        this.id = biomeId.getId();
        this.biomeData = InternalRegistries.BIOME_DATA.get(biomeId);
        Registries.BIOMES.register(this.id, this.identifier, this);
    }

    /**
     * Creates and registers a custom biome after obtaining its allocated ID.
     * Metadata remains shared, and the allocator attempts persistence before
     * registration; registration failure does not undo allocation. Both
     * services must already be initialized.
     */
    public AllayBiomeType(Identifier identifier, BiomeData biomeData) {
        this.identifier = identifier;
        this.id = CustomBiomeIdAllocator.getInstance().allocateId(identifier);
        this.biomeData = biomeData;
        Registries.BIOMES.register(this.id, this.identifier, this);
    }
}
