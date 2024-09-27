package org.allaymc.api.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.type.EnchantmentTypes;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.item.ItemHelper.isSword;

/**
 * @author daoge_cmd
 */
public interface BlockBaseComponent extends BlockComponent {
    /**
     * Get the block type.
     *
     * @return block type.
     */
    BlockType<? extends BlockBehavior> getBlockType();

    /**
     * Check if the block can remain in its current position when a neighboring block updates.
     *
     * @param current  The current block.
     * @param neighbor The neighboring block that is causing the update.
     * @param face     The face of the current block that is being updated.
     *
     * @return {@code true} if the block can remain in its current position, {@code false} if the block should be broken.
     */
    default boolean canKeepExisting(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        return true;
    }

    /**
     * Call when a blockState causes another blockState to update.
     *
     * @param current  The current block.
     * @param neighbor The neighbor block that triggered the update.
     * @param face     The face of the current block that is being updated.
     */
    void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face);

    /**
     * Called when the block encounters random tick update.
     *
     * @param blockState the block.
     */
    void onRandomUpdate(BlockStateWithPos blockState);

    /**
     * Try to place a block.
     *
     * @param dimension     The dimension where the block is placed.
     * @param blockState    The block that is being placed.
     * @param placeBlockPos The pos that the player is trying to place the block on.
     * @param placementInfo The player placement info, can be null.
     *
     * @return {@code true} if the block is placed successfully, {@code false} if failed.
     */
    boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo);

    /**
     * Called when a block is placed.
     *
     * @param currentBlockState The block that is being replaced.
     * @param newBlockState     The block that is replacing the current block.
     * @param placementInfo     The player placement info, can be null.
     */
    void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Check if the block will drop as item when it is broke by the specified entity using the specified item.
     *
     * @param blockState the block being broke.
     * @param usedItem the item used to break the block, can be {@code null}.
     * @param entity the entity who break the block, can be {@code null}.
     * @return {@code true} if the block will drop as item, {@code false} otherwise.
     */
    boolean isDroppable(BlockStateWithPos blockState, ItemStack usedItem, Entity entity);

    /**
     * @param itemStack The item in the player's hand.
     * @param interactInfo The player interaction info.
     *
     * @return Whether the operation is valid.
     *         For example, right-clicking on the crafting table is normally considered a valid operation, so this method will return {@code true}
     *         If {@code false} is returned, the useItemOn method of the player's item will continue to be called
     */
    boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo);

    /**
     * Called when a block is replaced.
     *
     * @param currentBlockState The block that is being replaced.
     * @param newBlockState     The block that is replacing the current block.
     * @param placementInfo     The player placement info, can be null.
     */
    void onReplace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Called when a block is broken by non-creative game mode player.
     *
     * @param blockState The block that was broken.
     * @param usedItem   The item that was used to break the block, can be {@code null}.
     * @param entity     The player who broke the block, can be {@code null}.
     */
    void onBreak(BlockStateWithPos blockState, ItemStack usedItem, Entity entity);

    void onScheduledUpdate(BlockStateWithPos blockState);

    /**
     * Get the block's drops when it is broke by item normally.
     *
     * @param blockState the block being broke.
     * @param usedItem   the item used to break the block, can be {@code null}.
     * @param entity     the entity who break the block, can be {@code null}.
     * @return the drops.
     */
    Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity);

    /**
     * Get the block's drops when it is broke by an item which has silk touch enchantment.
     *
     * @param blockState the block which is being broke.
     * @return the drops.
     */
    default ItemStack getSilkTouchDrop(BlockStateWithPos blockState) {
        return blockState.blockState().toItemStack();
    }

    /**
     * Check if the block can damage the item.
     *
     * @param itemStack the item being damaged.
     * @return {@code true} if the block can damage the item, {@code false} otherwise.
     */
    default boolean canDamageItem(ItemStack itemStack) {
        return true;
    }

    /**
     * Called when an entity falls on the block.
     *
     * @param entity     the entity who falls on the block.
     * @param blockState the block state the entity falls on.
     */
    default void onEntityFallOn(Entity entity, BlockState blockState) {}

    /**
     * Get the block's fall damage reduction factor.
     * <p>
     * Fall damage reduction factor is a value that express
     * how much the block can reduce the fall damage.
     * <p>
     * For example, if fall damage is 10.0 and the reduction factor is 0.5,
     * the actual fall damage will be 5.0.
     *
     * @return the fall damage reduction factor.
     */
    default float getFallDamageReductionFactor() {
        return 0.0f;
    }

    /**
     * Calculate how long can break a specific block state.
     *
     * @param blockState the specific block state, must belong to this block type.
     * @param usedItem   the item used, can be {@code null}.
     * @param entity     the entity who break the block, can be {@code null}.
     *
     * @return the time (second).
     */
    default double calculateBreakTime(BlockState blockState, ItemStack usedItem, Entity entity) {
        checkBlockType(blockState);
        if (usedItem.canInstantBreak(blockState)) return 0;

        var blockHardness = blockState.getBlockStateData().hardness();
        var isCorrectTool = usedItem.isCorrectToolFor(blockState);
        var isAlwaysDestroyable = getBlockType().getMaterial().isAlwaysDestroyable();
        var hasAquaAffinity = false;
        var isInWater = false;
        var isOnGround = true;
        var hasteEffectLevel = 0;
        var miningFatigueLevel = 0;
        var efficiencyLevel = 0;

        if (entity != null) {
            isInWater = entity.isEyesInLiquid();
            isOnGround = entity.isOnGround();
            hasteEffectLevel = entity.getEffectLevel(EffectTypes.HASTE);
            // Conduit Power ensures at least level 2 haste effect
            if (entity.hasEffect(EffectTypes.CONDUIT_POWER)) {
                hasteEffectLevel = Integer.max(hasteEffectLevel, 2);
            }

            miningFatigueLevel = entity.getEffectLevel(EffectTypes.HASTE);

            if (entity instanceof EntityContainerHolderComponent containerHolder) {
                if (containerHolder.hasContainer(FullContainerType.ARMOR))
                    hasAquaAffinity = containerHolder
                            .getContainer(FullContainerType.ARMOR)
                            .getItemStack(0)
                            .hasEnchantment(EnchantmentTypes.AQUA_AFFINITY);

                if (containerHolder.hasContainer(FullContainerType.PLAYER_INVENTORY))
                    efficiencyLevel = containerHolder
                            .getContainer(FullContainerType.PLAYER_INVENTORY)
                            .getItemInHand()
                            .getEnchantmentLevel(EnchantmentTypes.EFFICIENCY);
            }
        }

        // Calculate break time
        // TODO: Further validation of the algorithm is needed
        var baseTime = ((isCorrectTool || isAlwaysDestroyable) ? 1.5 : 5d) * blockHardness;
        var speed = 1d / baseTime;
        if (isCorrectTool) {
            // Tool level (wooden, stone, iron, etc...) bonus
            speed *= usedItem.getBreakTimeBonus(blockState);
            // Tool efficiency enchantment bonus
            speed += speedBonusByEfficiency(efficiencyLevel);
        } else if (isSword(usedItem.getItemType())) {
            // The minimum speed for swords digging blocks is 1.5 times
            speed *= 1.5;
        }

        // Entity haste potion effect bonus
        speed *= 1d + (0.2d * hasteEffectLevel);
        // Entity mining fatigue effect negative bonus
        if (miningFatigueLevel != 0) speed /= Math.pow(miningFatigueLevel, 3);
        // In water but no underwater speed mining effect
        if (isInWater && !hasAquaAffinity) speed *= 0.2d;
        // In midair
        if (!isInWater && !isOnGround) speed *= 0.2d;
        return 1d / speed;
    }

    private double speedBonusByEfficiency(int efficiencyLevel) {
        if (efficiencyLevel == 0) return 0;
        return efficiencyLevel * efficiencyLevel + 1;
    }

    private void checkBlockType(BlockState blockState) {
        if (blockState.getBlockType() != getBlockType())
            throw new IllegalArgumentException("Block type is not match! Expected: " + getBlockType().getIdentifier() + ", actual: " + blockState.getBlockType().getIdentifier());
    }
}
