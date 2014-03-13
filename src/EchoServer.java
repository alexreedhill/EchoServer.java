/**
 * Created by alexhill on 3/13/14.
 */
import java.io.*;
import java.net.*;

public class EchoServer {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        OutputStream clientOutputStream = null;
        InputStream clientInputStream = null;
        String inputLine = null;
        Socket clientSocket = null;
        PrintWriter out = null;

        try {
            serverSocket = new ServerSocket(PORT);
            clientSocket = serverSocket.accept();
            clientOutputStream = clientSocket.getOutputStream();
            out = new PrintWriter(clientOutputStream, true);
            clientInputStream = clientSocket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientInputStream));
            System.out.println("Echo server started");
            while ((inputLine = in.readLine()) != null) {
                System.out.println("echoing: " + inputLine);
                out.println(inputLine);
            }
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        } catch(IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }

    }

}
