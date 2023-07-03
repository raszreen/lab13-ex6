import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandler implements Runnable {
    private Socket socket;
    public RequestHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        // Create a stream to read the request from the socket.
        InputStream inputStream = null;
		try {
			inputStream = socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Read the request from the socket.
        ObjectInputStream objectInputStream = null;
		try {
			objectInputStream = new ObjectInputStream(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Request request = null;
		try {
			request = (Request) objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Translate the text.
        String translatedText = translateText(request.getEnglishText(), request.getTargetLanguage());
        // Create a stream to write the response to the socket.
        OutputStream outputStream = null;
		try {
			outputStream = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Write the response to the socket.
        ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			objectOutputStream.writeObject(new Response(translatedText));
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
    private String translateText(String englishText, String targetLanguage) {
        // Create a Google Translate API client.
        GoogleTranslateAPIClient client = new GoogleTranslateAPIClient();
        // Translate the text.
        String translatedText = client.translate(englishText, targetLanguage);
        // Return the translated text.
        return translatedText;
    }
}
