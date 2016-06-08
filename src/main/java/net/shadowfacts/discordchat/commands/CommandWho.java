package net.shadowfacts.discordchat.commands;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.shadowfacts.discordchat.discord.DiscordThread;

/**
 * Created by James Hollowell on 4/14/2016.
 */
public class CommandWho extends Command {

    @Override
    public void doCommand(String channel, String[] args) {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        DiscordThread.instance.sendMessageToChannel(channel, "===Users Online: " + server.getCurrentPlayerCount() + "===\n" + server.getPlayerList().getFormattedListOfPlayers(false));
    }
}
