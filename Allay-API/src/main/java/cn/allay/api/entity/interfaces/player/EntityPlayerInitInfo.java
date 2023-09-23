package cn.allay.api.entity.interfaces.player;

import cn.allay.api.client.Client;
import cn.allay.api.entity.init.EntityInitInfo;

/**
 * Allay Project 2023/9/14
 *
 * @author Cool_Loong
 */
public interface EntityPlayerInitInfo extends EntityInitInfo<EntityPlayer> {
    Client getClient();
}
