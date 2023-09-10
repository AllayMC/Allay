package cn.allay.api.client;

import cn.allay.api.client.data.AdventureSettings;
import cn.allay.api.client.data.LoginData;
import cn.allay.api.client.movement.ClientMovementValidator;
import cn.allay.api.client.skin.Skin;
import cn.allay.api.entity.interfaces.EntityPlayer;
import cn.allay.api.server.Server;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.protocol.bedrock.data.GameType;

import javax.annotation.Nullable;
import javax.crypto.SecretKey;

/**
 * Allay Project 2023/8/4
 *
 * @author daoge_cmd
 */
@Getter
public abstract class BaseClient implements Client {
    protected LoginData loginData;
    @Setter
    protected int chunkLoadingRadius;
    protected Server server;
    protected AdventureSettings adventureSettings;
    protected String name;
    @Setter
    protected String displayName;
    protected EntityPlayer playerEntity;
    @Setter
    //TODO: read operator info from config
    protected boolean op = true;
    @Setter
    protected GameType gameType = GameType.CREATIVE;
    @Setter
    protected Skin skin;
    protected boolean networkEncryptionEnabled = false;
    @Nullable
    protected SecretKey encryptionSecretKey;
    @Getter
    @Setter
    protected ClientMovementValidator movementValidator = ClientMovementValidator.EMPTY_VALIDATOR;
    @Getter
    @Setter
    protected int chunkTrySendCountPerTick = 16;
}
