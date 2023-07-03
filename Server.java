import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws IOException {
        // Create a server socket.
        @SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(8080);
        // Listen for incoming connections.
        while (true) {
            // Accept an incoming connection.
            Socket socket = serverSocket.accept();
            // Create a new thread to handle the connection.
            Thread thread = new Thread(new RequestHandler(socket));
            thread.start();
        }
    }
}
