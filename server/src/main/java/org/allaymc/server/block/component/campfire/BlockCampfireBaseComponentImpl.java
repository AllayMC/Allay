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
 * {@code BlockCampfireBaseComponentImpl} implements campfire interaction, contact damage, and
 * comparator output. The extinguished property selects between relighting and lit behavior;
 * lit campfires accept food or shovel extinguishing, and water above can also extinguish them.
 *
 * <p>
 * Food storage belongs to the injected campfire block entity, while the block component
 * consumes accepted held items and emits interaction sounds. Comparator strength depends on
 * occupied cooking slots rather than stack fullness and uses a ceiling across the
 * fifteen-level signal range. Contact attacks apply only to living entities without fire
 * resistance and a lit campfire; normal entity damage handling still determines whether an
 * attack takes effect.
 *
 * @author daoge_cmd
 */
public class BlockCampfireBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * {@code blockEntityHolderComponent} supplies the cooking entity for food insertion and
     * comparator reads. Component initialization injects it before these block callbacks run.
     */
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityCampfire> blockEntityHolderComponent;

    /**
     * Creates the campfire behavior component for the supplied block type.
     */
    public BlockCampfireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns two charcoal items for ordinary campfire breaking without silk touch.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.CHARCOAL.createItemStack(2));
    }

    /**
     * Attempts one point of campfire damage against a colliding living entity. An extinguished
     * campfire or fire-resistance effect suppresses the attack; this hook does not impose its
     * own repeated-contact cooldown.
     */
    @Override
    public void onCollideWithEntity(Block block, Entity entity) {
        if (isExtinguished(block.getBlockState())) {
            return;
        }

        if (entity instanceof EntityLiving living) {
            if (living.hasEffect(EffectTypes.FIRE_RESISTANCE)) {
                return;
            }
            living.attack(DamageContainer.campfire(1f));
        }
    }

    /**
     * Relights an extinguished campfire or handles shovel and food interactions on a lit
     * campfire. Ignition and shovel use each attempt one point of item damage. Accepted food
     * is consumed only after the block entity adds it, and each successful action emits its
     * corresponding sound.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        var campfire = blockEntityHolderComponent.getBlockEntity(pos.x(), pos.y(), pos.z(), dimension);

        if (isExtinguished(blockState)) {
            if (canIgnite(itemStack)) {
                dimension.updateBlockProperty(BlockPropertyTypes.EXTINGUISHED, false, pos);
                dimension.addSound(MathUtils.center(pos), SimpleSound.IGNITE);
                itemStack.tryIncreaseDamage(1);
                return true;
            }
        } else {
            if (itemStack != null && ItemHelper.isShovel(itemStack.getItemType())) {
                dimension.updateBlockProperty(BlockPropertyTypes.EXTINGUISHED, true, pos);
                dimension.addSound(MathUtils.center(pos), SimpleSound.FIRE_EXTINGUISH);
                itemStack.tryIncreaseDamage(1);
                return true;
            }

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

    /**
     * Dispatches the base neighbor hook and extinguishes a lit campfire when water becomes its
     * upper neighbor. The local check recognizes stationary and flowing water and emits an
     * extinguishing sound after updating the property.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        var blockState = block.getBlockState();

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

    /**
     * Returns the state's extinguished property used by campfire behavior checks.
     */
    protected boolean isExtinguished(BlockState blockState) {
        return blockState.getPropertyValue(BlockPropertyTypes.EXTINGUISHED);
    }

    /**
     * Returns whether an item can relight an extinguished campfire. Flint and steel qualifies
     * directly, as does any item carrying Fire Aspect; a {@code null} item cannot ignite it.
     */
    protected boolean canIgnite(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        if (itemStack.getItemType() == ItemTypes.FLINT_AND_STEEL) {
            return true;
        }
        return itemStack.hasEnchantment(EnchantmentTypes.FIRE_ASPECT);
    }

    /**
     * Returns whether campfires supply comparator strength from their cooking slots.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns comparator strength derived from the campfire's occupied cooking slots. A
     * missing block entity produces {@code 0}, and an empty four-slot entity also produces
     * {@code 0}.
     *
     * <p>
     * Each slot counts when its item is neither {@code null} nor the canonical air stack;
     * stack counts do not affect this calculation. Nonempty occupancy maps to the ceiling of
     * its fraction of four slots times fifteen, giving strengths four, eight, twelve, and
     * fifteen for one through four occupied slots.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        var campfire = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (campfire == null) {
            return 0;
        }
        int filledSlots = 0;
        for (int i = 0; i < 4; i++) {
            ItemStack item = campfire.getItemStack(i);
            if (item != null && item != ItemAirStack.AIR_STACK) {
                filledSlots++;
            }
        }
        // Round partial occupancy upward so each additional occupied cooking slot
        // raises comparator output; three occupied slots therefore produce twelve, not eleven.
        if (filledSlots == 0) {
            return 0;
        }
        return (int) Math.ceil((double) filledSlots / 4.0 * 15.0);
    }
}
