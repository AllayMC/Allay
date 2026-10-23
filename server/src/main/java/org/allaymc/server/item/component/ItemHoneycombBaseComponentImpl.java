package org.allaymc.server.item.component;

import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3ic;

/**
 * {@code ItemHoneycombBaseComponentImpl} attempts to wax eligible
 * oxidation-aware blocks when honeycomb is used on them. The waxed state
 * reuses the clicked state's property values, while an eligible operation
 * attempts held-item consumption and emits its wax sound even when block
 * replacement reports failure.
 *
 * @author IWareQ
 */
public class ItemHoneycombBaseComponentImpl extends ItemBaseComponentImpl {
    /**
     * Creates honeycomb-use behavior from the supplied item initialization
     * data.
     */
    public ItemHoneycombBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Attempts to wax an unwaxed oxidation-aware clicked block and returns
     * {@code true} on that path. The superclass use-event result is ignored,
     * and a {@code false} replacement result still leads to a held-item
     * consumption attempt and wax sound; unsupported or already waxed blocks
     * return {@code false}.
     */
    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        super.useItemOnBlock(dimension, placeBlockPos, interactInfo);

        var clickedBlockState = interactInfo.getClickedBlock();
        if (!(clickedBlockState.getBehavior() instanceof BlockOxidationComponent oxidationComponent)) {
            return false;
        }

        if (oxidationComponent.isWaxed()) {
            return false;
        }

        var clickedBlockPos = interactInfo.clickedBlockPos();
        var nextBlockType = oxidationComponent.getBlockWithWaxed(true);
        var oldBlock = new Block(dimension.getBlockState(clickedBlockPos), new Position3i(clickedBlockPos, dimension), 0);
        // Resolve the waxed variant with the clicked state's complete property values
        // so waxing retains orientation and other state encoded by that variant.
        dimension.setBlockState(clickedBlockPos, nextBlockType.copyPropertyValuesFrom(oldBlock.getBlockState()));
        interactInfo.player().tryConsumeItemInHand();
        dimension.addSound(clickedBlockPos, SimpleSound.WAXED);
        return true;
    }
}
