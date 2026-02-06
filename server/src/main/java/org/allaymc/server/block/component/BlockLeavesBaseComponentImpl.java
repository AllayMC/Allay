package org.allaymc.server.block.component;

import it.unimi.dsi.fastutil.longs.Long2LongMap;
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.NeighborUpdate;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.block.FortuneDropHelper;
import org.allaymc.server.item.data.ItemId;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.UPDATE_BIT;
import org.allaymc.api.block.type.BlockState;

/**
 * @author daoge_cmd
 */
public class BlockLeavesBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final BlockFace[] LOG_FINDING_ORDER = new BlockFace[]{
            BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST, BlockFace.DOWN, BlockFace.UP
    };

    protected final ItemId saplingId; // Nullable
    protected final boolean canDropApple;
    protected final boolean dropMoreSaplings;

    public BlockLeavesBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId saplingId, boolean canDropApple, boolean dropMoreSaplings) {
        super(blockType);
        this.saplingId = saplingId;
        this.canDropApple = canDropApple;
        this.dropMoreSaplings = dropMoreSaplings;
    }

    protected static long hashBlockXYZ(int x, int y, int z) {
        return ((long) y << 52) + (((long) z & 0x3ffffff) << 26) + ((long) x & 0x3ffffff);
    }

    @Override
    public void onNeighborUpdate(NeighborUpdate context) {
        super.onNeighborUpdate(context);
        var block = context.block();
        onNeighborOrScheduledUpdate(block);
    }

    @Override
    public void onScheduledUpdate(Block block) {
        onNeighborOrScheduledUpdate(block);
    }

    protected void onNeighborOrScheduledUpdate(Block current) {
        var pos = current.getPosition();
        if (!current.getPropertyValue(UPDATE_BIT)) {
            current = current.setPropertyValue(UPDATE_BIT, true);
            pos.dimension().setBlockState(pos, current.getBlockState(), 0, true, false, false);
        }

        // Slowly propagates the need to update instead of peaking down the TPS for huge trees
        for (var face : BlockFace.VALUES) {
            var sideBlockState = current.offsetPos(face);
            if (sideBlockState.getBlockType().hasBlockTag(BlockTags.LEAVES)) {
                if (!sideBlockState.getPropertyValue(UPDATE_BIT)) {
                    pos.dimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(sideBlockState.getPosition(), 2);
                }
            }
        }
    }

    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);
        if (!block.getPropertyValue(UPDATE_BIT)) {
            return;
        }

        var pos = block.getPosition();
        if (block.getPropertyValue(BlockPropertyTypes.PERSISTENT_BIT) || findLog(block, 7, null)) {
            block = block.setPropertyValue(UPDATE_BIT, false);
            pos.dimension().setBlockState(pos, block.getBlockState(), 0, true, false, false);
        } else {
            if (new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState()).call()) {
                block.breakBlock();
            }
        }
    }

    protected boolean findLog(Block current, int distance, Long2LongMap visited) {
        if (visited == null) {
            visited = new Long2LongOpenHashMap();
            visited.defaultReturnValue(-1);
        }

        if (current.getBlockType().hasBlockTag(BlockTags.LEAVES_SUPPORTER)) {
            return true;
        }
        if (distance == 0 || !current.getBlockType().hasBlockTag(BlockTags.LEAVES)) {
            return false;
        }

        var pos = current.getPosition();
        long hash = hashBlockXYZ(pos.x(), pos.y(), pos.z());
        if (visited.get(hash) >= distance) {
            return false;
        }

        visited.put(hash, distance);
        for (BlockFace face : LOG_FINDING_ORDER) {
            var nextBlockStateWithPos = current.offsetPos(face);
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
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            return super.getDrops(block, usedItem, entity);
        }

        Set<ItemStack> drops = new HashSet<>();
        if (canDropApple && FortuneDropHelper.bonusChanceDivisor(usedItem, 200, 20)) {
            drops.add(ItemTypes.APPLE.createItemStack());
        }

        if (FortuneDropHelper.bonusChanceDivisor(usedItem, 50, 5)) {
            drops.add(ItemTypes.STICK.createItemStack(ThreadLocalRandom.current().nextInt(1, 3)));
        }

        if (saplingId != null && FortuneDropHelper.bonusChanceDivisor(usedItem, dropMoreSaplings ? 40 : 20, 4)) {
            drops.add(saplingId.getItemType().createItemStack());
        }

        return drops;
    }
}
