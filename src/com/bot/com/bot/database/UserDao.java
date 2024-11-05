package com.bot.database;

import com.bot.game.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public Player getPlayer(String discordId) {
        try (Connection conn = DatabaseManager.getConnection()) {
            // Tenta buscar o jogador no banco de dados
            String query = "SELECT * FROM players WHERE discord_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, discordId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                // Jogador encontrado no banco de dados
                Player player = new Player();
                player.setLevel(rs.getInt("level"));
                player.setPrestigeLevel(rs.getInt("prestige_level"));
                player.setCoins(rs.getInt("coins"));
                player.getBackpack().setTotalValue(rs.getInt("backpack_value"));
                player.getTool().setLevel(rs.getInt("tool_level"));
                player.getTool().setUpgradeCost(rs.getInt("tool_upgrade_cost"));
                return player;
            } else {
                // Jogador não encontrado, cria um novo
                Player newPlayer = new Player();
                createPlayer(conn, discordId, newPlayer);
                return newPlayer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void createPlayer(Connection conn, String discordId, Player player) throws SQLException {
        String insert = "INSERT INTO players (discord_id, level, prestige_level, coins, backpack_value, tool_level, tool_upgrade_cost) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(insert);
        statement.setString(1, discordId);
        statement.setInt(2, player.getLevel());
        statement.setInt(3, player.getPrestigeLevel());
        statement.setInt(4, player.getCoins());
        statement.setInt(5, player.getBackpack().calculateTotalValue());
        statement.setInt(6, player.getTool().getLevel());
        statement.setInt(7, player.getTool().getUpgradeCost());
        statement.executeUpdate();
    }
    
    public void updatePlayer(String discordId, Player player) {
        try (Connection conn = DatabaseManager.getConnection()) {
            String update = "UPDATE players SET level = ?, prestige_level = ?, coins = ?, backpack_value = ?, tool_level = ?, tool_upgrade_cost = ? WHERE discord_id = ?";
            PreparedStatement statement = conn.prepareStatement(update);
            statement.setInt(1, player.getLevel());
            statement.setInt(2, player.getPrestigeLevel());
            statement.setInt(3, player.getCoins());
            statement.setInt(4, player.getBackpack().calculateTotalValue());
            statement.setInt(5, player.getTool().getLevel());
            statement.setInt(6, player.getTool().getUpgradeCost());
            statement.setString(7, discordId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
