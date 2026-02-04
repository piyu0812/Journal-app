package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    public static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Autowired
    private UserEntryRepository userEntryRepository;
    public void saveNewUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userEntryRepository.save(user);
    }
    public void saveuser(User user)
    {
        userEntryRepository.save(user);
    }



    public List<User> getAll()
    {
        return userEntryRepository.findAll();

    }
    public Optional<User> findById(ObjectId id)
    {
        return userEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id)
    {
        userEntryRepository.deleteById(id);
    }
    public User findByUserName(String userName){
        return userEntryRepository.findByuserName(userName);
    }
}
