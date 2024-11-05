package com.bot.commands;

import com.bot.game.MiningLogic;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MineCommand {
    public void execute(MessageReceivedEvent event) {
        int loot = MiningLogic.mine();
        String response = "You mined and found " + loot + " resources!";
        event.getChannel().sendMessage(response).queue();
    }
}
