package org.allaymc.server.block.component.plant;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.interfaces.BlockTerracottaBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.type.BlockTypes.*;

/**
 * {@code BlockDeadbushBaseComponentImpl} implements dead-bush support and tool-sensitive
 * drops. Lower-face changes break a bush outside its accepted sand, soil, moss, or terracotta
 * support set. Shears retain the inherited plant drop, while ordinary tools receive a random
 * stick stack whose count may be zero.
 *
 * @author IWareQ
 */
public class BlockDeadbushBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the deadbush behavior component for the supplied block type.
     */
    public BlockDeadbushBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Dispatches the inherited neighbor hook and breaks a bush after invalid lower support
     * changes. Other faces leave this local support test untouched, and the current lower
     * state determines its eligibility.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN && !canBeSupportedAt(neighbor.getBlockState())) {
            block.breakBlock();
        }
    }

    /**
     * Returns whether the state belongs to terracotta behavior or the accepted ground set.
     * Sand, red sand, dirt, podzol, mud, grass, mycelium, and moss all qualify for dead-bush
     * support.
     */
    private boolean canBeSupportedAt(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockState.getBehavior() instanceof BlockTerracottaBehavior ||
               blockType == SAND ||
               blockType == RED_SAND ||
               blockType == DIRT ||
               blockType == PODZOL ||
               blockType == MUD ||
               blockType == GRASS_BLOCK ||
               blockType == MYCELIUM ||
               blockType == MOSS_BLOCK;
    }

    /**
     * Returns the inherited bush drop with shears and otherwise a stick stack of zero through
     * two items. A {@code null} tool uses the ordinary stick path; this method can return a
     * zero-count stack.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            return super.getDrops(block, usedItem, entity);
        }

        return Set.of(ItemTypes.STICK.createItemStack(ThreadLocalRandom.current().nextInt(0, 3)));
    }
}
