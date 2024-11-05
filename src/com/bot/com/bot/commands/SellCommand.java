package com.bot.commands;

import com.bot.database.UserDao;
import com.bot.game.Player;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SellCommand {
    private final UserDao userDao = new UserDao();

    public void execute(MessageReceivedEvent event) {
        String discordId = event.getAuthor().getId();
        Player player = userDao.getPlayer(discordId);

        int totalValue = player.getBackpack().calculateTotalValue();
        String response;
        if (totalValue > 0) {
            player.addCoins(totalValue);
            player.getBackpack().clear();
            userDao.updatePlayer(discordId, player);
            response = "You sold items for " + totalValue + " coins!";
        } else {
            response = "Your backpack is empty!";
        }

        event.getChannel().sendMessage(response).queue();
    }
}
