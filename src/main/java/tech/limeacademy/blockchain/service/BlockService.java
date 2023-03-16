package tech.limeacademy.blockchain.service;

import tech.limeacademy.blockchain.model.Block;

import java.util.List;

public interface BlockService {
    List<Block> getAllBlocks();
    Block getBlockByHash(final String hash);
    Long getBlocksCount();
    void saveBlock(final Block block);
}
