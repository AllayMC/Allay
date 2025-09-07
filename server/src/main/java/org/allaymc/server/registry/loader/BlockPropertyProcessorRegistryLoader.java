package org.allaymc.server.registry.loader;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.server.block.BlockPlaceHelper;
import org.allaymc.server.block.property.BlockPropertyProcessor;

import java.util.HashMap;
import java.util.Map;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * @author daoge_cmd
 */
public class BlockPropertyProcessorRegistryLoader implements RegistryLoader<Void, Map<BlockPropertyType<?>, BlockPropertyProcessor>> {
    @Override
    public Map<BlockPropertyType<?>, BlockPropertyProcessor> load(Void $) {
        var map = new HashMap<BlockPropertyType<?>, BlockPropertyProcessor>();
        map.put(PILLAR_AXIS, BlockPlaceHelper::processPillarAxisProperty);
        map.put(MINECRAFT_CARDINAL_DIRECTION, BlockPlaceHelper::processMinecraftCardinalDirectionProperty);
        map.put(FACING_DIRECTION, BlockPlaceHelper::processFacingDirectionProperty);
        map.put(GROUND_SIGN_DIRECTION, BlockPlaceHelper::processGroundSignDirectionProperty);
        map.put(UPSIDE_DOWN_BIT, BlockPlaceHelper::processUpsideDownBitProperty);
        return map;
    }
}
