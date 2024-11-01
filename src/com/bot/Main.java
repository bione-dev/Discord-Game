import net.dv8tion.jda.api.JDA; // Import JDA for interacting with Discord
import net.dv8tion.jda.api.JDABuilder;  // Import JDABuilder to create JDA instance
import net.dv8tion.jda.api.entities.Activity; // Import Activity for setting bot status
import net.dv8tion.jda.api.requests.GatewayIntent; // Import GatewayIntent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent; // Import for message events
import net.dv8tion.jda.api.hooks.ListenerAdapter; // Import ListenerAdapter
import javax.security.auth.login.LoginException; // Import LoginException
import java.io.IOException; // Import for handling file I/O (reading token)
import java.nio.file.Files; // Import for reading files
import java.nio.file.Paths; // Import for working with file paths

public class Main {
    public static void main(String[] args) throws LoginException, IOException, InterruptedException {
        // Load token from .env file
        String token = new String(Files.readAllBytes(Paths.get(".env")));

        // Build JDA instance with configuration
        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MESSAGES) // Listen for messages
                .enableIntents(GatewayIntent.MESSAGE_CONTENT) // Enable message content intent
                .setActivity(Activity.listening("seus comandos")) // Set bot status
                .build();

     // Adicione o listener para eventos de mensagem
        jda.addEventListener(new ListenerAdapter() {
            @Override
            public void onMessageReceived(MessageReceivedEvent event) {
                // Ignora mensagens enviadas pelo próprio bot
                if (event.getAuthor().isBot()) return;

                // Verifique se a mensagem começa com "!ping"
                if (event.getMessage().getContentRaw().startsWith("!ping")) {
                    // Responda com "Pong!"
                    event.getChannel().sendMessage("Pong!").queue();
                }
            }
        });


        // Connect to Discord (blocks until successful)
        jda.awaitReady();

        System.out.println("Bot is now online!");
    }
}
