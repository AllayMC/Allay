package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityTarget;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.entity.interfaces.EntitySmallFireball;
import org.allaymc.api.entity.interfaces.EntityThrownTrident;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3dc;

import java.time.Duration;

/**
 * Implementation of the target block behavior.
 * The target block outputs redstone power when hit by projectiles,
 * with power strength based on distance from center.
 *
 * @author daoge_cmd
 */
public class BlockTargetBaseComponentImpl extends BlockBaseComponentImpl {

    private static final int DEFAULT_SIGNAL_DURATION_TICKS = 8;
    private static final int ARROW_SIGNAL_DURATION_TICKS = 20;

    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityTarget> blockEntityHolderComponent;

    public BlockTargetBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean isPowerSource() {
        return true;
    }

    @Override
    public int getWeakPower(Block block, BlockFace face) {
        var blockEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        return blockEntity != null ? blockEntity.getActivePower() : 0;
    }

    @Override
    public void onProjectileHit(Block block, EntityProjectile projectile, Vector3dc hitPos) {
        int durationTicks = DEFAULT_SIGNAL_DURATION_TICKS;

        // Arrows, tridents, and fireballs have longer signal duration
        if (projectile instanceof EntityArrow ||
            projectile instanceof EntityThrownTrident ||
            projectile instanceof EntitySmallFireball) {
            durationTicks = ARROW_SIGNAL_DURATION_TICKS;
        }

        // Calculate power based on distance from center
        int power = calculatePowerFromHitPosition(block, hitPos);

        if (power > 0) {
            activatePower(block, power, durationTicks);
        }
    }

    /**
     * Calculates the redstone power based on how close the hit was to the center of the block.
     * A hit at the exact center gives power 15, while hits at edges give less power.
     */
    protected int calculatePowerFromHitPosition(Block block, Vector3dc hitPos) {
        // Get relative position within the block (0.0 to 1.0)
        double relX = hitPos.x() - block.getPosition().x();
        double relY = hitPos.y() - block.getPosition().y();
        double relZ = hitPos.z() - block.getPosition().z();

        // Determine which face was hit and calculate 2D distance from center
        BlockFace hitFace = determineHitFace(relX, relY, relZ);
        double[] coords = get2DCoordinates(hitFace, relX, relY, relZ);

        // Calculate distance from center (0.5, 0.5) for each coordinate
        double coord0 = normalizeCoordinate(coords[0]);
        double coord1 = normalizeCoordinate(coords[1]);

        // Average the two normalized coordinates to get a scale factor (0 to 1)
        double scale = (coord0 + coord1) / 2.0;

        // Convert to power (1-15), with center hits giving max power
        return (int) Math.ceil(MAX_REDSTONE_POWER * scale);
    }

    /**
     * Normalizes a coordinate from [0,1] to a distance-from-center scale.
     * 0.5 (center) returns 1.0, edges (0 or 1) return 0.0
     */
    private double normalizeCoordinate(double coord) {
        if (coord == 0.5) {
            return 1.0;
        } else if (coord <= 0 || coord >= 1) {
            return 0.0;
        } else if (coord < 0.5) {
            return coord * 2.0;
        } else {
            return (coord / (-0.5)) + 2.0;
        }
    }

    /**
     * Determines which face of the block was hit based on the relative position.
     */
    private BlockFace determineHitFace(double relX, double relY, double relZ) {
        // Find which coordinate is closest to an edge (0 or 1)
        double distToXMin = relX;
        double distToXMax = 1 - relX;
        double distToYMin = relY;
        double distToYMax = 1 - relY;
        double distToZMin = relZ;
        double distToZMax = 1 - relZ;

        double minDist = Math.min(distToXMin, Math.min(distToXMax,
                Math.min(distToYMin, Math.min(distToYMax,
                        Math.min(distToZMin, distToZMax)))));

        if (minDist == distToYMax) return BlockFace.UP;
        if (minDist == distToYMin) return BlockFace.DOWN;
        if (minDist == distToZMax) return BlockFace.SOUTH;
        if (minDist == distToZMin) return BlockFace.NORTH;
        if (minDist == distToXMax) return BlockFace.EAST;
        return BlockFace.WEST;
    }

    /**
     * Gets the 2D coordinates on the hit face.
     */
    private double[] get2DCoordinates(BlockFace face, double relX, double relY, double relZ) {
        return switch (face) {
            case UP, DOWN -> new double[]{relX, relZ};
            case NORTH, SOUTH -> new double[]{relX, relY};
            case EAST, WEST -> new double[]{relY, relZ};
        };
    }

    /**
     * Activates the target block's power output.
     */
    protected void activatePower(Block block, int power, int durationTicks) {
        if (power <= 0 || durationTicks <= 0) {
            deactivatePower(block);
            return;
        }

        var dimension = block.getDimension();
        var pos = block.getPosition();
        var blockEntity = blockEntityHolderComponent.getBlockEntity(pos);

        if (blockEntity == null) {
            return;
        }

        int previousPower = blockEntity.getActivePower();

        // Schedule update to deactivate power after the duration
        var updateManager = dimension.getBlockUpdateManager();
        updateManager.scheduleBlockUpdateInDelay(pos, Duration.ofMillis(durationTicks * 50L));

        // Update power
        blockEntity.setActivePower(power);

        // Update neighbors if power changed
        if (previousPower != power) {
            dimension.updateAround(pos);
        }
    }

    /**
     * Deactivates the target block's power output.
     */
    protected void deactivatePower(Block block) {
        var blockEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (blockEntity != null) {
            int currentPower = blockEntity.getActivePower();
            blockEntity.setActivePower(0);

            if (currentPower != 0) {
                block.getDimension().updateAround(block.getPosition());
            }
        }
    }

    @Override
    public void onScheduledUpdate(Block block) {
        deactivatePower(block);
    }
}
