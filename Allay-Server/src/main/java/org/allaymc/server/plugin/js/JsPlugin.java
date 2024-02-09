package org.allaymc.server.plugin.js;

import lombok.SneakyThrows;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.plugin.PluginContainer;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.io.IOAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;

/**
 * Allay Project 2024/2/9
 *
 * @author daoge_cmd
 */
public class JsPlugin extends Plugin {

    protected Context jsContext;
    protected Value jsExport;
    protected Logger log;

    @Override
    public void setPluginContainer(PluginContainer pluginContainer) {
        super.setPluginContainer(pluginContainer);
        log = LoggerFactory.getLogger(pluginContainer.descriptor().getName());
    }

    @SneakyThrows
    @Override
    public void onLoad() {
        jsContext = Context.newBuilder("js")
                .allowIO(IOAccess.ALL)
                .allowAllAccess(true)
                .allowHostAccess(HostAccess.ALL)
                .allowHostClassLoading(true)
                .allowHostClassLookup(className -> true)
                .option("js.esm-eval-returns-exports", "true")
                .build();
        initGlobalMembers();
        var entranceJsFileName = pluginContainer.descriptor().getEntrance();
        var path = pluginContainer.loader().getPluginPath().resolve(entranceJsFileName);
        jsExport = jsContext.eval(
                Source.newBuilder("js", path.toFile())
                        .name(entranceJsFileName)
                        .mimeType("application/javascript+module")
                        .build()
        );
        tryCallJsFunction("onLoad");
    }

    protected void initGlobalMembers() {
        var binding = jsContext.getBindings("js");
        binding.putMember("plugin", this);
        binding.putMember("console", log);
    }

    @Override
    public void onEnable() {
        tryCallJsFunction("onEnable");
    }

    @Override
    public void onDisable() {
        tryCallJsFunction("onDisable");
    }

    @Override
    public boolean isReloadable() {
        return true;
    }

    protected void tryCallJsFunction(String onLoad) {
        var func = jsExport.getMember(onLoad);
        if (func.canExecute())
            func.executeVoid();
    }
}
