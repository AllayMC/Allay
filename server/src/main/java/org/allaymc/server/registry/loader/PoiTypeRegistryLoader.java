package org.allaymc.server.registry.loader;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.world.poi.PoiType;
import org.allaymc.api.world.poi.PoiTypes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daoge_cmd
 */
public class PoiTypeRegistryLoader implements RegistryLoader<Void, Map<BlockType<?>, PoiType>> {
    @Override
    public Map<BlockType<?>, PoiType> load(Void $) {
        var map = new HashMap<BlockType<?>, PoiType>();
        map.put(BlockTypes.PORTAL, PoiTypes.NETHER_PORTAL);
        return map;
    }
}
