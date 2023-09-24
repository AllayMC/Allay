package cn.allay.api.entity.init;

import cn.allay.api.client.Client;
import cn.allay.api.entity.interfaces.EntityPlayer;

/**
 * Allay Project 2023/9/14
 *
 * @author Cool_Loong
 */
public interface EntityPlayerInitInfo extends EntityInitInfo<EntityPlayer> {
    Client getClient();
}
