package com.bot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CoinflipCommand {
    public void execute(MessageReceivedEvent event) {
        String result = Math.random() < 0.5 ? "Heads!" : "Tails!";
        event.getChannel().sendMessage(result).queue();
    }
}
