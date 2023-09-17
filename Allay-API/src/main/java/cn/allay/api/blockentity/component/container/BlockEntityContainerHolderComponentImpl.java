package cn.allay.api.blockentity.component.container;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.container.Container;
import cn.allay.api.container.ContainerHolder;
import cn.allay.api.identifier.Identifier;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public class BlockEntityContainerHolderComponentImpl implements BlockEntityContainerHolderComponent, ContainerHolder {
    @ComponentIdentifier
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_inventory_holder_component");
    protected Container container;

    public BlockEntityContainerHolderComponentImpl(Container container) {
        this.container = container;
    }

    @Impl
    @Override
    public <T extends Container> T getContainer() {
        return (T) container;
    }

    @Impl
    @Override
    public void setContainer(Container container) {
        this.container = container;
    }
}
