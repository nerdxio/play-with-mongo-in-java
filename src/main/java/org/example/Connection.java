/**
 * @author Hassan Refaat <hassan.refaat.dev@gmail.com>
 * @Created 4/15/2023 11:54 AM
 */
package org.example;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
public class Connection {
    public static void main(String[] args) {
        String uri = "mongodb+srv://the_nerd_dev:<password>@cluster0.2ihjgag.mongodb.net/?retryWrites=true&w=majority";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings =  MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .serverApi(serverApi)
                .build();

        try(MongoClient mongoClient = MongoClients.create()){
            MongoDatabase database = mongoClient.getDatabase("test");

            try{
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document result = database.runCommand(command);
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException me) {
                System.err.println(me.getMessage());
            }
        }
    }
}
