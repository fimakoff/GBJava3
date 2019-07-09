package chat.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    @FXML
    TextField msgField;
    @FXML
    TextArea chatArea;
    @FXML
    HBox bottomPanel;
    @FXML
    HBox upperPanel;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    ListView<String> clientList;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private final String ADDRESS = "localhost";
    private final int PORT = 8181;

    private static boolean isAuthorized;
    private static String login;
    private static String password;

    private StringBuffer sb = new StringBuffer();


    void timeOut(int time) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = time;

            @Override
            public void run() {
                System.out.println(Controller.isAuthorized + " " + i--);
                if (Controller.isAuthorized && i != 0) {
                    System.out.println("Client is already connected with login: \"" + login + "\" password: \"" + password + "\"");
                    timer.cancel();
                } else if (i == 0) {
                    System.exit(0);
                    System.out.println("Client is terminated");
                    close();
                }
            }
        }, 0, 1000);
    }

    private void setAuthorized(boolean isAuthorized) {
        Controller.isAuthorized = isAuthorized;
        if (!Controller.isAuthorized) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(true);
            clientList.setVisible(false);
            clientList.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
            clientList.setVisible(true);
            clientList.setManaged(true);
            Controller.isAuthorized = isAuthorized;
        }
    }

    private void connect() {
        try {
            socket = new Socket(ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    auth();
                    read();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() throws IOException {
        readFromFile();
        while (true) {
            String str = in.readUTF();
            writeInFile(str);
            if (str.equalsIgnoreCase("/serverclosed")) {
                break;
            }
            if (str.startsWith("/clientlist") || str.startsWith("/updatenick")) {
                String[] tokens = str.split(" ");
                Platform.runLater(() -> {
                    clientList.getItems().clear();
                    for (int i = 1; i < tokens.length; i++) {
                        clientList.getItems().add(tokens[i]);
                    }
                });
            }
            chatArea.appendText(str + "\n");
        }
    }

    private void auth() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/authOK")) {
                setAuthorized(true);
                break;
            } else {
                chatArea.appendText(str + "\n");
            }
        }
    }

    public void tryToAuth() {
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            login = loginField.getText().trim();
            password = passwordField.getText().trim();
            out.writeUTF("/auth " + login + " " + password);
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(msgField.getText());
            msgField.clear();
            msgField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeInFile(String str) {
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("history_[" + login + "].txt"));
            sb.append(str+"\n");
            out.writeUTF(String.valueOf(sb));
            writer.write(sb.toString());
            writer.flush();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void readFromFile() {

        try {
            File file = new File("history_[" + login + "].txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                chatArea.appendText(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            try {
                out.writeUTF("ББ приветствует тебя в чате!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.err.println("файла нет");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
