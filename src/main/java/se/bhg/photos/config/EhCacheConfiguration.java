package se.bhg.photos.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;

@EnableCaching
public class EhCacheConfiguration {
    @Bean
    CacheManager getCacheManager() {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(getEhCache().getObject());
        return ehCacheCacheManager;
    }

    @Bean
    EhCacheManagerFactoryBean getEhCache() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        return ehCacheManagerFactoryBean;
    }
}
