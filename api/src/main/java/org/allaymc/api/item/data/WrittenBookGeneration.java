package org.allaymc.api.item.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrKeys;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public enum WrittenBookGeneration {
    ORIGINAL_GENERATION(TrKeys.MC_BOOK_GENERATION_0),
    COPY_GENERATION(TrKeys.MC_BOOK_GENERATION_1),
    COPY_OF_COPY_GENERATION(TrKeys.MC_BOOK_GENERATION_2),
    TATTERED(TrKeys.MC_BOOK_GENERATION_3);

    private static final WrittenBookGeneration[] VALUES = values();

    private final @MayContainTrKey String name;

    /**
     * Gets WrittenBookGeneration enum entry by generation value
     *
     * @param generation the generation value.
     * @return the WrittenBookGeneration enum entry.
     */
    public static WrittenBookGeneration fromGeneration(int generation) {
        return VALUES[generation];
    }
}
