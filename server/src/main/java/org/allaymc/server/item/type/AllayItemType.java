package org.allaymc.server.item.type;

import com.google.common.base.Preconditions;
import com.google.common.base.Suppliers;
import lombok.Getter;
import lombok.ToString;
import me.sunlan.fastreflection.FastConstructor;
import me.sunlan.fastreflection.FastMemberLoader;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.item.data.ItemData;
import org.allaymc.api.item.data.ItemTag;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.Allay;
import org.allaymc.server.component.ComponentProvider;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.server.registry.InternalRegistries;
import org.allaymc.server.utils.BlockAndItemIdMapper;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.allaymc.server.component.ComponentProvider.toMap;

/**
 * @author daoge_cmd
 */
public final class AllayItemType<T extends ItemStack> implements ItemType<T> {

    private final Function<ItemStackInitInfo, T> instanceCreator;
    @Getter
    private final Identifier identifier;
    @Getter
    private final int runtimeId;
    @Getter
    private final Set<ItemTag> itemTags;
    @Getter
    private final ItemData itemData;

    private final Supplier<BlockType<?>> blockType;
    @Getter
    private final ItemDefinition itemDefinition;

    private AllayItemType(
            Function<ItemStackInitInfo, T> instanceCreator, Identifier identifier, int runtimeId,
            Set<ItemTag> itemTags, ItemData itemData, ItemDefinitionGenerator itemDefinitionGenerator
    ) {
        this.instanceCreator = instanceCreator;
        this.identifier = identifier;
        this.runtimeId = runtimeId;
        this.itemTags = itemTags;
        this.itemData = itemData;
        this.blockType = Suppliers.memoize(() -> Registries.BLOCKS.get(BlockAndItemIdMapper.itemIdToPossibleBlockId(identifier)));
        this.itemDefinition = itemDefinitionGenerator.generate(this);
    }

    public static <T extends ItemStack> Builder builder(Class<T> clazz) {
        return new Builder(clazz);
    }

    @Override
    public T createItemStack(ItemStackInitInfo info) {
        // "info" for ItemAirType is useless and can be null
        if (info != null) {
            info.setItemType(this);
        }
        return instanceCreator.apply(info);
    }

    @Override
    public BlockType<?> getBlockType() {
        return blockType.get();
    }

    @ToString
    public static class Builder {

        protected static int CUSTOM_ITEM_RUNTIME_ID_COUNTER = 10000;

        protected Class<?> clazz;
        protected Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders = new HashMap<>();
        protected Identifier identifier;
        protected int runtimeId = Integer.MAX_VALUE;
        protected Set<ItemTag> itemTags = Set.of();
        protected ItemData itemData = ItemData.DEFAULT;
        protected ItemDefinitionGenerator itemDefinitionGenerator;

        /**
         * Creates a new item type builder.
         *
         * @param clazz the item stack implementation class
         */
        public Builder(Class<?> clazz) {
            this.clazz = clazz;
            this.itemDefinitionGenerator = $ -> ItemDefinition.DEFAULT;
        }

        /**
         * Sets the item identifier.
         *
         * @param identifier the item identifier
         * @return this builder
         */
        public Builder identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        /**
         * Sets the item identifier from a string.
         *
         * @param identifier the item identifier string (e.g., "myplugin:my_item")
         * @return this builder
         */
        public Builder identifier(String identifier) {
            return identifier(new Identifier(identifier));
        }

        /**
         * Configures this builder for a vanilla item.
         * <p>
         * This method sets up the runtime ID, item data, item definition,
         * and item tags from the internal registries based on the vanilla item ID.
         *
         * @param itemId the vanilla item ID
         * @return this builder
         * @throws ItemTypeBuildException if item data is not found for the vanilla item
         */
        public Builder vanillaItem(ItemId itemId) {
            this.identifier = itemId.getIdentifier();
            this.runtimeId = itemId.getRuntimeId();

            // Item data for vanilla item
            itemData(InternalRegistries.ITEM_DATA.get(itemId));
            if (itemData == null) {
                throw new ItemTypeBuildException("Cannot find item data for vanilla item " + itemId + " in registry!");
            }

            // Item component data for vanilla item
            var vanillaItemDefinition = InternalRegistries.ITEM_DEFINITIONS.get(itemId);
            if (vanillaItemDefinition != null) {
                itemDefinitionGenerator($ -> vanillaItemDefinition);
            }

            // Tags for vanilla item
            var tags = InternalRegistries.ITEM_TAGS.get(itemId);
            if (tags != null) {
                setItemTags(tags);
            }

            return this;
        }

        /**
         * Sets the item data containing properties like max stack size, max damage, etc.
         *
         * @param itemData the item data
         * @return this builder
         * @see ItemData
         */
        public Builder itemData(ItemData itemData) {
            this.itemData = itemData;
            return this;
        }

