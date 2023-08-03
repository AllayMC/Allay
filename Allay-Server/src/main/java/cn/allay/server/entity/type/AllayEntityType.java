package cn.allay.server.entity.type;

import cn.allay.api.component.annotation.AutoRegister;
import cn.allay.api.component.interfaces.ComponentImpl;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.entity.component.impl.base.EntityBaseComponent;
import cn.allay.api.entity.component.impl.base.EntityBaseComponentImpl;
import cn.allay.api.entity.type.EntityInitInfo;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.entity.type.EntityTypeRegistry;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.block.type.BlockTypeBuildException;
import cn.allay.server.component.injector.AllayComponentInjector;
import cn.allay.server.utils.ComponentClassCacheUtils;
import lombok.SneakyThrows;
import org.joml.primitives.AABBd;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.lang.reflect.Modifier.isStatic;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public class AllayEntityType<T extends Entity> implements EntityType<T> {
    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    protected Constructor<T> constructor;
    protected List<ComponentProvider<? extends EntityComponentImpl>> componentProviders;
    protected Identifier identifier;

    @SneakyThrows
    protected AllayEntityType(Class<T> interfaceClass,
                              List<ComponentProvider<? extends EntityComponentImpl>> componentProviders,
                              Identifier identifier) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.identifier = identifier;
        try {
            ArrayList<ComponentProvider<? extends ComponentImpl>> components = new ArrayList<>(componentProviders);
            injectedClass = new AllayComponentInjector<T>()
                    .interfaceClass(interfaceClass)
                    .component(components)
                    .useCachedClass(ComponentClassCacheUtils.loadEntityType(interfaceClass))
                    .inject(true);
        } catch (Exception e) {
            throw new EntityTypeBuildException("Failed to create entity type!", e);
        }
        //Cache constructor
        constructor = injectedClass.getConstructor(ComponentInitInfo.class);
    }
    @Override
    public List<ComponentProvider<? extends EntityComponentImpl>> getComponentProviders() {
        return componentProviders;
    }

    @SneakyThrows
    @Override
    public T createEntity(EntityInitInfo<T> info) {
        info.setEntityType(this);
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
        protected ComponentProvider<? extends EntityComponentImpl> baseComponentProvider =
                ComponentProvider.of(
                        info -> new EntityBaseComponentImpl<>(
                                (EntityInitInfo<T>) info,
                                e -> new AABBd(0, 0, 0, 0, 0, 0)),
                        EntityBaseComponentImpl.class
                );

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
        public EntityTypeBuilder<T> addComponent(ComponentProvider<? extends EntityComponentImpl> componentProvider) {
            this.componentProviders.add(componentProvider);
            return this;
        }

        @Override
        public EntityTypeBuilder<T> addBasicComponents() {
            Arrays.stream(interfaceClass.getDeclaredFields())
                    .filter(field -> isStatic(field.getModifiers()))
                    .filter(field -> field.getDeclaredAnnotation(AutoRegister.class) != null)
                    .filter(field -> ComponentProvider.class.isAssignableFrom(field.getType()))
                    .sorted(Comparator.comparingInt(field -> field.getDeclaredAnnotation(AutoRegister.class).order()))
                    .forEach(field -> {
                        try {
                            addComponent((ComponentProvider<? extends EntityComponentImpl>) field.get(null));
                        } catch (IllegalAccessException e) {
                            throw new EntityTypeBuildException(e);
                        } catch (ClassCastException e) {
                            throw new EntityTypeBuildException("Field " + field.getName() + "in class" + interfaceClass + " is not a ComponentProvider<? extends EntityComponentImpl>!", e);
                        }
                    });
            addComponent(baseComponentProvider);
            return this;
        }

        @Override
        public <U extends EntityComponentImpl & EntityBaseComponent> EntityTypeBuilder<T> setBaseComponentProvider(ComponentProvider<U> baseComponentProvider) {
            this.baseComponentProvider = baseComponentProvider;
            return this;
        }

        @Override
        public EntityType<T> build() {
            if (identifier == null)
                throw new EntityTypeBuildException("identifier cannot be null!");
            var type = new AllayEntityType<>(interfaceClass, componentProviders, identifier);
            EntityTypeRegistry.getRegistry().register(identifier, type);
            return type;
        }
    }
}
