package dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import entities.DayForecast;
import redis.clients.jedis.Jedis;

public class Database {

    private static final String MONGO_CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "forecast";
    private static final String COLLECTION_NAME = "forecastData";
    private static final String REDIS_KEY = "weatherForecast";

    static {
        ArrayList<DayForecast> initialForecast = new ArrayList<>();

        initialForecast.add(new DayForecast(LocalDate.now(), 20));
        initialForecast.add(new DayForecast(LocalDate.now().plusDays(1), 22));
        initialForecast.add(new DayForecast(LocalDate.now().plusDays(2), 24));

        updateWeatherForecast(initialForecast);
    }

    public static ArrayList<DayForecast> getWeatherForecast() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<DayForecast> result = new ArrayList<>();

        try (MongoClient mongoClient = MongoClients.create(MONGO_CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            FindIterable<Document> data = collection.find();

            for (Document document : data) {
                String dateAsString = document.getString("date");
                int temperature = document.getInteger("temperature");

                LocalDate date = LocalDate.parse(dateAsString);

                result.add(new DayForecast(date, temperature));
            }
        }

        return result;
    }

    public static void updateWeatherForecast(ArrayList<DayForecast> newForecast) {
        try (MongoClient mongoClient = MongoClients.create(MONGO_CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            collection.deleteMany(new Document());

            for (DayForecast dayForecast : newForecast) {
                Document document = new Document();

                document.append("date", dayForecast.getDate().toString());
                document.append("temperature", dayForecast.getTemperature());

                collection.insertOne(document);
            }
        }

        Jedis redis = new Jedis();
        redis.del(REDIS_KEY);
        redis.close();
    }
}