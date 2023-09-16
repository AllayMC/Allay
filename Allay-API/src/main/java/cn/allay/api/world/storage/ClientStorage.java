package cn.allay.api.world.storage;

import cn.allay.api.client.Client;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public interface ClientStorage {

    void readClientData(Client client);

    void writeClientData(Client client);
}
