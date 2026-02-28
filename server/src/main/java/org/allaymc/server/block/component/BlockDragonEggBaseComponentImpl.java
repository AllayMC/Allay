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
 * Block component for dragon egg.
 *
 * <p>The dragon egg teleports to a random nearby air block when interacted
 * with (right-click or left-click). In creative mode, left-click breaks
 * the block normally instead of teleporting it. The egg also has falling
 * behavior (handled by {@code BlockFallableComponentImpl}).
 *
 * @author daoge_cmd
 */
public class BlockDragonEggBaseComponentImpl extends BlockBaseComponentImpl {

    private static final int MAX_TELEPORT_ATTEMPTS = 1000;
    private static final int TELEPORT_RANGE_XZ = 16;
    private static final int TELEPORT_RANGE_Y = 16;

    public BlockDragonEggBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

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

    @Override
    public void onPunch(Block block, BlockFace blockFace, ItemStack usedItem, Entity entity) {
        super.onPunch(block, blockFace, usedItem, entity);

        // In creative mode, left-click breaks the block normally
        if (entity instanceof EntityPlayer player && player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        teleport(block.getDimension(), block.getPosition());
    }

    private void teleport(Dimension dimension, Vector3ic fromPos) {
        var random = ThreadLocalRandom.current();
        var minY = dimension.getDimensionInfo().minHeight();
        var maxY = dimension.getDimensionInfo().maxHeight();

        for (int i = 0; i < MAX_TELEPORT_ATTEMPTS; i++) {
            int x = fromPos.x() + random.nextInt(-TELEPORT_RANGE_XZ, TELEPORT_RANGE_XZ);
            int y = fromPos.y() + random.nextInt(0, TELEPORT_RANGE_Y);
            int z = fromPos.z() + random.nextInt(-TELEPORT_RANGE_XZ, TELEPORT_RANGE_XZ);

            if (y < minY || y > maxY) continue;

            var targetState = dimension.getBlockState(x, y, z);
            if (targetState.getBlockType() != BlockTypes.AIR) continue;

            var diff = new Vector3i(fromPos.x() - x, fromPos.y() - y, fromPos.z() - z);
            dimension.addParticle(fromPos, new DragonEggTeleportParticle(diff));
            dimension.setBlockState(fromPos, BlockTypes.AIR.getDefaultState());
            dimension.setBlockState(x, y, z, blockType.getDefaultState());
            return;
        }
    }
}
