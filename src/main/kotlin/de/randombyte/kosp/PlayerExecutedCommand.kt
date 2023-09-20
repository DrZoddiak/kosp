package de.randombyte.kosp

import de.randombyte.kosp.extensions.toText
import org.spongepowered.api.command.CommandExecutor
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.exception.CommandException
import org.spongepowered.api.command.parameter.CommandContext
import org.spongepowered.api.entity.living.player.Player

/**
 * A command that can only be executed by a [Player]. Otherwise it will throw a [CommandException].
 */
abstract class PlayerExecutedCommand : CommandExecutor {
    abstract fun executedByPlayer(player: Player, args: CommandContext): CommandResult

    override fun execute(args: CommandContext): CommandResult? {
        val player = args.cause().root()
        if (player !is Player) throw CommandException("Command must be executed by a player!".toText())
        return executedByPlayer(player, args)
    }
}