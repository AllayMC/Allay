package org.allaymc.api.registry;

import lombok.Getter;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * {@code AbstractRegistry} holds loaded content and controls mutations made
 * through the registry's methods. {@code register} and {@code setContent} check
 * {@code frozen} while holding the registry monitor. {@code freeze} takes that same
 * monitor, so another thread cannot begin a registry mutation after freezing
 * has acquired it and set the flag.
 *
 * <p>
 * {@code content} is retained and exposed by reference. This base class does not copy
 * or make the content immutable when frozen; subclasses may replace it with
 * a snapshot. Direct mutation through an exposed reference bypasses registry
 * checks, and a registration callback can reenter synchronized methods on the
 * same thread. Unsynchronized content reads require appropriate publication
 * or caller coordination when content is replaced concurrently.
 *
 * <p>
 * A loader can transform an input value directly:
 *
 * <pre>
 * {@code
 *     public static final SimpleRegistry<Integer> STRING_TO_INT = SimpleRegistry.create("5", Integer::parseInt);
 * }
 * </pre>
 *
 * @param <CONTENT> the value being held by the registry
 *
 * @author GeyserMC | daoge_cmd
 */
@Getter
public abstract class AbstractRegistry<CONTENT> implements Registry<CONTENT> {
    /**
     * {@code content} holds the loader result or last replacement.
     * {@code getContent()} returns this same reference, whose mutability
     * depends on the concrete registry.
     */
    protected CONTENT content;
    /**
     * {@code frozen} makes registry mutation checks reject changes once set.
     * Its volatile read exposes freeze state without acquiring the mutation
     * monitor.
     */
    protected volatile boolean frozen;

    /**
     * Loads initial content immediately using the supplied input and loader.
     * The result is stored without copying or {@code null} validation, so the
     * loader defines the initial content's ownership and validity.
     */
    protected <INPUT> AbstractRegistry(INPUT input, RegistryLoader<INPUT, CONTENT> registryLoader) {
        this.content = registryLoader.load(input);
    }

    /**
     * Passes current content to the consumer after checking that mutation is
     * allowed. The consumer runs under the registry monitor; exceptions retain
     * any mutations already made, and retained content references bypass later
     * freeze checks.
     *
     * @throws IllegalStateException if the registry is already frozen
     */
    @Override
    public synchronized void register(Consumer<CONTENT> consumer) {
        ensureMutable();
        consumer.accept(content);
    }

    /**
     * Replaces the stored reference with the supplied nonnull content. Existing
     * references to the previous value remain usable; this method neither
     * clears nor closes the previous content.
     *
     * @throws IllegalStateException if the registry is frozen
     * @throws NullPointerException if {@code content} is {@code null}
     */
    @Override
    public synchronized void setContent(CONTENT content) {
        ensureMutable();
        this.content = Objects.requireNonNull(content, "content");
    }

    /**
     * Marks the registry frozen while holding its mutation monitor. Repeated
     * calls leave it frozen, but the underlying content reference remains
     * unchanged and can still expose mutable objects.
     */
    @Override
    public synchronized void freeze() {
        frozen = true;
    }

    /**
     * Returns whether the registry's mutation checks now reject changes.
     */
    @Override
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * Rejects a mutation when this registry has been frozen. Callers must hold
     * the registry monitor across this check and their mutation when
     * coordinating with concurrent {@code freeze} calls.
     *
     * @throws IllegalStateException if this registry is frozen
     */
    protected final void ensureMutable() {
        if (frozen) {
            throw new IllegalStateException("Registry is frozen");
        }
    }
}
