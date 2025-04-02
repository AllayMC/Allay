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
import org.jetbrains.annotations.ApiStatus;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.item.ItemHelper.isSword;

/**
 * @author daoge_cmd
 */
public interface BlockBaseComponent extends BlockComponent {
    /**
     * Retrieves the type of block associated with this component.
     *
     * @return the block type associated with this component.
     */
    BlockType<? extends BlockBehavior> getBlockType();

    /**
     * Called when a neighboring block causes the current block to update.
     *
     * @param current  the current block that is being updated.
     * @param neighbor the neighboring block that triggered the update.
     * @param face     the face of the current block that is being updated.
     */
    @ApiStatus.OverrideOnly
    void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face);

    /**
     * Called when the block encounters a random update.
     *
     * @param current the block that encountered the random update.
     */
    @ApiStatus.OverrideOnly
    void onRandomUpdate(BlockStateWithPos current);

    /**
     * Determines if the block can receive random updates.
     *
     * @return {@code true} if the block can receive random updates, {@code false} otherwise.
     */
    default boolean canRandomUpdate() {
        return false;
    }

    /**
     * Attempts to place a block at the specified position and with optional placement info.
     * This method assumes that the current block at the specified position is replaceable.
     *
     * @param dimension     the dimension where the block is being placed.
     * @param blockState    the block state to be placed.
     * @param placeBlockPos the position at which the block is being placed.
     * @param placementInfo information about the player's placement, can be {@code null}.
     *
     * @return {@code true} if the block is successfully placed, {@code false} otherwise.
     */
    boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo);

    /**
     * Attempts to combine the block with another block that is already in the dimension and not marked as replaceable.
     * For example, two slab blocks can be combined into a double slab block.
     *
     * @param dimension       the dimension where the block is being placed.
     * @param blockState      the block to be combined.
     * @param combineBlockPos the position of the block being combined.
     * @param placementInfo   the player placement information, can be {@code null}.
     *
     * @return {@code true} if the block is successfully combined, {@code false} otherwise.
     */
    default boolean combine(Dimension dimension, BlockState blockState, Vector3ic combineBlockPos, PlayerInteractInfo placementInfo) {
        return false;
    }

    /**
     * Called when the block is placed.
     * <p>
     * Note that the block has not been placed yet, so changes to the block state via {@link Dimension#setBlockState}
     * will be overridden by the actual placement.
     * </p>
     * For setting a new block state post-placement, consider using {@link BlockBehavior#afterPlaced}.
     *
     * @param currentBlockState the block that is being replaced.
     * @param newBlockState     the block that will replace the current block.
     * @param placementInfo     the player placement information, can be {@code null}.
     */
    @ApiStatus.OverrideOnly
    void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Called after the block is placed.
     *
     * @param oldBlockState the block that was replaced.
     * @param newBlockState the new block that replaced the old block.
     * @param placementInfo the player placement information, can be {@code null}.
     */
    @ApiStatus.OverrideOnly
    void afterPlaced(BlockStateWithPos oldBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Determines if the block will drop as an item when it is broken by the specified entity using the specified item.
     *
     * @param blockState the block being broken.
     * @param usedItem   the item used to break the block, can be {@code null}.
     * @param entity     the entity breaking the block, can be {@code null}.
     *
     * @return {@code true} if the block will drop as an item, {@code false} otherwise.
     */
    boolean isDroppable(BlockStateWithPos blockState, ItemStack usedItem, Entity entity);

    /**
     * Handles player interaction with the block.
     *
     * @param itemStack    the item in the player's hand.
     * @param interactInfo information about the interaction.
     *
     * @return {@code true} if the interaction is valid, {@code false} otherwise.
     */
    @ApiStatus.OverrideOnly
    boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo);

    /**
     * Called when the block is replaced.
     *
     * @param currentBlockState the block being replaced.
     * @param newBlockState     the block that replaces the current block.
     * @param placementInfo     the player placement information, can be {@code null}.
     */
    @ApiStatus.OverrideOnly
    void onReplace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Called after a block has been replaced.
     *
     * @param oldBlockState the block that was replaced.
     * @param newBlockState the block that replaced the old block.
     * @param placementInfo the player placement information, can be {@code null}.
     */
    @ApiStatus.OverrideOnly
    void afterReplaced(BlockStateWithPos oldBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Handles the case where a neighbor layer block is replaced.
     * For example, when a water block in layer 1 is affected by a change in layer 0.
     *
     * @param currentBlockState the block being replaced.
     * @param newBlockState     the new block replacing the current block.
     * @param placementInfo     the player placement information, can be {@code null}.
     */
    @ApiStatus.OverrideOnly
    void afterNeighborLayerReplace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Handles when a block is broken by a non-creative player.
     *
     * @param blockState the block that was broken.
     * @param usedItem   the item used to break the block, can be {@code null}.
     * @param entity     the entity that broke the block, can be {@code null}.
     */
    @ApiStatus.OverrideOnly
    void onBreak(BlockStateWithPos blockState, ItemStack usedItem, Entity entity);

    /**
     * Called when a block receives a scheduled update.
     *
     * @param blockStateWithPos the block receiving the scheduled update.
     */
    @ApiStatus.OverrideOnly
    default void onScheduledUpdate(BlockStateWithPos blockStateWithPos) {}

    /**
     * Retrieves the drops of the block when it is broken.
     *
     * @param blockState the block being broken.
     * @param usedItem   the item used to break the block, can be {@code null}.
     * @param entity     the entity breaking the block, can be {@code null}.
     *
     * @return a set of {@link ItemStack} representing the drops.
     */
    Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity);

    /**
     * Retrieves the drops of the block when it is broken with a silk touch enchantment.
     *
     * @param blockState the block being broken.
     *
     * @return the silk touch drop as an {@link ItemStack}.
     */
    default ItemStack getSilkTouchDrop(BlockStateWithPos blockState) {
        return blockState.blockState().toItemStack();
    }

    /**
     * Retrieves the amount of XP that should be dropped when the block is broken.
     *
     * @param blockState the block being broken.
     * @param usedItem   the item used to break the block.
     * @param entity     the entity breaking the block.
     *
     * @return the amount of XP to drop.
     */
    default int getDropXpAmount(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        return 0;
    }

    /**
     * Determines if the block can damage the item used to break it.
     *
     * @param itemStack the item being damaged.
     *
     * @return {@code true} if the block can damage the item, {@code false} otherwise.
     */
    default boolean canDamageItem(ItemStack itemStack) {
        return true;
    }

    /**
     * Handles when an entity falls on the block.
     *
     * @param entity the entity falling on the block.
     * @param block  the block the entity is falling on.
     */
    @ApiStatus.OverrideOnly
    default void onEntityFallOn(Entity entity, BlockStateWithPos block) {}

    /**
     * Retrieves the fall damage reduction factor for the block.
     * The factor determines how much fall damage is reduced when an entity falls on this block.
     *
     * @return the fall damage reduction factor.
     */
    default float getFallDamageReductionFactor() {
        return 0.0f;
    }

    /**
     * Determines if the block can reset fall damage.
     *
     * @return {@code true} if the block can reset fall damage, {@code false} otherwise.
     */
    default boolean canResetFallDamage() {
        return false;
    }

    /**
     * Determines whether the block type can collide with entities.
     *
     * @return {@code true} if the block can collide with entities, {@code false} otherwise.
     */
    default boolean canCollideWithEntity() {
        return false;
    }

    /**
     * Handles when a block collides with an entity.
     * This method is only called if {@link #canCollideWithEntity()} returns {@code true}.
     *
     * @param blockStateWithPos the block that collides with the entity.
     * @param entity            the entity that collides with the block.
     */
    @ApiStatus.OverrideOnly
    default void onCollideWithEntity(BlockStateWithPos blockStateWithPos, Entity entity) {}

    /**
     * Calculates the time it takes to break the specific block state with the given item.
     *
     * @param blockState the specific block state to be broken.
     * @param usedItem   the item used to break the block, can be {@code null}.
     * @param entity     the entity breaking the block, can be {@code null}.
     *
     * @return the time in seconds required to break the block.
     *
     * @see <a href="https://minecraft.wiki/w/Breaking#Calculation">Breaking Calculation</a>
     */
    default double calculateBreakTime(BlockState blockState, ItemStack usedItem, Entity entity) {
        checkBlockType(blockState);
        if (usedItem.canInstantBreak(blockState)) {
            return 0;
        }

        var blockHardness = blockState.getBlockStateData().hardness();
        if (blockHardness == -1) {
            return Integer.MAX_VALUE;
        }

        var isCorrectTool = usedItem.isCorrectToolFor(blockState);
        var requiresCorrectToolForDrops = blockState.getBlockStateData().requiresCorrectToolForDrops();

        var baseTime = ((isCorrectTool || !requiresCorrectToolForDrops) ? 1.5d : 5d) * blockHardness;
        var speed = 1d / baseTime;

        double efficiency = 1d;
        if (isCorrectTool) {
            // Tool level (wooden, stone, iron, etc...) bonus
            efficiency = usedItem.getBreakTimeBonus(blockState);
            // Tool efficiency enchantment bonus
            efficiency += speedBonusByEfficiency(usedItem.getEnchantmentLevel(EnchantmentTypes.EFFICIENCY));
        }

        if (isSword(usedItem.getItemType())) { // Special case
            efficiency *= 1.5d;
        }

        speed *= efficiency;

        if (entity != null) {
            if (entity.hasEffect(EffectTypes.HASTE) || entity.hasEffect(EffectTypes.CONDUIT_POWER)) {
                var level = Math.max(entity.getEffectLevel(EffectTypes.HASTE), entity.getEffectLevel(EffectTypes.CONDUIT_POWER));
                speed *= (0.2d * level + 1) * Math.pow(1.2d, level);
            }

            // Entity mining fatigue effect negative bonus
            if (entity.hasEffect(EffectTypes.MINING_FATIGUE)) {
                // speedMultiplier *= 0.3 ^ miningFatigueLevel
                // damage *= 0.7 ^ miningFatigueLevel
                // 0.3 + 0.7 = 0.21 ^ miningFatigueLevel
                speed *= Math.pow(0.21d, entity.getEffectLevel(EffectTypes.MINING_FATIGUE));
            }

            var hasAquaAffinity = false;
            if (entity instanceof EntityContainerHolderComponent containerHolder) {
                if (containerHolder.hasContainer(FullContainerType.ARMOR)) {
                    hasAquaAffinity = containerHolder
                            .getContainer(FullContainerType.ARMOR)
                            .getHelmet()
                            .hasEnchantment(EnchantmentTypes.AQUA_AFFINITY);
                }
            }

            // In water but no underwater speed mining effect
            if (entity.isEyesInWater() && !hasAquaAffinity) {
                speed /= 5d;
            }

            // In air
            if (!entity.isOnGround()) {
                speed /= 5d;
            }
        }

        return Math.ceil(1d / speed * 20d) / 20d;
    }

    /**
     * Check if a position on the side of the block placed in the world at a specific position is
     * closed. When this returns true (for example, when the side is below the position and the block is a
     * slab), liquid inside the block won't flow from pos into side.
     *
     * @param blockState the block to check.
     * @param blockFace  the side of the block to check.
     *
     * @return {@code true} if the side is closed, {@code false} otherwise.
     */
    default boolean canLiquidFlowIntoSide(BlockState blockState, BlockFace blockFace) {
        return true;
    }

    private double speedBonusByEfficiency(int efficiencyLevel) {
        if (efficiencyLevel == 0) {
            return 0;
        }

        return (efficiencyLevel * efficiencyLevel) + 1;
    }

    private void checkBlockType(BlockState blockState) {
        if (blockState.getBlockType() != getBlockType()) {
            throw new IllegalArgumentException("Block type is not match! Expected: " + getBlockType().getIdentifier() + ", actual: " + blockState.getBlockType().getIdentifier());
        }
    }
}
