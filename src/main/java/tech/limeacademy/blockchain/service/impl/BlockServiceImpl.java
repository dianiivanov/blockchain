package tech.limeacademy.blockchain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.limeacademy.blockchain.dao.BlockRepository;
import tech.limeacademy.blockchain.exception.NotExistingEntityException;
import tech.limeacademy.blockchain.exception.UnupdatableEntityException;
import tech.limeacademy.blockchain.model.Block;
import tech.limeacademy.blockchain.service.BlockService;

import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {
    private static final String NOT_EXISTING_BLOCK_HASH_MESSAGE = "Block with hash = '%s' does not exist";

    @Autowired
    BlockRepository blockRepository;

    @Override
    public List<Block> getAllBlocks() {
        return blockRepository.findAllByOrderByTimestamp();
    }

    @Override
    public Block getBlockByHash(String hash) {
        return blockRepository.findById(hash).orElseThrow(() -> new NotExistingEntityException(
                String.format(NOT_EXISTING_BLOCK_HASH_MESSAGE, hash)));
    }

    @Override
    public Long getBlocksCount() {
        return blockRepository.count();
    }

    @Override
    public void saveBlock(Block block) {
        if (blockRepository.findById(block.getBlockHash()).isPresent()) {
            throw new UnupdatableEntityException("Update of the blocks/transactions of the blockchain is restricted ");
        }
        blockRepository.save(block);
    }
}
