package cn.allay.api.player.skin;

import lombok.ToString;

/**
 * @author LucGamesYT | daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
@ToString
public record SkinAnimation(Image image, int type, float frames, int expression) {

}
