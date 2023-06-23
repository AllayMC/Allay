package cn.allay.api.player.skin;

import lombok.ToString;

/**
 * @author LucGamesYT | daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
@ToString
public record PersonaPiece(String pieceId, String pieceType, String packId, String productId, boolean isDefault) {

}
