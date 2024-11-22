package org.allaymc.server.item.type;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import me.sunlan.fastreflection.FastConstructor;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.tag.ItemTag;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.BlockAndItemIdMapper;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.allaymc.server.item.component.ItemDataComponentImpl;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.allaymc.server.component.interfaces.ComponentProvider.toMap;

/**
 * @author daoge_cmd
 */
public final class AllayItemType<T extends ItemStack> implements ItemType<T> {
    @Getter
    private final Identifier identifier;
    @Getter
    private final int runtimeId;
    @Getter
    private final Set<ItemTag> itemTags;
    private final Function<ItemStackInitInfo, T> instanceCreator;

    private BlockType<?> blockTypeCache;
    private boolean haveTriedInitBlockTypeCache;

    @SneakyThrows
    private AllayItemType(
            Function<ItemStackInitInfo, T> instanceCreator,
            Identifier identifier,
            int runtimeId,
            Set<ItemTag> itemTags
    ) {
        this.instanceCreator = instanceCreator;
        this.identifier = identifier;
        this.runtimeId = runtimeId;
        this.itemTags = itemTags;
    }

    public static <T extends ItemStack> Builder builder(Class<T> interfaceClass) {
        return new Builder(interfaceClass);
    }

    @SneakyThrows
    @Override
    public T createItemStack(ItemStackInitInfo info) {
        // "info" for ItemAirType is useless and can be null
        if (info != null) info.setItemType(this);
        return instanceCreator.apply(info);
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
    public static class Builder {

        protected static int CUSTOM_ITEM_RUNTIME_ID_COUNTER = 10000;

        protected Class<?> clazz;
        protected Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders = new HashMap<>();
        protected Identifier identifier;
        protected int runtimeId = Integer.MAX_VALUE;
        protected Set<ItemTag> itemTags = Set.of();

        public Builder(Class<?> clazz) {
            if (clazz == null)
                throw new ItemTypeBuildException("Interface class cannot be null!");
            this.clazz = clazz;
        }

        public Builder identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder identifier(String identifier) {
            return identifier(new Identifier(identifier));
        }

        public Builder vanillaItem(ItemId itemId) {
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

        public Builder runtimeId(int runtimeId) {
            this.runtimeId = runtimeId;
            return this;
        }

        public Builder setComponents(List<ComponentProvider<? extends ItemComponent>> componentProviders) {
            return setComponents(toMap(componentProviders));
        }

        public Builder setComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders) {
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        public Builder addComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        public Builder addComponent(Function<ItemStackInitInfo, ? extends ItemComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.SimpleComponentProvider<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        public Builder addComponent(ComponentProvider<? extends ItemComponent> p) {
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        public Builder addComponents(List<ComponentProvider<? extends ItemComponent>> componentProviders) {
            return addComponents(toMap(componentProviders));
        }

        public Builder addComponent(Supplier<? extends ItemComponent> supplier, Class<?> componentClass) {
            return addComponent($ -> supplier.get(), componentClass);
        }

        public Builder setItemTags(ItemTag... itemTags) {
            this.itemTags = Set.of(itemTags);
            return this;
        }

        public <T extends ItemStack> ItemType<T> build() {
            if (!componentProviders.containsKey(ItemBaseComponentImpl.IDENTIFIER))
                addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class);
            if (!componentProviders.containsKey(ItemDataComponentImpl.IDENTIFIER))
                addComponent($ -> ItemDataComponentImpl.ofDefault(), ItemDataComponentImpl.class);

            if (identifier == null) throw new ItemTypeBuildException("identifier cannot be null!");
            if (runtimeId == Integer.MAX_VALUE) runtimeId = CUSTOM_ITEM_RUNTIME_ID_COUNTER++;

            Function<ItemStackInitInfo, T> instanceCreator;
            try {
                var fastMemberConstructor = FastConstructor.create(clazz.getConstructors()[0]);
                var componentProviderList = new ArrayList<>(componentProviders.values());
                instanceCreator = info -> {
                    try {
                        return (T) fastMemberConstructor.invoke(info, componentProviderList);
                    } catch (Throwable t) {
                        throw new ItemTypeBuildException("Failed to create item stack instance!", t);
                    }
                };
            } catch (Exception e) {
                throw new ItemTypeBuildException("Failed to create item type!", e);
            }

            var type = new AllayItemType<>(instanceCreator, identifier, runtimeId, itemTags);
            Registries.ITEMS.register(identifier, type);
            return type;
        }
    }
}
