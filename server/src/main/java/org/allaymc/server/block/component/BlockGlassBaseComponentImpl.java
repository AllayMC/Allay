package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;

import java.util.Collections;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public class BlockGlassBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockGlassBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos current, ItemStack usedItem, Entity entity) {
        return Collections.emptySet();
    }
}
