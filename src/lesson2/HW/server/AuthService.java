package lesson2.HW.server;

import java.sql.*;

interface AuthService {

    String getNick(String login, String pass);

    void connect();

    void disconnect();

    void setNick(String login, String password, String newNick);

    void setBlackList(boolean sbl, String nick);

    boolean isBlackList(String nick);
}

class AuthServiceImpl implements AuthService {

    private static Connection connection;
    private static Statement statement;

    @Override
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/lesson2/HW/db");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setNick(String login, String password, String newNick) {
        String nick = getNick(login, password);
        String query = String.format("UPDATE users \n" +
                "SET nick = '%s'\n" +
                "WHERE nick = '%s'", newNick, nick);
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setBlackList(boolean sbl, String nick) {
        byte setBlackList;
        if (sbl) {
            setBlackList = 1;
        } else {
            setBlackList = 0;
        }

        String query = String.format("UPDATE users \n" +
                "SET isBlackList = '%s'\n" +
                "WHERE nick = '%s'", setBlackList, nick);
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isBlackList(String nick) {
        String query = String.format("SELECT nick FROM users\n"
                + "WHERE nick = '%s'\n " +
                "AND isBlackList = '1'", nick);
        try {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getNick(String login, String pass) {
        String query = String.format("SELECT nick FROM users\n"
                + "WHERE login = '%s'\n"
                + "  AND password = '%s'\n", login, pass);
        try {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
