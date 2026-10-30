package org.allaymc.server.block.component.workstation;

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
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.block.data.BlockId;
import org.joml.Vector3ic;

import java.util.function.Supplier;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION;

/**
 * {@code BlockAnvilBaseComponentImpl} opens anvil repair menus and calculates the
 * next damage-state replacement. Player placement rotates the anvil across the
 * player's facing and plays its landing sound. A lazy resolver supplies the next
 * anvil type, allowing registration dependencies to finish before damage is
 * evaluated.
 *
 * @author IWareQ
 */
public class BlockAnvilBaseComponentImpl extends BlockBaseComponentImpl implements BlockAnvilBaseComponent {
    /**
     * {@code nextAnvilType} resolves the replacement type on every damage call.
     * The supplier must return air or an anvil type compatible with the current
     * state's property values.
     */
    protected final Supplier<BlockType<?>> nextAnvilType;

    /**
     * Creates an anvil component retaining the lazy next-type resolver.
     * {@code nextAnvilTypeSupplier} is evaluated on each damage call, after block
     * registration can supply the replacement type. A {@code null} resolver fails
     * when a later damage call evaluates it.
     */
    public BlockAnvilBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<BlockType<?>> nextAnvilTypeSupplier) {
        super(blockType);
        this.nextAnvilType = nextAnvilTypeSupplier;
    }

    /**
     * Creates an anvil component whose next damage type resolves from the supplied
     * block identifier. The identifier's type lookup is retained for later damage
     * calls rather than evaluated immediately.
     */
    public BlockAnvilBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId nextAnvil) {
        this(blockType, nextAnvil::getBlockType);
    }

    /**
     * Places the anvil across the player's horizontal facing and plays its landing
     * sound before the write. A {@code null} placement context writes the supplied
     * state directly without that player orientation or sound.
     */
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

    /**
     * Runs shared handlers before opening the player's anvil container at the
     * clicked block. {@code interactInfo} must identify the player; simulated
     * players receive {@code true} without a menu viewer being added.
     */
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

    /**
     * Returns the next damage state using the configured replacement type. Air
     * returns its default state; other types copy {@code current}'s property
     * values through {@code copyPropertyValuesFrom}, whose incompatible inputs can
     * produce {@code null}.
     */
    @Override
    public BlockState damage(BlockState current) {
        var nextAnvil = nextAnvilType.get();
        if (nextAnvil == BlockTypes.AIR) {
            return BlockTypes.AIR.getDefaultState();
        }

        return nextAnvil.copyPropertyValuesFrom(current);
    }
}
