package tech.limeacademy.blockchain.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tech.limeacademy.blockchain.network.BlockchainNetwork;
import tech.limeacademy.blockchain.model.BlockTransaction;
import tech.limeacademy.blockchain.service.BlockService;

import java.util.List;

@Component
public class DataInit implements CommandLineRunner {
    @Autowired
    private BlockService blockService;
    @Autowired
    private BlockchainNetwork blockchainNetwork;

    public static final BlockTransaction blockTransaction1 = new BlockTransaction("me", "limechain", "message 1");
    public static final BlockTransaction blockTransaction2 = new BlockTransaction("limechain", "me", "message 2");
    public static final BlockTransaction blockTransaction3 = new BlockTransaction("limechain", "me", "message 3");
    public static final BlockTransaction blockTransaction4 = new BlockTransaction("me", "limechain", "message 4");

    @Override
    @Transactional
    public void run(String... args) {
        blockchainNetwork.addBlockForData(List.of(blockTransaction1));
        blockchainNetwork.addBlockForData(List.of(blockTransaction2));
        blockchainNetwork.addBlockForData(List.of(blockTransaction3, blockTransaction4));
    }
}