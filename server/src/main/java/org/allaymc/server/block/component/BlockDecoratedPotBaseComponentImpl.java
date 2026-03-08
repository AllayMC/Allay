package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.component.BlockEntityDecoratedPotBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityDecoratedPot;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.DecoratedPotInsertedSound;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3ic;

import java.util.Set;

/**
 * @author IWareQ
 */
public class BlockDecoratedPotBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockDecoratedPotBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var face = placementInfo.player().getHorizontalFace().opposite();
        blockState = blockState.setPropertyValue(BlockPropertyTypes.DIRECTION_4, face.getHorizontalIndex());
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var player = interactInfo.player();
        if (itemStack.isEmptyOrAir()) {
            return false;
        }

        var clickedBlock = interactInfo.getClickedBlock();

        var decoratedPot = clickedBlock.<BlockEntityDecoratedPot>getBlockEntity();
        if (decoratedPot.tryInsertItem(itemStack)) {
            if (player.getGameMode() != GameMode.CREATIVE) {
                player.tryConsumeItemInHand();
            }

            var progress = (double) decoratedPot.getItem().getCount() / BlockEntityDecoratedPotBaseComponent.MAX_STACK_SIZE;
            clickedBlock.addSound(new DecoratedPotInsertedSound(progress));
        } else {
            clickedBlock.addSound(SimpleSound.DECORATED_POT_INSERT_FAILED);
        }

        return true;
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        var decoratedPot = block.<BlockEntityDecoratedPot>getBlockEntity();
        if (decoratedPot == null) {
            return 0;
        }

        return decoratedPot.getComparatorOutput();
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(this.createDecoratedPotDrop(block));
    }

    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return this.createDecoratedPotDrop(block);
    }

    protected ItemStack createDecoratedPotDrop(Block block) {
        var blockEntity = block.getBlockEntity();
        if (blockEntity instanceof BlockEntityDecoratedPot decoratedPot) {
            var item = ItemTypes.DECORATED_POT.createItemStack(1);
            item.setSherds(decoratedPot.getSherds());
            return item;
        }

        return ItemTypes.DECORATED_POT.createItemStack(1);
    }
}
