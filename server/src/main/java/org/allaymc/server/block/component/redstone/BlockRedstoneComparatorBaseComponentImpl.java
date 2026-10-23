package org.allaymc.server.block.component.redstone;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.interfaces.BlockEntityComparator;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.Dependency;

import java.time.Duration;
import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * {@code BlockRedstoneComparatorBaseComponentImpl} evaluates directional comparator
 * input, side power, and compare or subtract mode, then stores output strength in its
 * block entity. Compare mode preserves input when it meets or exceeds side power;
 * subtract mode removes side power without returning a negative signal. Block types
 * separately identify powered and unpowered variants.
 *
 * <p>
 * Pending transitions use stored output to retain a short input detected while
 * unpowered. The component captures that value before scheduling but leaves an already
 * powered comparator's prior output intact until execution, allowing changed-strength
 * notifications to detect a difference. A powered variant with stable output sends no
 * extra notification. Input overrides can replace ordinary redstone readings, including
 * an override one block behind a solid input when the nearer reading is below maximum
 * power. Variant switches recreate block state before restoring output into the
 * corresponding entity.
 *
 * @author daoge_cmd
 */
public class BlockRedstoneComparatorBaseComponentImpl extends BlockRedstoneDiodeBaseComponentImpl {

    /**
     * {@code blockEntityHolderComponent} is injected before callbacks and resolves the
     * comparator entity holding captured or active {@code outputSignal} across delayed
     * update decisions.
     */
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityComparator> blockEntityHolderComponent;

