import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;

@Getter
public class TaskMongoClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 27017;
    private static final String DB = "local";
    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;

    public TaskMongoClient() {
       this.mongoClient = new MongoClient(HOST, PORT);
       this.mongoDatabase = mongoClient.getDatabase(DB);
    }

}
