package org.allaymc.server.block.component;

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
import org.allaymc.server.block.data.BlockId;
import org.joml.Vector3ic;

import java.util.Set;

/**
 * Base component for candle block behavior.
 * <p>
 * Candles can:
 * - Stack up to 4 candles in one block (candles property 0-3)
 * - Be lit with flint and steel (lit property true/false)
 * - Be extinguished by clicking with empty hand
 * - Be placed on a full cake to create candle cake
 *
 * @author daoge_cmd
 */
public class BlockCandleBaseComponentImpl extends BlockBaseComponentImpl {

    public static final int MAX_CANDLES = 3; // 0-3 means 1-4 candles

    private final BlockId candleCakeId;

    public BlockCandleBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId candleCakeId) {
        super(blockType);
        this.candleCakeId = candleCakeId;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, placementInfo);
        }

        var clickedBlockPos = placementInfo.clickedBlockPos();
        var clickedBlockState = dimension.getBlockState(clickedBlockPos);
        var clickedBlockType = clickedBlockState.getBlockType();

        // Check if placing on a full cake
        if (clickedBlockType == BlockTypes.CAKE) {
            var bites = clickedBlockState.getPropertyValue(BlockPropertyTypes.BITE_COUNTER);
            if (bites == 0) {
                dimension.setBlockState(
                        clickedBlockPos.x(), clickedBlockPos.y(), clickedBlockPos.z(),
                        candleCakeId.getBlockType().getDefaultState(), placementInfo
                );
                return true;
            }
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public boolean combine(Dimension dimension, BlockState blockState, Vector3ic combineBlockPos, PlayerInteractInfo placementInfo) {
        var existingState = dimension.getBlockState(combineBlockPos);

        // Only combine if same candle type
        if (existingState.getBlockType() != blockState.getBlockType()) {
            return false;
        }

        var currentCandles = existingState.getPropertyValue(BlockPropertyTypes.CANDLES);
        if (currentCandles >= MAX_CANDLES) {
            return false;
        }

        // Increment candle count
        dimension.updateBlockProperty(BlockPropertyTypes.CANDLES, currentCandles + 1, combineBlockPos);
        return true;
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        var isLit = blockState.getPropertyValue(BlockPropertyTypes.LIT);

        // Light with flint and steel
        if (!isLit && itemStack.getItemType() == ItemTypes.FLINT_AND_STEEL) {
            dimension.updateBlockProperty(BlockPropertyTypes.LIT, true, pos);
            dimension.addSound(MathUtils.center(pos), SimpleSound.IGNITE);
            itemStack.tryIncreaseDamage(1);
            return true;
        }

        // If holding a candle and current candle count is not full, let's increase the current candle count
        if (itemStack instanceof ItemCandleStack) {
            var currentCandles = blockState.getPropertyValue(BlockPropertyTypes.CANDLES);
            if (currentCandles < MAX_CANDLES) {
                dimension.updateBlockProperty(BlockPropertyTypes.CANDLES, currentCandles + 1, pos);
                interactInfo.player().tryConsumeItemInHand();
                return true;
            }
        }

        // Extinguish with empty hand or any non-flint-and-steel item
        if (isLit && itemStack.getItemType() != ItemTypes.FLINT_AND_STEEL) {
            dimension.updateBlockProperty(BlockPropertyTypes.LIT, false, pos);
            dimension.addSound(MathUtils.center(pos), SimpleSound.FIRE_EXTINGUISH);
            return true;
        }

        return false;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var candleCount = block.getBlockState().getPropertyValue(BlockPropertyTypes.CANDLES) + 1;
        return Set.of(this.blockType.getItemType().createItemStack(candleCount));
    }
}
