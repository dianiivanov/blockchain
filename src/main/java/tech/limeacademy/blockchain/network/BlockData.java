package tech.limeacademy.blockchain.network;

import lombok.Data;
import tech.limeacademy.blockchain.dto.BlockDTO;

//Double linked list implementation for the needs of the blockchain
@Data
public class BlockData {
    private BlockData previousBlockData;
    private BlockDTO blockDTO;
    private BlockData nextBlockData;
    public BlockData(BlockDTO blockDTO) {
        this.blockDTO = blockDTO;
    }
}
