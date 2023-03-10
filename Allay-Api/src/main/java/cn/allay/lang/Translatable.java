package cn.allay.lang;

import org.jetbrains.annotations.NotNull;

public interface Translatable {
    /**
     * Gets the translation key.
     */
    @NotNull String translationKey();
}
