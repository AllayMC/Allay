package org.allaymc.server.block.component.redstone;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.time.Duration;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.REDSTONE_SIGNAL;

/**
 * {@code BlockRedstoneDiodeBaseComponentImpl} supplies directional input, output,
 * support checks, and delayed transitions for repeaters and comparators. The stored
 * cardinal direction identifies the input side, so the output face is its opposite.
 * Each registered powered or unpowered variant carries a fixed {@code powered} identity
 * in its component.
 *
 * <p>
 * Neighbor changes schedule a transition when calculated input disagrees with that
 * identity, unless a subclass reports side locking. A scheduled callback on an
 * unpowered variant turns it on even when the original input pulse has disappeared,
 * then schedules release if needed. This latch lets a delayed transition preserve a
 * short detected input. Subclasses supply delay, output strength, and variant
 * replacement, while shared input sampling combines direct weak power, wire signal, and
 * strong power conducted through a solid input block.
 *
 * @author daoge_cmd
 */
public abstract class BlockRedstoneDiodeBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code BASE_DELAY} represents one redstone tick as two game ticks and supplies
     * the common delay unit for diode subclasses.
     */
    protected static final Duration BASE_DELAY = Duration.ofMillis(100);

    /**
     * {@code powered} identifies the registered diode variant owning this component.
     * Transition callbacks compare current input with that fixed identity rather than a
     * mutable powered field.
     */
    protected final boolean powered;

    /**
     * Creates a diode component with the supplied fixed powered-variant identity.
     */
    public BlockRedstoneDiodeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean powered) {
        super(blockType);
        this.powered = powered;
    }

    /**
     * Places a diode on a block with a full upper collision surface. Player placement
     * stores the opposite horizontal player facing as the input direction; {@code null}
     * {@code placementInfo} preserves the supplied direction.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        BlockState below = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!canSupportRedstoneDiode(below)) {
            return false;
        }

        if (placementInfo != null) {
            BlockFace playerFacing = placementInfo.player().getHorizontalFace().opposite();
            blockState = blockState.setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, playerFacing.toMinecraftCardinalDirection());
        }

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Schedules a delayed update when a newly placed diode currently should be powered.
     * Inherited placement handling runs first, and the new state supplies the snapshot
     * used for input evaluation.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        var newBlock = new Block(newBlockState, oldBlock.getPosition());
        if (shouldBePowered(newBlock)) {
            scheduleUpdate(newBlock);
        }
    }

    /**
     * Applies inherited handling, breaks a diode that loses support, and otherwise
     * checks its delayed state. A locked diode skips recalculation, while support loss
     * returns immediately after requesting a break.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN && !canSupportRedstoneDiode(neighbor.getBlockState())) {
            block.breakBlock();
            return;
        }

        if (!isLocked(block)) {
            updateState(block);
        }
    }

    /**
     * Schedules a delayed transition when current input disagrees with this variant's
     * {@code powered} identity. This base implementation does not guard against an
     * existing schedule, so the manager can replace its deadline.
     */
    protected void updateState(Block block) {
        boolean shouldBePowered = shouldBePowered(block);
        if (powered != shouldBePowered) {
            scheduleUpdate(block);
        }
    }

    /**
     * Applies a delayed transition unless the diode is currently locked. Powered
     * variants turn off only when current input no longer requires power. Unpowered
     * variants turn on whenever their scheduled callback executes, even if the input
     * that caused scheduling has disappeared.
     *
     * <p>
     * When that input has vanished, the newly activated position receives another
     * delayed update to turn off later. This ordering retains a short input as an
     * output pulse instead of discarding it during the delay. Subclass replacement
     * determines the new state and neighbor notifications.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        if (isLocked(block)) {
            return;
        }

        boolean shouldBePowered = shouldBePowered(block);

        if (powered && !shouldBePowered) {
            switchState(block, false);
        } else if (!powered) {
            // The pending update records an earlier positive input. Activate
            // before considering release so a short pulse is not lost during the delay.
            switchState(block, true);
            if (!shouldBePowered) {
                scheduleUpdate(block);
            }
        }
    }

    /**
     * Notifies surrounding circuits after a powered diode is replaced. Inherited
     * replacement handling runs first, and the output-side neighborhood also receives
     * notification because it may conduct power beyond the diode's immediate neighbors.
     */
    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);

        if (powered) {
            var dimension = oldBlock.getDimension();
            var pos = oldBlock.getPosition();

            dimension.updateAround(pos);

            BlockFace outputFace = getFacing(oldBlock);
            dimension.updateAround(outputFace.offsetPos(pos));
        }
    }

    /**
     * Returns subclass signal strength only on the output face of a powered variant.
     * Unpowered variants and other faces return {@code 0}, using the supplied
     * snapshot's direction to locate output.
     */
    @Override
    public int getWeakPower(Block block, BlockFace face) {
        if (!powered) {
            return 0;
        }

        BlockFace outputFace = getFacing(block);
        if (face == outputFace) {
            return getRedstoneSignal(block);
        }

        return 0;
    }

    /**
     * Returns the same directional strength as {@code getWeakPower}. Powered diode
     * output can therefore strongly power its front neighbor, while the other faces and
     * unpowered variants return {@code 0}.
     */
    @Override
    public int getStrongPower(Block block, BlockFace face) {
        return getWeakPower(block, face);
    }

    /**
     * Returns the output face opposite the stored cardinal input direction. Callers use
     * this reversal for emitted power and output-side notifications because the block
     * property itself represents the diode's back face.
     */
    protected BlockFace getFacing(Block block) {
        MinecraftCardinalDirection direction = block.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        return BlockFace.from(direction).opposite();
    }

    /**
     * Returns the back face encoded by the stored cardinal direction.
     */
    protected BlockFace getInputFace(Block block) {
        MinecraftCardinalDirection direction = block.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        return BlockFace.from(direction);
    }

    /**
     * Returns whether the calculated back-side input strength is greater than zero.
     */
    protected boolean shouldBePowered(Block block) {
        return calculateInputStrength(block) > 0;
    }

    /**
     * Combines power observations at the diode's back-side input position. The input
     * behavior first supplies weak power toward the diode. A redstone wire additionally
     * contributes its stored signal, and a solid input block can contribute conducted
     * strong power.
     *
     * <p>
     * Each additional observation can only increase the result. Strong-power sampling
     * is skipped once maximum power is already reached and excludes the face pointing
     * back toward this diode, preventing its own output-side relationship from being
     * counted through that excluded face.
     */
    protected int calculateInputStrength(Block block) {
        BlockFace inputFace = getInputFace(block);
        Vector3ic inputPos = inputFace.offsetPos(block.getPosition());

        var dimension = block.getDimension();
        BlockState inputState = dimension.getBlockState(inputPos);
        Block inputBlock = new Block(inputState, new Position3i(inputPos, dimension));

        int power = inputState.getBehavior().getWeakPower(inputBlock, inputFace.opposite());

        if (inputState.getBlockType() == BlockTypes.REDSTONE_WIRE) {
            int wireSignal = inputState.getPropertyValue(REDSTONE_SIGNAL);
            power = Math.max(power, wireSignal);
        }

        if (power < MAX_REDSTONE_POWER && inputState.getBlockStateData().isSolid()) {
            // Exclude the side facing this diode when sampling conducted power,
            // so the input block does not feed this diode's adjacent contribution back into itself.
            int strongPower = dimension.getStrongPowerAt(new Position3i(inputPos, dimension), inputFace.opposite());
            power = Math.max(power, strongPower);
        }

        return power;
    }

    /**
     * Returns {@code false} for the shared diode implementation. Subclasses may report
     * side locking to suppress neighbor-driven state checks and scheduled transitions
     * while leaving support checks active.
     */
    protected boolean isLocked(Block block) {
        return false;
    }

    /**
     * Returns the greater accepted input from the two horizontal sides of the diode.
     * Both positions are derived by rotating the output face, so side lookup stays
     * perpendicular to the stored input-output axis. The two values are compared rather
     * than added.
     *
     * <p>
     * {@code getPowerFromSide} determines accepted source types and can be overridden
     * by comparator behavior. Repeaters interpret any positive result as a lock, while
     * comparators use this same maximum to compare against or subtract from their rear
     * input.
     */
    protected int getPowerOnSides(Block block) {
        BlockFace facing = getFacing(block);
        BlockFace left = facing.rotateYCCW();
        BlockFace right = facing.rotateY();

        int leftPower = getPowerFromSide(block, left);
        int rightPower = getPowerFromSide(block, right);

        return Math.max(leftPower, rightPower);
    }

    /**
     * Returns strong power directed toward this diode from another diode on the
     * specified side. Non-diode blocks return {@code 0}, including ordinary sources
     * that a comparator override may choose to accept.
     */
    protected int getPowerFromSide(Block block, BlockFace side) {
        Vector3ic sidePos = side.offsetPos(block.getPosition());
        var dimension = block.getDimension();
        BlockState sideState = dimension.getBlockState(sidePos);

        if (!isDiode(sideState)) {
            return 0;
        }

        Block sideBlock = new Block(sideState, new Position3i(sidePos, dimension));
        return sideState.getBehavior().getStrongPower(sideBlock, side.opposite());
    }

    /**
     * Returns whether the state is a powered or unpowered repeater or comparator.
     */
    protected boolean isDiode(BlockState state) {
        var type = state.getBlockType();
        return type == BlockTypes.POWERED_REPEATER ||
               type == BlockTypes.UNPOWERED_REPEATER ||
               type == BlockTypes.POWERED_COMPARATOR ||
               type == BlockTypes.UNPOWERED_COMPARATOR;
    }

    /**
     * Requests a position-based callback after the subclass-selected delay. A new
     * request can replace the chunk's existing entry for that position, and later
     * dispatch uses the block present there at execution.
     */
    protected void scheduleUpdate(Block block) {
        block.scheduleUpdateInDelay(getDelay(block));
    }

    /**
     * Notifies the diode's surroundings and the neighborhood beyond its output face.
     * The second notification lets a front block relay a changed signal to circuits
     * beyond the diode's immediate adjacency.
     */
    protected void updateNeighborsOnStateChange(Dimension dimension, Vector3ic pos, BlockFace outputFace) {
        dimension.updateAround(pos);
        dimension.updateAround(outputFace.offsetPos(pos));
    }

    /**
     * Returns the delay used when scheduling this diode's transitions. Implementations
     * may derive it from the supplied snapshot, and the block update manager converts
     * the duration into whole game ticks.
     */
    protected abstract Duration getDelay(Block block);

    /**
     * Returns the output strength supplied by this diode's powered variant.
     */
    protected abstract int getRedstoneSignal(Block block);

    /**
     * Replaces the diode with the requested powered or unpowered variant and notifies
     * affected output circuits. Implementations preserve the direction and
     * variant-specific settings needed by later callbacks and power queries.
     */
    protected abstract void switchState(Block block, boolean newPowered);

    /**
     * Returns whether the supplied state has a full upper collision surface. Diode
     * placement and support-loss checks use this surface rule rather than the block's
     * general solid-state classification.
     */
    protected boolean canSupportRedstoneDiode(BlockState blockState) {
        return blockState.getBlockStateData().collisionShape().isFull(BlockFace.UP);
    }
}
