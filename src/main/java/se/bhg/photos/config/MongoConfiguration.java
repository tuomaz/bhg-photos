package se.bhg.photos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration
@EnableMongoRepositories(basePackages = "se.bhg.photos.repository")
public class MongoConfiguration {

    @Value("${mongo.host}")
    private String mongoURL;

    @Value("${mongo.db}")
    private String mongoDB;
    
    @Value("${mongo.user}")
    private String mongoUser;
    
    @Value("${mongo.password}")
    private String mongoPassword;

    private MongoDbFactory mongoDbFactory() throws Exception {
        //MongoClientOptions mongoOptions = MongoClientOptions.builder().connectionsPerHost(200).build();
        
        MongoClient mongo = null;
        mongo = new MongoClient(mongoURL);
        return new SimpleMongoDbFactory(mongo, mongoDB);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        mongoTemplate.setWriteConcern(WriteConcern.NORMAL);
        return mongoTemplate;
    }

}
