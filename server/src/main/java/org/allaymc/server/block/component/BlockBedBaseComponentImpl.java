package org.allaymc.server.block.component;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBedBaseComponent;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityBed;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockExplodeEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.Explosion;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3ic;

import java.util.Set;

/**
 * @author harry-xi
 */
public class BlockBedBaseComponentImpl extends BlockBaseComponentImpl implements BlockBedBaseComponent {

    @Dependency
    BlockEntityHolderComponent<BlockEntityBed> blockEntityHolderComponent;

    public BlockBedBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    public static Block getPairBlock(Block block) {
        var otherPos = posOfOtherPart(block);
        return new Block(block.getDimension(), otherPos);
    }

    private static Vector3ic posOfOtherPart(Block block) {
        var head = block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT);
        var face = Preconditions.checkNotNull(BlockFace.fromHorizontalIndex(block.getPropertyValue(BlockPropertyTypes.DIRECTION_4)));
        return (head ? face.opposite() : face).offsetPos(block.getPos());
    }

    private static boolean posEqVec3ic(Position3ic pos, Vector3ic other) {
        return pos.x() == other.x() && pos.y() == other.y() && pos.z() == other.z();
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var playerFace = placementInfo.player().getHorizontalFace();
        var nextPos = playerFace.offsetPos(placeBlockPos);
        var nextBlockState = dimension.getBlockState(nextPos);
        if (!nextBlockState.getBlockType().hasBlockTag(BlockCustomTags.REPLACEABLE)) {
            return false;
        }

        blockState = blockState
                .setPropertyValue(BlockPropertyTypes.DIRECTION_4, playerFace.getHorizontalIndex())
                .setPropertyValue(BlockPropertyTypes.OCCUPIED_BIT, false);

        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                processBlockProperties(blockState.setPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT, false), placeBlockPos, placementInfo),
                placementInfo
        ) && dimension.setBlockState(
                nextPos.x(), nextPos.y(), nextPos.z(),
                processBlockProperties(blockState.setPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT, true), nextPos, placementInfo),
                placementInfo
        );
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (dimension.getDimensionInfo() != DimensionInfo.OVERWORLD &&
            dimension.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.RESPAWN_BLOCKS_EXPLODE)) {
            var explosion = new Explosion(5, true);
            var event = new BlockExplodeEvent(interactInfo.getClickedBlock(), explosion);
            if (!event.call()) {
                return false;
            }

            explosion.explode(dimension, interactInfo.clickedBlockPos());
            return true;
        }

        // TODO: implement sleep logic
        return false;
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);
        if (posEqVec3ic(neighbor.getPos(), posOfOtherPart(block))
            && neighbor.getBlockType() != getBlockType()) {
            block.breakBlock();
        }
    }

    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        if (block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT)) {
            var footPos = posOfOtherPart(block);
            block.getDimension().breakBlock(footPos, null, entity);
        }
        super.onBreak(block, usedItem, entity);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return block.getPropertyValue(BlockPropertyTypes.HEAD_PIECE_BIT) ? Utils.EMPTY_ITEM_STACK_SET : createBedDrop(block);
    }

    private Set<ItemStack> createBedDrop(Block blockState) {
        var blockEntity = blockEntityHolderComponent.getBlockEntity(blockState.getPos());
        var drop = blockState.toItemStack();
        drop.setMeta(blockEntity.getColor().ordinal());
        return Set.of(drop);
    }
}
