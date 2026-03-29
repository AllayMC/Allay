package org.allaymc.api.ddui;

import org.allaymc.api.ddui.element.LayoutElement;
import org.allaymc.api.ddui.internal.DataDrivenProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.jetbrains.annotations.ApiStatus;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Base type for all DDUI screens backed by a Bedrock data store payload.
 *
 * @author Miroshka
 */
public abstract class DataDrivenScreen extends ObjectProperty<Object> {

    private final Set<Consumer<DataDrivenProperty<?>>> sessions = new LinkedHashSet<>();
    private final Map<String, DataDrivenProperty<?>> resolvedPathCache = new ConcurrentHashMap<>();
    protected final LayoutElement layout;

    protected DataDrivenScreen() {
        super("");
        this.layout = new LayoutElement(this);
        setProperty(layout);
    }

    /**
     * Returns the Bedrock DDUI screen identifier.
     *
     * @return screen identifier
     */
    public abstract String getIdentifier();

    /**
     * Returns the top-level property name used in the backing data store.
     *
     * @return data store property name
     */
    public abstract String getPropertyName();

    /**
     * Sends this DDUI screen to the provided viewer.
     *
     * @param viewer target viewer
     */
    public void sendTo(DataDrivenScreenViewer viewer) {
        viewer.viewDataDrivenScreen(this);
    }

    /**
     * Returns the root layout element of this screen.
     *
     * @return layout element
     */
    public LayoutElement getLayout() {
        return layout;
    }

    /**
     * Resolves a DDUI property by its wire path.
     *
     * @param path property path
     *
     * @return resolved property or {@code null} if the path is invalid
     */
    public DataDrivenProperty<?> resolvePath(String path) {
        if (path == null || path.isEmpty()) {
            return this;
        }

        var cached = resolvedPathCache.get(path);
        if (cached != null) {
            return cached;
        }

        var resolved = resolveUncachedPath(path);
        if (resolved != null) {
            resolvedPathCache.put(path, resolved);
        }
        return resolved;
    }

    @ApiStatus.Internal
    public void attachSession(Consumer<DataDrivenProperty<?>> session) {
        sessions.add(session);
    }

    @ApiStatus.Internal
    public void detachSession(Consumer<DataDrivenProperty<?>> session) {
        sessions.remove(session);
    }

    @ApiStatus.Internal
    public boolean hasSessions() {
        return !sessions.isEmpty();
    }

    @ApiStatus.Internal
    public void propertyChanged(DataDrivenProperty<?> property) {
        sessions.forEach(session -> session.accept(property));
    }

    @ApiStatus.Internal
    public void structureChanged() {
        resolvedPathCache.clear();
    }

    public void dispose() {
        disposeBindings();
    }

    @Override
    public DataDrivenScreen getRootScreen() {
        return this;
    }

    private DataDrivenProperty<?> resolveUncachedPath(String path) {
        DataDrivenProperty<?> current = this;
        var cursor = 0;
        while (cursor < path.length()) {
            var segment = readPathSegment(path, cursor);
            if (segment == null) {
                return null;
            }

            current = descend(current, segment.token());
            if (current == null) {
                return null;
            }

            cursor = segment.nextIndex();
            if (cursor >= path.length()) {
                return current;
            }

            if (path.charAt(cursor) != '.') {
                continue;
            }

            cursor++;
            if (cursor >= path.length() || path.charAt(cursor) == '[' || path.charAt(cursor) == '.') {
                return null;
            }
        }

        return current;
    }

    private DataDrivenProperty<?> descend(DataDrivenProperty<?> current, CharSequence token) {
        if (token.length() == 0) {
            return current;
        }
        if (!(current instanceof ObjectProperty<?> objectProperty)) {
            return null;
        }
        return objectProperty.getProperty(token.toString());
    }

    private PathSegment readPathSegment(String path, int startIndex) {
        return switch (path.charAt(startIndex)) {
            case '[' -> readIndexedSegment(path, startIndex);
            case '.', ']' -> null;
            default -> readNamedSegment(path, startIndex);
        };
    }

    private PathSegment readNamedSegment(String path, int startIndex) {
        var cursor = startIndex;
        while (cursor < path.length()) {
            var currentChar = path.charAt(cursor);
            if (currentChar == '.' || currentChar == '[' || currentChar == ']') {
                break;
            }
            cursor++;
        }

        if (cursor == startIndex) {
            return null;
        }

        return new PathSegment(path.substring(startIndex, cursor), cursor);
    }

    private PathSegment readIndexedSegment(String path, int startIndex) {
        var cursor = startIndex + 1;
        if (cursor >= path.length()) {
            return null;
        }

        while (cursor < path.length() && Character.isDigit(path.charAt(cursor))) {
            cursor++;
        }

        if (cursor == startIndex + 1 || cursor >= path.length() || path.charAt(cursor) != ']') {
            return null;
        }

        return new PathSegment(path.substring(startIndex + 1, cursor), cursor + 1);
    }

    /**
     * Parsed DDUI path segment descriptor.
     *
     * @author Miroshka
     */
    private record PathSegment(String token, int nextIndex) {
    }
}
