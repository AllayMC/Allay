import {a} from './another.js';

export function onEnable(order) {
    console.info("Example-Plugin-Js has been enabled!");
    console.info(plugin.getPluginI18n().tr("ejp:example_js_plugin_i18n_test"));
    a();
}

export function onDisable() {
    console.info("Example-Plugin-Js has been disabled!");
}