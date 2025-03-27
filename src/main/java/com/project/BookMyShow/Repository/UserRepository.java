package com.project.BookMyShow.Repository;

import com.project.BookMyShow.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends  JpaRepository<User,Long> {
}
