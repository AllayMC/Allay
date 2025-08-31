package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockInfestedBlockBaseComponent;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.gamerule.GameRule;

import java.util.Collections;
import java.util.Set;

/**
 * @author IWareQ
 */
public class BlockInfestedBlockBaseComponentImpl extends BlockBaseComponentImpl implements BlockInfestedBlockBaseComponent {
    protected final BlockId imitatedBlockId;

    public BlockInfestedBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId imitatedBlockId) {
        super(blockType);
        this.imitatedBlockId = imitatedBlockId;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        if (dimension.getWorld().getWorldData().getGameRuleValue(GameRule.DO_TILE_DROPS)) {
            dimension.getEntityService().addEntity(EntityTypes.SILVERFISH.createEntity(
                    EntityInitInfo.builder()
                            .dimension(dimension)
                            .pos(pos.x() + 0.5f, pos.y(), pos.z() + 0.5f)
                            .build()
            ));
        }

        return Collections.emptySet();
    }

    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return getImitatedBlock().getItemType().createItemStack();
    }

    @Override
    public BlockType<?> getImitatedBlock() {
        return imitatedBlockId.getBlockType();
    }
}
