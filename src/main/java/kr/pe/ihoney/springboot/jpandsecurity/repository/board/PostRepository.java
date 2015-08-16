package kr.pe.ihoney.springboot.jpandsecurity.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.pe.ihoney.springboot.jpandsecurity.domain.board.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
