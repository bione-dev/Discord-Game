package com.bot.commands;

import com.bot.database.UserDao;
import com.bot.game.Player;
import com.bot.game.Tool;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class UpgradeCommand {
    private final UserDao userDao = new UserDao();

    public void execute(MessageReceivedEvent event) {
        String discordId = event.getAuthor().getId();
        Player player = userDao.getPlayer(discordId);
        Tool tool = player.getTool();

        int upgradeCost = tool.getUpgradeCost();
        String response;
        if (player.getCoins() >= upgradeCost) {
            player.addCoins(-upgradeCost);
            tool.upgrade();
            userDao.updatePlayer(discordId, player);
            response = "Tool upgraded successfully!";
        } else {
            response = "Not enough coins for upgrade!";
        }

        event.getChannel().sendMessage(response).queue();
    }
}
