package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.GoatHornInstrument;

import java.util.Objects;

public interface ItemGoatHornStack extends ItemStack {
    default GoatHornInstrument getInstrument() {
        return GoatHornInstrument.fromMeta(getMeta());
    }

    default void setInstrument(GoatHornInstrument instrument) {
        setMeta(Objects.requireNonNull(instrument, "instrument").getMeta());
    }
}
