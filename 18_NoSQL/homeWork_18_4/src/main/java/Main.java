
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Scanner;

public class Main {

    final static String collectionStores = "stores";
    final static String collectionProducts = "products";


    public static void main(String[] args) {
         MongoDatabase database = new TaskMongoClient().getMongoDatabase();
        MongoCollection<Document> stores = database.getCollection(collectionStores);
        MongoCollection<Document> products = database.getCollection(collectionProducts);
        //stores.drop();
        //products.drop();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Используйте следующие команды:\nДОБАВИТЬ_МАГАЗИН имя_магазина\n" +
                "ДОБАВИТЬ_ТОВАР наименование_товара цена_товара\nВЫСТАВИТЬ_ТОВАР наименование имя_магазина\nСТАТИСТИКА_ТОВАРОВ");
        for (; ; ) {
            String cmd = scanner.nextLine();
            if (MongoDBUtils.checkAddStoreCmd(cmd)) {
               MongoDBUtils.addStore(cmd, stores);
                MongoDBUtils.writeMemberOfCollection(stores);
            } else
            if (MongoDBUtils.checkAddProductCmd(cmd)) {
                MongoDBUtils.addProduct(cmd, products);
                MongoDBUtils.writeMemberOfCollection(products);
            } else
            if (MongoDBUtils.checkDisplayProductCmd(cmd)) {
               MongoDBUtils.displayProductToStore(cmd, products, stores);
                MongoDBUtils.writeMemberOfCollection(stores);
            } else
            if (MongoDBUtils.checkProductStatisticsCmd(cmd)) {
                MongoDBUtils.writeProductStatistics(products, stores);
            } else {
                System.out.println("<!> Некорректная команда");
            }
        }
    }


}
