package org.allaymc.server.block.component;

import it.unimi.dsi.fastutil.longs.Long2LongMap;
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.type.EnchantmentTypes;
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

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
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
            blockState = blockState.setProperty(BlockPropertyTypes.UPDATE_BIT, true);
            pos.dimension().setBlockState(pos, blockState, 0, true, false, false);
        }

        // Slowly propagates the need to update instead of peaking down the TPS for huge trees
        for (var face : BlockFace.values()) {
            var offsetPos = face.offsetPos(pos);
            var sideBlockState = pos.dimension().getBlockState(offsetPos);
            if (sideBlockState.getBlockType().hasBlockTag(BlockTags.LEAVES)) {
                if (!sideBlockState.getPropertyValue(BlockPropertyTypes.UPDATE_BIT)) {
                    pos.dimension().getBlockUpdateService().scheduleBlockUpdate(offsetPos, 2);
                }
            }
        }
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos blockStateWithPos) {
        var blockState = blockStateWithPos.blockState();
        if (!blockState.getPropertyValue(BlockPropertyTypes.UPDATE_BIT)) {
            return;
        }

        var pos = blockStateWithPos.pos();
        if (blockState.getPropertyValue(BlockPropertyTypes.PERSISTENT_BIT) || findLog(blockStateWithPos, 7, null)) {
            blockState = blockState.setProperty(BlockPropertyTypes.UPDATE_BIT, false);
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

        if (blockState.getBlockType().hasBlockTag(BlockTags.LEAVES_SUPPORTER)) {
            return true;
        }
        if (distance == 0 || !blockState.getBlockType().hasBlockTag(BlockTags.LEAVES)) {
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
        int fortune = usedItem != null ? usedItem.getEnchantmentLevel(EnchantmentTypes.FORTUNE) : 0;
        int appleOdds;
        int stickOdds;
        int saplingOdds;
        switch (fortune) {
            case 0 -> {
                appleOdds = 200;
                stickOdds = 50;
                saplingOdds = dropMoreSaplings ? 40 : 20;
            }
            case 1 -> {
                appleOdds = 180;
                stickOdds = 45;
                saplingOdds = dropMoreSaplings ? 36 : 16;
            }
            case 2 -> {
                appleOdds = 160;
                stickOdds = 40;
                saplingOdds = dropMoreSaplings ? 32 : 12;
            }
            default -> {
                appleOdds = 120;
                stickOdds = 30;
                saplingOdds = dropMoreSaplings ? 24 : 10;
            }
        }

        ThreadLocalRandom random = ThreadLocalRandom.current();
        if (canDropApple && random.nextInt(appleOdds) == 0) {
            drops.add(ItemTypes.APPLE.createItemStack());
        }
        if (random.nextInt(stickOdds) == 0) {
            drops.add(ItemTypes.STICK.createItemStack());
        }
        if (random.nextInt(saplingOdds) == 0 && saplingType != null) {
            drops.add(saplingType.createItemStack());
        }

        return drops;
    }

    protected static long hashBlockXYZ(int x, int y, int z) {
        return ((long) y << 52) + (((long) z & 0x3ffffff) << 26) + ((long) x & 0x3ffffff);
    }
}
