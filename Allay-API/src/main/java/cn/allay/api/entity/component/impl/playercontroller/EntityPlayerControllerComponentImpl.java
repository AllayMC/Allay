package cn.allay.api.entity.component.impl.playercontroller;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.client.Client;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
public class EntityPlayerControllerComponentImpl implements EntityPlayerControllerComponent, EntityComponentImpl {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_player_controller_component");

    protected Client client;

    @Override
    @Nullable
    @Impl
    public Client getClient() {
        //TODO: 使用空对象优化这玩意
        return client;
    }

    @Override
    @Impl
    public void setClient(@Nullable Client client) {
        this.client = client;
    }
}
