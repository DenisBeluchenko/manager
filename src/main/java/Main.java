import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Other other = Other.loadTitle();
        JSONObject messageJson = new JSONObject();
        JSONObject titleJson = new JSONObject();
        other.loadTitle();
        Category category = new Category(null);
        category.addCategory(other.titleCategory());
        try (ServerSocket serverSocket = new ServerSocket(8989);) {
            System.out.println("Сервер запущен");
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    String s = in.readLine();
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(s);
                    titleJson.put("category", other.title(json.get("title").toString()));
                    titleJson.put("sum", category.answer(other.title(json.get("title").toString()), Integer.parseInt(json.get("sum").toString())));
                    messageJson.put("maxCategory", titleJson);
                    out.println(messageJson);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
