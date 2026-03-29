package org.allaymc.api.ddui;

/**
 * Thread-local guard for suppressing recursive DDUI observable propagation.
 *
 * @author Miroshka
 */
public final class BindingScope {

    private static final ThreadLocal<Integer> SUPPRESSED = ThreadLocal.withInitial(() -> 0);

    private BindingScope() {
    }

    public static boolean isSuppressed() {
        return SUPPRESSED.get() > 0;
    }

    public static void suppressed(Runnable action) {
        var depth = SUPPRESSED.get();
        SUPPRESSED.set(depth + 1);
        try {
            action.run();
        } finally {
            if (depth == 0) {
                SUPPRESSED.remove();
            } else {
                SUPPRESSED.set(depth);
            }
        }
    }
}
