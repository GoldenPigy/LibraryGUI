package com.example.library.Repository;

import com.example.library.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String > {
}
