package org.allaymc.server.spark;

import me.lucko.spark.common.platform.PlatformInfo;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.utils.GitProperties;

public final class AllayPlatformInfo implements PlatformInfo {

    @Override
    public Type getType() {
        return Type.SERVER;
    }

    @Override
    public String getName() {
        return "Allay";
    }

    @Override
    public String getBrand() {
        return "Allay";
    }

    @Override
    public String getVersion() {
        return GitProperties.getBuildVersion();
    }

    @Override
    public String getMinecraftVersion() {
        var version = ProtocolInfo.getLatestMinecraftVersion();
        return version.major() + "." + version.minor() + "." + version.patch();
    }
}