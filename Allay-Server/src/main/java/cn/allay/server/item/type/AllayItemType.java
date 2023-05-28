package cn.allay.server.item.type;

import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.ItemComponentImpl;
import cn.allay.api.item.component.impl.attribute.ItemAttributeComponentImpl;
import cn.allay.api.item.component.impl.attribute.VanillaItemAttributeRegistry;
import cn.allay.api.item.component.impl.base.ItemBaseComponentImpl;
import cn.allay.api.item.data.VanillaItemId;
import cn.allay.api.item.type.ItemStackInitInfo;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.server.component.injector.AllayComponentInjector;
import lombok.SneakyThrows;
import lombok.ToString;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/19 <br>
 * Allay Project <br>
 */
public class AllayItemType<T extends ItemStack> implements ItemType<T> {
    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    protected Constructor<T> constructor;
    protected List<ComponentProvider<? extends ItemComponentImpl>> componentProviders;
    protected Identifier namespaceId;

    protected AllayItemType(Class<T> interfaceClass,
                            List<ComponentProvider<? extends ItemComponentImpl>> componentProviders,
                            Identifier namespaceId) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.namespaceId = namespaceId;
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
    public Identifier getNamespaceId() {
        return namespaceId;
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
        protected Identifier namespaceId;

        public Builder(Class<T> interfaceClass) {
            if (interfaceClass == null)
                throw new ItemTypeBuildException("Interface class cannot be null!");
            this.interfaceClass = interfaceClass;
        }

        @Override
        public ItemTypeBuilder<T> namespace(Identifier namespaceId) {
            this.namespaceId = namespaceId;
            return this;
        }

        @Override
        public ItemTypeBuilder<T> namespace(String namespaceId) {
            this.namespaceId = new Identifier(namespaceId);
            return this;
        }

        @Override
        public ItemTypeBuilder<T> vanillaItem(VanillaItemId vanillaItemId) {
            return vanillaItem(vanillaItemId, true);
        }

        @Override
        public ItemTypeBuilder<T> vanillaItem(VanillaItemId vanillaItemId, boolean initVanillaItemAttributeComponent) {
            this.namespaceId = vanillaItemId.getNamespaceId();
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
        public ItemTypeBuilder<T> addBasicComponents() {
            //保留以处理后续需求
            return this;
        }

        @Override
        public ItemType<T> build() {
            if (namespaceId == null)
                throw new ItemTypeBuildException("NamespaceId cannot be null!");
            var type = new AllayItemType<>(interfaceClass, componentProviders, namespaceId);
            componentProviders.add(ComponentProvider.of(info -> new ItemBaseComponentImpl(type, (ItemStackInitInfo) info), ItemBaseComponentImpl.class));
            return type.complete();
        }
    }
}
