package de.randombyte.ktskript.utils

import de.randombyte.kosp.extensions.toText
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextDecoration
import org.spongepowered.api.Sponge


/*fun TextComponent.format(format: TextFormat): TextComponent = toBuilder().format(format).build()*/

/*fun TextComponent.color(color: TextColor): Text = format(format.color(color))*/

fun TextComponent.aqua(): TextComponent = color(NamedTextColor.AQUA)
fun TextComponent.black(): TextComponent = color(NamedTextColor.BLACK)
fun TextComponent.blue(): TextComponent = color(NamedTextColor.BLUE)
fun TextComponent.darkAqua(): TextComponent = color(NamedTextColor.DARK_AQUA)
fun TextComponent.darkBlue(): TextComponent = color(NamedTextColor.DARK_BLUE)
fun TextComponent.darkGray(): TextComponent = color(NamedTextColor.DARK_GRAY)
fun TextComponent.darkGreen(): TextComponent = color(NamedTextColor.DARK_GREEN)
fun TextComponent.darkPurple(): TextComponent = color(NamedTextColor.DARK_PURPLE)
fun TextComponent.darkRed(): TextComponent = color(NamedTextColor.DARK_RED)
fun TextComponent.gold(): TextComponent = color(NamedTextColor.GOLD)
fun TextComponent.gray(): TextComponent = color(NamedTextColor.GRAY)
fun TextComponent.green(): TextComponent = color(NamedTextColor.GREEN)
fun TextComponent.lightPurple(): TextComponent = color(NamedTextColor.LIGHT_PURPLE)
fun TextComponent.red(): TextComponent = color(NamedTextColor.RED)
fun TextComponent.white(): TextComponent = color(NamedTextColor.WHITE)
fun TextComponent.yellow(): TextComponent = color(NamedTextColor.YELLOW)

/*fun Text.style(style: Style): TextComponent = format(format.style(style))*/

fun TextComponent.bold(): TextComponent = decorate(TextDecoration.BOLD)
fun TextComponent.italic(): TextComponent = decorate(TextDecoration.ITALIC)
fun TextComponent.obfuscated(): TextComponent = decorate(TextDecoration.OBFUSCATED)
/*fun TextComponent.reset(): TextComponent = style(Style.RESET)*/
fun TextComponent.strikethrough(): TextComponent = decorate(TextDecoration.STRIKETHROUGH)
fun TextComponent.underline(): TextComponent = decorate(TextDecoration.UNDERLINED)

/*fun <T : TextComponent<*>> Text.action(action: T): Text {
    return when (action) {
        is ClickAction<*> -> toBuilder().onClick(action)
        is ShiftClickAction<*> -> toBuilder().onShiftClick(action)
        is HoverAction<*> -> toBuilder().onHover(action)
        else -> return this
    }.build()
}*/

operator fun TextComponent.plus(other: TextComponent): TextComponent = this.append(other)
operator fun TextComponent.plus(other: String): TextComponent = this + other.toText()

/*fun TextComponent.serialize() = TextSerializers.FORMATTING_CODE.serialize(this)*/

// sending texts
/*
fun TextComponent.sendTo(vararg messageChannels: MessageChannel) {
    if (!isEmpty) messageChannels.forEach { it.send(this) }
}

fun Iterable<TextComponent>.sendTo(vararg messageChannels: MessageChannel) = forEach { it.sendTo(*messageChannels) }
*/

fun TextComponent.sendTo(vararg receivers: Audience) = sendTo(receivers.asIterable())
fun TextComponent.sendTo(receivers: Iterable<Audience>) {
    if (this.content().isNotEmpty()) receivers.forEach { it.sendMessage(this) }
}

fun Iterable<TextComponent>.sendTo(vararg receivers: Audience) = sendTo(receivers.asIterable())
fun Iterable<TextComponent>.sendTo(receivers: Iterable<Audience>) = forEach { it.sendTo(receivers) }

fun TextComponent.broadcast() = sendTo(Sponge.server().broadcastAudience())
fun Iterable<TextComponent>.broadcast() = sendTo(Sponge.server().broadcastAudience())