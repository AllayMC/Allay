package org.allaymc.server.block.component.ore;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.server.block.FortuneDropHelper;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.item.data.ItemId;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * @author IWareQ
 */
public class BlockOreBaseComponentImpl extends BlockBaseComponentImpl {
    private final Supplier<ItemType<?>> dropItemType;
    private final int minDropItem, maxDropItem;
    private final int minDropXp, maxDropXp;

    public BlockOreBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<ItemType<?>> dropItemTypeSupplier, int minDropItem, int maxDropItem, int minDropXp, int maxDropXp) {
        super(blockType);
        Preconditions.checkArgument(maxDropItem >= minDropItem, "Maximum drop amount must be greater than or equal to minimum drop amount");
        Preconditions.checkArgument(maxDropXp >= minDropXp, "Maximum XP drop amount must be greater than or equal to minimum XP drop amount");
        this.dropItemType = dropItemTypeSupplier;
        this.minDropItem = minDropItem;
        this.maxDropItem = maxDropItem;
        this.minDropXp = minDropXp;
        this.maxDropXp = maxDropXp;
    }

    public BlockOreBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId dropItem, int minDropItem, int maxDropItem, int minDropXp, int maxDropXp) {
        this(blockType, dropItem::getItemType, minDropItem, maxDropItem, minDropXp, maxDropXp);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(this.dropItemType.get().createItemStack(FortuneDropHelper.weighted(usedItem, minDropItem, maxDropItem)));
    }

    @Override
    public int getDropXpAmount(Block block, ItemStack usedItem, Entity entity) {
        if (maxDropXp == 0) {
            return 0;
        }

        return ThreadLocalRandom.current().nextInt(minDropXp, maxDropXp + 1);
    }
}
