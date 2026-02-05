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
 * @author Dhaiven
 */
public class BlockButtonBaseComponentImpl extends BlockBaseComponentImpl {
    @Getter
    protected final Duration activationTime;

    public BlockButtonBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Duration activationTime) {
        super(blockType);
        this.activationTime = activationTime;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            BlockFace clickedFace = placementInfo.blockFace();
            blockState = blockState.setPropertyValue(FACING_DIRECTION, clickedFace.ordinal());

            // Verify the attachment block has a full surface on the attached face
            BlockFace attachedFace = clickedFace.opposite();
            Vector3ic attachedPos = attachedFace.offsetPos(placeBlockPos);
            BlockState attachedState = dimension.getBlockState(attachedPos);
            if (!attachedState.getBlockStateData().collisionShape().isFull(clickedFace)) {
                return false;
            }
        }
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        // Check if the neighbor is the attached block
        BlockFace buttonFacing = BlockFace.fromIndex(block.getPropertyValue(FACING_DIRECTION));
        if (buttonFacing == null || face != buttonFacing.opposite()) {
            return;
        }

        // Break if the attached block no longer has a full surface
        if (!neighbor.getBlockStateData().collisionShape().isFull(buttonFacing)) {
            block.breakBlock();
        }
    }

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

            // Trigger neighbor updates for power propagation
            dimension.updateAround(clickedBlockState.getPosition());

            // Also update the block the button is attached to
            BlockFace attachedFace = BlockFace.fromIndex(clickedBlockState.getPropertyValue(FACING_DIRECTION));
            if (attachedFace != null) {
                Vector3ic attachedPos = attachedFace.opposite().offsetPos(clickedBlockState.getPosition());
                dimension.updateAround(attachedPos);
            }
        }
        return true;
    }

    @Override
    public void onScheduledUpdate(Block block) {
        if (block.getPropertyValue(BUTTON_PRESSED_BIT)) {
            block.updateBlockProperty(BUTTON_PRESSED_BIT, false);
            block.addSound(new ButtonReleaseSound(block.getBlockState()));

            // Trigger neighbor updates when button releases
            Dimension dimension = block.getDimension();
            dimension.updateAround(block.getPosition());

            // Also update the block the button is attached to
            BlockFace attachedFace = BlockFace.fromIndex(block.getPropertyValue(FACING_DIRECTION));
            if (attachedFace != null) {
                Vector3ic attachedPos = attachedFace.opposite().offsetPos(block.getPosition());
                dimension.updateAround(attachedPos);
            }
        }
    }

    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);
        // If button was pressed when removed, update around attached block
        if (oldBlock.getPropertyValue(BUTTON_PRESSED_BIT)) {
            BlockFace attachedFace = BlockFace.fromIndex(oldBlock.getPropertyValue(FACING_DIRECTION));
            if (attachedFace != null) {
                Vector3ic attachedPos = attachedFace.opposite().offsetPos(oldBlock.getPosition());
                oldBlock.getDimension().updateAround(attachedPos);
            }
        }
    }

    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return block.getPropertyValue(BUTTON_PRESSED_BIT) ? MAX_REDSTONE_POWER : 0;
    }

    @Override
    public int getStrongPower(Block block, BlockFace face) {
        if (!block.getPropertyValue(BUTTON_PRESSED_BIT)) {
            return 0;
        }

        // Button provides strong power to the block it's attached to
        BlockFace attachedFace = BlockFace.fromIndex(block.getPropertyValue(FACING_DIRECTION));
        if (attachedFace != null && face == attachedFace.opposite()) {
            return MAX_REDSTONE_POWER;
        }
        return 0;
    }
}
