package org.allaymc.api.ddui;

import org.allaymc.api.ddui.element.LayoutElement;
import org.allaymc.api.ddui.internal.DataDrivenProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.jetbrains.annotations.ApiStatus;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Base type for all DDUI screens backed by a Bedrock data store payload.
 *
 * @author Miroshka
 */
public abstract class DataDrivenScreen extends ObjectProperty<Object> {

    private final Set<DataDrivenScreenSession> sessions = new LinkedHashSet<>();
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

        DataDrivenProperty<?> current = this;
        var index = 0;
        while (index < path.length()) {
            var currentChar = path.charAt(index);
            if (currentChar == '.') {
                index++;
                continue;
            }

            String token;
            if (currentChar == '[') {
                var end = path.indexOf(']', index + 1);
                if (end < 0) {
                    return null;
                }

                token = path.substring(index + 1, end);
                index = end + 1;
            } else {
                var end = index;
                while (end < path.length() && path.charAt(end) != '.' && path.charAt(end) != '[') {
                    end++;
                }

                token = path.substring(index, end);
                index = end;
            }

            if (!(current instanceof ObjectProperty<?> objectProperty)) {
                return null;
            }

            current = objectProperty.getProperty(token);
            if (current == null) {
                return null;
            }
        }

        return current;
    }

    @ApiStatus.Internal
    public void attachSession(DataDrivenScreenSession session) {
        sessions.add(session);
    }

    @ApiStatus.Internal
    public void detachSession(DataDrivenScreenSession session) {
        sessions.remove(session);
    }

    @ApiStatus.Internal
    public void propertyChanged(DataDrivenProperty<?> property) {
        sessions.forEach(session -> session.markDirty(property));
    }

    @Override
    public DataDrivenScreen getRootScreen() {
        return this;
    }
}
