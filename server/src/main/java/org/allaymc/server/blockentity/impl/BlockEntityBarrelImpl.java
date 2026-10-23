package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBarrel;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityBarrelImpl} implements the composed block entity's barrel
 * inventory capabilities through injected components. Component assembly
 * assigns the delegation fields before completion hooks run, and the inherited
 * base component supplies identity, position, naming, and NBT serialization for
 * the same object.
 *
 * @author daoge_cmd
 */
public class BlockEntityBarrelImpl extends BlockEntityImpl implements BlockEntityBarrel {

    /**
     * {@code containerHolderComponent} supplies barrel inventory operations.
     * Component assembly requires exactly one assignable provider result before
     * generated delegates can use this reference.
     */
    @Delegate
    private BlockEntityContainerHolderComponent containerHolderComponent;

    /**
     * Creates the composed block entity from the supplied component providers.
     * The superclass performs injection and completion hooks before returning;
     * providers must supply the inherited base capability and every declared
     * delegation capability.
     */
    public BlockEntityBarrelImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
