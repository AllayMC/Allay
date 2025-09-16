package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.block.action.SimpleBlockAction;
import org.allaymc.api.blockentity.component.BlockEntityEnderChestBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.sound.SimpleSound;

/**
 * @author IWareQ
 */
public class BlockEntityEnderChestBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityEnderChestBaseComponent {
    @Getter
    protected int viewersCount = 0;

    public BlockEntityEnderChestBaseComponentImpl(BlockEntityInitInfo info) {
        super(info);
    }

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
