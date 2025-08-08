package org.allaymc.server.block.component.ore;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.FortuneDropHelper;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author IWareQ
 */
public class BlockOreBaseComponentImpl extends BlockBaseComponentImpl {
    private final ItemId dropItem;
    private final int minDropItem, maxDropItem;
    private final int minDropXp, maxDropXp;

    public BlockOreBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId dropItem, int minDropItem, int maxDropItem, int minDropXp, int maxDropXp) {
        super(blockType);
        Preconditions.checkArgument(maxDropItem >= minDropItem, "Maximum drop amount must be greater than or equal to minimum drop amount");
        Preconditions.checkArgument(maxDropXp >= minDropXp, "Maximum XP drop amount must be greater than or equal to minimum XP drop amount");
        this.dropItem = dropItem;
        this.minDropItem = minDropItem;
        this.maxDropItem = maxDropItem;
        this.minDropXp = minDropXp;
        this.maxDropXp = maxDropXp;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(this.dropItem.getItemType().createItemStack(FortuneDropHelper.weighted(usedItem, minDropItem, maxDropItem)));
    }

    @Override
    public int getDropXpAmount(Block block, ItemStack usedItem, Entity entity) {
        if (maxDropXp == 0) {
            return 0;
        }

        return ThreadLocalRandom.current().nextInt(minDropXp, maxDropXp + 1);
    }
}
