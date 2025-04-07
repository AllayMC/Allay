package org.allaymc.server.block.component;

import it.unimi.dsi.fastutil.longs.Long2LongMap;
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.FortuneDropHelper;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class BlockLeavesBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final BlockFace[] LOG_FINDING_ORDER = new BlockFace[]{
            BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST, BlockFace.DOWN, BlockFace.UP
    };

    // Can be null
    protected final ItemType<?> saplingType;
    protected final boolean canDropApple;
    protected final boolean dropMoreSaplings;

    public BlockLeavesBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemType<?> saplingType, boolean canDropApple, boolean dropMoreSaplings) {
        super(blockType);
        this.saplingType = saplingType;
        this.canDropApple = canDropApple;
        this.dropMoreSaplings = dropMoreSaplings;
    }

    protected static long hashBlockXYZ(int x, int y, int z) {
        return ((long) y << 52) + (((long) z & 0x3ffffff) << 26) + ((long) x & 0x3ffffff);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);
        onNeighborOrScheduledUpdate(current);
    }

    @Override
    public void onScheduledUpdate(BlockStateWithPos blockStateWithPos) {
        onNeighborOrScheduledUpdate(blockStateWithPos);
    }

    protected void onNeighborOrScheduledUpdate(BlockStateWithPos blockStateWithPos) {
        var blockState = blockStateWithPos.blockState();
        var pos = blockStateWithPos.pos();

        if (!blockState.getPropertyValue(BlockPropertyTypes.UPDATE_BIT)) {
            blockState = blockState.withPropertyValue(BlockPropertyTypes.UPDATE_BIT, true);
            pos.dimension().setBlockState(pos, blockState, 0, true, false, false);
        }

        // Slowly propagates the need to update instead of peaking down the TPS for huge trees
        for (var face : BlockFace.values()) {
            var offsetPos = face.offsetPos(pos);
            var sideBlockState = pos.dimension().getBlockState(offsetPos);
            if (sideBlockState.getBlockType().hasBlockTag(BlockCustomTags.LEAVES)) {
                if (!sideBlockState.getPropertyValue(BlockPropertyTypes.UPDATE_BIT)) {
                    pos.dimension().getBlockUpdateService().scheduleBlockUpdateInDelay(offsetPos, 2);
                }
            }
        }
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos blockStateWithPos) {
        super.onRandomUpdate(blockStateWithPos);
        var blockState = blockStateWithPos.blockState();
        if (!blockState.getPropertyValue(BlockPropertyTypes.UPDATE_BIT)) {
            return;
        }

        var pos = blockStateWithPos.pos();
        if (blockState.getPropertyValue(BlockPropertyTypes.PERSISTENT_BIT) || findLog(blockStateWithPos, 7, null)) {
            blockState = blockState.withPropertyValue(BlockPropertyTypes.UPDATE_BIT, false);
            pos.dimension().setBlockState(pos, blockState, 0, true, false, false);
        } else {
            if (new BlockFadeEvent(blockStateWithPos, BlockTypes.AIR.getDefaultState()).call()) {
                pos.dimension().breakBlock(pos, null, null);
            }
        }
    }

    protected boolean findLog(BlockStateWithPos currentBlockStateWithPos, int distance, Long2LongMap visited) {
        var blockState = currentBlockStateWithPos.blockState();
        var pos = currentBlockStateWithPos.pos();

        if (visited == null) {
            visited = new Long2LongOpenHashMap();
            visited.defaultReturnValue(-1);
        }

        if (blockState.getBlockType().hasBlockTag(BlockCustomTags.LEAVES_SUPPORTER)) {
            return true;
        }
        if (distance == 0 || !blockState.getBlockType().hasBlockTag(BlockCustomTags.LEAVES)) {
            return false;
        }

        long hash = hashBlockXYZ(pos.x(), pos.y(), pos.z());
        if (visited.get(hash) >= distance) {
            return false;
        }

        visited.put(hash, distance);
        for (BlockFace face : LOG_FINDING_ORDER) {
            var offsetPos = face.offsetPos(pos);
            var nextBlockStateWithPos = new BlockStateWithPos(
                    pos.dimension().getBlockState(offsetPos),
                    new Position3i(offsetPos, pos.dimension()),
                    0
            );
            if (findLog(nextBlockStateWithPos, distance - 1, visited)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            return super.getDrops(blockState, usedItem, entity);
        }

        Set<ItemStack> drops = new HashSet<>();
        if (canDropApple && FortuneDropHelper.bonusChanceDivisor(usedItem, 200, 20)) {
            drops.add(ItemTypes.APPLE.createItemStack());
        }

        if (FortuneDropHelper.bonusChanceDivisor(usedItem, 50, 5)) {
            drops.add(ItemTypes.STICK.createItemStack(ThreadLocalRandom.current().nextInt(1, 2)));
        }

        if (saplingType != null && FortuneDropHelper.bonusChanceDivisor(usedItem, dropMoreSaplings ? 40 : 20, 4)) {
            drops.add(saplingType.createItemStack());
        }

        return drops;
    }
}
