package de.randombyte.kosp.extensions

import de.randombyte.ktskript.utils.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import org.spongepowered.api.Sponge
import org.spongepowered.api.profile.GameProfile
import java.util.concurrent.CompletableFuture

fun String.toText(): TextComponent = Component.text(this)

fun String.aqua(): TextComponent = toText().aqua()
fun String.black(): TextComponent = toText().black()
fun String.blue(): TextComponent = toText().blue()
fun String.darkAqua(): TextComponent = toText().darkAqua()
fun String.darkBlue(): TextComponent = toText().darkBlue()
fun String.darkGray(): TextComponent = toText().darkGray()
fun String.darkGreen(): TextComponent = toText().darkGreen()
fun String.darkPurple(): TextComponent = toText().darkPurple()
fun String.darkRed(): TextComponent = toText().darkRed()
fun String.gold(): TextComponent = toText().gold()
fun String.gray(): TextComponent = toText().gray()
fun String.green(): TextComponent = toText().green()
fun String.lightPurple(): TextComponent = toText().lightPurple()
fun String.red(): TextComponent = toText().red()
fun String.white(): TextComponent = toText().white()
fun String.yellow(): TextComponent = toText().yellow()

fun String.bold(): TextComponent = toText().bold()
fun String.italic(): TextComponent = toText().italic()
fun String.obfuscated(): TextComponent = toText().obfuscated()

/*fun String.reset(): TextComponent = toText().reset()*/
fun String.strikethrough(): TextComponent = toText().strikethrough()
fun String.underline(): TextComponent = toText().underline()

/*fun <T : TextAction<*>> String.action(action: T): TextComponent = toText().action(action)*/

/*fun String.toArg(): TextTemplate.Arg = TextTemplate.arg(this).build()

fun String.deserialize(): TextComponent = TextSerializers.FORMATTING_CODE.deserialize(this)*/

fun String.limit(end: Int): String = substring(0, end.coerceAtMost(length))

fun String.replace(vararg args: Pair<String, String>): String = replace(args.toMap())
fun String.replace(values: Map<String, String>): String {
    var string = this
    values.forEach { (argument, value) ->
        string = string.replace(argument, value)
    }
    return string
}

// API safe wrappers

/**
 * Tries to process the placeholders if PlaceholderAPI is loaded.
 */
/*fun String.tryReplacePlaceholders(source: Any? = null, observer: Any? = null): String {
    if (!Sponge.pluginManager().plugin("placeholderapi").isPresent) return this

    val placeholderService =
        PlaceholderService::class.getServiceOrFail(failMessage = "PlaceholderAPI could not be loaded although the plugin itself is present!")

    val placeholders = Common.PLACEHOLDER_PATTERN
        .findAll(this)
        .map { matchResult -> matchResult.groupValues[1] }.toList()
    val replacements = placeholders.mapNotNull { placeholder ->
        val replacement = placeholderService.parse(placeholder, source, observer) ?: return@mapNotNull null
        val replacementString = if (replacement is Text) {
            TextSerializers.FORMATTING_CODE.serialize(replacement)
        } else {
            replacement.toString()
        }
        "%$placeholder%" to replacementString
    }.toMap()

    return this.replace(replacements)
}*/

/*
fun String.tryAsByteItem(failMessage: String? = null): ItemStackSnapshot {
    if (!Sponge.getPluginManager().getPlugin("byte-items").isPresent) {
        // fall back to normal minecraft item types
        val itemType = Sponge.getRegistry().getType(ItemType::class.java, this)
            .orElseThrow { IllegalArgumentException("Couldn't find ItemType '$this'!") }
        return ItemStack.of(itemType, 1).createSnapshot()
    }

    val byteItemsApi =
        ByteItemsApi::class.getServiceOrFail(failMessage = "ByteItems could not be loaded although the plugin itself is present!")
    return if (failMessage != null) {
        byteItemsApi.getItemSafely(id = this, failMessage = failMessage)
    } else {
        byteItemsApi.getItemSafely(id = this)
    }
}
*/

// Misc functions

fun String.asGameProfile(): CompletableFuture<GameProfile> = Sponge.server().gameProfileManager().profile(this)

fun String.executeAsConsole() = Sponge.server().commandManager().process(this)