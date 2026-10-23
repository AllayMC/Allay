package org.allaymc.server.block.component.redstone;

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
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3dc;

import java.time.Duration;

/**
 * {@code BlockTargetBaseComponentImpl} turns projectile hits into temporary weak
 * redstone output stored in a target block entity. Projectile kind selects the
 * duration, while the hit position determines strength. Arrows, thrown tridents, and
 * small fireballs use the longer interval; other projectiles use the default interval.
 *
 * <p>
 * The calculation chooses the nearest block face, projects the hit onto that face, and
 * averages two center-weighted coordinates. A center hit reaches maximum power, while a
 * face corner yields zero and does not activate the target. Activation replaces the
 * position's scheduled release and stores the new strength; neighbors are notified only
 * when that strength changes. Scheduled callbacks clear the block entity's signal
 * rather than changing the target's visible block state.
 *
 * @author daoge_cmd
 */
public class BlockTargetBaseComponentImpl extends BlockBaseComponentImpl {

    // onProjectileHit begins with DEFAULT_SIGNAL_DURATION_TICKS, then selects
    // ARROW_SIGNAL_DURATION_TICKS for arrows, thrown tridents, and small fireballs.
    // activatePower converts either game-tick count into the scheduled release duration.
    /**
     * Default projectile pulse duration, measured in game ticks.
     */
    private static final int DEFAULT_SIGNAL_DURATION_TICKS = 8;
    /**
     * Extended projectile pulse duration, measured in game ticks.
     */
    private static final int ARROW_SIGNAL_DURATION_TICKS = 20;

    /**
     * {@code blockEntityHolderComponent} is injected before callbacks run and resolves
     * the target entity whose retained {@code activePower} supplies weak output and
     * scheduled release state.
     */
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityTarget> blockEntityHolderComponent;

    /**
     * Creates the target component for the supplied registered block type.
     */
    public BlockTargetBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns the target block entity's active power for every queried face. Entity
     * lookup uses the injected holder, which creates a missing entity according to its
     * loaded-chunk contract.
     */
    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return blockEntityHolderComponent.getBlockEntity(block.getPosition()).getActivePower();
    }

    /**
     * Activates the target from a projectile's world-space hit position. Arrows, thrown
     * tridents, and small fireballs select the longer duration; other kinds use the
     * default. A calculated zero strength leaves existing output unchanged.
     */
    @Override
    public void onProjectileHit(Block block, EntityProjectile projectile, Vector3dc hitPos) {
        int durationTicks = DEFAULT_SIGNAL_DURATION_TICKS;

        if (projectile instanceof EntityArrow ||
            projectile instanceof EntityThrownTrident ||
            projectile instanceof EntitySmallFireball) {
            durationTicks = ARROW_SIGNAL_DURATION_TICKS;
        }

        int power = calculatePowerFromHitPosition(block, hitPos);

        if (power > 0) {
            activatePower(block, power, durationTicks);
        }
    }

    /**
     * Calculates target power by projecting a world-space hit onto the nearest block
     * face. Relative coordinates identify that face, and its two in-plane coordinates
     * are normalized so the center contributes one and either edge contributes zero.
     *
     * <p>
     * The average of those contributions scales {@code MAX_REDSTONE_POWER} and rounds
     * upward. A face-center hit yields maximum power, while both edge coordinates at a
     * corner yield zero. This calculation uses coordinate-wise weighting rather than
     * Euclidean distance and does not use the projectile's travel direction.
     */
    protected int calculatePowerFromHitPosition(Block block, Vector3dc hitPos) {
        double relX = hitPos.x() - block.getPosition().x();
        double relY = hitPos.y() - block.getPosition().y();
        double relZ = hitPos.z() - block.getPosition().z();

        BlockFace hitFace = determineHitFace(relX, relY, relZ);
        double[] coords = get2DCoordinates(hitFace, relX, relY, relZ);

        double coord0 = normalizeCoordinate(coords[0]);
        double coord1 = normalizeCoordinate(coords[1]);

        // Averaging the in-plane weights makes both axes contribute equally:
        // a corner contributes zero on both, while a face center contributes one.
        double scale = (coord0 + coord1) / 2.0;

        // Round nonzero fractional strength upward so near-edge hits can still activate.
        return (int) Math.ceil(MAX_REDSTONE_POWER * scale);
    }

    /**
     * Returns a center-weighted scale for one face coordinate. The center {@code 0.5}
     * maps to {@code 1.0}, coordinates at or beyond either edge map to {@code 0.0}, and
     * interior values vary linearly.
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
     * Selects the nearest boundary plane from coordinates relative to the block origin.
     * Each coordinate and its distance from one contribute a candidate distance.
     * Callers normally supply a hit on the block's unit cube surface.
     *
     * <p>
     * Equal distances resolve in the fixed order up, down, south, north, east, then
     * west. That precedence matters at edges and corners because the chosen face
     * determines which two coordinates contribute to signal strength; no
     * projectile-direction data participates in this selection.
     */
    private BlockFace determineHitFace(double relX, double relY, double relZ) {
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
     * Returns a new pair containing the hit coordinates lying in the selected face's
     * plane. Opposite faces share their axis pair, and {@code face} must be non-{@code null}
     * for the exhaustive switch.
     */
    private double[] get2DCoordinates(BlockFace face, double relX, double relY, double relZ) {
        return switch (face) {
            case UP, DOWN -> new double[]{relX, relZ};
            case NORTH, SOUTH -> new double[]{relX, relY};
            case EAST, WEST -> new double[]{relY, relZ};
        };
    }

    /**
     * Stores target power and requests a release after the supplied number of game
     * ticks. Nonpositive {@code power} or {@code durationTicks} delegates to immediate
     * deactivation. Positive power is passed to the block entity without an upper-range
     * clamp in this helper.
     *
     * <p>
     * The new schedule replaces any existing entry for the position, so a later hit can
     * reset the release deadline even when strength is unchanged. The previous power
     * determines whether neighbors need notification after storage. The delay uses
     * fifty milliseconds per game tick, matching the manager's duration conversion.
     */
    protected void activatePower(Block block, int power, int durationTicks) {
        if (power <= 0 || durationTicks <= 0) {
            deactivatePower(block);
            return;
        }

        var dimension = block.getDimension();
        var pos = block.getPosition();
        var blockEntity = blockEntityHolderComponent.getBlockEntity(pos);
        int previousPower = blockEntity.getActivePower();

        var updateManager = dimension.getBlockUpdateManager();
        // The chunk scheduler keeps one entry per position, so this request
        // replaces an earlier target release and lets the latest positive hit reset its lifetime.
        updateManager.scheduleBlockUpdateInDelay(pos, Duration.ofMillis(durationTicks * 50L));

        blockEntity.setActivePower(power);

        if (previousPower != power) {
            dimension.updateAround(pos);
        }
    }

    /**
     * Clears the target block entity's active power and notifies neighbors only when
     * the old value was nonzero. The helper does not remove any separately pending
     * scheduled entry at the position.
     */
    protected void deactivatePower(Block block) {
        var blockEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        int currentPower = blockEntity.getActivePower();
        blockEntity.setActivePower(0);

        if (currentPower != 0) {
            block.getDimension().updateAround(block.getPosition());
        }
    }

    /**
     * Clears the target signal when its position's scheduled update arrives. The
     * callback uses the entity currently resolved by the holder and does not compare
     * the event with a particular earlier projectile hit.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        deactivatePower(block);
    }
}
