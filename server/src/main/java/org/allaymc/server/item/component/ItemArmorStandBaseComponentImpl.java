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
 * {@code ItemArmorStandBaseComponentImpl} places armor stand entities from item
 * interactions. Placement checks two replaceable block spaces and rejects an
 * existing armor stand in the prospective bounds; successful placement copies
 * the item name, plays a sound, and consumes the held item outside creative mode.
 *
 * @author daoge_cmd
 */
public class ItemArmorStandBaseComponentImpl extends ItemBaseComponentImpl {

    /**
     * Creates an armor stand item component from the stack initialization data.
     */
    public ItemArmorStandBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Handles component-defined use first, then attempts armor stand placement.
     * After inherited handling declines, a {@code null} interaction, adventure
     * or spectator player, occupied block space, or overlapping armor stand
     * returns {@code false}; other entity types
     * do not reject placement. Successful placement snaps facing to sixteen
     * directions, copies a nonempty custom name, adds the stand, plays its sound,
     * and then asks the player to consume the held item.
     */
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

        if (gameMode == GameMode.ADVENTURE || gameMode == GameMode.SPECTATOR) {
            return false;
        }

        var blockState = dimension.getBlockState(placeBlockPos);
        if (!blockState.getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            return false;
        }

        // Require the upper space as well because the stand extends beyond its base block.
        var abovePos = new org.joml.Vector3i(placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z());
        var aboveBlockState = dimension.getBlockState(abovePos);
        if (!aboveBlockState.getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            return false;
        }

        double x = placeBlockPos.x() + 0.5;
        double y = placeBlockPos.y();
        double z = placeBlockPos.z() + 0.5;
        // Match the stand's placement bounds so an existing stand cannot be stacked into this footprint.
        var armorStandAABB = new AABBd(x - 0.25, y, z - 0.25, x + 0.25, y + 1.975, z + 0.25);

        var collidingEntities = dimension.getEntityManager().getPhysicsService()
                .computeCollidingEntities(armorStandAABB);

        for (var entity : collidingEntities) {
            if (entity instanceof EntityArmorStand) {
                return false;
            }
        }

        // Face the stand toward the player, then snap its rotation to the sixteen
        // placement directions represented by increments of 22.5 degrees.
        double yaw = (player.getLocation().yaw() + 180) % 360;

        yaw = Math.round(yaw / 22.5) * 22.5;

        var entity = EntityTypes.ARMOR_STAND.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(x, y, z)
                        .rot(yaw, 0)
                        .build()
        );

        var customName = thisItemStack.getCustomName();
        if (customName != null && !customName.isEmpty()) {
            entity.setNameTag(customName);
        }

        dimension.getEntityManager().addEntity(entity);

        dimension.addSound(
                entity.getLocation(),
                SimpleSound.ARMOR_STAND_PLACE
        );

        player.tryConsumeItemInHand();

        return true;
    }
}
