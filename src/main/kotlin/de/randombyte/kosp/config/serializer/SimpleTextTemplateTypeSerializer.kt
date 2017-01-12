package de.randombyte.kosp.config.serializer

import com.google.common.reflect.TypeToken
import ninja.leaping.configurate.ConfigurationNode
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer
import org.spongepowered.api.text.TextTemplate

/**
 * Simplifies the config appearance of [TextTemplate]s. Arguments are enclosed in curly brackets.
 * Restrictions:    - Every argument is required, there are no optional ones
 *                  - Formatting codes have to be used to apply formatting to the text
 * Example: '&cThe number is {number}.'
 * Note: The meaning of arguments and parameters is somehow switched in [TextTemplate]s, I'll keep this error in comments.
 *
 * Comments set by `@Setting(comment = "...")` get processed when prefixed with `%`. The format is described at [SimpleTextTemplateSerializer.parseExistingComment].
 */
object SimpleTextTemplateTypeSerializer : TypeSerializer<TextTemplate> {
    override fun serialize(type: TypeToken<*>, textTemplate: TextTemplate, node: ConfigurationNode) = SimpleTextTemplateSerializer.serialize(textTemplate, node)
    override fun deserialize(type: TypeToken<*>, node: ConfigurationNode) = SimpleTextTemplateDeserializer.deserialize(node)

    internal fun String.toFullArgumentName() = TextTemplate.DEFAULT_OPEN_ARG + this + TextTemplate.DEFAULT_CLOSE_ARG
}