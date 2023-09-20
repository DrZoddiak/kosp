package de.randombyte.kosp.config

import com.google.common.reflect.TypeToken
import de.randombyte.kosp.config.serializers.date.SimpleDateTypeSerializer
import de.randombyte.kosp.config.serializers.duration.SimpleDurationTypeSerializer
import de.randombyte.kosp.extensions.typeToken
import org.spongepowered.configurate.CommentedConfigurationNode
import org.spongepowered.configurate.ConfigurationOptions
import org.spongepowered.configurate.loader.ConfigurationLoader
import org.spongepowered.configurate.serialize.TypeSerializerCollection
import java.time.Duration
import java.util.*

/**
 * A class for one specific config file which handles generating configs if it isn't present and various
 * custom type serializers.
 */
class ConfigManager<T : Any>(
    val configLoader: ConfigurationLoader<CommentedConfigurationNode>,
    clazz: Class<T>,
    simpleDurationSerialization: Boolean = true,
    simpleDateSerialization: Boolean = true,
    additionalSerializers: TypeSerializerCollection.() -> Any = { },
) {

    private val typeToken: TypeToken<T> = clazz.kotlin.typeToken
    private val options: ConfigurationOptions = ConfigurationOptions.defaults()
        .serializers {
            if (simpleDurationSerialization)
                it.register(Duration::class.java, SimpleDurationTypeSerializer)
            if (simpleDateSerialization)
                it.register(Date::class.java, SimpleDateTypeSerializer)
            additionalSerializers.invoke(it.build())
        }
        .shouldCopyDefaults(true)

    /**
     * Returns the saved config. If none exists a new one is generated and already saved.
     */
    @Suppress("UNCHECKED_CAST")
    fun load(): T = configLoader.load(options).get(typeToken.type) as T? ?: run {
        save(typeToken.rawType.newInstance() as T)
        load()
    }

    @Deprecated("Use load() instead", ReplaceWith("load()"))
    fun get(): T = load()

    fun save(config: T) = configLoader.apply { save(load(options).set(typeToken.type, config)) }

    /**
     * get() already generates the config when none exists but this method also inserts missing nodes
     * and reformats the structure.
     */
    fun generate() = save(load())
}