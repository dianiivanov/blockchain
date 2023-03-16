package tech.limeacademy.blockchain.network;

import tech.limeacademy.blockchain.model.BlockTransaction;

import java.util.List;

public interface BlockchainNetwork {
    void addBlockForData(List<BlockTransaction> blockTransactions);
}
