package cn.allay.api.entity.component.container;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.container.BaseContainerHolder;
import cn.allay.api.container.Container;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.identifier.Identifier;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public class EntityContainerHolderComponentImpl extends BaseContainerHolder implements EntityContainerHolderComponent {

    @ComponentIdentifier
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:entity_inventory_holder_component");

    public EntityContainerHolderComponentImpl() {
    }

    public EntityContainerHolderComponentImpl(Container... containers) {
        super(containers);
    }

    @Override
    public @UnmodifiableView Map<FullContainerType<?>, Container> getContainers() {
        return super.getContainers();
    }

    @Override
    @Nullable
    public <T extends Container> T getContainer(FullContainerType<T> type) {
        return super.getContainer(type);
    }

    @Override
    public void addContainer(Container container) {
        super.addContainer(container);
    }
}
