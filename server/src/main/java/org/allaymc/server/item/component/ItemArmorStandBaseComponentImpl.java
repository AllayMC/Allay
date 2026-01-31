package org.allaymc.server.item.component;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityArmorStand;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3ic;
import org.joml.primitives.AABBd;

/**
 * Item component for armor stand placement.
 *
 * @author daoge_cmd
 */
public class ItemArmorStandBaseComponentImpl extends ItemBaseComponentImpl {

    public ItemArmorStandBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
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
        var gameMode = player.getGameMode();

        // Adventure and spectator modes cannot place armor stands
        if (gameMode == GameMode.ADVENTURE || gameMode == GameMode.SPECTATOR) {
            return false;
        }

        // Check if the block at placement position is replaceable
        var blockState = dimension.getBlockState(placeBlockPos);
        if (!blockState.getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            return false;
        }

        // Check if the block above is also replaceable (armor stand is 2 blocks tall)
        var abovePos = new org.joml.Vector3i(placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z());
        var aboveBlockState = dimension.getBlockState(abovePos);
        if (!aboveBlockState.getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            return false;
        }

        // Check for entity collisions at the placement position
        double x = placeBlockPos.x() + 0.5;
        double y = placeBlockPos.y();
        double z = placeBlockPos.z() + 0.5;
        var armorStandAABB = new AABBd(x - 0.25, y, z - 0.25, x + 0.25, y + 1.975, z + 0.25);

        var collidingEntities = dimension.getEntityManager().getPhysicsService()
                .computeCollidingEntities(armorStandAABB);

        // Check if any colliding entity is an armor stand (prevent stacking)
        for (var entity : collidingEntities) {
            if (entity instanceof EntityArmorStand) {
                return false;
            }
        }

        // Calculate yaw based on player's facing direction (opposite direction)
        double yaw = (player.getLocation().yaw() + 180) % 360;

        // Round yaw to nearest 22.5 degrees for 16-direction precision (like Bedrock)
        yaw = Math.round(yaw / 22.5) * 22.5;

        // Create and spawn the armor stand
        var entity = EntityTypes.ARMOR_STAND.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(x, y, z)
                        .rot(yaw, 0)
                        .build()
        );

        // Apply custom name from item if present
        var customName = thisItemStack.getCustomName();
        if (customName != null && !customName.isEmpty()) {
            entity.setNameTag(customName);
        }

        dimension.getEntityManager().addEntity(entity);

        // Play placement sound
        dimension.addSound(
                entity.getLocation(),
                SimpleSound.ARMOR_STAND_PLACE
        );

        // Consume item in survival/adventure mode
        player.tryConsumeItemInHand();

        return true;
    }
}
