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
 * {@code BlockBaseComponent} defines block lifecycle hooks, placement operations,
 * mining results, entity contact effects, and redstone output. A block behavior
 * exposes these operations through its components. Placement hooks distinguish the
 * old block snapshot from the incoming state: preplacement callbacks run before the
 * stored block changes, while postplacement callbacks can observe the replacement.
 * Optional player interaction data also allows changes initiated without a player.
 *
 * <p>
 * Mining behavior depends on both the block state and the item and entity involved.
 * Break time accounts for tool suitability and entity conditions, while drop
 * eligibility, ordinary drops, silk touch results, and experience remain separate
 * operations. Default hooks provide neutral behavior for unsupported capabilities,
 * including no random updates, no comparator override, and no emitted redstone power.
 * Implementations specialize these contracts for their block family.
 *
 * @author daoge_cmd
 */
public interface BlockBaseComponent extends BlockComponent {
    /**
     * {@code MAX_REDSTONE_POWER} bounds the signal strength produced by redstone
     * output and comparator input implementations.
     */
    int MAX_REDSTONE_POWER = 15;

    /**
     * Returns the block type whose behavior this component implements.
     */
    BlockType<? extends BlockBehavior> getBlockType();

    /**
     * Handles a neighboring block change for the supplied block snapshot.
     * {@code face} points from {@code block} toward {@code neighbor}, and
     * {@code oldNeighborState} may be {@code null} when the previous neighbor state
     * is unknown.
     */
    @ApiStatus.OverrideOnly
    void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState);

    /**
     * Handles a random block update for the supplied world snapshot.
     */
    @ApiStatus.OverrideOnly
    void onRandomUpdate(Block block);

    /**
     * Returns whether this block behavior receives random update callbacks. The
     * default returns {@code false}; implementations enable random updates when their
     * state or companion components require them.
     */
    default boolean canRandomUpdate() {
        return false;
    }

    /**
     * Attempts placement at the supplied replaceable position in the dimension.
     * Support for {@code null} {@code placementInfo} depends on the implementation:
     * the shared server base permits it, while some specialized behaviors require
     * player context. The result reports whether placement succeeds.
     */
    boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo);

    /**
     * Attempts to combine the supplied state with an existing nonreplaceable block,
     * such as a matching slab. The default returns {@code false} without changing the
     * dimension; specialized implementations define whether {@code placementInfo} may
     * be {@code null}.
     */
    default boolean combine(Dimension dimension, BlockState blockState, Vector3ic combineBlockPos, PlayerInteractInfo placementInfo) {
        return false;
    }

    /**
     * Handles the incoming block's placement before the stored state changes.
     * {@code block} is the old snapshot and {@code placementInfo} may be
     * {@code null}; state changes at that position can be overwritten by placement.
     */
    @ApiStatus.OverrideOnly
    void onPlace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Handles the incoming block after the dimension stores its new state.
     * {@code oldBlock} retains the replaced snapshot, and {@code placementInfo} may
     * be {@code null} when no player initiated placement.
     */
    @ApiStatus.OverrideOnly
    void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Returns whether break handling may emit item drops for this block.
     * {@code usedItem} and {@code entity} may be {@code null} for breaks without a
     * tool or responsible entity.
     */
    boolean isDroppable(Block block, ItemStack usedItem, Entity entity);

    /**
     * Handles the player's item interaction with the selected block. A {@code true}
     * result identifies a handled interaction, allowing the caller to avoid
     * additional item-use behavior for that attempt.
     */
    @ApiStatus.OverrideOnly
    boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo);

    /**
     * Handles the outgoing block before the dimension stores its replacement.
     * {@code block} is the outgoing snapshot, {@code newBlockState} is the incoming
     * state, and {@code placementInfo} may be {@code null}.
     */
    @ApiStatus.OverrideOnly
    void onReplace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Handles the outgoing block after the dimension stores its replacement.
     * {@code oldBlock} retains the outgoing state and position, while
     * {@code placementInfo} may be {@code null} for changes without a player.
     */
    @ApiStatus.OverrideOnly
    void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Handles a replacement in the other block layer at this position.
     * {@code currentBlock} describes the changed layer's old state,
     * {@code newBlockState} describes its replacement, and {@code placementInfo} may
     * be {@code null}.
     */
    @ApiStatus.OverrideOnly
    void afterNeighborLayerReplace(Block currentBlock, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Handles an entity punching a particular face of the block. {@code usedItem} and
     * {@code entity} may be {@code null} when the punch has no associated tool or
     * entity.
     */
    @ApiStatus.OverrideOnly
    void onPunch(Block block, BlockFace blockFace, ItemStack usedItem, Entity entity);

    /**
     * Handles item and experience drops when this block is broken. {@code usedItem}
     * and {@code entity} may be {@code null}, and implementations apply their own
     * drop eligibility and enchantment policies.
     */
    @ApiStatus.OverrideOnly
    void onBreak(Block block, ItemStack usedItem, Entity entity);

    /**
     * Handles an update scheduled for this block at its world position. The default
     * performs no action, allowing components without scheduled behavior to share the
     * same callback contract.
     */
    @ApiStatus.OverrideOnly
    default void onScheduledUpdate(Block block) {
    }

    /**
     * Handles a piston move after the block reaches its new position. {@code block}
     * describes that destination snapshot, and the default performs no action for
     * blocks without movement behavior.
     */
    @ApiStatus.OverrideOnly
    default void onMoved(Block block) {
    }

    /**
     * Returns the ordinary item stacks produced by breaking this block.
     * {@code usedItem} and {@code entity} may be {@code null}; silk touch selection
     * and experience drops use separate operations.
     */
    Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity);

    /**
     * Returns the item stack selected for a silk touch break. The default converts
     * {@code block} to its item representation; specialized blocks can preserve
     * additional data in their returned stack.
     */
    default ItemStack getSilkTouchDrop(Block block) {
        return block.toItemStack();
    }

    /**
     * Returns the experience point count produced by breaking this block. The default
     * returns {@code 0}, and {@code usedItem} and {@code entity} may be {@code null}
     * for unattributed block breaks.
     */
    default int getDropXpAmount(Block block, ItemStack usedItem, Entity entity) {
        return 0;
    }

    /**
     * Returns whether breaking this block may damage the supplied item. The default
     * returns {@code true}; specialized blocks can suppress durability changes when
     * their break behavior requires it.
     */
    default boolean canDamageItem(ItemStack itemStack) {
        return true;
    }

    /**
     * Handles an entity landing on the supplied block snapshot. The default performs
     * no action; fall damage reduction and fall-distance resetting are exposed
     * through separate capability methods.
     */
    @ApiStatus.OverrideOnly
    default void onEntityFallOn(Entity entity, Block block) {
    }

    /**
     * Returns the fraction subtracted from fall damage when landing on this block.
     * The default returns {@code 0.0f}, so the block does not reduce the computed
     * fall damage.
     */
    default float getFallDamageReductionFactor() {
        return 0.0f;
    }

    /**
     * Returns whether contact with this block can reset accumulated fall distance.
     * The default returns {@code false}; entity physics uses enabled behavior while
     * checking blocks intersecting the entity.
     */
    default boolean canResetFallDamage() {
        return false;
    }

    /**
     * Handles an entity colliding with the supplied block snapshot. The default
     * performs no action, so specialized block behaviors opt into collision effects
     * by overriding this hook.
     */
    @ApiStatus.OverrideOnly
    default void onCollideWithEntity(Block block, Entity entity) {
    }

    /**
     * Handles an entity occupying the supplied block snapshot. The default performs
     * no action, while specialized blocks can apply contact effects separately from
     * collision or landing behavior.
     */
    @ApiStatus.OverrideOnly
    default void onEntityInside(Block block, Entity entity) {
    }

    /**
     * Handles a splash water bottle reaching the supplied block. The default performs
     * no action, so only block behaviors with water-sensitive effects need to
     * override this hook.
     */
    @ApiStatus.OverrideOnly
    default void onSplash(Block block) {
    }

    /**
     * Handles a projectile hitting this block at the supplied world coordinates. The
     * default performs no action, leaving projectile-sensitive block behavior to
     * implementations that override this hook.
     */
    @ApiStatus.OverrideOnly
    default void onProjectileHit(Block block, EntityProjectile projectile, Vector3dc hitPos) {
    }

    /**
     * Calculates the breaking duration in seconds for a state of this component's
     * block type. The server implementation requires a non-{@code null}
     * {@code usedItem}; an air item represents breaking without a held tool, and
     * {@code entity} may be {@code null}.
     *
     * <p>
     * Tool suitability, block hardness, enchantments, active effects, and
     * environmental penalties contribute to the duration. The server rounds the
     * result upward to a game tick, returns {@code 0} for instant breaking, and uses
     * {@code Integer.MAX_VALUE} seconds for negative-one hardness.
     *
     * @throws IllegalArgumentException if {@code blockState} belongs to another block type
     */
    double calculateBreakTime(BlockState blockState, ItemStack usedItem, Entity entity);

    /**
     * Returns whether liquid may flow through the supplied face of this state. The
     * default returns {@code true}; shapes such as slabs can close particular faces
     * to constrain liquid movement.
     */
    default boolean canLiquidFlowIntoSide(BlockState blockState, BlockFace blockFace) {
        return true;
    }

    /**
     * Returns weak redstone power emitted toward the supplied face, from {@code 0} to
     * {@code MAX_REDSTONE_POWER}. The default emits {@code 0}; weak power activates
     * neighboring components without conducting through solid blocks.
     */
    default int getWeakPower(Block block, BlockFace face) {
        return 0;
    }

    /**
     * Returns strong redstone power emitted toward the supplied face, from {@code 0}
     * to {@code MAX_REDSTONE_POWER}. The default emits {@code 0}; strong power can
     * conduct through solid blocks to other components.
     */
    default int getStrongPower(Block block, BlockFace face) {
        return 0;
    }

    /**
     * Returns whether comparators use this block's specialized input value. The
     * default returns {@code false}; supported blocks return {@code true} even when
     * their current {@code getComparatorInputOverride} result is {@code 0}.
     */
    default boolean hasComparatorInputOverride() {
        return false;
    }

    /**
     * Returns this block's comparator input strength from {@code 0} to
     * {@code MAX_REDSTONE_POWER}. Comparators consult this result when
     * {@code hasComparatorInputOverride} returns {@code true}; the default input
     * signal strength is {@code 0}.
     */
    default int getComparatorInputOverride(Block block) {
        return 0;
    }
}
