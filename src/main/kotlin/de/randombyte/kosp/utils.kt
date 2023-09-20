package de.randombyte.kosp

import org.spongepowered.api.Sponge
import org.spongepowered.api.scheduler.Task
import org.spongepowered.api.util.Ticks
import org.spongepowered.plugin.PluginContainer

/**
 * Use this instead of TextTemplate.of(...) to workaround [this issue](https://github.com/SpongePowered/SpongeCommon/issues/1152).
 */
fun fixedTextTemplateOf(vararg elements: Any): Nothing = throw NotImplementedError("")

@Deprecated(
    "plugin should be instance of PluginContainer",
    ReplaceWith("delayTicks(ticks:Int,plugin:PluginContainer,action:()->Unit)")
)
fun delayTicks(ticks: Int, plugin: Any, action: () -> Unit) {
    if (plugin is PluginContainer)
        delayTicks(ticks, plugin, action)
    else throw TypeCastException("Plugin was not type: org.spongepowered.plugin.PluginContainer")
}

fun delayTicks(ticks: Int, plugin: PluginContainer, action: () -> Unit) {
    Sponge.server().scheduler().submit(
        Task.builder()
            .delay(Ticks.of(ticks.toLong()))
            .execute(action)
            .plugin(plugin)
            .build()
    )
}