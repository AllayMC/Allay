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
 * {@code ItemEndCrystalBaseComponentImpl} creates an end crystal when a player
 * uses the item on a suitable support block. Its checks require an allowed game
 * mode, obsidian or bedrock support, replaceable space above, and no colliding
 * entity before consuming the held item.
 *
 * @author ClexaGod | daoge_cmd
 */
public class ItemEndCrystalBaseComponentImpl extends ItemBaseComponentImpl {
    /**
     * Creates an end-crystal item component from the supplied stack initialization.
     */
    public ItemEndCrystalBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Attempts end-crystal creation above the clicked obsidian or bedrock block.
     * The caller must supply interaction information. A handled inherited component
     * event returns immediately; otherwise mode, space, and collision checks can
     * reject creation. Success adds the crystal and attempts to consume the held item.
     */
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
        // The crystal needs two replaceable blocks above its support.
        if (!dimension.getBlockState(BlockFace.UP.offsetPos(clickedBlockPos)).getBlockType().hasBlockTag(BlockTags.REPLACEABLE) ||
            !dimension.getBlockState(BlockFace.UP.offsetPos(clickedBlockPos, 2)).getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            return false;
        }

        var crystalPos = BlockFace.UP.offsetPos(interactInfo.clickedBlockPos());
        // Test the crystal's full one-by-two-block volume before spawning it.
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
                        // Convert the unit random sample to a full yaw turn.
                        .rot(ThreadLocalRandom.current().nextDouble() * 360, 0)
                        .build()
        );
        dimension.getEntityManager().addEntity(crystal);
        player.tryConsumeItemInHand();
        return true;
    }
}
