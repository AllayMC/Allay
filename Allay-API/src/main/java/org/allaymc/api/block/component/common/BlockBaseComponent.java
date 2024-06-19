package org.allaymc.api.block.component.common;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.common.EntityContainerHolderComponent;
import org.allaymc.api.entity.effect.type.EffectConduitPowerType;
import org.allaymc.api.entity.effect.type.EffectHasteType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.type.EnchantmentAquaAffinityType;
import org.allaymc.api.item.enchantment.type.EnchantmentEfficiencyType;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import static org.allaymc.api.item.ItemHelper.isSword;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public interface BlockBaseComponent extends BlockComponent {
    private static double speedBonusByEfficiency(int efficiencyLevel) {
        if (efficiencyLevel == 0) return 0;
        return efficiencyLevel * efficiencyLevel + 1;
    }

    BlockType<? extends BlockBehavior> getBlockType();

    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, int x, int y, int z, Dimension dimension) {
        updateBlockProperty(propertyType, value, x, y, z, dimension, 0);
    }

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
     * Call when a blockState causes another blockState to update.
     *
     * @param current  The current block
     * @param neighbor The neighbor block that triggered the update
     * @param face     The face of the current block
     */
    void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face);

    void onRandomUpdate(BlockStateWithPos blockState);

    default void checkPlaceMethodParam(EntityPlayer player, Dimension dimension, BlockState blockState, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
        Preconditions.checkState(getBlockType() == blockState.getBlockType());
        // player is nullable
        Preconditions.checkNotNull(dimension);
        Preconditions.checkNotNull(blockState);
        Preconditions.checkNotNull(targetBlockPos);
        Preconditions.checkNotNull(placeBlockPos);
        // clickPos is nullable
        Preconditions.checkNotNull(blockFace);
    }

    /**
     * Try to place a block
     *
     * @param player         The player who is placing the block, can be null
     * @param dimension      The dimension where the block is placed
     * @param blockState     The block that is being placed
     * @param targetBlockPos The block that the player clicked on
     * @param placeBlockPos  The pos that the player is trying to place the block on
     * @param clickPos       The precise pos where the player clicked
     * @param blockFace      The face of the block that the player clicked on
     *
     * @return true if the block is placed successfully, false if failed
     */
    boolean place(
            EntityPlayer player, Dimension dimension, BlockState blockState, Vector3ic targetBlockPos,
            Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace
    );

    /**
     * Called when a block is placed.
     *
     * @param player            The player who placed the block, can be null
     * @param currentBlockState The block that is being replaced
     * @param newBlockState     The block that is replacing the current block
     */
    void onPlace(EntityPlayer player, BlockStateWithPos currentBlockState, BlockState newBlockState);

    /**
     * @param player        The player who interacted with the block, can be null
     * @param itemStack     The item in the player's hand
     * @param blockPos      The pos of the block that the player clicked on
     * @param placeBlockPos Assuming the player is holding a block item in their hand, this parameter indicates where the block will be placed (if it can be placed)
     * @param clickPos      The precise pos where the player clicked
     * @param blockFace     The face of the block that the player clicked on
     *
     * @return Whether the operation is valid.
     * For example, right-clicking on the crafting table is normally considered a valid operation, so this method will return true
     * If false is returned, the useItemOn method of the player's item will continue to be called
     */
    boolean onInteract(
            EntityPlayer player, ItemStack itemStack, Dimension dimension, Vector3ic blockPos,
            Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace
    );

    /**
     * Called when a block is replaced.
     *
     * @param player            The player who replaced the block, can be null
     * @param currentBlockState The block that is being replaced
     * @param newBlockState     The block that is replacing the current block
     */
    void onReplace(EntityPlayer player, BlockStateWithPos currentBlockState, BlockState newBlockState);

    /**
     * Called when a block is broken by non-creative game mode player
     *
     * @param blockState The block that was broken
     * @param usedItem   The item that was used to break the block
     * @param player     The player who broke the block, can be null
     */
    void onBreak(BlockStateWithPos blockState, ItemStack usedItem, EntityPlayer player);

    void onScheduledUpdate(BlockStateWithPos blockState);

    default ItemStack[] getDrops(BlockState blockState, ItemStack usedItem) {
        // TODO: Fortune
        if (getBlockType().getItemType() != null)
            return new ItemStack[]{getBlockType().getItemType().createItemStack()};

        return Utils.EMPTY_ITEM_STACK_ARRAY;
    }

    default boolean canDamageItem(ItemStack itemStack) {
        return true;
    }

    default double calculateBreakTime(BlockState blockState, ItemStack usedItem, Entity entity) {
        checkBlockType(blockState);
        if (usedItem.canInstantBreak(blockState)) return 0;

        var blockHardness = blockState.getBlockAttributes().hardness();
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
            hasteEffectLevel = entity.getEffectLevel(EffectHasteType.HASTE_TYPE);
            // Conduit Power ensures at least level 2 haste effect
            if (entity.hasEffect(EffectConduitPowerType.CONDUIT_POWER_TYPE)) {
                hasteEffectLevel = Integer.max(hasteEffectLevel, 2);
            }

            miningFatigueLevel = entity.getEffectLevel(EffectHasteType.HASTE_TYPE);

            if (entity instanceof EntityContainerHolderComponent containerHolder) {
                if (containerHolder.hasContainer(FullContainerType.ARMOR))
                    hasAquaAffinity = containerHolder
                            .getContainer(FullContainerType.ARMOR)
                            .getItemStack(0)
                            .hasEnchantment(EnchantmentAquaAffinityType.AQUA_AFFINITY_TYPE);

                if (containerHolder.hasContainer(FullContainerType.PLAYER_INVENTORY))
                    efficiencyLevel = containerHolder
                            .getContainer(FullContainerType.PLAYER_INVENTORY)
                            .getItemInHand()
                            .getEnchantmentLevel(EnchantmentEfficiencyType.EFFICIENCY_TYPE);
            }
        }

        // Calculate break time
        // TODO: Further validation of the algorithm is needed
        var baseTime = ((isCorrectTool || isAlwaysDestroyable) ? 1.5 : 5.0) * blockHardness;
        var speed = 1.0d / baseTime;
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
        speed *= 1.0d + (0.2d * hasteEffectLevel);
        // Entity mining fatigue effect negative bonus
        if (miningFatigueLevel != 0) speed /= Math.pow(miningFatigueLevel, 3);
        // In water but no underwater speed mining effect
        if (isInWater && !hasAquaAffinity) speed *= 0.2d;
        // In midair
        if (!isInWater && !isOnGround) speed *= 0.2d;
        return 1.0d / speed;
    }

    private void checkBlockType(BlockState blockState) {
        if (blockState.getBlockType() != getBlockType())
            throw new IllegalArgumentException("Block type is not match! Expected: " + getBlockType().getIdentifier() + ", actual: " + blockState.getBlockType().getIdentifier());
    }
}
