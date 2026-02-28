package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.interfaces.BlockShelfBehavior;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityShelf;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.eventbus.event.block.ShelfUseEvent;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockShelfBaseComponentImpl extends BlockBaseComponentImpl {

    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityShelf> blockEntityHolderComponent;

    public BlockShelfBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        // Check redstone power immediately after placement
        if (oldBlock.isPowered()) {
            var dimension = oldBlock.getDimension();
            var pos = oldBlock.getPosition();
            dimension.updateBlockProperty(BlockPropertyTypes.POWERED_BIT, true, pos);
            updateChainConnections(dimension, pos);
            dimension.addSound(MathUtils.center(pos), SimpleSound.ACTIVATED);
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var player = interactInfo.player();
        if (player.isSneaking()) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        var facing = blockState.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);
        var facingBlockFace = BlockFace.from(facing);

        // Only interact when clicking the front face
        if (interactInfo.blockFace() != facingBlockFace) {
            return false;
        }

        // Check click Y is within shelf area
        var clickedPos = interactInfo.clickedPos();
        if (clickedPos.y() < 0.25f || clickedPos.y() > 0.75f) {
            return false;
        }

        // Calculate slot from click position
        int slot = calculateSlot(facing, clickedPos.x(), clickedPos.z());

        var shelf = blockEntityHolderComponent.getBlockEntity(pos.x(), pos.y(), pos.z(), dimension);
        if (shelf == null) {
            return false;
        }

        var clickedBlock = new Block(dimension, pos);
        boolean powered = blockState.getPropertyValue(BlockPropertyTypes.POWERED_BIT);
        if (powered) {
            var event = new ShelfUseEvent(clickedBlock, player, ShelfUseEvent.Action.MULTI_SWAP);
            if (!event.call()) {
                return false;
            }
            performMultiSwap(dimension, pos, blockState, facing, player);
            dimension.addSound(MathUtils.center(pos), SimpleSound.SHELF_SWAP_MULTI);
        } else {
            var event = new ShelfUseEvent(clickedBlock, player, ShelfUseEvent.Action.SINGLE_SWAP);
            if (!event.call()) {
                return false;
            }
            var heldItem = player.getItemInHand();
            Container shelfContainer = shelf.getContainer();
            if (player.getGameMode() == GameMode.CREATIVE) {
                // Creative mode: replace slot item with a copy of the held item, no swap
                var newItem = (heldItem != null && heldItem != ItemAirStack.AIR_STACK) ? heldItem.copy() : ItemAirStack.AIR_STACK;
                shelfContainer.setItemStack(slot, newItem);
            } else {
                // Survival/Adventure: directly swap entire hand stack with slot
                var oldItem = shelfContainer.getItemStack(slot);
                shelfContainer.setItemStack(slot, heldItem != null ? heldItem : ItemAirStack.AIR_STACK);
                player.setItemInHand(oldItem);
            }
            dimension.addSound(MathUtils.center(pos), SimpleSound.SHELF_SWAP_SINGLE);
        }
        return true;
    }

    private int calculateSlot(MinecraftCardinalDirection facing, float clickX, float clickZ) {
        // Use the same rotateYCCW + dot product approach as PNX to determine
        // the horizontal position along the shelf face from left to right
        var offset = BlockFace.from(facing).rotateYCCW().getOffset();
        float distance = clickX * offset.x() + clickZ * offset.z();
        if (distance < 0) distance += 1.0f;
        return distance < (1 / 3f) ? 0 : distance < (2 / 3f) ? 1 : 2;
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var blockState = block.getBlockState();
        boolean currentPowered = blockState.getPropertyValue(BlockPropertyTypes.POWERED_BIT);
        boolean shouldBePowered = block.isPowered();

        if (currentPowered == shouldBePowered) {
            return;
        }

        var dimension = block.getDimension();
        var pos = block.getPosition();

        if (shouldBePowered) {
            // Rising edge - activate
            dimension.updateBlockProperty(BlockPropertyTypes.POWERED_BIT, true, pos);
            updateChainConnections(dimension, pos);
            dimension.addSound(MathUtils.center(pos), SimpleSound.ACTIVATED);
        } else {
            // Falling edge - deactivate
            dimension.updateBlockProperty(BlockPropertyTypes.POWERED_BIT, false, pos);
            dimension.updateBlockProperty(BlockPropertyTypes.POWERED_SHELF_TYPE, 0, pos);
            // Update neighbors that might be in the same chain
            updateNeighborChains(dimension, pos);
            dimension.addSound(MathUtils.center(pos), SimpleSound.DEACTIVATED);
        }
    }

    private void updateChainConnections(Dimension dimension, Vector3ic pos) {
        var blockState = dimension.getBlockState(pos);
        var facing = blockState.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);

        // Collect the full chain including this block
        var chain = collectChain(dimension, pos, facing);

        // Assign POWERED_SHELF_TYPE to each shelf in the chain
        for (int i = 0; i < chain.size(); i++) {
            int type;
            if (chain.size() == 1) {
                type = 0; // UNCONNECTED
            } else if (i == 0) {
                type = 3; // LEFT
            } else if (i == chain.size() - 1) {
                type = 1; // RIGHT
            } else {
                type = 2; // CENTER
            }
            dimension.updateBlockProperty(BlockPropertyTypes.POWERED_SHELF_TYPE, type, chain.get(i));
        }
    }

    private void updateNeighborChains(Dimension dimension, Vector3ic pos) {
        var blockState = dimension.getBlockState(pos);
        var facing = blockState.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);
        var facingBlockFace = BlockFace.from(facing);

        // Check both perpendicular neighbors
        var leftDir = facingBlockFace.rotateY();
        var rightDir = facingBlockFace.rotateYCCW();

        var leftPos = leftDir.offsetPos(pos);
        var rightPos = rightDir.offsetPos(pos);

        // If the neighbor is a powered shelf with same facing, rebuild its chain
        if (isConnectableShelf(dimension, leftPos, facing)) {
            updateChainConnections(dimension, leftPos);
        }
        if (isConnectableShelf(dimension, rightPos, facing)) {
            updateChainConnections(dimension, rightPos);
        }
    }

    private List<Vector3ic> collectChain(Dimension dimension, Vector3ic origin, MinecraftCardinalDirection facing) {
        var facingBlockFace = BlockFace.from(facing);
        var leftDir = facingBlockFace.rotateY();
        var rightDir = facingBlockFace.rotateYCCW();

        List<Vector3ic> chain = new ArrayList<>();

        // Walk left to find the leftmost shelf
        var current = leftDir.offsetPos(origin);
        List<Vector3ic> leftShelves = new ArrayList<>();
        while (leftShelves.size() < 2 && isConnectableShelf(dimension, current, facing)) {
            leftShelves.add(0, current);
            current = leftDir.offsetPos(current);
        }

        // Build chain: left shelves + self + right shelves
        chain.addAll(leftShelves);
        chain.add(origin);

        // Walk right
        current = rightDir.offsetPos(origin);
        while (chain.size() < 3 && isConnectableShelf(dimension, current, facing)) {
            chain.add(current);
            current = rightDir.offsetPos(current);
        }

        // Cap at 3
        if (chain.size() > 3) {
            // Keep only the 3 shelves closest to origin
            int originIdx = chain.indexOf(origin);
            int start = Math.max(0, originIdx - 1);
            int end = Math.min(chain.size(), start + 3);
            chain = new ArrayList<>(chain.subList(start, end));
        }

        return chain;
    }

    private boolean isConnectableShelf(Dimension dimension, Vector3ic pos, MinecraftCardinalDirection facing) {
        var state = dimension.getBlockState(pos);
        if (!(state.getBlockType().getBlockBehavior() instanceof BlockShelfBehavior)) {
            return false;
        }
        if (!state.getPropertyValue(BlockPropertyTypes.POWERED_BIT)) {
            return false;
        }
        return state.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION) == facing;
    }

    private void performMultiSwap(Dimension dimension, Vector3ic clickedPos, BlockState clickedState,
                                  MinecraftCardinalDirection facing,
                                  org.allaymc.api.entity.interfaces.EntityPlayer player) {
        // Collect all shelves in the chain
        var chain = collectChain(dimension, clickedPos, facing);
        var inventory = player.getContainer(ContainerTypes.INVENTORY);

        // Hotbar offset: 1 shelf -> slots 6-8, 2 shelves -> slots 3-8, 3 shelves -> slots 0-8
        int hotbarOffset = (3 - chain.size()) * 3;

        // Map chain slots to hotbar slots (right-aligned) and swap
        for (int shelfIdx = 0; shelfIdx < chain.size(); shelfIdx++) {
            var shelfPos = chain.get(shelfIdx);
            var shelf = blockEntityHolderComponent.getBlockEntity(shelfPos.x(), shelfPos.y(), shelfPos.z(), dimension);
            if (shelf == null) continue;

            Container shelfContainer = shelf.getContainer();
            for (int slotInShelf = 0; slotInShelf < ContainerTypes.SHELF.getSize(); slotInShelf++) {
                int hotbarSlot = hotbarOffset + shelfIdx * 3 + slotInShelf;
                if (hotbarSlot >= 9) break;

                var shelfItem = shelfContainer.getItemStack(slotInShelf);
                var hotbarItem = inventory.getItemStack(hotbarSlot);

                // Swap
                shelfContainer.setItemStack(slotInShelf, hotbarItem);
                inventory.setItemStack(hotbarSlot, shelfItem);
            }
        }
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        var shelf = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (shelf == null) {
            return 0;
        }
        int output = 0;
        Container shelfContainer = shelf.getContainer();
        for (int i = 0; i < ContainerTypes.SHELF.getSize(); i++) {
            if (shelfContainer.getItemStack(i) != ItemAirStack.AIR_STACK) {
                output |= (1 << i);
            }
        }
        return output;
    }
}
