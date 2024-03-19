package com.rootincode.twitterapi.Model.Repository;

import com.rootincode.twitterapi.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {


}
