package org.allaymc.server.registry.loader;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.world.poi.PoiType;
import org.allaymc.api.world.poi.PoiTypes;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code PoiTypeRegistryLoader} maps block types to the built-in point-of-interest kinds
 * recognized by the world. Its current table associates portal blocks with Nether portal
 * points, and the returned mutable map is ready for the registry to extend or query.
 *
 * @author daoge_cmd
 */
public class PoiTypeRegistryLoader implements RegistryLoader<Void, Map<BlockType<?>, PoiType>> {
    /**
     * Creates the mutable portal-block mapping for Nether portal point-of-interest detection.
     */
    @Override
    public Map<BlockType<?>, PoiType> load(Void $) {
        var map = new HashMap<BlockType<?>, PoiType>();
        map.put(BlockTypes.PORTAL, PoiTypes.NETHER_PORTAL);
        return map;
    }
}
