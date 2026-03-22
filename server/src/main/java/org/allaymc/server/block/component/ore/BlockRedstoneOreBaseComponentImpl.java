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
 * @author IWareQ
 */
public class BlockRedstoneOreBaseComponentImpl extends BlockOreBaseComponentImpl {

    private static final Duration TURN_OFF_DELAY = Duration.ofMillis(1500);

    private final boolean lit;
    private final Identifier litBlockIdentifier;
    private final Identifier unlitBlockIdentifier;

    public BlockRedstoneOreBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Identifier litBlockIdentifier, Identifier unlitBlockIdentifier) {
        super(blockType, ItemId.REDSTONE, 0, 0, 1, 5);
        this.lit = blockType.getIdentifier().equals(litBlockIdentifier);
        this.litBlockIdentifier = litBlockIdentifier;
        this.unlitBlockIdentifier = unlitBlockIdentifier;
    }

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

    @Override
    public void onPunch(Block block, BlockFace blockFace, ItemStack usedItem, Entity entity) {
        super.onPunch(block, blockFace, usedItem, entity);
        activate(block);
    }

    @Override
    public void onCollideWithEntity(Block block, Entity entity) {
        activate(block);
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        if (lit) {
            oldBlock.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(oldBlock.getPosition(), TURN_OFF_DELAY);
        }
    }

    @Override
    public void onScheduledUpdate(Block block) {
        if (lit) {
            switchToUnlit(block);
        }
    }

    @Override
    public boolean canRandomUpdate() {
        return lit;
    }

    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        if (lit) {
            switchToUnlit(block);
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.REDSTONE.createItemStack(FortuneDropHelper.discrete(usedItem, 4, 5, 8)));
    }

    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return getUnlitBlockType().getItemType().createItemStack();
    }

    private void activate(Block block) {
        var activeBlock = block;
        if (!lit) {
            activeBlock = switchToLit(block);
        }
        activeBlock.scheduleUpdateInDelay(TURN_OFF_DELAY);
    }

    private Block switchToLit(Block block) {
        return block.replaceState(getLitBlockType().getDefaultState());
    }

    private void switchToUnlit(Block block) {
        block.replaceState(getUnlitBlockType().getDefaultState());
    }

    private BlockType<?> getLitBlockType() {
        return Registries.BLOCKS.get(litBlockIdentifier);
    }

    private BlockType<?> getUnlitBlockType() {
        return Registries.BLOCKS.get(unlitBlockIdentifier);
    }
}
