//package com.usersecurity.repository;
//change 2
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.usersecurity.model.User;
//
//@Repository
//public interface UserRepository extends MongoRepository<User, String> {
//
//    // Method to find a user by email
//    @Query("{email: ?0}")
//    User getByEmail(String email);
//    
//    // Method to delete a user by email
//    @Query(value="{'email' : ?0}", delete = true)
//    void deleteByEmail(String email);
//    
//    // Method to find a user by username
//    User findByUsername(String username);
//    
//    // Method to check if a user exists by username
//    Boolean existsByUsername(String username);
//    
//    // Method to check if a user exists by email
//    Boolean existsByEmail(String email);
//}
package com.usersecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usersecurity.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
	User findByUsername(String username);
	void deleteByEmail(String email);
	User getByEmail(String email);
}

