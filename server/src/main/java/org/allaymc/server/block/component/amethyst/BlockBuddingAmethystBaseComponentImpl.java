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
 * Component for budding amethyst block.
 * Handles random tick growth of amethyst buds and prevents silk touch drops.
 *
 * @author daoge_cmd
 */
public class BlockBuddingAmethystBaseComponentImpl extends BlockBaseComponentImpl {

    private static final int MAX_GROW_ATTEMPTS = 7;

    public BlockBuddingAmethystBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var random = ThreadLocalRandom.current();
        // 20% chance to attempt growing a bud
        if (random.nextInt(5) != 0) {
            return;
        }

        tryGrow(block, 0);
    }

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
        var minecraftFace = MinecraftBlockFace.values()[face.ordinal()];

        if (neighbor.isAir()) {
            // Place a small amethyst bud on the empty face
            var newState = BlockTypes.SMALL_AMETHYST_BUD.getDefaultState()
                    .setPropertyValue(MINECRAFT_BLOCK_FACE, minecraftFace);
            dimension.setBlockState(neighborPos, newState);
        } else if (neighborType == BlockTypes.SMALL_AMETHYST_BUD) {
            // Upgrade small bud to medium bud
            var newState = BlockTypes.MEDIUM_AMETHYST_BUD.getDefaultState()
                    .setPropertyValue(MINECRAFT_BLOCK_FACE, minecraftFace);
            dimension.setBlockState(neighborPos, newState);
        } else if (neighborType == BlockTypes.MEDIUM_AMETHYST_BUD) {
            // Upgrade medium bud to large bud
            var newState = BlockTypes.LARGE_AMETHYST_BUD.getDefaultState()
                    .setPropertyValue(MINECRAFT_BLOCK_FACE, minecraftFace);
            dimension.setBlockState(neighborPos, newState);
        } else if (neighborType == BlockTypes.LARGE_AMETHYST_BUD) {
            // Upgrade large bud to amethyst cluster
            var newState = BlockTypes.AMETHYST_CLUSTER.getDefaultState()
                    .setPropertyValue(MINECRAFT_BLOCK_FACE, minecraftFace);
            dimension.setBlockState(neighborPos, newState);
        } else {
            // Face is blocked, retry with another random face
            tryGrow(block, attempt + 1);
        }
    }

    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        // Budding amethyst drops nothing, not even with silk touch
    }
}
