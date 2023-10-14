package cn.allay.server.item.type;

import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.component.interfaces.Component;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.ItemComponent;
import cn.allay.api.item.component.attribute.ItemAttributeComponentImpl;
import cn.allay.api.item.component.attribute.VanillaItemAttributeRegistry;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.init.ItemStackInitInfo;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.server.component.injector.AllayComponentInjector;
import cn.allay.server.utils.ComponentClassCacheUtils;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public final class AllayItemType<T extends ItemStack> implements ItemType<T> {
    private final MethodHandle constructorMethodHandle;
    private final Class<T> interfaceClass;
    private final Class<T> injectedClass;
    private final List<ComponentProvider<? extends ItemComponent>> componentProviders;
    @Getter
    private final Identifier identifier;
    @Getter
    private final int runtimeId;
    @Getter
    @Nullable
    private final Identifier blockIdentifier;
    @Getter
    @Nullable
    private BlockType<?> blockTypeCache;

    @SneakyThrows
    private AllayItemType(Class<T> interfaceClass,
                          List<ComponentProvider<? extends ItemComponent>> componentProviders,
                          Identifier identifier,
                          int runtimeId,
                          @Nullable Identifier blockIdentifier) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.identifier = identifier;
        this.runtimeId = runtimeId;
        this.blockIdentifier = blockIdentifier;
        try {
            ArrayList<ComponentProvider<? extends Component>> components = new ArrayList<>(componentProviders);
            injectedClass = new AllayComponentInjector<T>()
                    .interfaceClass(interfaceClass)
                    .component(components)
                    .useCachedClass(ComponentClassCacheUtils.loadItemType(interfaceClass))
                    .inject(true);
        } catch (Exception e) {
            throw new ItemTypeBuildException("Failed to create item type!", e);
        }
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(void.class, ComponentInitInfo.class);
        //Cache constructor Method Handle
        var temp = lookup.findConstructor(injectedClass, methodType);
        constructorMethodHandle = temp.asType(temp.type().changeParameterType(0, ItemStackInitInfo.class).changeReturnType(Object.class));
    }

    public static <T extends ItemStack> ItemTypeBuilder<T, ItemComponent> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    @SneakyThrows
    @Override
    public T createItemStack(ItemStackInitInfo<T> info) {
        //"info" for ItemAirType is useless and can be null
        if (info != null) info.setItemType(this);
        return injectedClass.cast(constructorMethodHandle.invokeExact(info));
    }

    @Override
    public List<ComponentProvider<? extends ItemComponent>> getComponentProviders() {
        return componentProviders;
    }

    @Override
    public @Nullable BlockType<?> getBlockType() {
        if (blockTypeCache != null) return blockTypeCache;
        if (blockIdentifier == null) return null;
        blockTypeCache = BlockTypeRegistry.getRegistry().get(blockIdentifier);
        if (blockTypeCache == null)
            throw new IllegalStateException("Block type " + blockIdentifier + " not registered");
        return blockTypeCache;
    }

    @ToString
    public static class Builder<T extends ItemStack> implements ItemTypeBuilder<T, ItemComponent> {
        protected Class<T> interfaceClass;
        protected Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders = new HashMap<>();
        protected Identifier identifier;
        @Nullable
        protected Identifier blockIdentifier;
        protected int runtimeId = Integer.MAX_VALUE;

        public Builder(Class<T> interfaceClass) {
            if (interfaceClass == null)
                throw new ItemTypeBuildException("Interface class cannot be null!");
            this.interfaceClass = interfaceClass;
        }

        @Override
        public ItemTypeBuilder<T, ItemComponent> identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        @Override
        public ItemTypeBuilder<T, ItemComponent> blockIdentifier(Identifier blockIdentifier) {
            this.blockIdentifier = blockIdentifier;
            return this;
        }

        @Override
        public ItemTypeBuilder<T, ItemComponent> vanillaItem(VanillaItemId vanillaItemId) {
            this.identifier = vanillaItemId.getIdentifier();
            this.blockIdentifier = vanillaItemId.getBlockIdentifier();
            this.runtimeId = vanillaItemId.getRuntimeId();
            var attributes = VanillaItemAttributeRegistry.getRegistry().get(vanillaItemId);
            if (attributes == null)
                throw new ItemTypeBuildException("Cannot find vanilla item attribute component for " + vanillaItemId + " from vanilla item attribute registry!");
            var attributeComponent = new ItemAttributeComponentImpl(attributes);
            componentProviders.put(ItemAttributeComponentImpl.IDENTIFIER, ComponentProvider.ofSingleton(attributeComponent));
            return this;
        }

        @Override
        public ItemTypeBuilder<T, ItemComponent> runtimeId(int runtimeId) {
            this.runtimeId = runtimeId;
            return this;
        }

        @Override
        public ItemTypeBuilder<T, ItemComponent> setComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders) {
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        @Override
        public ItemTypeBuilder<T, ItemComponent> addComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        @Override
        public ItemTypeBuilder<T, ItemComponent> addComponent(Function<ItemStackInitInfo<T>, ItemComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.ItemComponentProvider<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        @Override
        public ItemType<T> build() {
            if (!componentProviders.containsKey(ItemBaseComponentImpl.IDENTIFIER)) {
                addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class);
            }
            if (identifier == null) {
                throw new ItemTypeBuildException("identifier cannot be null!");
            }
            if (runtimeId == Integer.MAX_VALUE) {
                throw new ItemTypeBuildException("runtimeId unassigned!");
            }
            var type = new AllayItemType<>(interfaceClass, new ArrayList<>(componentProviders.values()), identifier, runtimeId, blockIdentifier);
            ItemTypeRegistry.getRegistry().register(identifier, type);
            return type;
        }
    }
}
