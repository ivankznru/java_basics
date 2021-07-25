import org.redisson.Redisson;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

/**
 * Класс RedisStorage.
 * Работа с котейнером Redis.
 * Домашняя работа 18.1.
 * Задание 1.
 * Создаём контейнер Redis командой docker run --rm --name skill-redis -p 6379:6379 -d redis.
 */
public class RedisStorage {

    private RedissonClient redissonClient;
    private RSet<String> todoSet;

    /**
     * Метод init().
     * Подключение к docker-контейнеру Redis.
     * Порт: 6379
     */
    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redissonClient = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Failed to connect to Redis");
            System.out.println(Exc.getMessage());
        }
        todoSet = redissonClient.getSet("todoSet");
    }

    /**
     * Метод addNewTodo.
     * Добавление нового дела.
     * @param todo новое дело.
     */
    public void addNewTodo(String todo) {
        todoSet.add(todo);
        System.out.println("Added new todo: " + todo);
    }

    /**
     * Метод doTodo.
     * Выполнение нового дела путем удаления из контейнера Redis.
     * @param todo дело.
     */
    public void doTodo(String todo) {
        todoSet.remove(todo);
        System.out.println(todo + " -> done 100%.");
    }

}
