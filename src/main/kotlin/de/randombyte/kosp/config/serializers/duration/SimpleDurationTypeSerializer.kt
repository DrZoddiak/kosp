package de.randombyte.kosp.config.serializers.duration

import com.google.common.reflect.TypeToken
import org.spongepowered.configurate.ConfigurationNode
import org.spongepowered.configurate.serialize.TypeSerializer
import java.lang.reflect.Type
import java.time.Duration

/**
 * A simple format for defining [Duration]s.
 *
 * 'd' -> days
 * 'h' -> hours
 * 'm' -> minutes
 * 's' -> seconds
 * 'ms' -> milliseconds
 *
 * Examples: '1d4h30m20s90ms', '30s', '2h', '12m3s', '500ms'
 */
object SimpleDurationTypeSerializer : TypeSerializer<Duration> {
    private const val SECOND = 1000
    private const val MINUTE = 60
    private const val HOUR = 60 * MINUTE
    private const val DAY = 24 * HOUR

    val REGEX = "(?:(\\d+)d)?(?:(\\d+)h)?(?:(\\d+)m)?(?:(\\d+)s)?(?:(\\d+)ms)?".toRegex()

    override fun deserialize(type: Type, node: ConfigurationNode): Duration? = node.string?.let { deserialize(it) }


    override fun serialize(type: Type?, obj: Duration?, node: ConfigurationNode?) {
        node?.set(obj?.let { serialize(it) })
    }

    fun deserialize(string: String): Duration {
        val result = REGEX.matchEntire(string) ?: throw RuntimeException("Couldn't parse duration '$string'!")

        val days = result.groupValues[1].toLongOrZero()
        val hours = result.groupValues[2].toLongOrZero()
        val minutes = result.groupValues[3].toLongOrZero()
        val seconds = result.groupValues[4].toLongOrZero()
        val milliseconds = result.groupValues[5].toLongOrZero()

        return Duration.ofDays(days).plusHours(hours).plusMinutes(minutes).plusSeconds(seconds).plusMillis(milliseconds)
    }

    fun serialize(duration: Duration, outputMilliseconds: Boolean = true): String {
        val days = duration.seconds / DAY
        val hours = duration.seconds % DAY / HOUR
        val minutes = duration.seconds % DAY % HOUR / MINUTE
        val seconds = duration.seconds % DAY % HOUR % MINUTE
        val milliseconds = duration.nano / 1000000

        val sb = StringBuilder()
        days.apply { if (this != 0L) sb.append(this).append("d") }
        hours.apply { if (this != 0L) sb.append(this).append("h") }
        minutes.apply { if (this != 0L) sb.append(this).append("m") }
        seconds.apply { if (this != 0L) sb.append(this).append("s") }
        milliseconds.apply { if (outputMilliseconds && this != 0) sb.append(this).append("ms") }

        val string = sb.toString()
        return if (string.isNotEmpty()) string else "0s" // prevent "" return values
    }

    private fun String.toLongOrZero() = if (isEmpty()) 0 else toLong()
}