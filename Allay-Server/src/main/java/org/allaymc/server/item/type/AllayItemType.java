package org.allaymc.server.item.type;

import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import me.sunlan.fastreflection.FastConstructor;
import me.sunlan.fastreflection.FastMemberLoader;
import org.allaymc.api.block.registry.BlockTypeRegistry;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.data.VanillaItemTags;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.item.registry.ItemTypeRegistry;
import org.allaymc.api.item.registry.VanillaItemAttributeRegistry;
import org.allaymc.api.item.tag.ItemTag;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.BlockAndItemIdMapper;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.Allay;
import org.allaymc.server.component.injector.AllayComponentInjector;
import org.allaymc.server.item.component.common.ItemAttributeComponentImpl;
import org.allaymc.server.item.component.common.ItemBaseComponentImpl;
import org.allaymc.server.utils.ComponentClassCacheUtils;
import org.allaymc.server.utils.ResourceUtils;

import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
@Getter
public final class AllayItemType<T extends ItemStack> implements ItemType<T> {
    private final Class<T> interfaceClass;
    private final List<ComponentProvider<? extends ItemComponent>> componentProviders;
    private final Identifier identifier;
    private final int runtimeId;
    private final Set<ItemTag> itemTags;
    private final Class<T> injectedClass;

    private final FastConstructor<T> constructor;

    private BlockType<?> blockTypeCache;
    private boolean haveTriedInitBlockTypeCache;

    @SneakyThrows
    private AllayItemType(
            Class<T> interfaceClass,
            List<ComponentProvider<? extends ItemComponent>> componentProviders,
            Identifier identifier,
            int runtimeId,
            Set<ItemTag> itemTags
    ) {
        this.interfaceClass = interfaceClass;
        this.componentProviders = componentProviders;
        this.identifier = identifier;
        this.runtimeId = runtimeId;
        this.itemTags = itemTags;

        try {
            List<ComponentProvider<? extends Component>> components = new ArrayList<>(componentProviders);
            injectedClass = new AllayComponentInjector<T>()
                    .interfaceClass(interfaceClass)
                    .component(components)
                    .useCachedClass(ComponentClassCacheUtils.getCacheClass(interfaceClass))
                    .inject(false);//todo custom item is always update
        } catch (Exception e) {
            throw new ItemTypeBuildException("Failed to create item type!", e);
        }

        var fastMemberLoader = new FastMemberLoader(Allay.EXTRA_RESOURCE_CLASS_LOADER);
        this.constructor = FastConstructor.create(
                injectedClass.getConstructor(ComponentInitInfo.class),
                fastMemberLoader,
                false
        );
    }

    public static <T extends ItemStack> ItemTypeBuilder<T, ItemComponent> builder(Class<T> interfaceClass) {
        return new Builder<>(interfaceClass);
    }

    @SneakyThrows
    @Override
    public T createItemStack(ItemStackInitInfo<T> info) {
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
            blockTypeCache = BlockTypeRegistry.getRegistry().get(blockIdentifier);
            haveTriedInitBlockTypeCache = true;
        }
        return blockTypeCache;
    }

    @ToString
    public static class Builder<T extends ItemStack> implements ItemTypeBuilder<T, ItemComponent> {
        // Use array instead of set to reduce memory usage
        protected static final Map<VanillaItemId, ItemTag[]> VANILLA_ITEM_TAGS = new HashMap<>();

        protected static int CUSTOM_ITEM_RUNTIME_ID_COUNTER = 10000;

        static {
            Map<VanillaItemId, Set<ItemTag>> itemId2tags = new HashMap<>();
            var element = JsonParser.parseReader(new InputStreamReader(ResourceUtils.getResource("item_tags.json")));
            element.getAsJsonObject().entrySet().forEach(entry -> {
                var tag = VanillaItemTags.getTagByName(entry.getKey());
                entry.getValue().getAsJsonArray().forEach(itemId -> {
                    var id = VanillaItemId.valueOf(AllayStringUtils.fastTwoPartSplit(itemId.getAsString(), ":", "")[1].toUpperCase());
                    itemId2tags.computeIfAbsent(id, $ -> new HashSet<>()).add(tag);
                });
            });
            itemId2tags.forEach((id, tags) -> VANILLA_ITEM_TAGS.put(id, tags.toArray(new ItemTag[0])));
        }

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

        @Override
        public ItemTypeBuilder<T, ItemComponent> identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        @Override
        public ItemTypeBuilder<T, ItemComponent> vanillaItem(VanillaItemId vanillaItemId) {
            this.identifier = vanillaItemId.getIdentifier();
            this.runtimeId = vanillaItemId.getRuntimeId();

            // Attributes for vanilla item
            var attributes = VanillaItemAttributeRegistry.getRegistry().get(vanillaItemId);
            if (attributes == null)
                throw new ItemTypeBuildException("Cannot find vanilla item attribute component for " + vanillaItemId + " from vanilla item attribute registry!");

            var attributeComponent = new ItemAttributeComponentImpl(attributes);
            componentProviders.put(ItemAttributeComponentImpl.IDENTIFIER, ComponentProvider.ofSingleton(attributeComponent));

            // Tags for vanilla item
            var tags = VANILLA_ITEM_TAGS.get(vanillaItemId);
            if (tags != null) setItemTags(tags);
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
            var p = new ComponentProvider.SimpleComponentProvider<>(provider, componentClass);
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        @Override
        public ItemTypeBuilder<T, ItemComponent> addComponent(ComponentProvider<ItemComponent> p) {
            this.componentProviders.put(p.findComponentIdentifier(), p);
            return this;
        }

        @Override
        public ItemTypeBuilder<T, ItemComponent> setItemTags(ItemTag... itemTags) {
            this.itemTags = Set.of(itemTags);
            return this;
        }

        @Override
        public ItemType<T> build() {
            if (!componentProviders.containsKey(ItemBaseComponentImpl.IDENTIFIER))
                addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class);

            if (!componentProviders.containsKey(ItemAttributeComponentImpl.IDENTIFIER))
                addComponent($ -> ItemAttributeComponentImpl.ofDefault(), ItemAttributeComponentImpl.class);

            if (identifier == null) throw new ItemTypeBuildException("identifier cannot be null!");

            if (runtimeId == Integer.MAX_VALUE) runtimeId = CUSTOM_ITEM_RUNTIME_ID_COUNTER++;

            var type = new AllayItemType<>(interfaceClass, new ArrayList<>(componentProviders.values()), identifier, runtimeId, itemTags);
            ItemTypeRegistry.getRegistry().register(identifier, type);
            return type;
        }
    }
}
