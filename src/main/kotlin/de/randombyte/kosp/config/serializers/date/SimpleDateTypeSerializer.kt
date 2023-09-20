package de.randombyte.kosp.config.serializers.date

import org.spongepowered.configurate.ConfigurateException
import org.spongepowered.configurate.ConfigurationNode
import org.spongepowered.configurate.serialize.TypeSerializer
import java.lang.reflect.Type

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object SimpleDateTypeSerializer : TypeSerializer<Date> {

    private val legacyDateFormat = SimpleDateFormat("HH:mm:ss.SSS dd.MM.yyyy")
    private val dateFormat = SimpleDateFormat("HH:mm:ss.SSS-dd.MM.yyyy")

    fun deserialize(string: String): Date {
        try {
            return dateFormat.parse(string)
        } catch (exception: ParseException) {
            try {
                return deserializeLegacy(string)
            } catch (exception: ParseException) {
                // Will be handled further down
            }

            throw ConfigurateException("Invalid input value '$string' for a date like this: '21:18:25.300-28.03.2017'", exception)
        }
    }

    fun serialize(date: Date): String = dateFormat.format(date)

    private fun deserializeLegacy(string: String) = legacyDateFormat.parse(string)
    override fun deserialize(type: Type?, node: ConfigurationNode?): Date? = node?.string?.let { deserialize(it) }

    override fun serialize(type: Type?, obj: Date?, node: ConfigurationNode?) {
        node?.set(obj?.let { serialize(it) })
    }
}