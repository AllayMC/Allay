package org.allaymc.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.StandardException;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.selector.EntitySelectorAPI;
import org.allaymc.api.command.tree.CommandNodeFactory;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.container.FakeContainerFactory;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.NBTIO;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * {@code AllayAPI} connects required API contracts with implementation suppliers
 * and publishes their values into instance holders during bootstrap.
 * Requirements establish insertion order,
 * bindings provide factories, and registered consumers receive each created
 * value. Internationalization is bound separately before the main pass so
 * initialization can report translated messages through an available service.
 *
 * <p>
 * Publication is incremental rather than transactional: a missing binding,
 * missing consumer, or callback failure leaves earlier publications intact.
 * {@code implemented}, {@code coreName}, and {@code isDevBuild} change only after the full pass
 * returns. Bootstrap callers must serialize registration and implementation;
 * the maps and flags do not coordinate concurrent initialization. Per-API
 * holders expose the published instances, while the binding map retains the
 * supplier records used to construct them rather than those instances.
 * Generated map getters expose the mutable registries directly and therefore
 * share the same serialized bootstrap access requirement.
 *
 * @author daoge_cmd
 */
@Slf4j
@Getter
public final class AllayAPI {

    /**
     * {@code INSTANCE} holds the process-wide requirement and binding registry used by bootstrap
     * code to connect public API holders with server implementations.
     */
    private static final AllayAPI INSTANCE = new AllayAPI();

    // bindings preserves first insertion order for implementation, while consumers
    // stores publication targets by the same class key. Requiring an existing API
    // resets its binding but preserves an earlier consumer when none is supplied.
    /** Ordered supplier registrations. */
    private final Map<Class<?>, ApiBindingAction<?>> bindings;
    /** Per-API publication consumers. */
    private final Map<Class<?>, Consumer<?>> consumers;
    /**
     * {@code i18nSet} records whether {@code bindI18n} reached publication before
     * logging the language selected for bootstrap, even if that logging fails.
     */
    private boolean i18nSet;
    /**
     * {@code implemented} records whether an implementation pass completed; later
     * registrations retain this flag even when they replace previously valid bindings.
     */
    private boolean implemented;
    /**
     * {@code coreName} records the implementing core's label from the last
     * successful {@code implement} call. It remains {@code null} before the
     * first successful pass.
     */
    private String coreName;
    /**
     * {@code isDevBuild} records the development-build flag from the last
     * successful {@code implement} call. Its initial {@code false} value remains
     * until a successful pass supplies metadata.
     */
    private boolean isDevBuild;

    /**
     * Creates the binding registry and registers the built-in API publication targets.
     */
    private AllayAPI() {
        this.bindings = new LinkedHashMap<>();
        this.consumers = new HashMap<>();
        registerDefaultAPIRequirements();
    }

    /**
     * Returns the process-wide registry for API requirements and implementation bindings.
     */
    public static AllayAPI getInstance() {
        return INSTANCE;
    }

