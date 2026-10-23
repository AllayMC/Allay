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
 * {@code BlockOreBaseComponentImpl} selects configurable ore items and experience
 * counts for ordinary breaks. The item type is resolved lazily, item quantities use
 * the weighted Fortune distribution, and experience is sampled independently from an
 * inclusive range unless its configured maximum is zero.
 *
 * @author IWareQ
 */
public class BlockOreBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * {@code dropItemType} is retained for lazy item lookup and evaluated whenever
     * ordinary ore drops are selected.
     */
    private final Supplier<ItemType<?>> dropItemType;
    /**
     * {@code minDropItem} is the minimum base item count, and {@code maxDropItem} is
     * the initial inclusive maximum. The constructor validates their ordering before
     * weighted Fortune selection can expand only the upper bound.
     */
    private final int minDropItem, maxDropItem;
    /**
     * {@code minDropXp} is the minimum experience-point count, and {@code maxDropXp}
     * is its inclusive maximum. The constructor validates their ordering, while a
     * zero maximum suppresses experience selection without sampling either bound.
     */
    private final int minDropXp, maxDropXp;

    /**
     * Creates ore behavior with deferred item lookup and ordered item and experience
     * bounds. Both maximum values must be at least their matching minimum; the
     * supplier is retained for later ordinary drop selection.
     *
     * @throws IllegalArgumentException if either maximum is smaller than its corresponding minimum
     */
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

    /**
     * Creates ore behavior with a deferred item-identifier lookup and the supplied
     * quantity bounds. The supplier constructor validates the ordering of both ranges
     * before later drop operations evaluate the item type.
     */
    public BlockOreBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId dropItem, int minDropItem, int maxDropItem, int minDropXp, int maxDropXp) {
        this(blockType, dropItem::getItemType, minDropItem, maxDropItem, minDropXp, maxDropXp);
    }

    /**
     * Returns the configured ore item with a weighted Fortune-adjusted count. A
     * {@code null} tool selects the base distribution, and the retained supplier
     * resolves the item type for each lookup.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(this.dropItemType.get().createItemStack(FortuneDropHelper.weighted(usedItem, minDropItem, maxDropItem)));
    }

    /**
     * Returns an inclusive random experience count unless the configured maximum is
     * zero. A zero maximum returns {@code 0} without sampling, and this selection
     * does not consult the tool or entity.
     */
    @Override
    public int getDropXpAmount(Block block, ItemStack usedItem, Entity entity) {
        if (maxDropXp == 0) {
            return 0;
        }

        // The exclusive random bound adds one to include the configured maximum.
        return ThreadLocalRandom.current().nextInt(minDropXp, maxDropXp + 1);
    }
}
