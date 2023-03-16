package tech.limeacademy.blockchain.network.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.limeacademy.blockchain.model.BlockTransaction;
import tech.limeacademy.blockchain.network.BlockData;
import tech.limeacademy.blockchain.network.BlockchainNetwork;
import tech.limeacademy.blockchain.dto.BlockDTO;
import tech.limeacademy.blockchain.model.Block;
import tech.limeacademy.blockchain.service.BlockService;
import tech.limeacademy.blockchain.utils.BlockUtils;

import java.util.List;
import java.util.Objects;

@Component
public class BlockchainNetworkImpl implements BlockchainNetwork {
    @Autowired
    private BlockService blockService;

    @Autowired
    private ModelMapper modelMapper;

    private BlockData blockchain;

    @Override
    public void addBlockForData(List<BlockTransaction> blockTransactions) {
        Block block = new Block(Objects.isNull(blockchain)
                ? null
                : blockchain.getBlockDTO().getCurrentBlockHash(), blockTransactions);

        if (blockchain == null) {
            addGenesisBlock(block);
        } else if (isBlockValid(block)) {
            addBlock(block);
        }
    }

    private void addGenesisBlock(Block block) {
        blockService.saveBlock(block);
        blockchain = new BlockData(modelMapper.map(block, BlockDTO.class));
    }

    private void addBlock(Block block) {
        blockService.saveBlock(block);
        BlockDTO blockDTO = modelMapper.map(block, BlockDTO.class);

        BlockData newBlockData = new BlockData(blockDTO);
        newBlockData.setPreviousBlockData(blockchain);
        blockchain.setNextBlockData(newBlockData);
        blockchain = newBlockData;
    }

    private boolean isBlockValid(Block block) {
        Block previousBlock = blockService.getBlockByHash(block.getPreviousHash());
        return block.getBlockHash().equals(BlockUtils.calculateBlockHash(block)) &&
                previousBlock.getBlockHash().equals(BlockUtils.calculateBlockHash(previousBlock));
    }
}
