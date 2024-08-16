package org.allaymc.api.block.component.common;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.data.BlockFace;
import org.allaymc.api.block.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.data.VanillaEffectTypes;
import org.allaymc.api.data.VanillaEnchantmentTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.common.EntityContainerHolderComponent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.item.ItemHelper.isSword;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public interface BlockBaseComponent extends BlockComponent {
    /**
     * Get the block type
     *
     * @return block type
     */
    BlockType<? extends BlockBehavior> getBlockType();

    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, Vector3ic pos, Dimension dimension) {
        updateBlockProperty(propertyType, value, pos.x(), pos.y(), pos.z(), dimension, 0);
    }

    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, int x, int y, int z, Dimension dimension) {
        updateBlockProperty(propertyType, value, x, y, z, dimension, 0);
    }

    /**
     * Update a specific property of a specific block
     *
     * @param propertyType the property type needs to be updated
     * @param value        the new property value
     * @param x            block's x coordinate
     * @param y            block's y coordinate
     * @param z            block's z coordinate
     * @param dimension    the dimension which the block is in
     * @param layer        the layer which contains the block
     */
    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, int x, int y, int z, Dimension dimension, int layer) {
        var chunk = dimension.getChunkService().getChunkByLevelPos(x, z);
        if (chunk == null) return;

        var xIndex = x & 15;
        var zIndex = z & 15;
        var oldBlockState = chunk.getBlockState(xIndex, y, zIndex, layer);
        if (oldBlockState.getBlockType() != getBlockType())
            throw new IllegalArgumentException("Block type is not match! Expected: " + getBlockType().getIdentifier() + ", actual: " + oldBlockState.getBlockType().getIdentifier());

        var newBlockState = oldBlockState.setProperty(propertyType, value);
        if (oldBlockState == newBlockState) return;

        chunk.setBlockState(xIndex, y, zIndex, newBlockState, layer);
        chunk.sendChunkPacket(Dimension.createBlockUpdatePacket(newBlockState, x, y, z, layer));
    }

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
     * Check if the block can be placed on the specified block type.
     *
     * @param blockType The type of the block on which this block is being placed.
     *
     * @return {@code true} if the block can be placed on the specified block type, {@code false} otherwise.
     */
    default boolean canPlaceOnBlock(BlockType<?> blockType) {
        return true;
    }

    /**
     * Call when a blockState causes another blockState to update.
     *
     * @param current  The current block
     * @param neighbor The neighbor block that triggered the update
     * @param face     The face of the current block that is being updated
     */
    void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face);

    /**
     * Called when the block encounters random tick update
     *
     * @param blockState the block
     */
    void onRandomUpdate(BlockStateWithPos blockState);

    default void checkPlaceMethodParam(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        Preconditions.checkState(getBlockType() == blockState.getBlockType());
        Preconditions.checkNotNull(dimension);
        Preconditions.checkNotNull(blockState);
        Preconditions.checkNotNull(placeBlockPos);
        // PlacementInfo is nullable
    }

    /**
     * Try to place a block
     *
     * @param dimension     The dimension where the block is placed
     * @param blockState    The block that is being placed
     * @param placeBlockPos The pos that the player is trying to place the block on
     * @param placementInfo The player placement info, can be null
     *
     * @return true if the block is placed successfully, false if failed
     */
    boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo);

    /**
     * Called when a block is placed.
     *
     * @param currentBlockState The block that is being replaced
     * @param newBlockState     The block that is replacing the current block
     * @param placementInfo     The player placement info, can be null
     */
    void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo);

    boolean isDroppable(BlockStateWithPos blockState, ItemStack usedItem, Entity entity);

    /**
     * @param itemStack    The item in the player's hand
     * @param interactInfo The player interaction info
     *
     * @return Whether the operation is valid.
     * For example, right-clicking on the crafting table is normally considered a valid operation, so this method will return true
     * If false is returned, the useItemOn method of the player's item will continue to be called
     */
    boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo);

    /**
     * Called when a block is replaced.
     *
     * @param currentBlockState The block that is being replaced
     * @param newBlockState     The block that is replacing the current block
     * @param placementInfo     The player placement info, can be null
     */
    void onReplace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo);

    /**
     * Called when a block is broken by non-creative game mode player
     *
     * @param blockState The block that was broken
     * @param usedItem   The item that was used to break the block, can be null
     * @param entity     The player who broke the block, can be null
     */
    void onBreak(BlockStateWithPos blockState, ItemStack usedItem, Entity entity);

    void onScheduledUpdate(BlockStateWithPos blockState);

    /**
     * Get the block's drops when it is broke by item normally
     *
     * @param blockState the block being broke
     * @param usedItem   the item used to break the block, can be null
     * @param entity     the entity who break the block, can be null
     *
     * @return the drops
     */
    Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity);

    /**
     * Get the block's drops when it is broke by an item which has silk touch enchantment
     *
     * @param blockState the block which is being broke
     *
     * @return the drops
     */
    default ItemStack getSilkTouchDrop(BlockStateWithPos blockState) {
        return blockState.blockState().toItemStack();
    }

    default boolean canDamageItem(ItemStack itemStack) {
        return true;
    }

    /**
     * Calculate how long can break a specific block state
     *
     * @param blockState the specific block state, must belong to this block type
     * @param usedItem   the item used, can be null
     * @param entity     the entity who break the block, can be null
     *
     * @return the time (second)
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
            isInWater = entity.isInWater();
            isOnGround = entity.isOnGround();
            hasteEffectLevel = entity.getEffectLevel(VanillaEffectTypes.HASTE);
            // Conduit Power ensures at least level 2 haste effect
            if (entity.hasEffect(VanillaEffectTypes.CONDUIT_POWER)) {
                hasteEffectLevel = Integer.max(hasteEffectLevel, 2);
            }

            miningFatigueLevel = entity.getEffectLevel(VanillaEffectTypes.HASTE);

            if (entity instanceof EntityContainerHolderComponent containerHolder) {
                if (containerHolder.hasContainer(FullContainerType.ARMOR))
                    hasAquaAffinity = containerHolder
                            .getContainer(FullContainerType.ARMOR)
                            .getItemStack(0)
                            .hasEnchantment(VanillaEnchantmentTypes.AQUA_AFFINITY);

                if (containerHolder.hasContainer(FullContainerType.PLAYER_INVENTORY))
                    efficiencyLevel = containerHolder
                            .getContainer(FullContainerType.PLAYER_INVENTORY)
                            .getItemInHand()
                            .getEnchantmentLevel(VanillaEnchantmentTypes.EFFICIENCY);
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

    /**
     * Check if the provided block state is belong to this block type
     *
     * @param blockState the block state you want to check
     */
    private void checkBlockType(BlockState blockState) {
        if (blockState.getBlockType() != getBlockType())
            throw new IllegalArgumentException("Block type is not match! Expected: " + getBlockType().getIdentifier() + ", actual: " + blockState.getBlockType().getIdentifier());
    }
}
