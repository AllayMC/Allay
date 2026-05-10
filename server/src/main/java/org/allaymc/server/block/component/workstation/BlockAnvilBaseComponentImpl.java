package org.allaymc.server.block.component.workstation;

import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockAnvilBaseComponent;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.data.BlockId;
import org.joml.Vector3ic;

import java.util.function.Supplier;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION;

/**
 * @author IWareQ
 */
public class BlockAnvilBaseComponentImpl extends BlockBaseComponentImpl implements BlockAnvilBaseComponent {
    protected final Supplier<BlockType<?>> nextAnvilType;

    public BlockAnvilBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<BlockType<?>> nextAnvilTypeSupplier) {
        super(blockType);
        this.nextAnvilType = nextAnvilTypeSupplier;
    }

    public BlockAnvilBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId nextAnvil) {
        this(blockType, nextAnvil::getBlockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        dimension.addSound(placeBlockPos, SimpleSound.ANVIL_LAND);

        blockState = blockState.setPropertyValue(
                MINECRAFT_CARDINAL_DIRECTION,
                placementInfo.player().getHorizontalFace().rotateYCCW().toMinecraftCardinalDirection()
        );
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var player = interactInfo.player();
        if (player.isActualPlayer()) {
            var anvilContainer = player.getContainer(ContainerTypes.ANVIL);
            anvilContainer.setBlockPos(new Position3i(interactInfo.clickedBlockPos(), interactInfo.player().getDimension()));
            anvilContainer.addViewer(player.getController());
        }
        return true;
    }

    @Override
    public BlockState damage(BlockState current) {
        var nextAnvil = nextAnvilType.get();
        if (nextAnvil == BlockTypes.AIR) {
            return BlockTypes.AIR.getDefaultState();
        }

        return nextAnvil.copyPropertyValuesFrom(current);
    }
}
