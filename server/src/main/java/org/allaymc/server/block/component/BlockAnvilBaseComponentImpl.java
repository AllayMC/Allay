package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockAnvilBaseComponent;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.impl.AnvilContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.Sound;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION;

/**
 * @author IWareQ
 */
public class BlockAnvilBaseComponentImpl extends BlockBaseComponentImpl implements BlockAnvilBaseComponent {
    protected final BlockId nextAnvil;

    public BlockAnvilBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId nextAnvil) {
        super(blockType);
        this.nextAnvil = nextAnvil;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }

        dimension.addSound(placeBlockPos, Sound.RANDOM_ANVIL_LAND);

        blockState = blockState.withPropertyValue(
                MINECRAFT_CARDINAL_DIRECTION,
                placementInfo.player().getHorizontalFace().rotateYCCW().toMinecraftCardinalDirection()
        );
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var player = interactInfo.player();
        if (player.isSneaking()) {
            return false;
        }

        var anvilContainer = new AnvilContainer();
        anvilContainer.setBlockPos(new Position3i(interactInfo.clickedBlockPos(), interactInfo.player().getDimension()));
        anvilContainer.addViewer(player);
        return true;
    }

    @Override
    public BlockState damage(BlockState current) {
        if (nextAnvil == BlockId.AIR) {
            return BlockId.AIR.getBlockType().getDefaultState();
        }

        return nextAnvil.getBlockType().copyPropertyValuesFrom(current);
    }
}
