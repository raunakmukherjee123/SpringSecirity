package com.example.SecurityPractice.repository;

import com.example.SecurityPractice.model.UserInfo;
import com.example.SecurityPractice.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
    Optional<UserInfo> findByName(String username);

    @Query(value = """
            SELECT u.name,
            u.email,
            u.roles
            FROM UserInfo u
            WHERE u.id=:id
            """)
    UserProjection getUserById(@Param("id") Integer id);
}
