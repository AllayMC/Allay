package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockInfestedBlockBaseComponent;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.gamerule.GameRule;

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
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        var dimension = blockState.dimension();
        var pos = blockState.pos();
        if (dimension.getWorld().getWorldData().getGameRuleValue(GameRule.DO_TILE_DROPS)) {
            dimension.getEntityService().addEntity(EntityTypes.SILVERFISH.createEntity(
                    EntityInitInfo.builder()
                            .dimension(dimension)
                            .pos(pos.x() + 0.5f, pos.y(), pos.z() + 0.5f)
                            .build()
            ));
        }

        return Utils.EMPTY_ITEM_STACK_SET;
    }

    @Override
    public ItemStack getSilkTouchDrop(BlockStateWithPos blockState) {
        return getImitatedBlock().getItemType().createItemStack();
    }

    @Override
    public BlockType<?> getImitatedBlock() {
        return imitatedBlockId.getBlockType();
    }
}
