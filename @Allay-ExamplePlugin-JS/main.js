import {a} from './another.js';

export function onLoad() {
    console.info("Example-Plugin-Js has been loaded!");
}

export function onEnable() {
    console.info("Example-Plugin-Js has been enabled!");
    a();
}

export function onDisable() {
    console.info("Example-Plugin-Js has been disabled!");
}