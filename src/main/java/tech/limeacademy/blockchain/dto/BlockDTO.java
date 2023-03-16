package tech.limeacademy.blockchain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BlockDTO {
    private Long timestamp;
    private List<TransactionDataDTO> data;
    private String currentBlockHash;
    private String previousBlockHash;
    private Long nonce;
}
