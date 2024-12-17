package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.type.EnchantmentTypes;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class BlockLeavesBaseComponentImpl extends BlockBaseComponentImpl {

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
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        if (usedItem.getItemType() == ItemTypes.SHEARS) {
            return super.getDrops(blockState, usedItem, entity);
        }

        Set<ItemStack> drops = new HashSet<>();
        int fortune = usedItem.getEnchantmentLevel(EnchantmentTypes.FORTUNE);
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
}
