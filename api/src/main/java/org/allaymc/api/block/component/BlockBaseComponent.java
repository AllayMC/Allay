package org.allaymc.api.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.jetbrains.annotations.ApiStatus;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public interface BlockBaseComponent extends BlockComponent {
    /**
     * Maximum redstone signal strength.
     */
    int MAX_REDSTONE_POWER = 15;

    /**
     * Retrieves the type of block associated with this component.
     *
     * @return the block type associated with this component
     */
    BlockType<? extends BlockBehavior> getBlockType();

    /**
     * Called when a neighboring block causes the current block to update.
     *
     * @param block    the current block that is being updated
     * @param neighbor the neighboring block that triggered the update
     * @param face     the face of the current block that is being updated
     */
    @ApiStatus.OverrideOnly
    void onNeighborUpdate(Block block, Block neighbor, BlockFace face);

    /**
     * Called when the block encounters a random update.
     *
     * @param block the block that encountered the random update
     */
    @ApiStatus.OverrideOnly
    void onRandomUpdate(Block block);

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
     * @param dimension     the dimension where the block is being placed
     * @param blockState    the block state to be placed
     * @param placeBlockPos the position at which the block is being placed
     * @param placementInfo information about the player's placement, can be {@code null}
     * @return {@code true} if the block is successfully placed, {@code false} otherwise.
     */
    boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo);

    /**
     * Attempts to combine the block with another block that is already in the dimension and not marked as replaceable.
     * For example, two slab blocks can be combined into a double slab block.
     *
     * @param dimension       the dimension where the block is being placed
     * @param blockState      the block to be combined
     * @param combineBlockPos the position of the block being combined
     * @param placementInfo   the player placement information, can be {@code null}
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
     * @param block         the block that is being replaced
     * @param newBlockState the block that will replace the current block
     * @param placementInfo the player placement information, can be {@code null}
     */
    @ApiStatus.OverrideOnly
    void onPlace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Called after the block is placed.
     *
     * @param oldBlock      the block that was replaced
     * @param newBlockState the new block that replaced the old block
     * @param placementInfo the player placement information, can be {@code null}
     */
    @ApiStatus.OverrideOnly
    void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Determines if the block will drop as an item when it is broken by the specified entity using the specified item.
     *
     * @param block    the block being broken
     * @param usedItem the item used to break the block, can be {@code null}
     * @param entity   the entity breaking the block, can be {@code null}
     * @return {@code true} if the block will drop as an item, {@code false} otherwise.
     */
    boolean isDroppable(Block block, ItemStack usedItem, Entity entity);

    /**
     * Handles player interaction with the block.
     *
     * @param itemStack    the item in the player's hand
     * @param dimension    the dimension where the interaction occurs
     * @param interactInfo information about the interaction
     * @return {@code true} if the interaction is valid, {@code false} otherwise.
     */
    @ApiStatus.OverrideOnly
    boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo);

    /**
     * Called when the block is replaced.
     *
     * @param block         the block being replaced
     * @param newBlockState the block that replaces the current block
     * @param placementInfo the player placement information, can be {@code null}
     */
    @ApiStatus.OverrideOnly
    void onReplace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Called after a block has been replaced.
     *
     * @param oldBlock      the block that was replaced
     * @param newBlockState the block that replaced the old block
     * @param placementInfo the player placement information, can be {@code null}
     */
    @ApiStatus.OverrideOnly
    void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Handles the case where a neighbor layer block is replaced.
     * For example, when a water block in layer 1 is affected by a change in layer 0.
     *
     * @param currentBlock  the block being replaced
     * @param newBlockState the new block replacing the current block
     * @param placementInfo the player placement information, can be {@code null}
     */
    @ApiStatus.OverrideOnly
    void afterNeighborLayerReplace(Block currentBlock, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Called when the block is punched by an entity.
     *
     * @param block     the block being punched
     * @param blockFace the face of the block being punched
     * @param usedItem  the item used to punch the block, can be {@code null}
     * @param entity    the entity who punched the block, can be {@code null}
     */
    @ApiStatus.OverrideOnly
    void onPunch(Block block, BlockFace blockFace, ItemStack usedItem, Entity entity);

    /**
     * Handles when a block is broken by a non-creative entity.
     *
     * @param block    the block that was broken
     * @param usedItem the item used to break the block, can be {@code null}
     * @param entity   the entity that broke the block, can be {@code null}
     */
    @ApiStatus.OverrideOnly
    void onBreak(Block block, ItemStack usedItem, Entity entity);

    /**
     * Called when a block receives a scheduled update.
     *
     * @param block the block receiving the scheduled update
     */
    @ApiStatus.OverrideOnly
    default void onScheduledUpdate(Block block) {
    }

    /**
     * Called when a block is moved by a piston.
     * This method is invoked after the block has been placed at its new position.
     *
     * @param block the block that was moved (at its new position)
     */
    @ApiStatus.OverrideOnly
    default void onMoved(Block block) {
    }

    /**
     * Retrieves the drops of the block when it is broken.
     *
     * @param block    the block being broken
     * @param usedItem the item used to break the block, can be {@code null}
     * @param entity   the entity breaking the block, can be {@code null}
     * @return a set of {@link ItemStack} representing the drops
     */
    Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity);

    /**
     * Retrieves the drops of the block when it is broken with a silk touch enchantment.
     *
     * @param block the block being broken
     * @return the silk touch drop as an {@link ItemStack}
     */
    default ItemStack getSilkTouchDrop(Block block) {
        return block.toItemStack();
    }

    /**
     * Retrieves the amount of XP that should be dropped when the block is broken.
     *
     * @param block    the block being broken
     * @param usedItem the item used to break the block
     * @param entity   the entity breaking the block
     * @return the amount of XP to drop
     */
    default int getDropXpAmount(Block block, ItemStack usedItem, Entity entity) {
        return 0;
    }

    /**
     * Determines if the block can damage the item used to break it.
     *
     * @param itemStack the item being damaged
     * @return {@code true} if the block can damage the item, {@code false} otherwise.
     */
    default boolean canDamageItem(ItemStack itemStack) {
        return true;
    }

    /**
     * Handles when an entity falls on the block.
     *
     * @param entity the entity falling on the block
     * @param block  the block the entity is falling on
     */
    @ApiStatus.OverrideOnly
    default void onEntityFallOn(Entity entity, Block block) {
    }

    /**
     * Retrieves the fall damage reduction factor for the block.
     * The factor determines how much fall damage is reduced when an entity falls on this block.
     *
     * @return the fall damage reduction factor
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
     * Called when the block collides with an entity.
     *
     * @param block  the block that collides with the entity
     * @param entity the entity that collides with the block
     */
    @ApiStatus.OverrideOnly
    default void onCollideWithEntity(Block block, Entity entity) {
    }

    /**
     * Called when an entity is inside the block.
     *
     * @param block  the block that the entity inside
     * @param entity the entity that inside a block
     */
    @ApiStatus.OverrideOnly
    default void onEntityInside(Block block, Entity entity) {
    }

    /**
     * Called when the block is splashed by a splash water bottle.
     *
     * @param block the block being splashed
     */
    @ApiStatus.OverrideOnly
    default void onSplash(Block block) {
    }

    /**
     * Called when the block is hit by a projectile.
     *
     * @param block      the block that is being hit
     * @param projectile the projectile that hits the block
     * @param hitPos     the hit pos
     */
    @ApiStatus.OverrideOnly
    default void onProjectileHit(Block block, EntityProjectile projectile, Vector3dc hitPos) {
    }

    /**
     * Calculates the time it takes to break the specific block state with the given item.
     *
     * @param blockState the specific block state to be broken
     * @param usedItem   the item used to break the block, can be {@code null}
     * @param entity     the entity breaking the block, can be {@code null}
     * @return the time in seconds required to break the block
     * @see <a href="https://minecraft.wiki/w/Breaking#Calculation">Breaking Calculation</a>
     */
    double calculateBreakTime(BlockState blockState, ItemStack usedItem, Entity entity);

    /**
     * Check if a position on the side of the block placed in the world at a specific position is
     * closed. When this returns true (for example, when the side is below the position and the block is a
     * slab), liquid inside the block won't flow from pos into side.
     *
     * @param blockState the block to check
     * @param blockFace  the side of the block to check
     * @return {@code true} if the side is closed, {@code false} otherwise.
     */
    default boolean canLiquidFlowIntoSide(BlockState blockState, BlockFace blockFace) {
        return true;
    }

    /**
     * Gets the weak redstone power output from this block to the specified face.
     * <p>
     * Weak power can activate adjacent redstone components (pistons, lamps, etc.),
     * but cannot be conducted through opaque solid blocks.
     *
     * @param block the block
     * @param face  the face from which power is being queried (the direction power flows out)
     * @return the weak power level (0-15)
     */
    default int getWeakPower(Block block, BlockFace face) {
        return 0;
    }

    /**
     * Gets the strong redstone power output from this block to the specified face.
     * <p>
     * Strong power can be conducted through opaque solid blocks. When a solid block
     * receives strong power, it becomes "powered" and can activate adjacent redstone
     * components (including redstone dust).
     *
     * @param block the block
     * @param face  the face from which power is being queried (the direction power flows out)
     * @return the strong power level (0-15)
     */
    default int getStrongPower(Block block, BlockFace face) {
        return 0;
    }

    /**
     * Determines if this block has a custom comparator input override.
     * <p>
     * When this returns {@code true}, comparators will use {@link #getComparatorInputOverride(Block)}
     * to determine the input signal strength instead of reading the normal redstone power level.
     * <p>
     * <b>Important:</b> This method indicates whether the block <i>supports</i> comparator input,
     * not whether the current signal is non-zero. For example:
     * <ul>
     *   <li>An empty chest should return {@code true} here (it supports comparator input),
     *       but return {@code 0} from {@link #getComparatorInputOverride(Block)}</li>
     *   <li>A stone block should return {@code false} here (it doesn't support comparator input)</li>
     * </ul>
     * This distinction matters because when {@code hasComparatorInputOverride()} returns {@code false},
     * the comparator will read the normal redstone signal from behind, which is different from
     * reading a zero signal from a supported block.
     *
     * @return {@code true} if the block supports comparator input override, {@code false} otherwise
     * @see #getComparatorInputOverride(Block)
     */
    default boolean hasComparatorInputOverride() {
        return false;
    }

    /**
     * Gets the comparator input override signal strength for this block.
     * <p>
     * This method is only called when {@link #hasComparatorInputOverride()} returns {@code true}.
     * The returned signal strength should be between 0 and 15.
     *
     * @param block the block to get the signal from
     * @return the comparator input signal strength (0-15)
     * @see #hasComparatorInputOverride()
     */
    default int getComparatorInputOverride(Block block) {
        return 0;
    }
}
