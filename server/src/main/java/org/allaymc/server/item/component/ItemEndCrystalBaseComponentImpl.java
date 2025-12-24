package org.allaymc.server.item.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3d;
import org.joml.Vector3ic;
import org.joml.primitives.AABBd;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ClexaGod
 */
public class ItemEndCrystalBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemEndCrystalBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        super.useItemOnBlock(dimension, placeBlockPos, interactInfo);

        if (interactInfo == null || interactInfo.player() == null) {
            return false;
        }

        var player = interactInfo.player();
        if (player.getGameMode() == GameMode.ADVENTURE || player.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }

        if (interactInfo.blockFace() != BlockFace.UP) {
            return false;
        }

        var clickedBlockState = interactInfo.getClickedBlock();
        var clickedBlockType = clickedBlockState.getBlockType();
        if (clickedBlockType != BlockTypes.OBSIDIAN && clickedBlockType != BlockTypes.BEDROCK) {
            return false;
        }

        if (dimension.getBlockState(placeBlockPos).getBlockType() != BlockTypes.AIR) {
            return false;
        }
        var abovePos = BlockFace.UP.offsetPos(placeBlockPos);
        if (dimension.getBlockState(abovePos).getBlockType() != BlockTypes.AIR) {
            return false;
        }

        var basePos = interactInfo.clickedBlockPos();
        var aabb = new AABBd(basePos.x(), basePos.y(), basePos.z(), basePos.x() + 1, basePos.y() + 2, basePos.z() + 1);
        var collidingEntities = dimension.getEntityManager()
                .getPhysicsService()
                .computeCollidingEntities(aabb, entity -> true);
        if (!collidingEntities.isEmpty()) {
            return false;
        }

        var spawnPos = new Vector3d(basePos.x() + 0.5, basePos.y() + 1.0, basePos.z() + 0.5);
        var crystal = EntityTypes.ENDER_CRYSTAL.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(spawnPos)
                        .rot(ThreadLocalRandom.current().nextDouble() * 360.0d, 0)
                        .build()
        );
        dimension.getEntityManager().addEntity(crystal);
        player.tryConsumeItemInHand();
        return true;
    }
}
