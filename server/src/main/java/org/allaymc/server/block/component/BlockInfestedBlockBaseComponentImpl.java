package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockInfestedBlockBaseComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.block.data.BlockId;

import java.util.Collections;
import java.util.Set;
import java.util.function.Supplier;

/**
 * {@code BlockInfestedBlockBaseComponentImpl} selects infested block break results
 * and lazily resolves the ordinary block it imitates. Ordinary drop lookup can spawn
 * a silverfish when tile drops are enabled, while silk touch selects the imitated
 * block item instead of spawning through that lookup.
 *
 * @author IWareQ
 */
public class BlockInfestedBlockBaseComponentImpl extends BlockBaseComponentImpl implements BlockInfestedBlockBaseComponent {
    /**
     * {@code imitatedBlockType} is retained for deferred counterpart lookup and is
     * evaluated whenever {@code getImitatedBlock} resolves the normal block type.
     */
    protected final Supplier<BlockType<?>> imitatedBlockType;

    /**
     * Creates infested block behavior with a deferred imitated-type supplier. The
     * constructor retains {@code imitatedBlockTypeSupplier}, and
     * {@code getImitatedBlock} evaluates it when drops or other callers need the
     * ordinary counterpart.
     */
    public BlockInfestedBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<BlockType<?>> imitatedBlockTypeSupplier) {
        super(blockType);
        this.imitatedBlockType = imitatedBlockTypeSupplier;
    }

    /**
     * Creates infested block behavior that resolves its ordinary counterpart from a
     * block identifier. {@code imitatedBlockId} supplies a deferred lookup rather
     * than requiring its block type during construction.
     */
    public BlockInfestedBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId imitatedBlockId) {
        this(blockType, imitatedBlockId::getBlockType);
    }

    /**
     * Returns no ordinary items and spawns a silverfish when
     * {@code GameRule.DO_TILE_DROPS} is enabled. Calling this lookup therefore has an
     * entity-spawn side effect rather than only computing a drop set.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        if (dimension.getWorld().getWorldData().getGameRuleValue(GameRule.DO_TILE_DROPS)) {
            dimension.getEntityManager().addEntity(EntityTypes.SILVERFISH.createEntity(
                    EntityInitInfo.builder()
                            .dimension(dimension)
                            .pos(pos.x() + 0.5f, pos.y(), pos.z() + 0.5f)
                            .build()
            ));
        }

        return Collections.emptySet();
    }

    /**
     * Returns the item stack for the block type this infestation imitates.
     */
    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return getImitatedBlock().getItemType().createItemStack();
    }

    /**
     * Returns the imitated block type resolved by the configured supplier.
     */
    @Override
    public BlockType<?> getImitatedBlock() {
        return imitatedBlockType.get();
    }
}
