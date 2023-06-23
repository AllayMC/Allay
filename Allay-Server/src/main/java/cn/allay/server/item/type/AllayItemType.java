package cn.allay.server.item.type;

import cn.allay.api.component.annotation.AutoRegister;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.ItemComponentImpl;
import cn.allay.api.item.component.impl.attribute.ItemAttributeComponentImpl;
import cn.allay.api.item.component.impl.attribute.VanillaItemAttributeRegistry;
import cn.allay.api.item.component.impl.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemStackInitInfo;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.server.component.injector.AllayComponentInjector;
import lombok.SneakyThrows;
import lombok.ToString;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.lang.reflect.Modifier.isStatic;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/19 <br>
 * Allay Project <br>
 */
public class AllayItemType<T extends ItemStack> implements ItemType<T> {
    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    protected Constructor<T> constructor;
    protected List<ComponentProvider<? extends ItemComponentImpl>> componentProviders;
    protected Identifier identifier;

    protected AllayItemType(Class<T> interfaceClass,
                            List<ComponentProvider<? extends ItemComponentImpl>> componentProviders,
                            Identifier identifier) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.identifier = identifier;
    }

    @SneakyThrows
    protected AllayItemType<T> complete() {
        try {
            injectedClass = new AllayComponentInjector<T>()
                    .interfaceClass(interfaceClass)
                    .component(new ArrayList<>(componentProviders))
                    .inject();
        } catch (Exception e) {
            throw new ItemTypeBuildException("Failed to create item type!", e);
        }
        //Cache constructor
        constructor = injectedClass.getConstructor(ComponentInitInfo.class);
        return this;
    }

    public static <T extends ItemStack> ItemTypeBuilder<T> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public List<ComponentProvider<? extends ItemComponentImpl>> getComponentProviders() {
        return componentProviders;
    }

    @SneakyThrows
    @Override
    public T createItemStack(ItemStackInitInfo info) {
        return constructor.newInstance(info);
    }

    @ToString
    public static class Builder<T extends ItemStack> implements ItemTypeBuilder<T> {
        protected Class<T> interfaceClass;
        protected List<ComponentProvider<? extends ItemComponentImpl>> componentProviders = new ArrayList<>();
        protected Identifier identifier;

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
            if (initVanillaItemAttributeComponent) {
                var attributes = VanillaItemAttributeRegistry.getRegistry().get(vanillaItemId);
                if (attributes == null)
                    throw new ItemTypeBuildException("Cannot find vanilla item attribute component for " + vanillaItemId + " from vanilla item attribute registry!");
                var attributeComponent = new ItemAttributeComponentImpl(attributes);
                componentProviders.add(ComponentProvider.ofSingleton(attributeComponent));
            }
            return this;
        }

        @Override
        public ItemTypeBuilder<T> setComponents(List<ComponentProvider<? extends ItemComponentImpl>> componentProviders) {
            this.componentProviders = new ArrayList<>(componentProviders);
            return this;
        }

        @Override
        public ItemTypeBuilder<T> addComponents(List<ComponentProvider<? extends ItemComponentImpl>> componentProviders) {
            this.componentProviders.addAll(componentProviders);
            return this;
        }

        @Override
        public ItemTypeBuilder<T> addComponent(ComponentProvider<? extends ItemComponentImpl> componentProvider) {
            this.componentProviders.add(componentProvider);
            return this;
        }

        @Override
        public ItemTypeBuilder<T> addBasicComponents() {
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
            var type = new AllayItemType<>(interfaceClass, componentProviders, identifier);
            componentProviders.add(ComponentProvider.of(info -> new ItemBaseComponentImpl(type, (ItemStackInitInfo) info), ItemBaseComponentImpl.class));
            return type.complete();
        }
    }
}