    /**
     * Creates a comparator component with the supplied fixed powered-variant identity.
     */
    public BlockRedstoneComparatorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean powered) {
        super(blockType, powered);
    }

    /**
     * Returns the comparator entity at the supplied block's position through the
     * injected holder. A missing entity is created according to the holder's
     * loaded-chunk rules rather than represented by {@code null}.
     */
    protected BlockEntityComparator getBlockEntity(Block block) {
        return blockEntityHolderComponent.getBlockEntity(block.getPosition());
    }

    /**
     * Toggles compare/subtract mode unless inherited handling consumes the interaction.
     * A {@code null} {@code interactInfo} returns {@code false}; otherwise a fresh
     * world snapshot is used to request delayed output recalculation after the
     * mode-property write.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (interactInfo == null) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        boolean isSubtractMode = blockState.getPropertyValue(OUTPUT_SUBTRACT_BIT);

        dimension.updateBlockProperty(OUTPUT_SUBTRACT_BIT, !isSubtractMode, pos);

        var block = new Block(dimension.getBlockState(pos), new Position3i(pos, dimension));
        updateState(block);

        return true;
    }

    /**
     * Returns a single comparator item regardless of the registered powered variant.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.COMPARATOR.createItemStack());
    }

    /**
     * Schedules a comparator update only when calculated output differs from its stored
     * value and no update is already pending. An existing entry returns immediately
     * without refreshing captured input or replacing its deadline.
     *
     * <p>
     * An unpowered variant stores the newly calculated strength before scheduling,
     * preserving an input that may disappear during the delay. A powered variant keeps
     * its previous value instead, so scheduled execution can detect a changed output
     * and notify downstream circuits. The holder supplies storage for both captured and
     * active values.
     */
    @Override
    protected void updateState(Block block) {
        var manager = block.getDimension().getBlockUpdateManager();
        // A pending transition already owns its captured value and deadline.
        // Leave them intact so later notifications cannot erase or postpone the earlier pulse.
        if (manager.hasScheduledBlockUpdate(block.getPosition())) {
            return;
        }

        int newOutput = calculateOutput(block);
        int currentOutput = getBlockEntity(block).getOutputSignal();

        if (newOutput != currentOutput) {
            // Capture only an unpowered transition. Powered callbacks need the
            // old stored value to recognize a strength change and notify downstream circuits.
            if (!powered) {
                getBlockEntity(block).setOutputSignal(newOutput);
            }
            scheduleUpdate(block);
        }
    }

    /**
     * Applies a delayed comparator decision using newly calculated output and the
     * entity's stored strength. A powered comparator losing all output stores zero and
     * switches off. An unpowered comparator uses the greater of current and captured
     * output, retaining an earlier short pulse when present input has fallen.
     *
     * <p>
     * A latched activation schedules another update when present input is zero, so it
     * can release later. A powered comparator whose positive strength changes updates
     * its entity and notifies output neighbors without switching type. An unchanged
     * positive strength performs neither action, avoiding notifications that would
     * otherwise perpetuate stable feedback.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        int output = calculateOutput(block);
        var blockEntity = getBlockEntity(block);
        int currentOutput = blockEntity.getOutputSignal();
        boolean shouldBePowered = output > 0;

        if (powered && !shouldBePowered) {
            blockEntity.setOutputSignal(output);
            switchState(block, false, output);
        } else if (!powered) {
            // Keep the captured input when it outlasts the real pulse, so the
            // delayed transition still emits the strength that caused it to be scheduled.
            int effectiveOutput = Math.max(output, currentOutput);
            if (effectiveOutput > 0) {
                blockEntity.setOutputSignal(effectiveOutput);
                switchState(block, true, effectiveOutput);
                if (!shouldBePowered) {
                    scheduleUpdate(block);
                }
            }
        // Stable powered output needs no notification; reporting it again can
        // sustain feedback between comparators whose values have already settled.
        } else if (powered && output != currentOutput) {
            blockEntity.setOutputSignal(output);
            notifyOutputNeighbors(block);
        }
    }

    /**
     * Notifies circuits around the comparator and its output-side neighbor. This shared
     * path is used when strength changes without a powered-type transition, allowing
     * downstream readers to observe the new entity value.
     */
    protected void notifyOutputNeighbors(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        BlockFace outputFace = getFacing(block);
        updateNeighborsOnStateChange(dimension, pos, outputFace);
    }

    /**
     * Returns whether the comparator's currently calculated output is greater than
     * zero.
     */
    @Override
    protected boolean shouldBePowered(Block block) {
        return calculateOutput(block) > 0;
    }

    /**
     * Calculates output from rear input, side power, and the stored subtraction mode.
     * In subtract mode, side power reduces input and the result cannot fall below zero.
     * In compare mode, input survives unchanged only when it is at least the side
     * strength.
     *
     * <p>
     * The same rear reading can therefore produce a positive signal or zero depending
     * on the interacting side input and mode. Both modes leave output amplitude
     * available for delayed capture rather than reducing the result to a powered
     * boolean.
     */
    protected int calculateOutput(Block block) {
        int input = calculateInputStrength(block);
        int sidePower = getPowerOnSides(block);

        if (isSubtractMode(block)) {
            return Math.max(0, input - sidePower);
        } else {
            return input >= sidePower ? input : 0;
        }
    }

    /**
     * Returns side input from blocks tagged as power sources, broadening the base
     * diode-only rule. Wire supplies its stored signal and a redstone block supplies
     * maximum power; other accepted sources contribute strong power directed toward the
     * comparator.
     */
    @Override
    protected int getPowerFromSide(Block block, BlockFace side) {
        var sidePos = side.offsetPos(block.getPosition());
        var dimension = block.getDimension();
        BlockState sideState = dimension.getBlockState(sidePos);

        if (!sideState.getBlockType().hasBlockTag(BlockTags.POWER_SOURCE)) {
            return 0;
        }

        // Wire connectivity can suppress its directional output toward this
        // side; comparator side sampling uses the stored signal instead of that geometry filter.
        if (sideState.getBlockType() == BlockTypes.REDSTONE_WIRE) {
            return sideState.getPropertyValue(REDSTONE_SIGNAL);
        }

        // A redstone block supplies maximum weak power but inherits zero
        // strong power, so comparator side sampling recognizes that source explicitly.
        if (sideState.getBlockType() == BlockTypes.REDSTONE_BLOCK) {
            return MAX_REDSTONE_POWER;
        }

        Block sideBlock = new Block(sideState, new Position3i(sidePos, dimension));
        return sideState.getBehavior().getStrongPower(sideBlock, side.opposite());
    }

    /**
     * Returns whether the comparator snapshot selects subtraction rather than
     * comparison mode.
     */
    protected boolean isSubtractMode(Block block) {
        return block.getPropertyValue(OUTPUT_SUBTRACT_BIT);
    }

    /**
     * Calculates rear input while giving comparator overrides precedence over ordinary
     * redstone readings. The base diode calculation first combines direct, wire, and
     * conducted power at the adjacent input block. An override on that block replaces
     * the result even when it is lower.
     *
     * <p>
     * Without a nearer override, a solid input whose ordinary reading is below maximum
     * allows one further block to be inspected. An override there also replaces the
     * prior result; otherwise the ordinary reading survives. This precedence lets
     * stored block-specific values participate instead of always taking the maximum
     * redstone observation.
     */
    @Override
    protected int calculateInputStrength(Block block) {
        int power = super.calculateInputStrength(block);
        BlockFace inputFace = getInputFace(block);
        var inputPos = inputFace.offsetPos(block.getPosition());
        var dimension = block.getDimension();
        BlockState inputState = dimension.getBlockState(inputPos);
        Block inputBlock = new Block(inputState, new Position3i(inputPos, dimension));

        if (inputState.getBehavior().hasComparatorInputOverride()) {
            power = inputState.getBehavior().getComparatorInputOverride(inputBlock);
        } else if (power < MAX_REDSTONE_POWER && inputState.getBlockStateData().isSolid()) {
            var behindPos = inputFace.offsetPos(inputPos);
            BlockState behindState = dimension.getBlockState(behindPos);
            if (behindState.getBehavior().hasComparatorInputOverride()) {
                Block behindBlock = new Block(behindState, new Position3i(behindPos, dimension));
                power = behindState.getBehavior().getComparatorInputOverride(behindBlock);
            }
        }

        return power;
    }

    /**
     * Returns the fixed one-redstone-tick delay used for comparator transitions.
     */
    @Override
    protected Duration getDelay(Block block) {
        return BASE_DELAY;
    }

    /**
     * Returns the comparator entity's stored output for inherited directional power
     * queries.
     */
    @Override
    protected int getRedstoneSignal(Block block) {
        return getBlockEntity(block).getOutputSignal();
    }

    /**
     * Requests a comparator variant switch using freshly calculated output strength.
     * The overload preserving explicit output performs the replacement, restores entity
     * output, and notifies the output-side circuits.
     */
    @Override
    protected void switchState(Block block, boolean newPowered) {
        switchState(block, newPowered, calculateOutput(block));
    }

    /**
     * Requests the selected comparator variant while preserving facing and subtraction
     * mode. The requested output controls {@code OUTPUT_LIT_BIT} independently from
     * {@code newPowered}. World replacement runs before entity lookup because accepted
     * replacement can remove the previous block entity and create another one.
     *
     * <p>
     * The resolved entity then receives {@code outputSignal}, followed by output-side
     * notifications. The replacement result is not checked, so entity mutation and
     * notifications still occur after a refused update; this sequence does not provide
     * rollback or atomic publication across the state and entity.
     */
    protected void switchState(Block block, boolean newPowered, int outputSignal) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        MinecraftCardinalDirection direction = block.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        boolean subtractMode = block.getPropertyValue(OUTPUT_SUBTRACT_BIT);
        boolean outputLit = outputSignal > 0;

        BlockType<?> newType = newPowered ? BlockTypes.POWERED_COMPARATOR : BlockTypes.UNPOWERED_COMPARATOR;
        BlockState newState = newType.getDefaultState()
                .setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, direction)
                .setPropertyValue(OUTPUT_SUBTRACT_BIT, subtractMode)
                .setPropertyValue(OUTPUT_LIT_BIT, outputLit);

        dimension.setBlockState(pos.x(), pos.y(), pos.z(), newState);

        // Accepted type replacement recreates the comparator entity. Resolve it
        // afterward so captured output is restored into the entity that subsequent power queries read.
        getBlockEntity(new Block(newState, pos)).setOutputSignal(outputSignal);

        BlockFace outputFace = getFacing(block);
        updateNeighborsOnStateChange(dimension, pos, outputFace);
    }
}
