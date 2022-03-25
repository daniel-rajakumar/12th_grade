import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.*;

public class Server {

    HashSet<Socket> sockets = new HashSet<>();

    Server(){
        try {
            System.out.println("server started in: " + Inet4Address.getLocalHost().getHostAddress());

            ServerSocket serverSocket = new ServerSocket(8080);

            System.out.println("Server: waiting for a client...");
            new Thread(() -> {
                while (sockets.size() < 20){
                    try {
                        sockets.add(serverSocket.accept());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println();
                    for (Socket socket: sockets){
//                        System.out.println(
//                                MessageFormat.format(
//                                        "inet address: {0} | local address: {1} | remote address: {2} | closed: {3}"
//                                        , socket.getInetAddress()
//                                        , socket.getLocalAddress()
//                                        , socket.getRemoteSocketAddress()
//                                        , socket.isClosed()
//                                ));
                    }
                    System.out.println();


                    for (Socket socket: sockets){
    //                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
    //                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                        try {
                            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                            printWriter.println("Daniel: " + new Date(System.currentTimeMillis()));
                            printWriter.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }, 0, 1000);


//
//
//            String str = bufferedReader.readLine();
//

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Server();
    }
}
