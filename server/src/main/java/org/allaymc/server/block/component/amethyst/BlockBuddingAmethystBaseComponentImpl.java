package org.allaymc.server.block.component.amethyst;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.enums.MinecraftBlockFace;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MINECRAFT_BLOCK_FACE;

/**
 * {@code BlockBuddingAmethystBaseComponentImpl} grows adjacent amethyst buds during
 * random updates and suppresses all break drops. Growth can create a small bud in air
 * or advance existing buds toward a cluster, while bounded retries choose another
 * random face when a neighbor cannot grow.
 *
 * @author daoge_cmd
 */
public class BlockBuddingAmethystBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code MAX_GROW_ATTEMPTS} bounds recursive random-face retries when surrounding
     * blocks cannot accept a new bud or growth stage.
     */
    private static final int MAX_GROW_ATTEMPTS = 7;

    /**
     * Creates budding amethyst base behavior for the supplied block type.
     */
    public BlockBuddingAmethystBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns whether budding amethyst receives random updates for bud growth.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Forwards the random update and attempts growth on one fifth of ticks. A
     * selected attempt begins at retry index zero and can inspect additional random
     * faces when blocked.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var random = ThreadLocalRandom.current();
        // One of five equiprobable outcomes admits a growth attempt.
        if (random.nextInt(5) != 0) {
            return;
        }

        tryGrow(block, 0);
    }

    /**
     * Attempts a random adjacent growth stage until the retry limit is reached. Air
     * accepts a small bud, existing buds advance toward clusters, and unrelated
     * blocks retry without guaranteeing that each face is distinct.
     */
    private void tryGrow(Block block, int attempt) {
        if (attempt >= MAX_GROW_ATTEMPTS) {
            return;
        }

        var random = ThreadLocalRandom.current();
        var face = BlockFace.values()[random.nextInt(BlockFace.VALUES.length)];
        var neighbor = block.offsetPos(face);
        var neighborType = neighbor.getBlockState().getBlockType();
        var dimension = block.getDimension();
        var neighborPos = face.offsetPos(block.getPosition());
        // The two face enums share down-up-north-south-west-east declaration order.
        // Preserve that alignment so a randomly selected neighbor receives the
        // matching bud attachment direction.
        var minecraftFace = MinecraftBlockFace.values()[face.ordinal()];

        if (neighbor.isAir()) {
            var newState = BlockTypes.SMALL_AMETHYST_BUD.getDefaultState()
                    .setPropertyValue(MINECRAFT_BLOCK_FACE, minecraftFace);
            dimension.setBlockState(neighborPos, newState);
        } else if (neighborType == BlockTypes.SMALL_AMETHYST_BUD) {
            var newState = BlockTypes.MEDIUM_AMETHYST_BUD.getDefaultState()
                    .setPropertyValue(MINECRAFT_BLOCK_FACE, minecraftFace);
            dimension.setBlockState(neighborPos, newState);
        } else if (neighborType == BlockTypes.MEDIUM_AMETHYST_BUD) {
            var newState = BlockTypes.LARGE_AMETHYST_BUD.getDefaultState()
                    .setPropertyValue(MINECRAFT_BLOCK_FACE, minecraftFace);
            dimension.setBlockState(neighborPos, newState);
        } else if (neighborType == BlockTypes.LARGE_AMETHYST_BUD) {
            var newState = BlockTypes.AMETHYST_CLUSTER.getDefaultState()
                    .setPropertyValue(MINECRAFT_BLOCK_FACE, minecraftFace);
            dimension.setBlockState(neighborPos, newState);
        } else {
            // The selected neighbor cannot advance along the bud growth sequence.
            // Retry another random face until the fixed attempt limit stops the
            // search.
            tryGrow(block, attempt + 1);
        }
    }

    /**
     * Suppresses every item and experience drop from budding amethyst breaks. This
     * override bypasses inherited break handling, so silk touch also produces no
     * block item through this path.
     */
    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
    }
}
