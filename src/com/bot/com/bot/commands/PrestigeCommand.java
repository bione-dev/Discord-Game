package com.bot.commands;

import com.bot.database.UserDao;
import com.bot.game.Player;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PrestigeCommand {
    private final UserDao userDao = new UserDao();

    public void execute(MessageReceivedEvent event) {
        String discordId = event.getAuthor().getId();
        Player player = userDao.getPlayer(discordId);

        String response;
        if (player.getLevel() >= 100) {
            player.setPrestigeLevel(player.getPrestigeLevel() + 1);
            player.resetStatsForPrestige();
            userDao.updatePlayer(discordId, player);
            response = "Congratulations! You've reached a new prestige level!";
        } else {
            response = "You need to reach level 100 to prestige.";
        }

        event.getChannel().sendMessage(response).queue();
    }
}
