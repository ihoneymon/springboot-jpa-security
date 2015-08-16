package kr.pe.ihoney.springboot.jpandsecurity.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.pe.ihoney.springboot.jpandsecurity.domain.system.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
