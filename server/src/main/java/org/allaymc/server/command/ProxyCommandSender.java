package org.allaymc.server.command;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.math.location.Location3dc;

/**
 * {@code ProxyCommandSender} delegates command identity, permissions, and
 * messaging to another sender while optionally replacing its execution
 * location. {@code cmdExecuteLocation} takes precedence when present; a
 * {@code null} override keeps location queries tied to the original sender's
 * current location.
 *
 * @author daoge_cmd
 */
public class ProxyCommandSender implements CommandSender {

    /**
     * {@code origin} supplies delegated sender behavior and the fallback
     * execution location. The constructor retains this sender directly, and
     * {@code getOrigin()} exposes the same object.
     */
    @Delegate
    @Getter
    protected CommandSender origin;
    /**
     * {@code cmdExecuteLocation} overrides the delegated execution location
     * when non-null. The generated setter retains the supplied location
     * reference; setting {@code null} restores delegation to {@code origin}.
     */
    @Setter
    protected Location3dc cmdExecuteLocation;

    /**
     * Creates a sender proxy retaining the supplied sender for delegated
     * operations.
     */
    public ProxyCommandSender(CommandSender origin) {
        this.origin = origin;
    }

    /**
     * Returns the configured location override, or queries the original sender
     * when no override exists. The returned location is shared with its
     * provider, and this method does not copy either location.
     */
    @Override
    public Location3dc getCommandExecuteLocation() {
        if (cmdExecuteLocation != null) {
            return cmdExecuteLocation;
        }
        // A missing override keeps this proxy attached to the original sender's
        // current origin, so movement remains visible to later location queries.
        return origin.getCommandExecuteLocation();
    }
}
