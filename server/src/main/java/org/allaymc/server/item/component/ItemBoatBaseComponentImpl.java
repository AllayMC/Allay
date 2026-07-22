package org.allaymc.server.item.component;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.data.BoatVariant;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;
import org.joml.primitives.AABBd;

import java.util.Objects;

/**
 * Places a configured ordinary boat variant.
 */
public class ItemBoatBaseComponentImpl extends ItemBaseComponentImpl {
    protected final BoatVariant variant;

    public ItemBoatBaseComponentImpl(ItemStackInitInfo initInfo, BoatVariant variant) {
        super(initInfo);
        this.variant = Objects.requireNonNull(variant, "variant");
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        if (super.useItemOnBlock(dimension, placeBlockPos, interactInfo)) {
            return true;
        }
        if (interactInfo == null) {
            return false;
        }

        var player = interactInfo.player();
        if (player.getGameMode() == GameMode.ADVENTURE || player.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }
        if (!dimension.getBlockState(placeBlockPos).getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            return false;
        }

        var x = placeBlockPos.x() + 0.5;
        var y = (double) placeBlockPos.y();
        var z = placeBlockPos.z() + 0.5;
        var boatAABB = new AABBd(x - 0.7, y, z - 0.7, x + 0.7, y + 0.455, z + 0.7);
        if (dimension.getCollidingBlockStates(boatAABB) != null ||
            !dimension.getEntityManager().getPhysicsService().computeCollidingEntities(boatAABB).isEmpty()) {
            return false;
        }

        var boat = EntityTypes.BOAT.createEntity(EntityInitInfo.builder()
                .dimension(dimension)
                .pos(x, y, z)
                .rot(player.getLocation().yaw(), 0)
                .build());
        boat.setBoatVariant(variant);
        var customName = thisItemStack.getCustomName();
        if (customName != null && !customName.isEmpty()) {
            boat.setNameTag(customName);
        }
        dimension.getEntityManager().addEntity(boat);
        player.tryConsumeItemInHand();
        return true;
    }
}
