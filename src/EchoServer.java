/**
 * Created by alexhill on 3/13/14.
 */
import java.io.*;
import java.net.*;

public class EchoServer {
    private static final int PORT = 8000;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public EchoServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
        clientSocket = serverSocket.accept();
        OutputStream clientOutputStream = clientSocket.getOutputStream();
        out = new PrintWriter(clientOutputStream, true);
        InputStream clientInputStream = clientSocket.getInputStream();
        in = new BufferedReader(new InputStreamReader(clientInputStream));
    }

    public static void main(String[] args) {
        try  {
            EchoServer server = new EchoServer();
            server.run();
        } catch(IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
    }

    private void run() throws IOException {
        System.out.println("Echo server started");
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("echoing: " + inputLine);
            out.println(inputLine);
        }
        stop();
    }

    private void stop() throws IOException {
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }

}
