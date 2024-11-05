package com.bot.commands;

import com.bot.database.UserDao;
import com.bot.game.Player;
import utils.Utils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HuntCommand {
    private final UserDao userDao = new UserDao();

    public void execute(MessageReceivedEvent event) {
        String discordId = event.getAuthor().getId();
        Player player = userDao.getPlayer(discordId);

        // Simula a caçada com uma chance de sucesso de 70%
        int loot = Utils.checkSuccess(70) ? (int)(Math.random() * 50) + 10 : 0;
        
        String response;
        if (loot > 0) {
            player.addCoins(loot);
            userDao.updatePlayer(discordId, player); // Atualiza o jogador no banco de dados
            response = "You went on a hunt and found " + loot + " coins!";
        } else {
            response = "The hunt was unsuccessful. Better luck next time!";
        }
        
        event.getChannel().sendMessage(response).queue();
    }
}
