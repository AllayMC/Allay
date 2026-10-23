package org.allaymc.server.block.component.button;

import lombok.Getter;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.ButtonPressSound;
import org.allaymc.api.world.sound.ButtonReleaseSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.time.Duration;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.BUTTON_PRESSED_BIT;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;

/**
 * {@code BlockButtonBaseComponentImpl} implements attached buttons that emit redstone
 * power while their pressed bit is set. Player interaction starts an activation
 * interval, plays the press sound, and notifies neighboring circuits. Scheduled updates
 * release an active button and notify both the button's surroundings and its supporting
 * block.
 *
 * <p>
 * The pressed bit also controls admission of a new activation: pressing an already
 * active button does not replace its release deadline. {@code activationTime} supplies
 * the configured interval for the first press, while the position-based scheduler later
 * dispatches using the block present at that location. Removal of a pressed button
 * additionally notifies the support's neighborhood, where strong-power effects can
 * extend beyond the button's direct neighbors.
 *
 * @author Dhaiven
 */
public class BlockButtonBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * {@code activationTime} supplies the release delay used when a player first
     * presses an inactive button. The generated getter returns this same immutable
     * duration.
     */
    @Getter
    protected final Duration activationTime;

    /**
     * Creates a button component using the supplied release delay for activations. The
     * constructor retains {@code activationTime} unchanged, and later activation
     * forwards it to the update manager without duration normalization or
     * constructor-time validation.
     */
    public BlockButtonBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Duration activationTime) {
        super(blockType);
        this.activationTime = activationTime;
    }

    /**
     * Places a button facing away from the clicked support surface. Player placement
     * requires that surface to be full; {@code null} {@code placementInfo} skips
     * orientation and support validation and keeps the supplied state.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            BlockFace clickedFace = placementInfo.blockFace();
            blockState = blockState.setPropertyValue(FACING_DIRECTION, clickedFace.ordinal());

            BlockFace attachedFace = clickedFace.opposite();
            Vector3ic attachedPos = attachedFace.offsetPos(placeBlockPos);
            BlockState attachedState = dimension.getBlockState(attachedPos);
            if (!attachedState.getBlockStateData().collisionShape().isFull(clickedFace)) {
                return false;
            }
        }
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Checks the button's supporting face after inherited neighbor handling. Updates
     * from other sides are ignored, and loss of a full attachment surface asks the
     * block to break itself.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        BlockFace buttonFacing = BlockFace.fromIndex(block.getPropertyValue(FACING_DIRECTION));
        if (buttonFacing == null || face != buttonFacing.opposite()) {
            return;
        }

        if (!neighbor.getBlockStateData().collisionShape().isFull(buttonFacing)) {
            block.breakBlock();
        }
    }

    /**
     * Activates an unpressed button unless inherited interaction handling consumes the
     * action. When inherited handling declines, {@code interactInfo} must be non-{@code null}
     * to identify the clicked block. A new press requests the pressed state before
     * scheduling release and notifying circuits around both the button and its support.
     *
     * <p>
     * Pressing an already active snapshot still returns {@code true}, but does not
     * replace its release deadline or replay the sound. The release request targets the
     * position rather than retaining the block snapshot, so later scheduled dispatch
     * uses the block then present there.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var clickedBlockState = interactInfo.getClickedBlock();
        if (!clickedBlockState.getPropertyValue(BUTTON_PRESSED_BIT)) {
            clickedBlockState.updateBlockProperty(BUTTON_PRESSED_BIT, true);
            dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(clickedBlockState.getPosition(), getActivationTime());
            clickedBlockState.addSound(new ButtonPressSound(clickedBlockState.getBlockState()));

            dimension.updateAround(clickedBlockState.getPosition());

            // The support can relay strong power beyond the button's immediate
            // neighbors, so its surrounding circuits also need the changed state.
            BlockFace attachedFace = BlockFace.fromIndex(clickedBlockState.getPropertyValue(FACING_DIRECTION));
            if (attachedFace != null) {
                Vector3ic attachedPos = attachedFace.opposite().offsetPos(clickedBlockState.getPosition());
                dimension.updateAround(attachedPos);
            }
        }
        return true;
    }

    /**
     * Releases a button whose supplied snapshot is still pressed. The release plays its
     * sound and notifies circuits around both the button and its support; an already
     * unpressed snapshot has no effect.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        if (block.getPropertyValue(BUTTON_PRESSED_BIT)) {
            block.updateBlockProperty(BUTTON_PRESSED_BIT, false);
            block.addSound(new ButtonReleaseSound(block.getBlockState()));

            Dimension dimension = block.getDimension();
            dimension.updateAround(block.getPosition());

            // The support can relay strong power beyond the button's immediate
            // neighbors, so its surrounding circuits also need the changed state.
            BlockFace attachedFace = BlockFace.fromIndex(block.getPropertyValue(FACING_DIRECTION));
            if (attachedFace != null) {
                Vector3ic attachedPos = attachedFace.opposite().offsetPos(block.getPosition());
                dimension.updateAround(attachedPos);
            }
        }
    }

    /**
     * Notifies circuits around the supporting block after a pressed button is replaced.
     * Inherited replacement handling runs first; unpressed buttons require no
     * additional supporting-block notification from this override.
     */
    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);
        if (oldBlock.getPropertyValue(BUTTON_PRESSED_BIT)) {
            BlockFace attachedFace = BlockFace.fromIndex(oldBlock.getPropertyValue(FACING_DIRECTION));
            if (attachedFace != null) {
                Vector3ic attachedPos = attachedFace.opposite().offsetPos(oldBlock.getPosition());
                oldBlock.getDimension().updateAround(attachedPos);
            }
        }
    }

    /**
     * Returns maximum weak power while the button snapshot is pressed. Every queried
     * face receives the same result, and an unpressed snapshot returns {@code 0}
     * without querying surrounding blocks.
     */
    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return block.getPropertyValue(BUTTON_PRESSED_BIT) ? MAX_REDSTONE_POWER : 0;
    }

    /**
     * Returns maximum strong power only toward the supporting block of a pressed
     * button. Other faces, unpressed snapshots, and facing values that cannot identify
     * a face return {@code 0}.
     */
    @Override
    public int getStrongPower(Block block, BlockFace face) {
        if (!block.getPropertyValue(BUTTON_PRESSED_BIT)) {
            return 0;
        }

        BlockFace attachedFace = BlockFace.fromIndex(block.getPropertyValue(FACING_DIRECTION));
        if (attachedFace != null && face == attachedFace.opposite()) {
            return MAX_REDSTONE_POWER;
        }
        return 0;
    }
}
