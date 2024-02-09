package org.allaymc.spark;

import me.lucko.spark.common.platform.PlatformInfo;

/**
 * Allay Project 08/02/2024
 *
 * @author IWareQ
 */
public class AllayPlatformInfo implements PlatformInfo {

    @Override
    public Type getType() {
        return Type.SERVER;
    }

    @Override
    public String getName() {
        return "Allay";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String getMinecraftVersion() {
        return "1.20.60";
    }
}
