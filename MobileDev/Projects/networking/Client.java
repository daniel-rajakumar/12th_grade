import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        try {
            System.out.println("Client: Looking for host...");
            Socket socket = new Socket("localhost", 8080);
            System.out.println("Client: Connected");

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("is it working");
            printWriter.flush();

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = bufferedReader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
