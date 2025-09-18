package org.allaymc.server.item.component;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.entity.data.EntityId;
import org.joml.Vector3ic;

/**
 * @author IWareQ
 */
@Slf4j
public class ItemSpawnEggBaseComponentImpl extends ItemBaseComponentImpl {
    protected EntityId spawnEntityId;

    public ItemSpawnEggBaseComponentImpl(ItemStackInitInfo initInfo, EntityId spawnEntityId) {
        super(initInfo);
        this.spawnEntityId = spawnEntityId;
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var clickedPos = interactInfo.clickedPos();
        var clickedBlockPos = interactInfo.clickedBlockPos();
        var entity = spawnEntityId.getEntityType().createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(
                                (double) clickedPos.x() + clickedBlockPos.x(),
                                (double) clickedPos.y() + clickedBlockPos.y(),
                                (double) clickedPos.z() + clickedBlockPos.z()
                        )
                        .build()
        );
        dimension.getEntityManager().addEntity(entity);
        return true;
    }
}