        /**
         * Sets the item definition generator for custom items.
         * <p>
         * The generator creates the client-side item definition that is sent to players.
         *
         * @param itemDefinitionGenerator the item definition generator
         * @return this builder
         */
        public Builder itemDefinitionGenerator(ItemDefinitionGenerator itemDefinitionGenerator) {
            this.itemDefinitionGenerator = itemDefinitionGenerator;
            return this;
        }

        /**
         * Sets the runtime ID for this item.
         * <p>
         * For custom items, a unique runtime ID will be auto-generated if not set.
         * For vanilla items, use {@link #vanillaItem(ItemId)} which sets the correct runtime ID.
         *
         * @param runtimeId the runtime ID
         * @return this builder
         */
        public Builder runtimeId(int runtimeId) {
            this.runtimeId = runtimeId;
            return this;
        }

        /**
         * Sets the item components from a list, replacing any existing components.
         *
         * @param componentProviders the list of component providers
         * @return this builder
         */
        public Builder setComponents(List<ComponentProvider<? extends ItemComponent>> componentProviders) {
            return setComponents(toMap(componentProviders));
        }

        /**
         * Sets the item components, replacing any existing components.
         *
         * @param componentProviders a map of component identifiers to component providers
         * @return this builder
         */
        public Builder setComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders) {
            this.componentProviders = new HashMap<>(componentProviders);
            return this;
        }

        /**
         * Adds multiple components to this item.
         *
         * @param componentProviders a map of component identifiers to component providers
         * @return this builder
         */
        public Builder addComponents(Map<Identifier, ComponentProvider<? extends ItemComponent>> componentProviders) {
            this.componentProviders.putAll(componentProviders);
            return this;
        }

        /**
         * Adds a component with a factory function.
         *
         * @param provider       the component factory function
         * @param componentClass the component class for identifier resolution
         * @return this builder
         */
        public Builder addComponent(Function<ItemStackInitInfo, ? extends ItemComponent> provider, Class<?> componentClass) {
            var p = new ComponentProvider.Simple<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        /**
         * Adds a component provider to this item.
         *
         * @param p the component provider
         * @return this builder
         */
        public Builder addComponent(ComponentProvider<? extends ItemComponent> p) {
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        /**
         * Adds multiple components from a list to this item.
         *
         * @param componentProviders the list of component providers to add
         * @return this builder
         */
        public Builder addComponents(List<ComponentProvider<? extends ItemComponent>> componentProviders) {
            return addComponents(toMap(componentProviders));
        }

        /**
         * Adds a component with a supplier (for stateless components).
         *
         * @param supplier       the component supplier
         * @param componentClass the component class for identifier resolution
         * @return this builder
         */
        public Builder addComponent(Supplier<? extends ItemComponent> supplier, Class<?> componentClass) {
            return addComponent($ -> supplier.get(), componentClass);
        }

        /**
         * Sets the item tags for this item.
         * <p>
         * Item tags are used for grouping items and checking item properties
         * (e.g., "minecraft:tools", "minecraft:weapons").
         *
         * @param itemTags the collection of item tags
         * @return this builder
         */
        public Builder setItemTags(Collection<ItemTag> itemTags) {
            this.itemTags = new HashSet<>(itemTags);
            return this;
        }

        /**
         * Builds and registers the item type using the default class loader.
         *
         * @param <T> the item stack type
         * @return the built and registered item type
         * @throws NullPointerException   if identifier is not set
         * @throws ItemTypeBuildException if item type creation fails
         */
        public <T extends ItemStack> ItemType<T> build() {
            return build(Allay.class.getClassLoader());
        }

        /**
         * Builds and registers the item type using the specified class loader.
         * <p>
         * This method creates the item type and registers it to the item registry.
         *
         * @param <T>         the item stack type
         * @param classLoader the class loader for reflection operations
         * @return the built and registered item type
         * @throws NullPointerException   if identifier is not set
         * @throws ItemTypeBuildException if item type creation fails
         */
        public <T extends ItemStack> ItemType<T> build(ClassLoader classLoader) {
            Preconditions.checkNotNull(identifier, "identifier");
            if (!componentProviders.containsKey(ItemBaseComponentImpl.IDENTIFIER)) {
                addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class);
            }
            if (runtimeId == Integer.MAX_VALUE) {
                runtimeId = CUSTOM_ITEM_RUNTIME_ID_COUNTER++;
            }

            Function<ItemStackInitInfo, T> instanceCreator;
            try {
                var constructor = FastConstructor.create(clazz.getConstructor(ItemStackInitInfo.class, List.class), new FastMemberLoader(classLoader), false);
                var componentProviderList = new ArrayList<>(componentProviders.values());
                instanceCreator = info -> {
                    try {
                        return (T) constructor.invoke(info, componentProviderList);
                    } catch (Throwable t) {
                        throw new ItemTypeBuildException("Failed to create item stack instance!", t);
                    }
                };
            } catch (Exception e) {
                throw new ItemTypeBuildException("Failed to create item type!", e);
            }

            var type = new AllayItemType<>(instanceCreator, identifier, runtimeId, itemTags, itemData, itemDefinitionGenerator);
            Registries.ITEMS.register(identifier, type);
            return type;
        }
    }
}
