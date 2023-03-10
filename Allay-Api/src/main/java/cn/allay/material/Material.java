package cn.allay.material;

import cn.allay.lang.Translatable;

public interface Material extends Identified, Translatable {
    /**
     * Gets if this material is a type of air.
     *
     * @return If it's air
     */
    boolean isAir();
}
