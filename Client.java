import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        // Create a client object.
        Client client = new Client();
        // Send a request to the server.
        client.sendRequest("Hello world", "Bahasa Malaysia");
        // Receive the response from the server.
        String response = null;
		try {
			try {
				response = client.receiveResponse();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Print the response.
        System.out.println(response);
    }
    private void sendRequest(String englishText, String targetLanguage) {
        // Create a socket connection to the server.
        Socket socket = null;
		try {
			socket = new Socket("localhost", 8080);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Create a stream to write the request to the socket.
        OutputStream outputStream = null;
		try {
			outputStream = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Write the request to the socket.
        ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			objectOutputStream.writeObject(new Request(englishText, targetLanguage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Close the socket connection.
        try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private String receiveResponse() throws UnknownHostException, IOException, ClassNotFoundException {
        // Create a socket connection to the server.
        Socket socket = new Socket("localhost", 8080);
        // Create a stream to read the response from the socket.
        InputStream inputStream = socket.getInputStream();
        // Read the response from the socket.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Response response = (Response) objectInputStream.readObject();
        // Close the socket connection.
        socket.close();
        // Return the response.
        return response.getTranslatedText();
    }
}
