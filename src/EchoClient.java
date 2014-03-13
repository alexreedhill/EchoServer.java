import java.io.*;
import java.net.*;

/**
 * Created by alexhill on 3/13/14.
 */
public class EchoClient {
    private PrintWriter out;
    private BufferedReader networkIn;
    private BufferedReader userIn;


    public EchoClient() throws IOException {
        String hostname = "localhost";
        Socket localhostSocket = new Socket(hostname, 8000);
        InputStreamReader networkStreamReader;
        networkStreamReader = new InputStreamReader(localhostSocket.getInputStream());
        networkIn = new BufferedReader(networkStreamReader);
        InputStreamReader userStreamReader;
        userStreamReader = new InputStreamReader(System.in);
        userIn = new BufferedReader(userStreamReader);
        out = new PrintWriter(localhostSocket.getOutputStream());
    }

    public static void main(String[] args) {
        try {
            EchoClient client = new EchoClient();
            client.run();
        } catch(IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
    }

    private void run() throws IOException {
        System.out.println("Connected to echo server");
        while(true) {
            String theLine = userIn.readLine();
            if (theLine.equals(".")) break;
            out.println(theLine);
            out.flush();
            System.out.println(networkIn.readLine());
         }
         stop();
    }

    private void stop() throws IOException {
        if (networkIn != null) networkIn.close();
        if (out != null) out.close();
    }
}
