package org.allaymc.api;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.BlockComponentImplFactory;
import org.allaymc.api.block.registry.VanillaBlockAttributeRegistry;
import org.allaymc.api.block.palette.BlockStateHashPalette;
import org.allaymc.api.block.registry.BlockTypeRegistry;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.blockentity.component.BlockEntityComponentImplFactory;
import org.allaymc.api.blockentity.registry.BlockEntityTypeRegistry;
import org.allaymc.api.blockentity.type.BlockEntityTypeBuilder;
import org.allaymc.api.command.CommandRegistry;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.component.interfaces.ComponentInjector;
import org.allaymc.api.data.VanillaItemMetaBlockStateBiMap;
import org.allaymc.api.entity.component.EntityComponentImplFactory;
import org.allaymc.api.entity.effect.EffectRegistry;
import org.allaymc.api.entity.registry.EntityTypeRegistry;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.exception.MissingImplementationException;
import org.allaymc.api.exception.MissingRequirementException;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.component.ItemComponentImplFactory;
import org.allaymc.api.item.registry.VanillaItemAttributeRegistry;
import org.allaymc.api.item.enchantment.EnchantmentRegistry;
import org.allaymc.api.item.recipe.RecipeRegistry;
import org.allaymc.api.item.registry.CreativeItemRegistry;
import org.allaymc.api.item.registry.ItemTypeRegistry;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.perm.tree.PermTree;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.biome.BiomeTypeRegistry;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * This class is used to manage the implementation of the API to the module<br>
 * The Allay-API defines a number of interfaces that need to be implemented in advance, <br>
 * and in Allay-Server, this will be implemented through such registrations<br>
 * <p>
 * Note that for each interface that needs to be implemented, there is only one instance of the implementation class in runtime
 * <p>
 * Allay Project 2023/3/11
 *
 * @author daoge_cmd
 */
@Slf4j
@Getter
public final class AllayAPI {

    public static final String API_VERSION = "1.0.0";

    private static final AllayAPI INSTANCE = new AllayAPI();
    private final Map<Class<?>, ApiBindingAction<?>> bindings = new LinkedHashMap<>();
    private final Map<Class<?>, Consumer<?>> consumers = new HashMap<>();
    private boolean i18nSet = false;
    private boolean implemented = false;

    private AllayAPI() {
        defaultAPIRequirements();
    }

    /**
     * @return the API instance
     */
    public static AllayAPI getInstance() {
        return INSTANCE;
    }

    /**
     * After you have finished registering your implementation, you need to call this method to complete the implementation injection
     *
     * @throws MissingImplementationException If there are interface which are not been implemented
     */
    @SuppressWarnings("unchecked")
    public void implement(String coreName) throws MissingImplementationException {
        if (!i18nSet) {
            throw new MissingImplementationException("Missing i18n implementation!");
        }
        for (Map.Entry<Class<?>, ApiBindingAction<?>> entry : bindings.entrySet()) {
            if (entry.getValue() == null) {
                throw new MissingImplementationException("Missing binding for " + entry.getKey().getName());
            }
            var apiInstance = entry.getValue().bindingAction.get();
            Consumer<Object> consumer = (Consumer<Object>) consumers.get(entry.getKey());
            if (consumer == null) {
                throw new MissingRequirementException("Missing requirement for " + entry.getKey().getName());
            }
            consumer.accept(apiInstance);
            if (entry.getValue().afterBound != null) {
                ((Consumer<Object>) entry.getValue().afterBound).accept(apiInstance);
            }
        }
        log.info(I18n.get().tr(TrKeys.A_API_IMPLEMENTED, coreName, API_VERSION));
        implemented = true;
    }

    public <T> void requireImpl(Class<T> api) {
        requireImpl(api, null);
    }

    /**
     * Add an interface to be implemented<br>
     * It needs to be implemented by the server
     *
     * @param api the interface
     */
    public <T> void requireImpl(Class<T> api, @Nullable Consumer<T> apiInstanceConsumer) {
        bindings.put(api, null);
        if (apiInstanceConsumer != null) {
            consumers.put(api, apiInstanceConsumer);
        }
    }

    public <T> void bind(Class<T> api, Supplier<T> supplier) {
        bind(api, supplier, null);
    }

