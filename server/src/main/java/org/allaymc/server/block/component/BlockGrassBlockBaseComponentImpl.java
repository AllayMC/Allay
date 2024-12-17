package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.eventbus.event.block.BlockSpreadEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class BlockGrassBlockBaseComponentImpl extends BlockBaseComponentImpl {

    public static final int MINIMUM_LIGHT_LEVEL_FOR_SPREADING = 9;

    public BlockGrassBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.DIRT.createItemStack(1));
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos blockStateWithPos) {
        var pos = blockStateWithPos.pos();
        var dimension = pos.dimension();

        var upperBlockState = dimension.getBlockState(BlockFace.UP.offsetPos(pos));
        // Grass dies and changes to dirt after a random time (when a random tick lands on the block)
        // if directly covered by any opaque block.
        // Transparent blocks can kill grass in a similar manner,
        // but only if they cause the light level above the grass block to be four or below (like water does),
        // and the surrounding area is not otherwise sufficiently lit up.
        if (upperBlockState.getBlockStateData().lightDampening() > 1) {
            var event = new BlockFadeEvent(blockStateWithPos, BlockTypes.DIRT.getDefaultState());
            if (event.call()) {
                dimension.setBlockState(pos, event.getNewBlockState());
            }
            return;
        }

        // Grass can spread to nearby dirt blocks.
        // Grass spreading without player intervention depends heavily on the time of day.
        // For a dirt block to accept grass from a nearby grass block, the following requirements must be met:

        // The source block must have a light level of 9 or brighter directly above it.
        if (dimension.getLightService().getInternalLight(pos.x(), pos.y() + 1, pos.z()) >= MINIMUM_LIGHT_LEVEL_FOR_SPREADING) {
            // The dirt block receiving grass must be within a 3×5×3 range of the source block
            // where the source block is in the center of the second topmost layer of that range.
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int x = random.nextInt(pos.x() - 1, pos.x() + 1 + 1);
            int y = random.nextInt(pos.y() - 3, pos.y() + 1 + 1);
            int z = random.nextInt(pos.z() - 1, pos.z() + 1 + 1);
            var blockState = dimension.getBlockState(x, y, z);
            if (blockState.getBlockType() == BlockTypes.DIRT &&
                // The dirt block must have a light level of at least 4 above it.
                dimension.getLightService().getInternalLight(x, y + 1, z) >= 4 &&
                // Any block directly above the dirt block must not reduce light by 2 levels or more.
                dimension.getBlockState(x, y + 1, z).getBlockStateData().lightDampening() < 2) {
                var spreadBlockState = new BlockStateWithPos(BlockTypes.GRASS_BLOCK.getDefaultState(), new Position3i(x, y, z, dimension), 0);
                var event = new BlockSpreadEvent(blockStateWithPos, spreadBlockState);
                if (event.call()) {
                    dimension.setBlockState(x, y, z, event.getSpreadBlockState().blockState());
                }
            }
        }
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }
}
