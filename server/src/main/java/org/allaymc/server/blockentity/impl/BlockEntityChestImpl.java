package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityChestContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityPairableComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityChest;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityChestImpl} implements the composed block entity's chest
 * inventory and pairing capabilities through injected components. Component
 * assembly assigns the delegation fields before completion hooks run, and the
 * inherited base component supplies identity, position, naming, and NBT
 * serialization for the same object.
 *
 * @author daoge_cmd
 */
public class BlockEntityChestImpl extends BlockEntityImpl implements BlockEntityChest {
    /**
     * {@code chestContainerHolderComponent} supplies chest inventory
     * operations. Component assembly requires exactly one assignable provider
     * result before generated delegates can use this reference.
     */
    @Delegate
    private BlockEntityChestContainerHolderComponent chestContainerHolderComponent;
    /**
     * {@code pairableComponent} supplies chest pairing operations. Component
     * assembly requires exactly one assignable provider result before generated
     * delegates can use this reference.
     */
    @Delegate
    private BlockEntityPairableComponent pairableComponent;

    /**
     * Creates the composed block entity from the supplied component providers.
     * The superclass performs injection and completion hooks before returning;
     * providers must supply the inherited base capability and every declared
     * delegation capability.
     */
    public BlockEntityChestImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
