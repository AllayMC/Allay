package cn.allay.server.entity.type;

import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.entity.component.impl.base.EntityBaseComponentImpl;
import cn.allay.api.entity.type.EntityInitInfo;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.block.type.BlockTypeBuildException;
import cn.allay.server.component.injector.AllayComponentInjector;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/20 <br>
 * Allay Project <br>
 */
public class AllayEntityType<T extends Entity> implements EntityType<T> {
    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    protected Constructor<T> constructor;
    protected List<ComponentProvider<? extends EntityComponentImpl>> componentProviders;
    protected Identifier identifier;

    protected AllayEntityType(Class<T> interfaceClass,
                              List<ComponentProvider<? extends EntityComponentImpl>> componentProviders,
                              Identifier identifier) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.identifier = identifier;
    }

    @SneakyThrows
    protected AllayEntityType<T> complete() {
        try {
            injectedClass = new AllayComponentInjector<T>()
                    .interfaceClass(interfaceClass)
                    .component(new ArrayList<>(componentProviders))
                    .inject();
        } catch (Exception e) {
            throw new EntityTypeBuildException("Failed to create entity type!", e);
        }
        //Cache constructor
        constructor = injectedClass.getConstructor(ComponentInitInfo.class);
        return this;
    }

    @Override
    public List<ComponentProvider<? extends EntityComponentImpl>> getComponentProviders() {
        return componentProviders;
    }

    @SneakyThrows
    @Override
    public T createEntity(EntityInitInfo info) {
        return constructor.newInstance(info);
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    public static <T extends Entity> EntityTypeBuilder<T> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    public static class Builder<T extends Entity> implements EntityTypeBuilder<T> {
        protected Class<T> interfaceClass;
        protected List<ComponentProvider<? extends EntityComponentImpl>> componentProviders = new ArrayList<>();
        protected Identifier identifier;

        public Builder(Class<T> interfaceClass) {
            this.interfaceClass = interfaceClass;
        }

        @Override
        public EntityTypeBuilder<T> namespace(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        @Override
        public EntityTypeBuilder<T> namespace(String identifier) {
            this.identifier = new Identifier(identifier);
            return this;
        }

        @Override
        public EntityTypeBuilder<T> vanillaEntity(VanillaEntityId vanillaEntityId) {
            this.identifier = vanillaEntityId.getIdentifier();
            return this;
        }

        @Override
        public EntityTypeBuilder<T> setComponents(List<ComponentProvider<? extends EntityComponentImpl>> componentProviders) {
            if (componentProviders == null)
                throw new BlockTypeBuildException("Component providers cannot be null");
            this.componentProviders = new ArrayList<>(componentProviders);
            return this;
        }

        @Override
        public EntityTypeBuilder<T> addComponents(List<ComponentProvider<? extends EntityComponentImpl>> componentProviders) {
            this.componentProviders.addAll(componentProviders);
            return this;
        }

        @Override
        public EntityTypeBuilder<T> addBasicComponents() {
            //Unused
            return this;
        }

        @Override
        public EntityType<T> build() {
            if (identifier == null)
                throw new EntityTypeBuildException("identifier cannot be null!");
            var type = new AllayEntityType<>(interfaceClass, componentProviders, identifier);
            componentProviders.add(ComponentProvider.of(info -> new EntityBaseComponentImpl(type, (EntityInitInfo) info), EntityBaseComponentImpl.class));
            return type.complete();
        }
    }
}
