package org.allaymc.server.block.component;

import lombok.Getter;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.poi.BlockStateWithPos;
import org.allaymc.api.block.poi.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.Manager;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaEnchantmentTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.allaymc.server.block.component.event.CBlockOnNeighborUpdateEvent;
import org.allaymc.server.block.component.event.CBlockOnPlaceEvent;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.server.block.type.BlockLootTable;
import org.allaymc.server.loottable.context.BreakBlockContext;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3f;
import org.joml.Vector3ic;

import java.util.Set;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public class BlockBaseComponentImpl implements BlockBaseComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_base_component");

    @Manager
    protected ComponentManager manager;

    @Getter
    protected BlockType<? extends BlockBehavior> blockType;

    public BlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        this.blockType = blockType;
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        manager.callEvent(new CBlockOnNeighborUpdateEvent(current, neighbor, face));
        if (!canKeepExisting(current, neighbor, face)) {
            current.pos().dimension().breakBlock(current.pos(), null, null);
        }
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos blockState) {}

    @Override
    public void onScheduledUpdate(BlockStateWithPos blockState) {}

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        var vanillaBlockId = VanillaBlockId.fromIdentifier(blockType.getIdentifier());
        if (vanillaBlockId != null) {
            var lootTable = BlockLootTable.getLootTable(vanillaBlockId);
            if (lootTable != null) {
                var context = new BreakBlockContext(entity, usedItem);
                return lootTable.loot(context);
            }
        }

        if (getBlockType().getItemType() != null)
            return Set.of(getSilkTouchDrop(blockState));

        return Utils.EMPTY_ITEM_STACK_SET;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                processBlockProperties(blockState, placeBlockPos, placementInfo),
                placementInfo
        );
        return true;
    }

    protected BlockState processBlockProperties(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        for (var propertyType : blockState.getBlockType().getProperties().values()) {
            var processor = propertyType.getProcessor();
            if (processor != null) {
                blockState = processor.process(blockState, placeBlockPos, placementInfo);
            }
        }
        return blockState;
    }

    @Override
    public void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockOnPlaceEvent(currentBlockState, newBlockState, placementInfo));
    }

    @Override
    public void onReplace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockOnReplaceEvent(currentBlockState, newBlockState, placementInfo));
    }

    @Override
    public void onBreak(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        if (!isDroppable(blockState, usedItem, entity)) return;

        var dropPos = new Vector3f(blockState.pos()).add(0.5f, 0.5f, 0.5f);
        var dimension = blockState.pos().dimension();
        if (usedItem != null && usedItem.hasEnchantment(VanillaEnchantmentTypes.SILK_TOUCH)) {
            // Silk Touch, directly drop the block itself
            dimension.dropItem(getSilkTouchDrop(blockState), dropPos);
            return;
        }

        var drops = getDrops(blockState, usedItem, entity);
        for (var drop : drops) {
            dimension.dropItem(drop, dropPos);
        }
    }

    @Override
    public boolean isDroppable(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        if (entity instanceof EntityPlayer player && player.getGameType() == GameType.CREATIVE) return false;
        return blockState.blockState().getBlockType().getMaterial().isAlwaysDestroyable() || (usedItem != null && usedItem.isCorrectToolFor(blockState.blockState()));
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        var event = new CBlockOnInteractEvent(itemStack, dimension, interactInfo, false);
        manager.callEvent(event);
        return event.isSuccess();
    }
}
