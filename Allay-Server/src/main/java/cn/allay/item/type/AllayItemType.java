package cn.allay.item.type;

import cn.allay.component.injector.AllayComponentInjector;
import cn.allay.component.interfaces.ComponentInitInfo;
import cn.allay.component.interfaces.ComponentInjector;
import cn.allay.component.interfaces.ComponentProvider;
import cn.allay.identifier.Identifier;
import cn.allay.item.ItemStack;
import cn.allay.item.component.ItemComponentImpl;
import cn.allay.item.component.impl.attribute.ItemAttributeComponentImpl;
import cn.allay.item.component.impl.attribute.VanillaItemAttributeRegistry;
import cn.allay.item.component.impl.base.ItemBaseComponentImpl;
import cn.allay.item.data.VanillaItemId;
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
            return null;
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
            //TODO: 分离逻辑
            componentProviders.add(ComponentProvider.of(info -> {
                var cast = (ItemStackInitInfo) info;
                return new ItemBaseComponentImpl(type, cast.count(), cast.meta(), cast.nbt());
            }, ItemBaseComponentImpl.class));
            return type.complete();
        }
    }
}
