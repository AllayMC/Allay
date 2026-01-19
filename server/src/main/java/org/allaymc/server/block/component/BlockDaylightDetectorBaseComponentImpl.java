package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.time.Duration;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.REDSTONE_SIGNAL;

/**
 * Implementation of the daylight detector block.
 * Outputs redstone signal based on sky light level and time of day.
 *
 * @author daoge_cmd
 */
public class BlockDaylightDetectorBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final Duration UPDATE_INTERVAL = Duration.ofMillis(1000); // Update every second
    protected static final int TICKS_PER_DAY = 24000;
    protected static final int NOON = 6000;

    protected final boolean inverted;

    public BlockDaylightDetectorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean inverted) {
        super(blockType);
        this.inverted = inverted;
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        // Schedule first update
        scheduleUpdate(oldBlock.getDimension(), oldBlock.getPosition());
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }
        if (interactInfo == null) {
            return false;
        }

        // Toggle between normal and inverted
        var block = interactInfo.getClickedBlock();
        var pos = block.getPosition();
        int currentSignal = block.getPropertyValue(REDSTONE_SIGNAL);

        BlockType<?> newType = inverted ? BlockTypes.DAYLIGHT_DETECTOR : BlockTypes.DAYLIGHT_DETECTOR_INVERTED;
        BlockState newState = newType.getDefaultState().setPropertyValue(REDSTONE_SIGNAL, currentSignal);
        dimension.setBlockState(pos.x(), pos.y(), pos.z(), newState);

        // Update signal for the new type
        updateSignal(dimension, pos);

        return true;
    }

    @Override
    public void onScheduledUpdate(Block block) {
        updateSignal(block.getDimension(), block.getPosition());
        // Schedule next update
        scheduleUpdate(block.getDimension(), block.getPosition());
    }

    /**
     * Updates the redstone signal based on current light conditions.
     */
    protected void updateSignal(Dimension dimension, Vector3ic pos) {
        int newSignal = calculateSignal(dimension, pos);
        BlockState currentState = dimension.getBlockState(pos);
        int currentSignal = currentState.getPropertyValue(REDSTONE_SIGNAL);

        if (newSignal != currentSignal) {
            dimension.updateBlockProperty(REDSTONE_SIGNAL, newSignal, pos);
            dimension.updateAround(pos);
        }
    }

    /**
     * Calculates the redstone signal based on sky light and time of day.
     */
    protected int calculateSignal(Dimension dimension, Vector3ic pos) {
        // Get sky light at block position + 1 (above the detector)
        int skyLight = dimension.getLightEngine().getSkyLight(pos.x(), pos.y() + 1, pos.z());

        // Get time of day (0-24000)
        int timeOfDay = dimension.getWorld().getWorldData().getTimeOfDay() % TICKS_PER_DAY;

        // Calculate internal sky light based on time
        // During night (13000-23000), reduce the effective light level
        int internalLight = calculateInternalLight(skyLight, timeOfDay);

        // For inverted detector, invert the signal
        if (inverted) {
            return 15 - internalLight;
        }
        return internalLight;
    }

    /**
     * Calculates internal light level based on sky light and time of day.
     * This simulates how sunlight varies throughout the day.
     */
    protected int calculateInternalLight(int skyLight, int timeOfDay) {
        if (skyLight == 0) {
            return 0;
        }

        // Calculate sun angle factor (0.0 to 1.0)
        // Noon (6000) = 1.0, Midnight (18000) = 0.0
        double sunFactor = getSunFactor(timeOfDay);

        // Apply sun factor to sky light
        int effectiveLight = (int) Math.round(skyLight * sunFactor);

        return Math.clamp(effectiveLight, 0, 15);
    }

    /**
     * Gets the sun factor based on time of day.
     * Returns 1.0 at noon, 0.0 at midnight, with smooth transitions.
     */
    protected double getSunFactor(int timeOfDay) {
        // Day: 0-12000, Night: 12000-24000
        // Noon is at 6000, Midnight at 18000
        // Dawn: ~23000-24000 and 0-1000
        // Dusk: ~11000-13000

        if (timeOfDay >= 0 && timeOfDay < 12000) {
            // Day time - calculate based on distance from noon
            double distFromNoon = Math.abs(timeOfDay - NOON);
            return 1.0 - (distFromNoon / 6000.0) * 0.5; // 0.5 to 1.0 during day
        } else {
            // Night time (12000-24000)
            double nightProgress = (timeOfDay - 12000) / 12000.0;
            if (nightProgress < 0.5) {
                // Dusk to midnight: 0.5 -> 0
                return 0.5 - nightProgress;
            } else {
                // Midnight to dawn: 0 -> 0.5
                return nightProgress - 0.5;
            }
        }
    }

    protected void scheduleUpdate(Dimension dimension, Vector3ic pos) {
        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, UPDATE_INTERVAL);
    }

    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return block.getPropertyValue(REDSTONE_SIGNAL);
    }
}
