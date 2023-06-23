package cn.allay.api.player.skin;

import lombok.ToString;

import java.util.List;

/**
 * @author LucGamesYT | daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
@ToString
public record PersonaPieceTint(String pieceType, List<String> colors) {

}
