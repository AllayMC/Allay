package org.allaymc.server.item.component;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3f;
import org.joml.Vector3ic;

/**
 * @author IWareQ
 */
@Slf4j
public class ItemSpawnEggBaseComponentImpl extends ItemBaseComponentImpl {
    protected Identifier customEntityId;

    public ItemSpawnEggBaseComponentImpl(ItemStackInitInfo initInfo, Identifier customEntityId) {
        super(initInfo);
        this.customEntityId = customEntityId;
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) return false;

        if (this.customEntityId == null) {
            var identifier = thisItemStack.getItemType().getIdentifier();
            this.customEntityId = new Identifier(identifier.toString().replace("_spawn_egg", ""));
        }

        var entity = Registries.ENTITIES.get(this.customEntityId).createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(interactInfo.clickedPos().add(
                                interactInfo.clickedBlockPos().x(),
                                interactInfo.clickedBlockPos().y(),
                                interactInfo.clickedBlockPos().z(),
                                new Vector3f()
                        ))
                        .build()
        );
        dimension.getEntityService().addEntity(entity);
        return true;
    }
}
