package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "app_Config" )
@Data
@NoArgsConstructor
//  @Data is equivalent to @Getter and @Setter
public class ConfigJournalAppEntity {
    private String key;
    private String value;

}

