package cn.allay.server.item.type;

import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.component.annotation.AutoRegister;
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
import cn.allay.api.item.type.ItemStackInitInfo;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.server.component.injector.AllayComponentInjector;
import cn.allay.server.utils.ComponentClassCacheUtils;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.util.*;

import static java.lang.reflect.Modifier.isStatic;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public final class AllayItemType<T extends ItemStack> implements ItemType<T> {
    private final Class<T> interfaceClass;
    private final Class<T> injectedClass;
    private final Constructor<T> constructor;
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
        //Cache constructor
        constructor = injectedClass.getConstructor(ComponentInitInfo.class);
    }

    public static <T extends ItemStack> ItemTypeBuilder<T> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    @Override
    public List<ComponentProvider<? extends ItemComponent>> getComponentProviders() {
        return componentProviders;
    }

    @SneakyThrows
    @Override
    public T createItemStack(ItemStackInitInfo<T> info) {
        info.setItemType(this);
        return constructor.newInstance(info);
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
    public static class Builder<T extends ItemStack> implements ItemTypeBuilder<T> {
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
        public ItemTypeBuilder<T> identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        @Override
        public ItemTypeBuilder<T> blockIdentifier(Identifier blockIdentifier) {
            this.blockIdentifier = blockIdentifier;
            return this;
        }

        @Override
        public ItemTypeBuilder<T> vanillaItem(VanillaItemId vanillaItemId) {
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
        public ItemTypeBuilder<T> runtimeId(int runtimeId) {
            this.runtimeId = runtimeId;
            return this;
        }

        @Override
        public ItemTypeBuilder<T> setComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders) {
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        @Override
        public ItemTypeBuilder<T> addComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        @Override
        public ItemTypeBuilder<T> addComponent(ComponentProvider<? extends ItemComponent> componentProvider) {
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
                            addComponent((ComponentProvider<? extends ItemComponent>) field.get(null));
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
            var type = new AllayItemType<>(interfaceClass, new ArrayList<>(componentProviders.values()), identifier, runtimeId, blockIdentifier);
            ItemTypeRegistry.getRegistry().register(identifier, type);
            return type;
        }
    }
}
