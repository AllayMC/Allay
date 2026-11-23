package org.allaymc.server.component;

import lombok.Getter;
import org.allaymc.api.component.Component;
import org.allaymc.api.component.ComponentInitInfo;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.entity.impl.EntityImpl;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

/**
 * {@code ComponentClass} is the base class for composed objects whose behavior
 * is assembled from a fixed set of {@link Component} implementations. Blueprint
 * compilation validates owner delegate fields, component dependencies, manager
 * and owner references, and internal event handlers once per type; instantiation
 * then executes precompiled {@code VarHandle} and {@code MethodHandle} bindings
 * without per-instance reflection or event-bus allocation.
 *
 * <p>
 * Subclasses constructed via {@link ComponentBlueprint#newInstance(ComponentInitInfo)}
 * through the no-argument constructor complete their own constructor body before
 * component wiring and {@link ComponentLifecycle#onInitFinish(ComponentInitInfo)}
 * hooks execute. Legacy subclasses using the provider-list constructor remain
 * supported and still reuse precompiled blueprints when built through
 * {@code Allay*Type}.
 *
 * @author daoge_cmd
 * @see ItemStackImpl
 * @see EntityImpl
 * @see BlockBehaviorImpl
 * @see BlockEntityImpl
 */
public abstract class ComponentClass {

    /**
     * {@code manager} dispatches internal component events through this object's
     * shared type-level {@link ComponentBlueprint}. Blueprint initialization
     * installs this reference before wiring components and running lifecycle hooks.
     */
    @Getter
    protected ComponentManager manager;

    /**
     * {@code components} retains this object's wired component instances indexed
     * by blueprint slot order for direct internal lookup through
     * {@link #getComponent(Class)}.
     */
    private Component[] components;

    /**
     * Creates an uninitialized composed object to be wired immediately afterward
     * by {@link ComponentBlueprint#newInstance(ComponentInitInfo)}.
     */
    protected ComponentClass() {
    }

    /**
     * Creates and wires components using the supplied initialization payload and
     * providers. When {@code componentProviders} originates from a compiled
     * {@link ComponentBlueprint}, wiring reuses its precompiled bindings directly;
     * otherwise a wiring-only blueprint is compiled for this class.
     */
    public ComponentClass(ComponentInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        if (componentProviders instanceof ComponentBlueprint.BlueprintProviderList bpList) {
            bpList.blueprint().initialize(this, initInfo);
        } else {
            ComponentBlueprint.compileForWiring(this.getClass(), componentProviders).initialize(this, initInfo);
        }
    }

    /**
     * Assigns this composed object's event-dispatch manager during blueprint
     * initialization.
     */
    protected void setManager(ComponentManager manager) {
        this.manager = manager;
    }

    /**
     * Stores this composed object's ordered component array during blueprint
     * initialization so {@link #getComponent(Class)} can resolve components
     * without reflection.
     */
    void setComponents(Component[] components) {
        this.components = components;
    }

    /**
     * Returns the first wired component assignable to {@code componentType}, or
     * {@code null} if this composed object has not been wired or carries no
     * matching component.
     *
     * @param componentType component interface or implementation class to locate
     * @param <C>           expected component type
     *
     * @return the matching component instance, or {@code null} if absent
     */
    @SuppressWarnings("unchecked")
    public <C extends Component> C getComponent(Class<C> componentType) {
        if (components == null) {
            return null;
        }
        for (var component : components) {
            if (componentType.isInstance(component)) {
                return (C) component;
            }
        }
        return null;
    }
}
