package cn.allay.api.entity.init;

import cn.allay.api.client.Client;
import cn.allay.api.entity.interfaces.EntityPlayer;
import cn.allay.api.math.location.Location3f;

/**
 * Allay Project 2023/9/14
 *
 * @author Cool_Loong
 */
public interface EntityPlayerInitInfo extends EntityInitInfo<EntityPlayer> {
    Client getClient();
}
