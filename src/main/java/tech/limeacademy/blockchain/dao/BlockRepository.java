package tech.limeacademy.blockchain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.limeacademy.blockchain.model.Block;

import java.util.List;
import java.util.Optional;

public interface  BlockRepository extends JpaRepository<Block, String> {
//    Optional<Block> findByHash(String hash);
    List<Block> findAllByOrderByTimestamp();
}
