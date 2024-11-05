package com.bot;

import com.bot.commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;
import utils.Config;

public class Main extends ListenerAdapter {

    private static JDA jda;

    public static void main(String[] args) throws LoginException {
        jda = JDABuilder.createDefault(Config.BOT_TOKEN)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)  // Habilita a intent MESSAGE_CONTENT
                .addEventListeners(new Main())
                .setActivity(Activity.playing("Mining Adventure"))
                .build();

        // Adiciona o shutdown hook para garantir que o bot será encerrado
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (jda != null) {
                try {
                    jda.shutdown();  // Desconecta de forma segura
                    jda.awaitStatus(JDA.Status.SHUTDOWN);  // Aguarda o desligamento
                    System.out.println("Bot foi desconectado com sucesso.");
                } catch (InterruptedException e) {
                    System.err.println("Erro ao encerrar o bot: " + e.getMessage());
                }
            }
        }));
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String message = event.getMessage().getContentRaw();

        if (message.startsWith("!backpack")) {
            new BackpackCommand().execute(event);
        } else if (message.startsWith("!coinflip")) {
            new CoinflipCommand().execute(event);
        } else if (message.startsWith("!hunt")) {
            new HuntCommand().execute(event);
        } else if (message.startsWith("!mine")) {
            new MineCommand().execute(event);
        } else if (message.startsWith("!prestige")) {
            new PrestigeCommand().execute(event);
        } else if (message.startsWith("!sell")) {
            new SellCommand().execute(event);
        } else if (message.startsWith("!upgrade")) {
            new UpgradeCommand().execute(event);
        }
    }
}
