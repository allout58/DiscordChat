package net.shadowfacts.discordchat.discord;

import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.shadowfacts.discordchat.DCCommands;
import net.shadowfacts.discordchat.DCConfig;
import net.shadowfacts.discordchat.commands.Command;
import net.shadowfacts.discordchat.utils.MiscUtils;

/**
 * @author shadowfacts
 */
public class MainListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (MiscUtils.shouldUseChannel(event.getChannel()) && event.getGuild().getId().equals(DCConfig.serverId)) {
            if (event.getMessage().getContent().startsWith("!")) {
                String msg = event.getMessage().getContent();
                String commandName = msg.substring(1);
                String[] args = {};
                if (commandName.contains(" ")) {
                    commandName = msg.substring(1, msg.indexOf(" "));
                    args = msg.substring(msg.indexOf(" ")).split(" ");
                }

                Command command = DCCommands.getInstance().getCommand(commandName);
                if (command != null) {
                    command.doCommand(event.getChannel().getName(), args);
                }
            } else if (!MiscUtils.isMessageFromMC(event.getMessage())) {
                MiscUtils.sendMessage(MiscUtils.fromDiscordMessage(event.getMessage()));
            }
        }
    }

}
