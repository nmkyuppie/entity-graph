package com.natwest.mmm.user.application.domain.user;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface UserRepository extends JpaRepository<User, Long> {

//    @EntityGraph(attributePaths = {"profession"})
    Page<User> findAll(Pageable pageable);

    @Query(
            """
           SELECT
            new com.natwest.mmm.user.application.domain.user.UserDTO(u.id, u.name, u.age, u.email,u.createdAt,u.updatedAt)
           FROM User u
        """)
    Page<UserDTO> findUsers(Pageable pageable);

    @Query(
            """
           SELECT
            new com.natwest.mmm.user.application.domain.user.UserDTO(u.id, u.name, u.age, u.email,u.createdAt,u.updatedAt)
           FROM User u
           WHERE u.email = ?1
        """)
    Optional<UserDTO> findUserByEmail(String email);
}
