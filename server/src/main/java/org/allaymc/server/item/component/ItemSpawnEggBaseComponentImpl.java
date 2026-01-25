package org.allaymc.server.item.component;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemSpawnEggBaseComponent;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.entity.data.EntityId;
import org.joml.Vector3ic;

/**
 * @author IWareQ
 */
@Slf4j
public class ItemSpawnEggBaseComponentImpl extends ItemBaseComponentImpl implements ItemSpawnEggBaseComponent {
    protected EntityId entityId;

    public ItemSpawnEggBaseComponentImpl(ItemStackInitInfo initInfo, EntityId entityId) {
        super(initInfo);
        this.entityId = entityId;
    }

    @Override
    public EntityType<?> getEntityType() {
        return entityId.getEntityType();
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var clickedPos = interactInfo.clickedPos();
        var clickedBlockPos = interactInfo.clickedBlockPos();
        var entity = entityId.getEntityType().createEntity(
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
