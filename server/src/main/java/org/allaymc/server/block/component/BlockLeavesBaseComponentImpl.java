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
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.type.ItemTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.UPDATE_BIT;

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
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);
        onNeighborOrScheduledUpdate(current);
    }

    @Override
    public void onScheduledUpdate(BlockStateWithPos current) {
        onNeighborOrScheduledUpdate(current);
    }

    protected void onNeighborOrScheduledUpdate(BlockStateWithPos current) {
        var pos = current.getPos();
        if (!current.getPropertyValue(UPDATE_BIT)) {
            current = current.setPropertyValue(UPDATE_BIT, true);
            pos.dimension().setBlockState(pos, current, 0, true, false, false);
        }

        // Slowly propagates the need to update instead of peaking down the TPS for huge trees
        for (var face : BlockFace.values()) {
            var sideBlockState = current.offsetPos(face);
            if (sideBlockState.getBlockType().hasBlockTag(BlockCustomTags.LEAVES)) {
                if (!sideBlockState.getPropertyValue(UPDATE_BIT)) {
                    pos.dimension().getBlockUpdateService().scheduleBlockUpdateInDelay(sideBlockState.getPos(), 2);
                }
            }
        }
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos current) {
        super.onRandomUpdate(current);
        if (!current.getPropertyValue(UPDATE_BIT)) {
            return;
        }

        var pos = current.getPos();
        if (current.getPropertyValue(BlockPropertyTypes.PERSISTENT_BIT) || findLog(current, 7, null)) {
            current = current.setPropertyValue(UPDATE_BIT, false);
            pos.dimension().setBlockState(pos, current, 0, true, false, false);
        } else {
            if (new BlockFadeEvent(current, BlockTypes.AIR.getDefaultState()).call()) {
                current.breakBlock();
            }
        }
    }

    protected boolean findLog(BlockStateWithPos current, int distance, Long2LongMap visited) {
        if (visited == null) {
            visited = new Long2LongOpenHashMap();
            visited.defaultReturnValue(-1);
        }

        if (current.getBlockType().hasBlockTag(BlockCustomTags.LEAVES_SUPPORTER)) {
            return true;
        }
        if (distance == 0 || !current.getBlockType().hasBlockTag(BlockCustomTags.LEAVES)) {
            return false;
        }

        var pos = current.getPos();
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
    public Set<ItemStack> getDrops(BlockStateWithPos current, ItemStack usedItem, Entity entity) {
        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            return super.getDrops(current, usedItem, entity);
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