    /**
     * Publishes all registered bindings and records the implementing core's metadata.
     * Bootstrap callers must bind internationalization and every required supplier
     * before calling this method under serialized access. Each supplier runs before its
     * consumer is checked, then the consumer publishes the result before the
     * optional after-bound callback runs.
     *
     * <p>
     * Failures leave earlier publications
     * intact and do not update the completion metadata. Repeated calls are not
     * rejected: suppliers run again even when instance holders retain their
     * previously published non-null values.
     *
     * @throws MissingImplementationException when internationalization or a binding is absent
     * @throws MissingRequirementException when a binding lacks a publication consumer
     */
    @SuppressWarnings("unchecked")
    @ApiStatus.Internal
    public void implement(String coreName, boolean isDevBuild) {
        if (!i18nSet) {
            throw new MissingImplementationException("Missing i18n implementation!");
        }

        // Consumers may publish into holders used by later suppliers, so each
        // entry completes publication and its callback before the next entry runs.
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

    /**
     * Declares an API requirement without supplying a new publication consumer.
     * An existing binding is reset to missing, while any previously registered
     * consumer remains associated with the class for later implementation.
     */
    @ApiStatus.Internal
    public <T> void requireImpl(Class<T> api) {
        requireImpl(api, null);
    }

    /**
     * Declares a required API and optionally replaces its publication consumer.
     * Calling this resets the binding even for an existing class; a {@code null}
     * consumer preserves the earlier consumer and does not make implementation
     * possible when no consumer was ever registered.
     */
    @ApiStatus.Internal
    public <T> void requireImpl(Class<T> api, Consumer<T> apiInstanceConsumer) {
        bindings.put(api, null);
        if (apiInstanceConsumer != null) consumers.put(api, apiInstanceConsumer);
    }

    /**
     * Registers an implementation supplier without an after-publication callback.
     * The supplier is retained for implementation rather than invoked now;
     * a matching requirement consumer must exist before {@code implement} reaches it.
     */
    @ApiStatus.Internal
    public <T> void bind(Class<T> api, Supplier<T> supplier) {
        bind(api, supplier, null);
    }

    /**
     * Registers or replaces a supplier and optional after-publication callback.
     * The API class and supplier must be non-null. Registration does not invoke
     * either callback or create a requirement consumer; {@code implement} later invokes
     * the supplier, publication consumer, and optional callback in that order.
     */
    @ApiStatus.Internal
    public <T> void bind(Class<T> api, Supplier<T> bindingAction, Consumer<T> afterBound) {
        Objects.requireNonNull(api);
        Objects.requireNonNull(bindingAction);
        bindings.put(api, new ApiBindingAction<>(bindingAction, afterBound));
    }

    /**
     * Offers internationalization to its holder and marks the prerequisite present.
     * A non-null value is required. The holder may retain an earlier instance,
     * while the language log uses this supplied instance even on repeated calls.
     */
    @ApiStatus.Internal
    public void bindI18n(I18n i18nImpl) {
        Objects.requireNonNull(i18nImpl);
        I18n.I18N.set(i18nImpl);
        i18nSet = true;
        log.info(i18nImpl.tr(TrKeys.ALLAY_LANG_SET, i18nImpl.getDefaultLangCode().toString()));
    }

    /**
     * Attempts a lookup through the binding registry after implementation completes.
     * This path currently casts the stored supplier record rather than the
     * produced implementation, so ordinary bound API types cause {@code ClassCastException}
     * and an absent entry returns {@code null}. Consumers needing published instances
     * use the API-specific holders instead.
     *
     * @throws APINotImplementedException before a successful implementation pass
     * @throws ClassCastException when the stored binding record does not implement
     *                            the requested API
     */
    @ApiStatus.Internal
    public <T> T getAPIInstance(Class<T> api) {
        if (!implemented) {
            throw new APINotImplementedException("AllayAPI::getAPIInstance cannot be called before it been implemented");
        }
        return api.cast(bindings.get(api));
    }

    /**
     * Registers built-in API requirements in their bootstrap publication order.
     * Each requirement names the corresponding static holder's setter, so
     * implementation publishes server services and factories before registry
     * construction begins in the server bootstrap.
     */
    private void registerDefaultAPIRequirements() {
        requireImpl(Server.class, Server.INSTANCE::set);
        requireImpl(Scheduler.Factory.class, Scheduler.FACTORY::set);
        requireImpl(EventBus.Factory.class, EventBus.FACTORY::set);

        requireImpl(EntitySelectorAPI.class, EntitySelectorAPI.API::set);
        requireImpl(CommandTree.Factory.class, CommandTree.FACTORY::set);
        requireImpl(CommandNodeFactory.class, CommandNodeFactory.FACTORY::set);

        requireImpl(NBTIO.class, NBTIO.API::set);
        requireImpl(FakeContainerFactory.class, FakeContainerFactory.FACTORY::set);
    }

    /**
     * {@code APIInstanceHolder} exposes an API implementation through a volatile
     * reference read by consumers.
     * Sequential assignments retain the first value other than {@code null};
     * callers must
     * serialize initialization because the check and assignment are not atomic.
     *
     * @author daoge_cmd
     */
    @NoArgsConstructor
    public static final class APIInstanceHolder<T> {

        /**
         * {@code instance} holds the published implementation, initially {@code null}.
         * Volatile visibility does not make competing first assignments in {@code set}
         * an atomic selection.
         */
        private volatile T instance;

        /**
         * Creates an empty holder for a future API implementation reference.
         */
        public static <T> APIInstanceHolder<T> create() {
            return new APIInstanceHolder<>();
        }

        /**
         * Stores the supplied reference only when the current reference is
         * {@code null}. {@code null} does not seal the holder against later
         * assignments. After a non-null assignment under serialized initialization,
         * later calls retain that reference without invoking replacement logic.
         *
         * <p>
         * Callers must serialize competing initializers because two callers can
         * both observe {@code null} and publish different values. Volatile writes
         * make the reference visible to readers, but provide neither
         * compare-and-set selection nor recovery of an overwritten competing value.
         */
        public void set(T instance) {
            if (this.instance == null) this.instance = instance;
        }

        /**
         * Returns the currently published implementation reference, which may be {@code null}.
         * Reading does not wait for bootstrap or create a value; callers must
         * arrange initialization before using required API services.
         */
        public T get() {
            return this.instance;
        }
    }

    /**
     * {@code ApiBindingAction} pairs a deferred implementation supplier with its
     * optional callback following publication.
     *
     * @param bindingAction deferred implementation supplier
     * @param afterBound optional callback invoked after publication; {@code null}
     *                   skips the callback without changing consumer publication
     */
    private record ApiBindingAction<T>(Supplier<T> bindingAction, Consumer<T> afterBound) {
    }

    /**
     * {@code APINotImplementedException} reports registry lookup before an
     * implementation pass completes.
     */
    @StandardException
    private static class APINotImplementedException extends RuntimeException {
    }

    /**
     * {@code MissingImplementationException} reports a missing internationalization
     * prerequisite or required implementation supplier.
     */
    @StandardException
    private static class MissingImplementationException extends RuntimeException {
    }

    /**
     * {@code MissingRequirementException} reports a binding without a matching
     * implementation publication consumer.
     */
    @StandardException
    private static class MissingRequirementException extends RuntimeException {
    }
}
