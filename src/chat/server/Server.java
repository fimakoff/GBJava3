package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class StartServer {

    public static void main(String[] args) {
        new Server();
    }
}

class Server {

    private List<ClientHandler> peers;
    private int poolSize = Runtime.getRuntime().availableProcessors();


    Server() {
        AuthService authService = new AuthServiceImpl();
        peers = new CopyOnWriteArrayList<>();
        ServerSocket serverSocket = null;
        Socket socket = null;
        ExecutorService pool = null;
        try {
            authService.connect();
            serverSocket = new ServerSocket(8181);
            System.out.println("Сервер запущен!");
            while (true) {
                pool = Executors.newFixedThreadPool(poolSize);
                socket = serverSocket.accept();
                pool.execute(new ClientHandler(this, socket));
                System.out.println("Клиент подключился!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            pool.shutdown();
        } finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            authService.disconnect();
        }
    }

    void broadcast(ClientHandler from, String message) {
        for (ClientHandler clientHandler : peers) {
            if (!clientHandler.checkBlackList(from.getNick())) {
                clientHandler.sendMsg(message);
            }
        }
    }

    void subscribe(ClientHandler clientHandler) {
        peers.add(clientHandler);
        broadcastClientList();
    }

    void unsubscribe(ClientHandler clientHandler) {
        peers.remove(clientHandler);
        broadcastClientList();
    }

    void sendPersonalMsg(ClientHandler from, String nickTo, String msg) {
        for (ClientHandler clientHandler : peers) {
            if (clientHandler.getNick().equalsIgnoreCase(nickTo)) {
                clientHandler.sendMsg("FROM: " + from.getNick() + " SEND: " + msg);
                from.sendMsg("TO: " + nickTo + " SEND: " + msg);
                return;
            }
        }
        from.sendMsg("Клиент с ником: " + nickTo + " не найден в чате");
    }

    boolean isNickBusy(String nick) {
        for (ClientHandler clientHandler : peers) {
            if (clientHandler.getNick().equalsIgnoreCase(nick)) {
                return true;
            }
        }
        return false;
    }

    private void broadcastClientList() {
        StringBuffer sb = new StringBuffer();
        sb.append("/clientlist ");
        for (ClientHandler clientHandler : peers) {
            sb.append(clientHandler.getNick() + " ");
        }
        String out = sb.toString();
        for (ClientHandler clientHandler : peers) {
            clientHandler.sendMsg(out);
        }
    }
}