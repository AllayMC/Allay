package org.allaymc.api.command.selector;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.selector.args.SelectorArgument;
import org.allaymc.api.entity.Entity;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Allay Project 2024/2/25
 *
 * @author daoge_cmd
 */
public interface EntitySelectorAPI {

    ApiInstanceHolder<EntitySelectorAPI> API = ApiInstanceHolder.create();

    static EntitySelectorAPI getAPI() {
        return API.get();
    }

    Pattern ENTITY_SELECTOR = Pattern.compile("^@([aeprs]|initiator)(?:\\[(.*)])?$");

    String ARGUMENT_JOINER = "=";

    /**
     * 通过给定的命令发送者和目标选择器文本匹配实体
     * @param sender 命令发送者
     * @param token 目标选择器文本
     * @return 目标实体
     */
    List<Entity> matchEntities(CommandSender sender, String token) throws SelectorSyntaxException;

    /**
     * 检查给定文本是否是合法目标选择器
     * @param token 给定文本
     * @return 是否是合法目标选择器
     */
    boolean checkValid(String token);

    /**
     * 注册一个选择器参数
     * @param argument 选择器参数对象
     * @return 是否注册成功（若已存在相同key值的选择器参数则注册失败，返回false）
     */
    boolean registerArgument(SelectorArgument argument);
}
