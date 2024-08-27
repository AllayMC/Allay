package org.allaymc.server.entity.type;

import lombok.Getter;
import lombok.SneakyThrows;
import me.sunlan.fastreflection.FastConstructor;
import me.sunlan.fastreflection.FastMemberLoader;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.Allay;
import org.allaymc.server.block.type.BlockTypeBuildException;
import org.allaymc.server.component.injector.AllayComponentInjector;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;

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
    protected final FastConstructor<T> constructor;
    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    @Getter
    protected List<ComponentProvider<? extends EntityComponent>> componentProviders;
    @Getter
    protected Identifier identifier;

    @SneakyThrows
    protected AllayEntityType(
            Class<T> interfaceClass,
            List<ComponentProvider<? extends EntityComponent>> componentProviders,
            Identifier identifier
    ) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.identifier = identifier;

        try {
            List<ComponentProvider<? extends Component>> components = new ArrayList<>(componentProviders);
            this.injectedClass = new AllayComponentInjector<T>()
                    .interfaceClass(interfaceClass)
                    .component(components)
                    .inject(false); // todo custom entity is always update
        } catch (Exception e) {
            throw new EntityTypeBuildException("Failed to create entity type!", e);
        }

        var fastMemberLoader = new FastMemberLoader(Allay.EXTRA_RESOURCE_CLASS_LOADER);
        this.constructor = FastConstructor.create(
                this.injectedClass.getConstructor(ComponentInitInfo.class),
                fastMemberLoader,
                false
        );
    }

    public static <T extends Entity> EntityTypeBuilder<T> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    @SneakyThrows
    @Override
    public T createEntity(EntityInitInfo info) {
        info.setEntityType(this);
        return (T) constructor.invoke(info);
    }

    public static class Builder<T extends Entity> implements EntityTypeBuilder<T> {
        protected Class<T> interfaceClass;
        protected Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders = new HashMap<>();
        protected Identifier identifier;

        public Builder(Class<T> interfaceClass) {
            this.interfaceClass = interfaceClass;
        }

        @Override
        public EntityTypeBuilder<T> identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        @Override
        public EntityTypeBuilder<T> identifier(String identifier) {
            this.identifier = new Identifier(identifier);
            return this;
        }

        @Override
        public EntityTypeBuilder<T> vanillaEntity(VanillaEntityId vanillaEntityId) {
            this.identifier = vanillaEntityId.getIdentifier();
            return this;
        }

        @Override
        public EntityTypeBuilder<T> setComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders) {
            if (componentProviders == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        @Override
        public EntityTypeBuilder<T> addComponents(Map<Identifier, ComponentProvider<? extends EntityComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        @Override
        public EntityTypeBuilder<T> addComponent(Function<EntityInitInfo, ? extends EntityComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.SimpleComponentProvider<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        @Override
        public EntityTypeBuilder<T> addComponent(ComponentProvider<? extends EntityComponent> p) {
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        @Override
        public EntityType<T> build() {
            if (!componentProviders.containsKey(EntityBaseComponentImpl.IDENTIFIER)) {
                addComponent(EntityBaseComponentImpl::new, EntityBaseComponentImpl.class);
            }
            if (identifier == null) {
                throw new EntityTypeBuildException("identifier cannot be null!");
            }
            var type = new AllayEntityType<>(interfaceClass, new ArrayList<>(componentProviders.values()), identifier);
            Registries.ENTITIES.register(identifier, type);
            return type;
        }
    }
}
