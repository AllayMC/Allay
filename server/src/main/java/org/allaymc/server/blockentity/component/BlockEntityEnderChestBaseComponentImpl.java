package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.block.action.SimpleBlockAction;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityEnderChestBaseComponent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.sound.SimpleSound;

/**
 * {@code BlockEntityEnderChestBaseComponentImpl} tracks an ender chest's
 * world-side viewer count and broadcasts its opening and closing effects.
 * Player-owned containers update this count separately from their inventory
 * contents; assigned values of one and zero emit the respective actions without
 * testing the previous count.
 *
 * @author IWareQ
 */
public class BlockEntityEnderChestBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityEnderChestBaseComponent {
    /**
     * {@code viewersCount} starts at zero and is updated by player-owned
     * ender-chest containers; assignments are stored directly without clamping
     * or persistence.
     */
    @Getter
    protected int viewersCount = 0;

    /**
     * Creates ender-chest base state using the supplied block-entity
     * initialization information.
     */
    public BlockEntityEnderChestBaseComponentImpl(BlockEntityInitInfo info) {
        super(info);
    }

    /**
     * Stores the supplied viewer count and emits its applicable chest effects.
     * A value of {@code 1} sends opening sound and action, while {@code 0}
     * sends closing effects; repeated assignments repeat those signals.
     */
    @Override
    public void setViewersCount(int count) {
        this.viewersCount = count;

        if (viewersCount == 1) {
            position.dimension().addSound(MathUtils.center(position), SimpleSound.ENDER_CHEST_OPEN);
            position.dimension().addBlockAction(position, SimpleBlockAction.OPEN);
        } else if (viewersCount == 0) {
            position.dimension().addSound(MathUtils.center(position), SimpleSound.ENDER_CHEST_CLOSE);
            position.dimension().addBlockAction(position, SimpleBlockAction.CLOSE);
        }
    }
}
