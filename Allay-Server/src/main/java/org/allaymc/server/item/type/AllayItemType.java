package org.allaymc.server.item.type;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import me.sunlan.fastreflection.FastConstructor;
import me.sunlan.fastreflection.FastMemberLoader;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.tag.ItemTag;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.BlockAndItemIdMapper;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.Allay;
import org.allaymc.server.component.injector.AllayComponentInjector;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.allaymc.server.item.component.ItemDataComponentImpl;
import org.allaymc.server.utils.ComponentClassCacheUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.allaymc.server.component.interfaces.ComponentProvider.toMap;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public final class AllayItemType<T extends ItemStack> implements ItemType<T> {
    @Getter
    private final Identifier identifier;
    @Getter
    private final int runtimeId;
    @Getter
    private final Set<ItemTag> itemTags;
    private final FastConstructor<T> constructor;

    private BlockType<?> blockTypeCache;
    private boolean haveTriedInitBlockTypeCache;

    @SneakyThrows
    private AllayItemType(
            FastConstructor<T> constructor,
            Identifier identifier,
            int runtimeId,
            Set<ItemTag> itemTags
    ) {
        this.constructor = constructor;
        this.identifier = identifier;
        this.runtimeId = runtimeId;
        this.itemTags = itemTags;
    }

    public static <T extends ItemStack> Builder<T> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    @SneakyThrows
    @Override
    public T createItemStack(ItemStackInitInfo info) {
        // "info" for ItemAirType is useless and can be null
        if (info != null) info.setItemType(this);
        return (T) constructor.invoke(info);
    }

    @Override
    public BlockType<?> getBlockType() {
        if (!haveTriedInitBlockTypeCache) {
            // Try to find out if this item type has a corresponding block type
            var blockIdentifier = BlockAndItemIdMapper.itemIdToPossibleBlockId(identifier);
            // Note that the block type still may be null
            blockTypeCache = Registries.BLOCKS.get(blockIdentifier);
            haveTriedInitBlockTypeCache = true;
        }
        return blockTypeCache;
    }

    @ToString
    public static class Builder<T extends ItemStack> {

        protected static int CUSTOM_ITEM_RUNTIME_ID_COUNTER = 10000;

        protected Class<T> interfaceClass;
        protected Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders = new HashMap<>();
        protected Identifier identifier;
        protected int runtimeId = Integer.MAX_VALUE;
        protected Set<ItemTag> itemTags = Set.of();

        public Builder(Class<T> interfaceClass) {
            if (interfaceClass == null)
                throw new ItemTypeBuildException("Interface class cannot be null!");
            this.interfaceClass = interfaceClass;
        }

        public Builder<T> identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder<T> identifier(String identifier) {
            return identifier(new Identifier(identifier));
        }

        public Builder<T> vanillaItem(ItemId itemId) {
            this.identifier = itemId.getIdentifier();
            this.runtimeId = itemId.getRuntimeId();

            // Attributes for vanilla item
            var itemData = Registries.ITEM_DATA.get(itemId);
            if (itemData == null)
                throw new ItemTypeBuildException("Cannot find vanilla item data component for " + itemId + " from vanilla item attribute registry!");

            var attributeComponent = new ItemDataComponentImpl(itemData);
            componentProviders.put(ItemDataComponentImpl.IDENTIFIER, ComponentProvider.ofSingleton(attributeComponent));

            // Tags for vanilla item
            var tags = InternalItemTypeData.getItemTags(itemId);
            if (tags.length != 0) setItemTags(tags);
            return this;
        }

        public Builder<T> runtimeId(int runtimeId) {
            this.runtimeId = runtimeId;
            return this;
        }

        public Builder<T> setComponents(List<ComponentProvider<? extends ItemComponent>> componentProviders) {
            return setComponents(toMap(componentProviders));
        }

        public Builder<T> setComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders) {
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        public Builder<T> addComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        public Builder<T> addComponent(Function<ItemStackInitInfo, ? extends ItemComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.SimpleComponentProvider<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        public Builder<T> addComponent(ComponentProvider<? extends ItemComponent> p) {
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        public Builder<T> addComponents(List<ComponentProvider<? extends ItemComponent>> componentProviders) {
            return addComponents(toMap(componentProviders));
        }

        public Builder<T> addComponent(Supplier<? extends ItemComponent> supplier, Class<?> componentClass) {
            return addComponent($ -> supplier.get(), componentClass);
        }

        public Builder<T> setItemTags(ItemTag... itemTags) {
            this.itemTags = Set.of(itemTags);
            return this;
        }

        public ItemType<T> build() {
            if (!componentProviders.containsKey(ItemBaseComponentImpl.IDENTIFIER))
                addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class);
            if (!componentProviders.containsKey(ItemDataComponentImpl.IDENTIFIER))
                addComponent($ -> ItemDataComponentImpl.ofDefault(), ItemDataComponentImpl.class);

            if (identifier == null) throw new ItemTypeBuildException("identifier cannot be null!");
            if (runtimeId == Integer.MAX_VALUE) runtimeId = CUSTOM_ITEM_RUNTIME_ID_COUNTER++;

            FastConstructor<T> constructor;
            try {
                var injectedClass = new AllayComponentInjector<T>()
                        .interfaceClass(interfaceClass)
                        .component(new ArrayList<>(componentProviders.values()))
                        .useCachedClass(ComponentClassCacheUtils.getCacheClass(interfaceClass))
                        .inject(false);

                var fastMemberLoader = new FastMemberLoader(Allay.EXTRA_RESOURCE_CLASS_LOADER);
                constructor = FastConstructor.create(
                        injectedClass.getConstructor(ComponentInitInfo.class),
                        fastMemberLoader,
                        false
                );
            } catch (Exception e) {
                throw new ItemTypeBuildException("Failed to create item type!", e);
            }

            var type = new AllayItemType<>(constructor, identifier, runtimeId, itemTags);
            Registries.ITEMS.register(identifier, type);
            return type;
        }
    }
}
