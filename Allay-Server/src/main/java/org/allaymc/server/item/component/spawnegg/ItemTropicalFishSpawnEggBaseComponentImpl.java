package org.allaymc.server.item.component.spawnegg;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.initinfo.SimpleEntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * @author IWareQ
 */
@Slf4j
public class ItemTropicalFishSpawnEggBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemTropicalFishSpawnEggBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) return false;
        var entity = EntityTypes.TROPICALFISH.createEntity(
                SimpleEntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z())
                        .build()
        );
        dimension.getEntityService().addEntity(entity);
        return true;
    }
}
