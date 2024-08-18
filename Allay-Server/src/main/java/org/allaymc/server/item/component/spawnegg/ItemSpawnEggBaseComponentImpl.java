package org.allaymc.server.item.component.spawnegg;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.PlayerInteractInfo;
import org.allaymc.api.entity.init.SimpleEntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.joml.Vector3f;
import org.joml.Vector3ic;

/**
 * Allay Project 27/06/2024
 *
 * @author IWareQ
 */
@Slf4j
public class ItemSpawnEggBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemSpawnEggBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) return false;
        var identifier = thisItemStack.getItemType().getIdentifier();
        identifier = new Identifier(identifier.toString().replace("_spawn_egg", ""));

        var entityType = Registries.ENTITIES.get(identifier);
        if (entityType == EntityTypes.VILLAGER) entityType = EntityTypes.VILLAGER_V2;
        var entity = entityType.createEntity(
                SimpleEntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(interactInfo.clickPos().add(
                                interactInfo.clickBlockPos().x(),
                                interactInfo.clickBlockPos().y(),
                                interactInfo.clickBlockPos().z(),
                                new Vector3f()))
                        .build()
        );
        dimension.getEntityService().addEntity(entity);
        return true;
    }
}
