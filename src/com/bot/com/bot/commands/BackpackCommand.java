package com.bot.commands;

import com.bot.database.UserDao;
import com.bot.game.Player;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class BackpackCommand {
    private final UserDao userDao = new UserDao();

    public void execute(MessageReceivedEvent event) {
        String discordId = event.getAuthor().getId();
        Player player = userDao.getPlayer(discordId);

        if (player == null) {
            event.getChannel().sendMessage("Could not load player data. Please try again later.").queue();
            return;
        }

        String response = "Total value in backpack: " + player.getBackpack().calculateTotalValue();
        event.getChannel().sendMessage(response).queue();
    }
}
