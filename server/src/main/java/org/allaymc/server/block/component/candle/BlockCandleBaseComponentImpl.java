package org.allaymc.server.block.component.candle;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemCandleStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.block.data.BlockId;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.function.Supplier;

/**
 * {@code BlockCandleBaseComponentImpl} handles candle counts, ignition,
 * extinguishing, and conversion of a full cake to candle cake. The stored candle
 * count is zero-based, while dropped item counts add one; a deferred supplier
 * identifies the matching candle-cake type for conversion.
 *
 * @author daoge_cmd
 */
public class BlockCandleBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code MAX_CANDLES} is the largest stored zero-based candle count, representing
     * a group of four candle items.
     */
    public static final int MAX_CANDLES = 3;

    /**
     * {@code candleCakeType} resolves the matching cake variant only when needed,
     * allowing its block type registration to complete after component construction.
     */
    private final Supplier<BlockType<?>> candleCakeType;

    /**
     * Creates candle behavior retaining a deferred candle-cake type supplier.
     * {@code candleCakeTypeSupplier} is evaluated when a full cake is converted,
     * rather than resolving its block type during construction.
     */
    public BlockCandleBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<BlockType<?>> candleCakeTypeSupplier) {
        super(blockType);
        this.candleCakeType = candleCakeTypeSupplier;
    }

    /**
     * Creates candle behavior using a deferred lookup for the supplied candle-cake
     * identifier. {@code candleCakeId} provides its block type only when placement
     * needs to convert a full cake.
     */
    public BlockCandleBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId candleCakeId) {
        this(blockType, candleCakeId::getBlockType);
    }

    /**
     * Converts a clicked unbitten cake to the configured candle-cake state, otherwise
     * using inherited placement. A {@code null} {@code placementInfo} uses ordinary
     * placement; cake conversion reports {@code true} without checking the write
     * result.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, placementInfo);
        }

        var clickedBlockPos = placementInfo.clickedBlockPos();
        var clickedBlockState = dimension.getBlockState(clickedBlockPos);
        var clickedBlockType = clickedBlockState.getBlockType();
        if (clickedBlockType == BlockTypes.CAKE) {
            var bites = clickedBlockState.getPropertyValue(BlockPropertyTypes.BITE_COUNTER);
            if (bites == 0) {
                dimension.setBlockState(
                        clickedBlockPos.x(), clickedBlockPos.y(), clickedBlockPos.z(),
                        candleCakeType.get().getDefaultState(), placementInfo
                );
                return true;
            }
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Increments an existing matching candle type while its stored count is below
     * {@code MAX_CANDLES}. Different types and full groups return {@code false};
     * accepted combinations report success after attempting the property update.
     */
    @Override
    public boolean combine(Dimension dimension, BlockState blockState, Vector3ic combineBlockPos, PlayerInteractInfo placementInfo) {
        var existingState = dimension.getBlockState(combineBlockPos);
        if (existingState.getBlockType() != blockState.getBlockType()) {
            return false;
        }

        var currentCandles = existingState.getPropertyValue(BlockPropertyTypes.CANDLES);
        if (currentCandles >= MAX_CANDLES) {
            return false;
        }
        dimension.updateBlockProperty(BlockPropertyTypes.CANDLES, currentCandles + 1, combineBlockPos);
        return true;
    }

    /**
     * Handles lighting, additional candles, and extinguishing when player context is
     * present. Flint and steel lights an unlit group; any candle item can increase
     * its count, and lit groups extinguish with non-flint-and-steel items when
     * stacking did not handle the interaction.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        var isLit = blockState.getPropertyValue(BlockPropertyTypes.LIT);
        if (!isLit && itemStack.getItemType() == ItemTypes.FLINT_AND_STEEL) {
            dimension.updateBlockProperty(BlockPropertyTypes.LIT, true, pos);
            dimension.addSound(MathUtils.center(pos), SimpleSound.IGNITE);
            itemStack.tryIncreaseDamage(1);
            return true;
        }
        if (itemStack instanceof ItemCandleStack) {
            var currentCandles = blockState.getPropertyValue(BlockPropertyTypes.CANDLES);
            if (currentCandles < MAX_CANDLES) {
                dimension.updateBlockProperty(BlockPropertyTypes.CANDLES, currentCandles + 1, pos);
                interactInfo.player().tryConsumeItemInHand();
                return true;
            }
        }
        if (isLit && itemStack.getItemType() != ItemTypes.FLINT_AND_STEEL) {
            dimension.updateBlockProperty(BlockPropertyTypes.LIT, false, pos);
            dimension.addSound(MathUtils.center(pos), SimpleSound.FIRE_EXTINGUISH);
            return true;
        }

        return false;
    }

    /**
     * Returns one candle item per stored candle plus its zero-based offset. The
     * resulting count ranges from one through four for valid candle states,
     * preserving the number represented by the group.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        // The stored count starts at zero for one candle.
        var candleCount = block.getBlockState().getPropertyValue(BlockPropertyTypes.CANDLES) + 1;
        return Set.of(this.blockType.getItemType().createItemStack(candleCount));
    }
}
