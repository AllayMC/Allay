package org.allaymc.server.item.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;
import org.joml.primitives.AABBd;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ClexaGod | daoge_cmd
 */
public class ItemEndCrystalBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemEndCrystalBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        if (super.useItemOnBlock(dimension, placeBlockPos, interactInfo)) {
            return true;
        }

        var player = interactInfo.player();
        var gameMode = player.getGameMode();
        if (gameMode == GameMode.ADVENTURE || gameMode == GameMode.SPECTATOR) {
            return false;
        }

        var clickedBlockType = interactInfo.getClickedBlock().getBlockType();
        if (clickedBlockType != BlockTypes.OBSIDIAN && clickedBlockType != BlockTypes.BEDROCK) {
            return false;
        }

        var clickedBlockPos = interactInfo.clickedBlockPos();
        if (!dimension.getBlockState(BlockFace.UP.offsetPos(clickedBlockPos)).getBlockType().hasBlockTag(BlockTags.REPLACEABLE) ||
            !dimension.getBlockState(BlockFace.UP.offsetPos(clickedBlockPos, 2)).getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            return false;
        }

        var crystalPos = BlockFace.UP.offsetPos(interactInfo.clickedBlockPos());
        var collidingEntities = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(new AABBd(
                crystalPos.x(), crystalPos.y(), crystalPos.z(),
                crystalPos.x() + 1, crystalPos.y() + 2, crystalPos.z() + 1
        ));
        if (!collidingEntities.isEmpty()) {
            return false;
        }

        var crystal = EntityTypes.ENDER_CRYSTAL.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(crystalPos.x() + 0.5, crystalPos.y(), crystalPos.z() + 0.5)
                        .rot(ThreadLocalRandom.current().nextDouble() * 360, 0)
                        .build()
        );
        dimension.getEntityManager().addEntity(crystal);
        player.tryConsumeItemInHand();
        return true;
    }
}
