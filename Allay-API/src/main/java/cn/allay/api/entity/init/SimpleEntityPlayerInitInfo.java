package cn.allay.api.entity.init;

import cn.allay.api.client.Client;
import cn.allay.api.entity.interfaces.EntityPlayer;
import cn.allay.api.world.World;
import lombok.Getter;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/9/14
 *
 * @author Cool_Loong
 */
@Getter
public class SimpleEntityPlayerInitInfo extends SimpleEntityInitInfo<EntityPlayer> implements EntityPlayerInitInfo {
    protected final Client client;

    protected SimpleEntityPlayerInitInfo(World world, NbtMap nbt, Client client) {
        super(world, nbt);
        this.client = client;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends SimpleEntityInitInfo.Builder {
        protected Client client;

        public Builder client(Client client) {
            this.client = client;
            return this;
        }

        @Override
        public SimpleEntityPlayerInitInfo build() {
            return new SimpleEntityPlayerInitInfo(world, nbtBuilder.build(), client);
        }
    }
}
