package cn.allay.server.item.type;

import cn.allay.api.component.annotation.AutoRegister;
import cn.allay.api.component.interfaces.ComponentImpl;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.ItemComponentImpl;
import cn.allay.api.item.component.impl.attribute.ItemAttributeComponentImpl;
import cn.allay.api.item.component.impl.attribute.VanillaItemAttributeRegistry;
import cn.allay.api.item.component.impl.base.ItemBaseComponent;
import cn.allay.api.item.component.impl.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemStackInitInfo;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.server.component.injector.AllayComponentInjector;
import cn.allay.server.utils.ComponentClassCacheUtils;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;

import java.lang.reflect.Constructor;
import java.util.*;

import static java.lang.reflect.Modifier.isStatic;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public class AllayItemType<T extends ItemStack> implements ItemType<T> {
    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    protected Constructor<T> constructor;
    protected List<ComponentProvider<? extends ItemComponentImpl>> componentProviders;
    @Getter
    protected Identifier identifier;
    @Getter
    protected int runtimeId;

    @SneakyThrows
    protected AllayItemType(Class<T> interfaceClass,
                            List<ComponentProvider<? extends ItemComponentImpl>> componentProviders,
                            Identifier identifier,
                            int runtimeId) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.identifier = identifier;
        this.runtimeId = runtimeId;
        try {
            ArrayList<ComponentProvider<? extends ComponentImpl>> components = new ArrayList<>(componentProviders);
            injectedClass = new AllayComponentInjector<T>()
                    .interfaceClass(interfaceClass)
                    .component(components)
                    .useCachedClass(ComponentClassCacheUtils.loadItemType(interfaceClass))
                    .inject(true);
        } catch (Exception e) {
            throw new ItemTypeBuildException("Failed to create item type!", e);
        }
        //Cache constructor
        constructor = injectedClass.getConstructor(ComponentInitInfo.class);
    }

    public static <T extends ItemStack> ItemTypeBuilder<T> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    @Override
    public List<ComponentProvider<? extends ItemComponentImpl>> getComponentProviders() {
        return componentProviders;
    }

    @SneakyThrows
    @Override
    public T createItemStack(ItemStackInitInfo<T> info) {
        info.setItemType(this);
        return constructor.newInstance(info);
    }

    @ToString
    public static class Builder<T extends ItemStack> implements ItemTypeBuilder<T> {
        protected Class<T> interfaceClass;
        protected Map<Identifier, ComponentProvider<? extends ItemComponentImpl>> componentProviders = new HashMap<>();
        protected Identifier identifier;
        protected int runtimeId = Integer.MAX_VALUE;

        public Builder(Class<T> interfaceClass) {
            if (interfaceClass == null)
                throw new ItemTypeBuildException("Interface class cannot be null!");
            this.interfaceClass = interfaceClass;
        }

        @Override
        public ItemTypeBuilder<T> namespace(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        @Override
        public ItemTypeBuilder<T> namespace(String identifier) {
            this.identifier = new Identifier(identifier);
            return this;
        }

        @Override
        public ItemTypeBuilder<T> vanillaItem(VanillaItemId vanillaItemId) {
            return vanillaItem(vanillaItemId, true);
        }

        @Override
        public ItemTypeBuilder<T> vanillaItem(VanillaItemId vanillaItemId, boolean initVanillaItemAttributeComponent) {
            this.identifier = vanillaItemId.getIdentifier();
            this.runtimeId = vanillaItemId.getRuntimeId();
            if (initVanillaItemAttributeComponent) {
                var attributes = VanillaItemAttributeRegistry.getRegistry().get(vanillaItemId);
                if (attributes == null)
                    throw new ItemTypeBuildException("Cannot find vanilla item attribute component for " + vanillaItemId + " from vanilla item attribute registry!");
                var attributeComponent = new ItemAttributeComponentImpl(attributes);
                componentProviders.put(ItemAttributeComponentImpl.IDENTIFIER, ComponentProvider.ofSingleton(attributeComponent));
            }
            return this;
        }

        @Override
        public ItemTypeBuilder<T> runtimeId(int runtimeId) {
            this.runtimeId = runtimeId;
            return this;
        }

        @Override
        public ItemTypeBuilder<T> setComponents(Map<Identifier, ComponentProvider<? extends ItemComponentImpl>> componentProviders) {
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        @Override
        public ItemTypeBuilder<T> addComponents(Map<Identifier, ComponentProvider<? extends ItemComponentImpl>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        @Override
        public ItemTypeBuilder<T> addComponent(ComponentProvider<? extends ItemComponentImpl> componentProvider) {
            this.componentProviders.put(componentProvider.findComponentIdentifier(), componentProvider);
            return this;
        }

        @Override
        public ItemTypeBuilder<T> addBasicComponents() {
            if (!componentProviders.containsKey(ItemBaseComponentImpl.IDENTIFIER))
                addComponent(ComponentProvider.of(info -> new ItemBaseComponentImpl<>((ItemStackInitInfo<T>) info), ItemBaseComponentImpl.class));
            Arrays.stream(interfaceClass.getDeclaredFields())
                    .filter(field -> isStatic(field.getModifiers()))
                    .filter(field -> field.getDeclaredAnnotation(AutoRegister.class) != null)
                    .filter(field -> ComponentProvider.class.isAssignableFrom(field.getType()))
                    .sorted(Comparator.comparingInt(field -> field.getDeclaredAnnotation(AutoRegister.class).order()))
                    .forEach(field -> {
                        try {
                            addComponent((ComponentProvider<? extends ItemComponentImpl>) field.get(null));
                        } catch (IllegalAccessException e) {
                            throw new ItemTypeBuildException(e);
                        } catch (ClassCastException e) {
                            throw new ItemTypeBuildException("Field " + field.getName() + "in class" + interfaceClass + " is not a ComponentProvider<? extends ItemTypeBuildException>!", e);
                        }
                    });
            return this;
        }

        @Override
        public ItemType<T> build() {
            if (identifier == null)
                throw new ItemTypeBuildException("identifier cannot be null!");
            if (runtimeId == Integer.MAX_VALUE)
                throw new ItemTypeBuildException("runtimeId unassigned!");
            var type = new AllayItemType<>(interfaceClass, new ArrayList<>(componentProviders.values()), identifier, runtimeId);
            ItemTypeRegistry.getRegistry().register(identifier, type);
            return type;
        }
    }
}
