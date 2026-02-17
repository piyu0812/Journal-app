package net.engineeringdigest.journalApp.cache;

import jakarta.annotation.PostConstruct;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        weather_api;
    }
    public Map<String,String> app_Cache;

    @Autowired
    ConfigJournalApp configJournalApp;


    @PostConstruct
    public void init(){
        app_Cache=new HashMap<>();
        List<ConfigJournalAppEntity> all=configJournalApp.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity:all){
            app_Cache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }
    }
}
