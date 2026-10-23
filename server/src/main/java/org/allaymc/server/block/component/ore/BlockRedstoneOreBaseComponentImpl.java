package org.allaymc.server.block.component.ore;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.FortuneDropHelper;
import org.allaymc.server.item.data.ItemId;

import java.time.Duration;
import java.util.Set;

/**
 * {@code BlockRedstoneOreBaseComponentImpl} handles paired lit and unlit redstone ore
 * types, activation, and redstone drops. Interaction, punching, or collision activates
 * the ore and schedules a later return to the unlit type. Lit instances also allow
 * random updates to extinguish the ore, while Silk Touch always selects the unlit
 * variant.
 *
 * @author IWareQ
 */
public class BlockRedstoneOreBaseComponentImpl extends BlockOreBaseComponentImpl {

    /**
     * {@code TURN_OFF_DELAY} controls delayed extinguishing after activation and after
     * placement of the lit ore variant.
     */
    private static final Duration TURN_OFF_DELAY = Duration.ofMillis(1500);

    /**
     * {@code lit} records whether this component belongs to the configured lit type.
     * Update callbacks use this fixed variant identity rather than reading a light
     * property.
     */
    private final boolean lit;
    /**
     * Registry identifier of the lit ore variant.
     */
    private final Identifier litBlockIdentifier;
    /**
     * Registry identifier of the unlit ore variant.
     */
    private final Identifier unlitBlockIdentifier;

    /**
     * Creates a redstone ore component for a paired set of registered variants. The
     * supplied block type determines {@code lit}, and later state switches resolve the
     * supplied identifiers from the block registry.
     */
    public BlockRedstoneOreBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Identifier litBlockIdentifier, Identifier unlitBlockIdentifier) {
        super(blockType, ItemId.REDSTONE, 0, 0, 1, 5);
        this.lit = blockType.getIdentifier().equals(litBlockIdentifier);
        this.litBlockIdentifier = litBlockIdentifier;
        this.unlitBlockIdentifier = unlitBlockIdentifier;
    }

    /**
     * Activates the clicked ore unless inherited interaction handling consumes the
     * action. A {@code null} {@code interactInfo} returns {@code false}; otherwise
     * activation schedules extinguishing and the interaction returns {@code true}.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (interactInfo == null) {
            return false;
        }

        activate(new Block(dimension, interactInfo.clickedBlockPos()));
        return true;
    }

    /**
     * Forwards the punch to inherited handling and then activates the ore. Activation
     * schedules extinguishing even when this component already represents the lit
     * variant, without changing the punch's item or entity.
     */
    @Override
    public void onPunch(Block block, BlockFace blockFace, ItemStack usedItem, Entity entity) {
        super.onPunch(block, blockFace, usedItem, entity);
        activate(block);
    }

    /**
     * Activates the ore when an entity collides with it. The entity type does not
     * affect this callback, and lit ore still requests a delayed extinguishing update.
     */
    @Override
    public void onCollideWithEntity(Block block, Entity entity) {
        activate(block);
    }

    /**
     * Schedules delayed extinguishing after placement of the lit variant. Inherited
     * placement handling runs first; placement of the unlit variant does not request an
     * update from this callback.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        if (lit) {
            oldBlock.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(oldBlock.getPosition(), TURN_OFF_DELAY);
        }
    }

    /**
     * Requests the unlit default state when this component represents lit ore. The
     * fixed component variant controls the decision, and the unlit component ignores
     * scheduled updates.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        if (lit) {
            switchToUnlit(block);
        }
    }

    /**
     * Returns whether this component belongs to the lit ore variant.
     */
    @Override
    public boolean canRandomUpdate() {
        return lit;
    }

    /**
     * Runs inherited random-update handling and extinguishes the lit ore variant. The
     * unlit component performs no variant replacement, so random updates do not
     * activate otherwise unlit ore.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        if (lit) {
            switchToUnlit(block);
        }
    }

    /**
     * Returns redstone dust drops calculated by the fortune drop helper. The helper
     * selects the quantity from the supplied tool's enchantments, while this result
     * contains a single dust stack.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.REDSTONE.createItemStack(FortuneDropHelper.discrete(usedItem, 4, 5, 8)));
    }

    /**
     * Returns an item stack for the unlit ore variant. Lit ore therefore drops the same
     * unlit block item, and the unlit type is resolved from its configured registry
     * identifier.
     */
    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return getUnlitBlockType().getItemType().createItemStack();
    }

    /**
     * Activates the ore and requests a delayed extinguishing update at its position. An
     * unlit component first replaces the state with the lit type's default; an already
     * lit component only requests the update.
     */
    private void activate(Block block) {
        var activeBlock = block;
        if (!lit) {
            activeBlock = switchToLit(block);
        }
        activeBlock.scheduleUpdateInDelay(TURN_OFF_DELAY);
    }

    /**
     * Requests replacement with the lit ore type's default state and returns its block
     * wrapper. {@code Block.replaceState} does not report a refused dimension update
     * through this returned wrapper.
     */
    private Block switchToLit(Block block) {
        return block.replaceState(getLitBlockType().getDefaultState());
    }

    /**
     * Requests replacement with the unlit ore type's default state. Other properties
     * from the supplied snapshot are not carried into the replacement, and this helper
     * does not inspect placement success.
     */
    private void switchToUnlit(Block block) {
        block.replaceState(getUnlitBlockType().getDefaultState());
    }

    /**
     * Returns the registered ore type identified by {@code litBlockIdentifier}.
     */
    private BlockType<?> getLitBlockType() {
        return Registries.BLOCKS.get(litBlockIdentifier);
    }

    /**
     * Returns the registered ore type identified by {@code unlitBlockIdentifier}.
     */
    private BlockType<?> getUnlitBlockType() {
        return Registries.BLOCKS.get(unlitBlockIdentifier);
    }
}
