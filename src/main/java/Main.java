import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Other other = Other.loadTitle();
        int sum;
        String msg;
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
                    System.out.println(s);
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(s);
                    msg = "{ \"maxCategory\": {\"category\": \"";
                    sum = category.answer(other.title(json.get("title").toString()), Integer.parseInt(json.get("sum").toString()));
                    msg += other.title(json.get("title").toString()) + " \", \"sum\": " + sum + "}}";
                    out.println(msg);
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
