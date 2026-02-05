package org.allaymc.server.block.component.campfire;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.interfaces.BlockEntityCampfire;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.component.annotation.Dependency;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public class BlockCampfireBaseComponentImpl extends BlockBaseComponentImpl {
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityCampfire> blockEntityHolderComponent;

    public BlockCampfireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        // Campfire drops 2 charcoal when broken
        return Set.of(ItemTypes.CHARCOAL.createItemStack(2));
    }

    @Override
    public void onCollideWithEntity(Block block, Entity entity) {
        if (isExtinguished(block.getBlockState())) {
            return;
        }

        if (entity instanceof EntityLiving living) {
            if (living.hasEffect(EffectTypes.FIRE_RESISTANCE)) {
                return;
            }
            // Campfire deals 1 damage per tick (0.5 hearts)
            living.attack(DamageContainer.campfire(1f));
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        var campfire = blockEntityHolderComponent.getBlockEntity(pos.x(), pos.y(), pos.z(), dimension);

        if (isExtinguished(blockState)) {
            // Try to ignite with flint and steel or fire aspect enchantment
            if (canIgnite(itemStack)) {
                dimension.updateBlockProperty(BlockPropertyTypes.EXTINGUISHED, false, pos);
                dimension.addSound(MathUtils.center(pos), SimpleSound.IGNITE);
                // Damage the item
                itemStack.tryIncreaseDamage(1);
                return true;
            }
        } else {
            // Try to extinguish with shovel
            if (itemStack != null && ItemHelper.isShovel(itemStack.getItemType())) {
                dimension.updateBlockProperty(BlockPropertyTypes.EXTINGUISHED, true, pos);
                dimension.addSound(MathUtils.center(pos), SimpleSound.FIRE_EXTINGUISH);
                // Damage the shovel
                itemStack.tryIncreaseDamage(1);
                return true;
            }

            // Try to add food item
            if (itemStack != null && campfire.canAcceptItemStack(itemStack)) {
                if (campfire.tryAddItemStack(itemStack)) {
                    interactInfo.player().tryConsumeItemInHand();
                    dimension.addSound(MathUtils.center(pos), SimpleSound.ITEM_ADD);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var blockState = block.getBlockState();

        // Check if water is placed above
        if (!isExtinguished(blockState) && face == BlockFace.UP) {
            var neighborState = neighbor.getBlockState();
            if (neighborState.getBlockType() == BlockTypes.WATER || neighborState.getBlockType() == BlockTypes.FLOWING_WATER) {
                var dimension = block.getDimension();
                var pos = block.getPosition();
                dimension.updateBlockProperty(BlockPropertyTypes.EXTINGUISHED, true, pos);
                dimension.addSound(MathUtils.center(pos), SimpleSound.FIRE_EXTINGUISH);
            }
        }
    }

    protected boolean isExtinguished(BlockState blockState) {
        return blockState.getPropertyValue(BlockPropertyTypes.EXTINGUISHED);
    }

    protected boolean canIgnite(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        // Check for flint and steel
        if (itemStack.getItemType() == ItemTypes.FLINT_AND_STEEL) {
            return true;
        }
        // Check for fire aspect enchantment
        return itemStack.hasEnchantment(EnchantmentTypes.FIRE_ASPECT);
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        var campfire = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (campfire == null) {
            return 0;
        }
        // Campfire has 4 slots, calculate signal based on filled slots
        int filledSlots = 0;
        for (int i = 0; i < 4; i++) {
            ItemStack item = campfire.getItemStack(i);
            if (item != null && item != ItemAirStack.AIR_STACK) {
                filledSlots++;
            }
        }
        // Signal = ceil(filledSlots / 4 * 15)
        // 0 items = 0, 1 item = 4, 2 items = 8, 3 items = 11, 4 items = 15
        if (filledSlots == 0) {
            return 0;
        }
        return (int) Math.ceil((double) filledSlots / 4.0 * 15.0);
    }
}
