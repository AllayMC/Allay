package org.allaymc.api;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.command.selector.EntitySelectorAPI;
import org.allaymc.api.command.tree.CommandNodeFactory;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * AllayAPI is used to manage api classes and the implementations of api instances.
 *
 * @author daoge_cmd
 */
@Slf4j
@Getter
public final class AllayAPI {

    private static final AllayAPI INSTANCE = new AllayAPI();

    private final Map<Class<?>, ApiBindingAction<?>> bindings = new LinkedHashMap<>();
    private final Map<Class<?>, Consumer<?>> consumers = new HashMap<>();
    private boolean i18nSet = false;
    private boolean implemented = false;
    /**
     * The name of the core which implements the AllayAPI.
     */
    private String coreName;
    /**
     * Whether the build of the implementation is a development build.
     */
    private boolean isDevBuild;

    private AllayAPI() {
        registerDefaultAPIRequirements();
    }

    /**
     * Get the instance of the AllayAPI.
     *
     * @return the API instance
     */
    public static AllayAPI getInstance() {
        return INSTANCE;
    }

    /**
     * Mark api as implemented with the specific core name.
     * <p>
     * After finish binding api instances, this method should be called to complete api injection process.
     *
     * @throws MissingImplementationException If there are interface which are not been implemented
     */
    @SuppressWarnings("unchecked")
    @ApiStatus.Internal
    public void implement(String coreName, boolean isDevBuild) throws MissingImplementationException {
        if (!i18nSet) {
            throw new MissingImplementationException("Missing i18n implementation!");
        }

        for (var entry : bindings.entrySet()) {
            if (entry.getValue() == null) {
                throw new MissingImplementationException("Missing binding for " + entry.getKey().getName());
            }

            var apiInstance = entry.getValue().bindingAction.get();
            var consumer = (Consumer<Object>) consumers.get(entry.getKey());
            if (consumer == null) {
                throw new MissingRequirementException("Missing requirement for " + entry.getKey().getName());
            }

            consumer.accept(apiInstance);
            if (entry.getValue().afterBound != null) {
                ((Consumer<Object>) entry.getValue().afterBound).accept(apiInstance);
            }
        }

        this.coreName = coreName;
        this.isDevBuild = isDevBuild;
        this.implemented = true;
    }

    @ApiStatus.Internal
    public <T> void requireImpl(Class<T> api) {
        requireImpl(api, null);
    }

    /**
     * Add an api class which needs to be implemented.
     *
     * @param api the api
     */
    @ApiStatus.Internal
    public <T> void requireImpl(Class<T> api, Consumer<T> apiInstanceConsumer) {
        bindings.put(api, null);
        if (apiInstanceConsumer != null) consumers.put(api, apiInstanceConsumer);
    }

    @ApiStatus.Internal
    public <T> void bind(Class<T> api, Supplier<T> supplier) {
        bind(api, supplier, null);
    }

    /**
     * Bind an api with the specific api instance.
     *
     * @param api           the api
     * @param bindingAction the supplier which provides the api instance
     * @param afterBound    the consumer which will be called after the api instance has been bound
     * @param <T>           the type of the api class
     */
    @ApiStatus.Internal
    public <T> void bind(Class<T> api, Supplier<T> bindingAction, Consumer<T> afterBound) {
        Objects.requireNonNull(api);
        Objects.requireNonNull(bindingAction);
        bindings.put(api, new ApiBindingAction<>(bindingAction, afterBound));
    }

    @ApiStatus.Internal
    public void bindI18n(I18n i18nImpl) {
        Objects.requireNonNull(i18nImpl);
        I18n.I18N.set(i18nImpl);
        i18nSet = true;
        log.info(i18nImpl.tr(TrKeys.ALLAY_LANG_SET, i18nImpl.getDefaultLangCode().toString()));
    }

    /**
     * Get the implementation instance of the specific api class.
     * <p>
     * Each api class has only one implementation instance, so calling this method with the same parameters will return an identical object
     *
     * @param api the interface
     *
     * @return the implementation instance of the specific interface
     *
     * @throws APINotImplementedException if the interface has not been implemented
     */
    @ApiStatus.Internal
    public <T> T getAPIInstance(Class<T> api) {
        if (!implemented) {
            throw new APINotImplementedException("AllayAPI::getAPIInstance cannot be called before it been implemented");
        }
        return api.cast(bindings.get(api));
    }

    private void registerDefaultAPIRequirements() {
        // Common
        requireImpl(Server.class, Server.INSTANCE::set);
        requireImpl(Scheduler.Factory.class, Scheduler.FACTORY::set);
        requireImpl(EventBus.Factory.class, EventBus.FACTORY::set);

        // Command
        requireImpl(EntitySelectorAPI.class, EntitySelectorAPI.API::set);
        requireImpl(CommandTree.Factory.class, CommandTree.FACTORY::set);
        requireImpl(CommandNodeFactory.class, CommandNodeFactory.FACTORY::set);

        // Misc
        requireImpl(BossBar.Factory.class, BossBar.FACTORY::set);
    }

    private record ApiBindingAction<T>(Supplier<T> bindingAction, Consumer<T> afterBound) {
    }
}
