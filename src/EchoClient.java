import java.io.*;
import java.net.*;

/**
 * Created by alexhill on 3/13/14.
 */
public class EchoClient {

    public static void main(String[] args) {
        String hostname = "localhost";

        if (args.length > 0) {
            hostname = args[0];
        }

        PrintWriter out = null;
        BufferedReader networkIn = null;
        try {
            Socket localhostSocket = new Socket(hostname, 8000);
            InputStreamReader networkStreamReader = new InputStreamReader(localhostSocket.getInputStream());
            networkIn = new BufferedReader(networkStreamReader);
            InputStreamReader userStreamReader = new InputStreamReader(System.in);
            BufferedReader userIn = new BufferedReader(userStreamReader);
            out = new PrintWriter(localhostSocket.getOutputStream());
            System.out.println("Connected to echo server");

            while(true) {
                String theLine = userIn.readLine();
                if (theLine.equals('.')) break;
                out.println(theLine);
                out.flush();
                System.out.println(networkIn.readLine());
            }
        } catch(IOException ex) {
            System.err.println(ex);
        } finally {
            try {
                if (networkIn != null) networkIn.close();
                if (out != null) out.close(); }
            catch (IOException ex) {}
        }

    }
}
