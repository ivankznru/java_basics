import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;

public class MongoDBUtils {

    final static String cmdAddStoreReg = "ДОБАВИТЬ_МАГАЗИН";
    final static String cmdAddProductReg = "ДОБАВИТЬ_ТОВАР";
    final static String cmdDisplayProductReg = "ВЫСТАВИТЬ_ТОВАР";
    final static String cmdStatisticsProductReg = "СТАТИСТИКА_ТОВАРОВ";
    static JsonWriterSettings writerSettings = new JsonWriterSettings(JsonMode.SHELL, true);



    static boolean checkAddStoreCmd(String cmd) {
        return (cmd.contains(cmdAddStoreReg) && (cmd.trim().split(" ").length == 2));
    }

    static boolean checkAddProductCmd(String cmd) {
        return (cmd.contains(cmdAddProductReg) && (cmd.trim().split(" ").length == 3));
    }

    static boolean checkDisplayProductCmd(String cmd) {
        return (cmd.contains(cmdDisplayProductReg) && (cmd.trim().split(" ").length == 3));
    }

    static boolean checkProductStatisticsCmd(String cmd) {
        return (cmd.contains(cmdStatisticsProductReg) && (cmd.trim().split(" ").length == 1));
    }


    static void addStore(String cmd, MongoCollection<Document> collectionStore) {
        cmd = cmd.replaceAll(cmdAddStoreReg, "").trim();
        Document storeDoc = new Document().append("Store", cmd).append("Products", new ArrayList<String>());
        collectionStore.insertOne(storeDoc);
        System.out.println("Магазин \"" + cmd + "\" добавлен");
    }

    static void addProduct(String cmd, MongoCollection<Document> collectionProducts) {
        int productName = 0;
        int productPrice = 1;
        String[] cmdArray = cmd.replaceAll(cmdAddProductReg, "").trim().split(" ");
        Document productDoc = new Document().append("Product", cmdArray[productName]).append("Price", Integer.valueOf(cmdArray[productPrice]));
        collectionProducts.insertOne(productDoc);
        System.out.println("Продукт \"" + cmdArray[productName] + "\" по цене " + cmdArray[productPrice] + "руб. добавлен");
    }

    static void displayProductToStore(String cmd, MongoCollection<Document> collectionProducts, MongoCollection<Document> collectionStore) {
        int productName = 0;
        int storeName = 1;
        String[] cmdArray = cmd.replaceAll(cmdDisplayProductReg, "").trim().split(" ");
        if (checkExistenceProduct(cmdArray[productName], collectionProducts) && checkExistenceStore(cmdArray[storeName], collectionStore)) {
            Document storeDoc = collectionStore.find(eq("Store", cmdArray[storeName])).first();
            ArrayList<String> productsList = (ArrayList<String>) storeDoc.get("Products");
            System.out.println(productsList);
            if ((productsList.size() == 0) || (!productsList.contains(cmdArray[productName]))) {
                productsList.add(cmdArray[productName]);
            }
            collectionStore.findOneAndUpdate(eq("Store", cmdArray[storeName]), new Document("$set", new Document("Products", productsList)));
            System.out.println("Продукт \"" + cmdArray[productName] + "\" добавлен в магазин \"" + cmdArray[storeName] + "\"");
        }
    }

    static void writeProductStatistics(MongoCollection<Document> collectionProducts, MongoCollection<Document> collectionStore) {
        AggregateIterable<Document> productsPciceStatistcs = collectionStore.aggregate(Arrays.asList(
                Aggregates.lookup(collectionProducts.getNamespace().getCollectionName(), "Products", "Product", "Products"),
                Aggregates.unwind("$Products"),
                Aggregates.group("$Store", Accumulators.sum("ProductsSum", 1),
                        Accumulators.avg("AvgPrice", "$Products.Price"),
                        Accumulators.max("MaxPrice", "$Products.Price"),
                        Accumulators.min("MinPrice", "$Products.Price"))
        ));
        AggregateIterable<Document> productsPriceLTs100 = collectionStore.aggregate(Arrays.asList(
                Aggregates.lookup(collectionProducts.getNamespace().getCollectionName(), "Products", "Product", "Products"),
                Aggregates.unwind("$Products"),
                Aggregates.match(lt("Products.Price", 100)),
                Aggregates.group("$Store", Accumulators.sum("ProductLowPriceSum", 1))
        ));
        System.out.println("Статистика товаров для каждого магазина: ");

        productsPciceStatistcs.forEach((Consumer<Document>) doc -> {
            System.out.println(doc.toJson(writerSettings));
        });
        productsPriceLTs100.forEach((Consumer<Document>) doc -> {
            System.out.println(doc.toJson(writerSettings));
        });
    }

    public static boolean checkExistenceProduct(String productName, MongoCollection<Document> collectionProducts){
        if (collectionProducts.find(eq("Product", productName)).first() != null) {
            return true;
        }
        System.out.println("<!>Добавьте сначала товар в список товаров");
        return false;
    }

    public static boolean checkExistenceStore (String storeName, MongoCollection<Document> collectionStores){
        if (collectionStores.find(eq("Store", storeName)).first() != null) {
            return true;
        }
        System.out.println("<!>Добавьте сначала магазин в список магазинов");
        return false;
    }

    public static void writeMemberOfCollection(MongoCollection<Document> collection) {
        collection.find().forEach((Consumer<Document>) doc -> {
            System.out.println(doc.toJson(writerSettings));
        });
    }


}