    public <T> void bind(Class<T> api, Supplier<T> bindingAction, @Nullable Consumer<T> afterBound) {
        Objects.requireNonNull(api);
        Objects.requireNonNull(bindingAction);
        bindings.put(api, new ApiBindingAction<>(bindingAction, afterBound));
    }

    public void bindI18n(I18n i18nImpl) {
        Objects.requireNonNull(i18nImpl);
        I18n.I18N.set(i18nImpl);
        i18nSet = true;
        log.info(i18nImpl.tr(TrKeys.A_LANG_SET, i18nImpl.getLangCode().toString()));
    }

    /**
     * Each interface has only one instance of the corresponding implementation class, so if you call this method with the same parameters, you will return an identical object <br>
     * If the interface has not been implemented, it will throw an exception <br>
     *
     * @param api the interface
     * @return the implementation instance of the specific interface
     */
    public <T> T getAPIInstance(Class<T> api) {
        if (!implemented)
            throw new RuntimeException("AllayAPI::getAPIInstance cannot be called before it been implemented");
        return api.cast(bindings.get(api));
    }

    private void defaultAPIRequirements() {
        // Common
        requireImpl(Server.class, Server.INSTANCE::set);
        requireImpl(ComponentInjector.ComponentInjectorFactory.class, ComponentInjector.ComponentInjectorFactory.FACTORY::set);
        requireImpl(Scheduler.SchedulerFactory.class, Scheduler.SchedulerFactory.FACTORY::set);

        // Item
        requireImpl(ItemComponentImplFactory.class, ItemComponentImplFactory.FACTORY::set);
        requireImpl(EnchantmentRegistry.class, EnchantmentRegistry.REGISTRY::set);
        requireImpl(ItemTypeBuilder.ItemTypeBuilderFactory.class, ItemTypeBuilder.FACTORY::set);
        requireImpl(VanillaItemAttributeRegistry.class, VanillaItemAttributeRegistry.REGISTRY::set);
        requireImpl(ItemTypeRegistry.class, ItemTypeRegistry.REGISTRY::set);

        // BlockEntity
        requireImpl(BlockEntityComponentImplFactory.class, BlockEntityComponentImplFactory.FACTORY::set);
        requireImpl(BlockEntityTypeBuilder.BlockEntityTypeBuilderFactory.class, BlockEntityTypeBuilder.FACTORY::set);
        requireImpl(BlockEntityTypeRegistry.class, BlockEntityTypeRegistry.REGISTRY::set);

        // Block
        requireImpl(BlockComponentImplFactory.class, BlockComponentImplFactory.FACTORY::set);
        requireImpl(BlockTypeBuilder.BlockTypeBuilderFactory.class, BlockTypeBuilder.FACTORY::set);
        requireImpl(VanillaBlockAttributeRegistry.class, VanillaBlockAttributeRegistry.REGISTRY::set);
        requireImpl(BlockStateHashPalette.class, BlockStateHashPalette.REGISTRY::set);
        requireImpl(BlockTypeRegistry.class, BlockTypeRegistry.REGISTRY::set);

        // Entity
        requireImpl(EntityComponentImplFactory.class, EntityComponentImplFactory.FACTORY::set);
        requireImpl(EffectRegistry.class, EffectRegistry.REGISTRY::set);
        requireImpl(EntityTypeBuilder.EntityTypeBuilderFactory.class, EntityTypeBuilder.FACTORY::set);
        requireImpl(EntityTypeRegistry.class, EntityTypeRegistry.REGISTRY::set);

        // Biome
        requireImpl(BiomeTypeRegistry.class, BiomeTypeRegistry.REGISTRY::set);

        // Misc
        requireImpl(VanillaItemMetaBlockStateBiMap.class, VanillaItemMetaBlockStateBiMap.REGISTRY::set);

        // Creative Item Registry
        requireImpl(CreativeItemRegistry.class, CreativeItemRegistry.REGISTRY::set);

        // Recipe
        requireImpl(RecipeRegistry.class, RecipeRegistry.REGISTRY::set);

        // Perm
        requireImpl(PermTree.PermTreeFactory.class, PermTree.FACTORY::set);

        // Command
//        requireImpl(CommandManager.class, CommandManager.INSTANCE::set);
        requireImpl(CommandTree.CommandTreeFactory.class, CommandTree.FACTORY::set);
    }

    private record ApiBindingAction<T>(Supplier<T> bindingAction, @Nullable Consumer<T> afterBound) {}
}