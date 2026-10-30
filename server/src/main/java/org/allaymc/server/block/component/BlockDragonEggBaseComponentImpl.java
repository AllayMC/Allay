package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.DragonEggTeleportParticle;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockDragonEggBaseComponentImpl} teleports dragon eggs in response to
 * interactions and noncreative punches. Candidate destinations must be air and within
 * dimension height bounds. The bounded search chooses horizontal offsets around the
 * source and nonnegative vertical offsets, so the egg can move upward or remain at
 * the same height while avoiding an unbounded search in enclosed spaces.
 *
 * <p>
 * A found destination produces a displacement particle, clears the source, and writes
 * a default egg state at the target. These writes are ordered but their results are
 * ignored, so rejection can leave a partial move; the component provides no rollback.
 * Creative punches bypass teleportation so the caller can perform ordinary breaking,
 * and the separate falling component supplies gravity behavior.
 *
 * @author daoge_cmd
 */
public class BlockDragonEggBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code MAX_TELEPORT_ATTEMPTS} bounds random destination searches so an egg
     * surrounded by unsuitable positions eventually returns without moving.
     */
    private static final int MAX_TELEPORT_ATTEMPTS = 1000;
    /**
     * {@code TELEPORT_RANGE_XZ} controls horizontal random offsets, with an inclusive
     * negative bound and an exclusive positive bound for each axis.
     */
    private static final int TELEPORT_RANGE_XZ = 16;
    /**
     * {@code TELEPORT_RANGE_Y} limits nonnegative vertical offsets, so teleport
     * candidates never originate below the egg's current height.
     */
    private static final int TELEPORT_RANGE_Y = 16;

    /**
     * Creates the dragon egg block behavior for the supplied block type.
     */
    public BlockDragonEggBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Offers the interaction to companion components before attempting egg
     * teleportation. Missing interaction context returns {@code false}; otherwise the
     * interaction is handled even when the bounded search finds no destination.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (interactInfo == null) {
            return false;
        }
        teleport(dimension, interactInfo.clickedBlockPos());
        return true;
    }

    /**
     * Forwards the punch and attempts teleportation unless the responsible player is
     * creative. A {@code null} entity still permits teleportation, while creative
     * punches leave ordinary break handling to the caller.
     */
    @Override
    public void onPunch(Block block, BlockFace blockFace, ItemStack usedItem, Entity entity) {
        super.onPunch(block, blockFace, usedItem, entity);
        if (entity instanceof EntityPlayer player && player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        teleport(block.getDimension(), block.getPosition());
    }

    /**
     * Attempts a bounded random search for an air destination at or above the egg's
     * height. The selected destination must lie within dimension height bounds;
     * exhausting the search leaves the egg unchanged.
     *
     * <p>
     * An accepted candidate emits a displacement particle before the source becomes
     * air and the destination receives the default egg state. State-write results are
     * ignored, so refused writes do not roll back the particle or earlier state
     * changes.
     */
    private void teleport(Dimension dimension, Vector3ic fromPos) {
        var random = ThreadLocalRandom.current();
        var minY = dimension.getDimensionType().getMinHeight();
        var maxY = dimension.getDimensionType().getMaxHeight();

        // Bound the retry search because nearby air may be absent, leaving the
        // original egg in place when no acceptable candidate is found.
        for (int i = 0; i < MAX_TELEPORT_ATTEMPTS; i++) {
            int x = fromPos.x() + random.nextInt(-TELEPORT_RANGE_XZ, TELEPORT_RANGE_XZ);
            int y = fromPos.y() + random.nextInt(0, TELEPORT_RANGE_Y);
            int z = fromPos.z() + random.nextInt(-TELEPORT_RANGE_XZ, TELEPORT_RANGE_XZ);

            if (y < minY || y > maxY) continue;

            var targetState = dimension.getBlockState(x, y, z);
            if (targetState.getBlockType() != BlockTypes.AIR) continue;

            var diff = new Vector3i(fromPos.x() - x, fromPos.y() - y, fromPos.z() - z);
            dimension.addParticle(fromPos, new DragonEggTeleportParticle(diff));
            // The source is removed before the destination is written. Neither result
            // is checked, so this move has no compensation for rejected writes.
            dimension.setBlockState(fromPos, BlockTypes.AIR.getDefaultState());
            dimension.setBlockState(x, y, z, blockType.getDefaultState());
            return;
        }
    }
}
