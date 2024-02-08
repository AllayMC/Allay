package org.allaymc.server.plugin.js;

import lombok.SneakyThrows;
import org.allaymc.api.plugin.Plugin;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.io.IOAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Allay Project 2024/2/9
 *
 * @author daoge_cmd
 */
public class JsPlugin extends Plugin {

    protected Context jsContext;
    protected Logger log;

    @SneakyThrows
    public JsPlugin(File entranceFile) {
        log = LoggerFactory.getLogger(pluginContainer.descriptor().getName());
        jsContext = Context.newBuilder("js")
                .allowAllAccess(true)
                .allowIO(IOAccess.ALL)
                .allowHostAccess(HostAccess.ALL)
                .allowHostClassLoading(true)
                .allowHostClassLookup(className -> true)
                .build();
        initGlobalMembers();
        jsContext.eval(
                Source.newBuilder("js", entranceFile)
                        .build()
        );
    }

    protected void initGlobalMembers() {
        var binding = jsContext.getBindings("js");
        binding.putMember("plugin", this);
        binding.putMember("console", log);
    }

    @Override
    public void onLoad() {
        tryCallJsFunction("onLoad");
    }

    @Override
    public void onEnable() {
        tryCallJsFunction("onEnable");
    }

    @Override
    public void onDisable() {
        tryCallJsFunction("onDisable");
    }

    protected void tryCallJsFunction(String onLoad) {
        var binding = jsContext.getBindings("js");
        var func = binding.getMember(onLoad);
        if (func.canExecute())
            func.executeVoid();
    }
}
