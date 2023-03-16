package tech.limeacademy.blockchain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDataDTO {
    private String from;
    private String to;
    private String message;
}
