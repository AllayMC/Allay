package cn.allay.api.entity.init;

import cn.allay.api.client.Client;
import cn.allay.api.entity.interfaces.EntityPlayer;
import cn.allay.api.math.location.Location3f;
import lombok.Getter;

/**
 * Allay Project 2023/9/14
 *
 * @author Cool_Loong
 */
class SimpleEntityPlayerInitInfo extends SimpleEntityInitInfo<EntityPlayer> implements EntityPlayerInitInfo {
    @Getter
    private final Client client;

    public SimpleEntityPlayerInitInfo(Client client, Location3f location) {
        super(location);
        this.client = client;
    }
}
