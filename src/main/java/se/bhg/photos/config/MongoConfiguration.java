package se.bhg.photos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "se.bhg.photos.repository")
public class MongoConfiguration extends AbstractMongoConfiguration {
    @Value("${mongo.url}")
    private String url;

    @Value("${mongo.dbname}")
    private String dbName;

    @Value("${mongo.user}")
    private String mongoUser;

    @Value("${mongo.password}")
    private String mongoPassword;

    @Override
    public String getDatabaseName() {
        return dbName;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(url);
    }

    @Bean
    @Override
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        MappingMongoConverter mmc = super.mappingMongoConverter();
        mmc.setMapKeyDotReplacement("<dot>");
        return mmc;
    }
}
