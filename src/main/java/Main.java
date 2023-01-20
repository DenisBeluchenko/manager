import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Other other = Other.loadingListProductsCategories();
        JSONObject messageJson = new JSONObject();
        JSONObject titleJson = new JSONObject();
        Category category = new Category(null);
        category.addingNewCategoryExistingCategories(other.creatingCategories());
        try (ServerSocket serverSocket = new ServerSocket(8989);) {
            System.out.println("Сервер запущен");
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    String string = bufferedReader.readLine();
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(string);
                    titleJson.put("category", other.determiningRelationshipProductCategory(json.get("title").toString()));
                    titleJson.put("sum", category.addingPurchaseAmountCategoryReturningTotalAmountPurchases(other.determiningRelationshipProductCategory(json.get("title").toString()), Integer.parseInt(json.get("sum").toString())));
                    messageJson.put("maxCategory", titleJson);
                    printWriter.println(messageJson);
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
