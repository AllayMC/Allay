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
import org.allaymc.api.eventbus.event.block.ShelfUseEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code BlockShelfBaseComponentImpl} manages shelf item exchanges, powered shelf
 * chains, and comparator signals. Unpowered interactions address one of three slots
 * on the shelf's front face; powered interactions exchange a chain with the player's
 * hotbar. Compatible chain members must be powered shelves with matching facing, and
 * each collected chain contains at most three positions.
 *
 * <p>
 * Chain order determines both the visible end and center properties and the
 * correspondence between shelf slots and hotbar slots. Exchanges align smaller chains
 * to the hotbar's right edge, preserving a deterministic item mapping for one, two,
 * or three shelves. Inventory writes happen successively without rollback, while
 * creative single-slot use copies the held item instead of swapping it. Comparator
 * output encodes occupied shelf slots as individual bits.
 *
 * @author daoge_cmd
 */
public class BlockShelfBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Injected shelf block entity holder.
     */
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityShelf> blockEntityHolderComponent;

    /**
     * Creates shelf base behavior for the supplied block type.
     */
    public BlockShelfBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Forwards placement and activates a shelf whose placement position is powered.
     * Activation updates the power property and chain connections before emitting its
     * sound, while unpowered placement leaves those properties unchanged.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        if (oldBlock.isPowered()) {
            var dimension = oldBlock.getDimension();
            var pos = oldBlock.getPosition();
            dimension.updateBlockProperty(BlockPropertyTypes.POWERED_BIT, true, pos);
            updateChainConnections(dimension, pos);
            dimension.addSound(MathUtils.center(pos), SimpleSound.ACTIVATED);
        }
    }

    /**
     * Handles front-face shelf exchanges for nonsneaking players within the shelf's
     * usable height. Missing player context, incompatible clicks, and cancelled use
     * events return {@code false} before inventory exchange.
     *
     * <p>
     * Powered shelves exchange their collected chain with the corresponding hotbar
     * segment. An unpowered shelf swaps the selected slot with the player's entire
     * held stack, except creative mode copies the held item into the shelf and keeps
     * the player's hand unchanged. Container updates occur successively and have no
     * rollback path.
     */
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
        if (interactInfo.blockFace() != facingBlockFace) {
            return false;
        }
        var clickedPos = interactInfo.clickedPos();
        // The middle half of the front face is the usable shelf height.
        if (clickedPos.y() < 0.25f || clickedPos.y() > 0.75f) {
            return false;
        }
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
            // Creative single-slot use copies the held item without returning shelf
            // contents to the hand; ordinary use exchanges both references.
            if (player.getGameMode() == GameMode.CREATIVE) {
                var newItem = (heldItem != null && heldItem != ItemAirStack.AIR_STACK) ? heldItem.copy() : ItemAirStack.AIR_STACK;
                shelfContainer.setItemStack(slot, newItem);
            } else {
                var oldItem = shelfContainer.getItemStack(slot);
                shelfContainer.setItemStack(slot, heldItem != null ? heldItem : ItemAirStack.AIR_STACK);
                player.setItemInHand(oldItem);
            }
            dimension.addSound(MathUtils.center(pos), SimpleSound.SHELF_SWAP_SINGLE);
        }
        return true;
    }

    /**
     * Returns the slot index selected by the click's horizontal coordinate along the
     * shelf face. The facing-relative projection is normalized into the unit
     * interval, then divided into three slot regions.
     */
    private int calculateSlot(MinecraftCardinalDirection facing, float clickX, float clickZ) {
        // Project the click along the shelf face instead of using a world axis, so
        // all cardinal orientations share the same slot ordering.
        var offset = BlockFace.from(facing).rotateYCCW().getOffset();
        float distance = clickX * offset.x() + clickZ * offset.z();
        if (distance < 0) distance += 1.0f;
        // Equal thirds partition the shelf front into three item slots.
        return distance < (1 / 3f) ? 0 : distance < (2 / 3f) ? 1 : 2;
    }

    /**
     * Forwards neighbor updates and applies changes to the shelf's powered state.
     * Activation rebuilds its chain; deactivation clears its connection type and
     * rebuilds neighboring compatible chains before playing the corresponding sound.
     */
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
            dimension.updateBlockProperty(BlockPropertyTypes.POWERED_BIT, true, pos);
            updateChainConnections(dimension, pos);
            dimension.addSound(MathUtils.center(pos), SimpleSound.ACTIVATED);
        } else {
            dimension.updateBlockProperty(BlockPropertyTypes.POWERED_BIT, false, pos);
            dimension.updateBlockProperty(BlockPropertyTypes.POWERED_SHELF_TYPE, 0, pos);
            updateNeighborChains(dimension, pos);
            dimension.addSound(MathUtils.center(pos), SimpleSound.DEACTIVATED);
        }
    }

    /**
     * Rebuilds visible shelf connection types for the collected powered chain. The
     * ordered chain determines unconnected, left, center, and right values, and each
     * dimension property write is applied separately.
     */
    private void updateChainConnections(Dimension dimension, Vector3ic pos) {
        var blockState = dimension.getBlockState(pos);
        var facing = blockState.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);
        var chain = collectChain(dimension, pos, facing);
        // The collected list fixes chain membership and order for this pass, instead
        // of recomputing neighbors between individual connection-property writes.
        for (int i = 0; i < chain.size(); i++) {
            int type;
            if (chain.size() == 1) {
                type = 0;
            } else if (i == 0) {
                type = 3;
            } else if (i == chain.size() - 1) {
                type = 1;
            } else {
                type = 2;
            }
            dimension.updateBlockProperty(BlockPropertyTypes.POWERED_SHELF_TYPE, type, chain.get(i));
        }
    }

    /**
     * Rebuilds chains for powered, equally facing shelf neighbors on both sides. The
     * current shelf's facing determines those perpendicular positions, allowing
     * deactivation to update the remaining adjacent chain fragments.
     */
    private void updateNeighborChains(Dimension dimension, Vector3ic pos) {
        var blockState = dimension.getBlockState(pos);
        var facing = blockState.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);
        var facingBlockFace = BlockFace.from(facing);
        var leftDir = facingBlockFace.rotateY();
        var rightDir = facingBlockFace.rotateYCCW();

        var leftPos = leftDir.offsetPos(pos);
        var rightPos = rightDir.offsetPos(pos);
        if (isConnectableShelf(dimension, leftPos, facing)) {
            updateChainConnections(dimension, leftPos);
        }
        if (isConnectableShelf(dimension, rightPos, facing)) {
            updateChainConnections(dimension, rightPos);
        }
    }

    /**
     * Returns an ordered list containing the origin and at most two compatible
     * neighbors. The supplied facing determines left and right directions, while
     * matching neighbors must be powered shelves facing the same way.
     *
     * <p>
     * The walk first collects up to two shelves on the left, inserts the origin, and
     * fills any remaining capacity from the right. This left-first selection keeps
     * the same list order for connection assignment and hotbar exchange; positions
     * beyond the three-member limit are omitted.
     */
    private List<Vector3ic> collectChain(Dimension dimension, Vector3ic origin, MinecraftCardinalDirection facing) {
        var facingBlockFace = BlockFace.from(facing);
        var leftDir = facingBlockFace.rotateY();
        var rightDir = facingBlockFace.rotateYCCW();

        List<Vector3ic> chain = new ArrayList<>();
        var current = leftDir.offsetPos(origin);
        List<Vector3ic> leftShelves = new ArrayList<>();
        // Two left neighbors fill the remaining capacity beside the origin.
        while (leftShelves.size() < 2 && isConnectableShelf(dimension, current, facing)) {
            leftShelves.add(0, current);
            current = leftDir.offsetPos(current);
        }
        chain.addAll(leftShelves);
        chain.add(origin);
        current = rightDir.offsetPos(origin);
        // Three shelves fill the nine-slot hotbar exchange capacity.
        while (chain.size() < 3 && isConnectableShelf(dimension, current, facing)) {
            chain.add(current);
            current = rightDir.offsetPos(current);
        }
        // Defensively retain a three-position slice around the origin.
        if (chain.size() > 3) {
            int originIdx = chain.indexOf(origin);
            int start = Math.max(0, originIdx - 1);
            int end = Math.min(chain.size(), start + 3);
            chain = new ArrayList<>(chain.subList(start, end));
        }

        return chain;
    }

    /**
     * Returns whether the position contains a powered shelf with the requested
     * facing. Other block behaviors, unpowered shelf states, and different cardinal
     * directions prevent that position from joining the chain.
     */
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

    /**
     * Exchanges each collected shelf slot with its corresponding player hotbar slot.
     * The chain has at most three members, and smaller chains align to the hotbar's
     * right edge.
     *
     * <p>
     * One shelf maps to slots six through eight, two shelves map to three through
     * eight, and three shelves use all nine slots. Each pair is read before its two
     * writes, retaining both item references for the exchange. Missing shelf entities
     * are skipped, and failures after earlier writes have no rollback.
     */
    private void performMultiSwap(Dimension dimension, Vector3ic clickedPos, BlockState clickedState,
                                  MinecraftCardinalDirection facing,
                                  org.allaymc.api.entity.interfaces.EntityPlayer player) {
        var chain = collectChain(dimension, clickedPos, facing);
        var inventory = player.getContainer(ContainerTypes.INVENTORY);
        // Right alignment reserves three hotbar slots per collected shelf.
        int hotbarOffset = (3 - chain.size()) * 3;
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
                // Both items were read before either write. Keep the displaced shelf
                // reference for the following hotbar assignment instead of reading
                // the modified shelf again.
                shelfContainer.setItemStack(slotInShelf, hotbarItem);
                inventory.setItemStack(hotbarSlot, shelfItem);
            }
        }
    }

    /**
     * Returns whether shelves always support their slot-occupancy comparator
     * encoding.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns a bit mask whose set bits identify nonair shelf slots. A missing shelf
     * entity returns {@code 0}, and each present slot contributes its index's bit
     * rather than an item count.
     */
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
                // Each occupied slot contributes one bit to the comparator value.
                output |= (1 << i);
            }
        }
        return output;
    }
}
