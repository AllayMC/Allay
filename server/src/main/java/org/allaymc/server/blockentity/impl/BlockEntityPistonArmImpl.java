package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityPistonArmBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityPistonArm;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * Implementation of piston arm block entity.
 *
 * @author daoge_cmd
 */
public class BlockEntityPistonArmImpl extends BlockEntityImpl implements BlockEntityPistonArm {

    @Delegate
    private BlockEntityPistonArmBaseComponent pistonArmBaseComponent;

    public BlockEntityPistonArmImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
