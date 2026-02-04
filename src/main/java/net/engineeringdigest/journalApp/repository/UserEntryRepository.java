package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;

public interface UserEntryRepository extends MongoRepository<User, ObjectId>{
    User findByuserName(String userName);
}

// Controller--> Service--> Repository