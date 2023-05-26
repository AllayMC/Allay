package cn.allay.entity.type;

import cn.allay.block.type.BlockTypeBuildException;
import cn.allay.component.injector.AllayComponentInjector;
import cn.allay.component.interfaces.ComponentInitInfo;
import cn.allay.component.interfaces.ComponentProvider;
import cn.allay.entity.Entity;
import cn.allay.entity.component.EntityComponentImpl;
import cn.allay.entity.component.impl.base.EntityBaseComponentImpl;
import cn.allay.entity.data.VanillaEntityId;
import cn.allay.identifier.Identifier;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/20 <br>
 * Allay Project <br>
 */
public class AllayEntityType<T extends Entity> implements EntityType<T> {
    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    protected Constructor<T> constructor;
    protected List<ComponentProvider<? extends EntityComponentImpl>> componentProviders;
    protected Identifier namespaceId;

    protected AllayEntityType(Class<T> interfaceClass,
                              List<ComponentProvider<? extends EntityComponentImpl>> componentProviders,
                              Identifier namespaceId) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.namespaceId = namespaceId;
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
    public Identifier getNamespaceId() {
        return namespaceId;
    }

    public static <T extends Entity> EntityTypeBuilder<T> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    public static class Builder<T extends Entity> implements EntityTypeBuilder<T> {
        protected Class<T> interfaceClass;
        protected List<ComponentProvider<? extends EntityComponentImpl>> componentProviders;
        protected Identifier namespaceId;

        public Builder(Class<T> interfaceClass) {
            this.interfaceClass = interfaceClass;
        }

        @Override
        public EntityTypeBuilder<T> namespace(Identifier identifier) {
            this.namespaceId = identifier;
            return this;
        }

        @Override
        public EntityTypeBuilder<T> namespace(String namespaceId) {
            this.namespaceId = new Identifier(namespaceId);
            return this;
        }

        @Override
        public EntityTypeBuilder<T> vanillaEntity(VanillaEntityId vanillaEntityId) {
            this.namespaceId = vanillaEntityId.getNamespaceId();
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
            if (namespaceId == null)
                throw new EntityTypeBuildException("NamespaceId cannot be null!");
            var type = new AllayEntityType<>(interfaceClass, componentProviders, namespaceId);
            componentProviders.add(ComponentProvider.of(info -> new EntityBaseComponentImpl(type, (EntityInitInfo) info), EntityBaseComponentImpl.class));
            return type.complete();
        }
    }
}
