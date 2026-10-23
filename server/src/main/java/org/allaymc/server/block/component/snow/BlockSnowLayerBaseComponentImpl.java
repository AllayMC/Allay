package org.allaymc.server.block.component.snow;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.HEIGHT;

/**
 * {@code BlockSnowLayerBaseComponentImpl} implements snow-layer placement, light-driven
 * melting, and snowball drops. Player placement can increase the clicked snow layer's encoded
 * height until its maximum and requires a full upper support face, while programmatic
 * placement follows the inherited path.
 *
 * <p>
 * Melting uses different light observations according to biome temperature: cold biomes use
 * block light, and warmer biomes use internal light. A qualifying light value raises a
 * cancellable fade proposal. Drop calculation derives snowball count from integer division of
 * the encoded height rather than converting it to a physical layer count first, with a minimum
 * stack of one.
 *
 * @author IWareQ
 */
public class BlockSnowLayerBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the snow layer behavior component for the supplied block type.
     */
    public BlockSnowLayerBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Chooses the light source for melting from the biome's temperature, after inherited
     * random-update handling. Temperatures at or below {@code 0.25} use block light; warmer
     * biomes use internal light.
     *
     * <p>
     * A selected light value above eleven proposes replacing the snow with air through a fade
     * event. Cancellation skips the write, while acceptance uses the event's replacement
     * state. The temperature decision therefore controls whether sky-derived internal
     * illumination participates in the melting trigger.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var dim = block.getDimension();
        var pos = block.getPosition();

        // Cold-biome melting uses emitted block light; warmer-biome melting also reflects
        // internal illumination, so the same location can melt differently across biome temperatures.
        var lightLevel = dim.getBiome(pos).getBiomeData().temperature() <= 0.25f
                ? dim.getLightEngine().getBlockLight(pos)
                : dim.getLightEngine().getInternalLight(pos);

        if (lightLevel > 11) {
            var event = new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                dim.setBlockState(pos, event.getNewBlockState());
            }
        }
    }

    /**
     * Places snow after player stacking and support checks. A {@code null} {@code
     * placementInfo} delegates directly to inherited placement; player placement increments a
     * clicked snow layer unless already maximal, then requires a full upper support face.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        var clickedBlockState = placementInfo.getClickedBlock();
        if (clickedBlockState.getBlockType() == BlockTypes.SNOW_LAYER) {
            var height = clickedBlockState.getPropertyValue(HEIGHT);
            if (height == HEIGHT.getMax()) {
                return false;
            }

            blockState = blockState.setPropertyValue(HEIGHT, height + 1);
        }

        if (!canBeSupportedAt(dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos)))) {
            return false;
        }

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
    }

    /**
     * Returns whether the supporting state's collision shape has a full upper face.
     */
    private boolean canBeSupportedAt(BlockState blockState) {
        var shape = blockState.getBlockStateData().collisionShape();
        return shape.isFull(BlockFace.UP);
    }

    /**
     * Returns at least one snowball using half the encoded height as the count. The division
     * is integer division before conversion, so odd height values truncate rather than
     * rounding upward.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        // Integer division truncates the encoded height before the minimum-one clamp is applied.
        return Set.of(ItemTypes.SNOWBALL.createItemStack((int) Math.max(1, (double) (block.getPropertyValue(HEIGHT) / 2))));
    }

    /**
     * Returns whether snow layers receive random updates for melting checks.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }
}
