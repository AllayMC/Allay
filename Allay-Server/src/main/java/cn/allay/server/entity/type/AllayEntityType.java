package cn.allay.server.entity.type;

import cn.allay.api.component.interfaces.Component;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.EntityComponent;
import cn.allay.api.entity.component.base.EntityBaseComponentImpl;
import cn.allay.api.entity.init.EntityInitInfo;
import cn.allay.api.entity.registry.EntityTypeRegistry;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.block.type.BlockTypeBuildException;
import cn.allay.server.component.injector.AllayComponentInjector;
import cn.allay.server.utils.ComponentClassCacheUtils;
import lombok.SneakyThrows;
import org.joml.primitives.AABBf;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public class AllayEntityType<T extends Entity> implements EntityType<T> {
    protected final MethodHandle constructorMethodHandle;
    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    protected List<ComponentProvider<? extends EntityComponent>> componentProviders;
    protected Identifier identifier;

    @SneakyThrows
    protected AllayEntityType(Class<T> interfaceClass,
                              List<ComponentProvider<? extends EntityComponent>> componentProviders,
                              Identifier identifier) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.identifier = identifier;
        try {
            ArrayList<ComponentProvider<? extends Component>> components = new ArrayList<>(componentProviders);
            injectedClass = new AllayComponentInjector<T>()
                    .interfaceClass(interfaceClass)
                    .component(components)
                    .useCachedClass(ComponentClassCacheUtils.loadEntityType(interfaceClass))
                    .inject(true);
        } catch (Exception e) {
            throw new EntityTypeBuildException("Failed to create entity type!", e);
        }
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(void.class, ComponentInitInfo.class);
        //Cache constructor Method Handle
        var temp = lookup.findConstructor(injectedClass, methodType);
        constructorMethodHandle = temp.asType(temp.type().changeParameterType(0, EntityInitInfo.class).changeReturnType(Object.class));
    }

    @Override
    public List<ComponentProvider<? extends EntityComponent>> getComponentProviders() {
        return componentProviders;
    }

    @SneakyThrows
    @Override
    public T createEntity(EntityInitInfo<T> info) {
        info.setEntityType(this);
        return injectedClass.cast(constructorMethodHandle.invokeExact(info));
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    public static <T extends Entity> EntityTypeBuilder<T, EntityComponent> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    public static class Builder<T extends Entity> implements EntityTypeBuilder<T, EntityComponent> {
        protected Class<T> interfaceClass;
        protected Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders = new HashMap<>();
        protected Identifier identifier;

        public Builder(Class<T> interfaceClass) {
            this.interfaceClass = interfaceClass;
        }

        @Override
        public EntityTypeBuilder<T, EntityComponent> identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        @Override
        public EntityTypeBuilder<T, EntityComponent> identifier(String identifier) {
            this.identifier = new Identifier(identifier);
            return this;
        }

        @Override
        public EntityTypeBuilder<T, EntityComponent> vanillaEntity(VanillaEntityId vanillaEntityId) {
            this.identifier = vanillaEntityId.getIdentifier();
            return this;
        }

        @Override
        public EntityTypeBuilder<T, EntityComponent> setComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders) {
            if (componentProviders == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        @Override
        public EntityTypeBuilder<T, EntityComponent> addComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        @Override
        public EntityTypeBuilder<T, EntityComponent> addComponent(Function<EntityInitInfo<T>, EntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.EntityComponentProvider<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        @Override
        public EntityType<T> build() {
            if (!componentProviders.containsKey(EntityBaseComponentImpl.IDENTIFIER)) {
                addComponent(info -> new EntityBaseComponentImpl<>(info, new AABBf(0, 0, 0, 1, 1, 1)),
                        EntityBaseComponentImpl.class);
            }
            if (identifier == null) {
                throw new EntityTypeBuildException("identifier cannot be null!");
            }
            var type = new AllayEntityType<>(interfaceClass, new ArrayList<>(componentProviders.values()), identifier);
            EntityTypeRegistry.getRegistry().register(identifier, type);
            return type;
        }
    }
}
