package tech.limeacademy.blockchain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.limeacademy.blockchain.utils.BlockUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Block {
    private static Long nonceGenerate = 0l;
    @Id
    @Column(updatable = false)
    private String blockHash;

    @Column(updatable = false)
    private String previousHash;
    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BlockTransaction> data;

    @Column(updatable = false)
    private Long timestamp;

    @Column(updatable = false)
    private Long nonce;

    public Block(final String previousHash, final List<BlockTransaction> data) {
        prepareData(data);
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.nonce = nonceGenerate++;
        this.blockHash = BlockUtils.calculateBlockHash(this);
    }

    private void prepareData(final List<BlockTransaction> data) {
        this.data = data;
        if (Objects.nonNull(this.data)) {
            this.data.forEach(d -> d.setBlock(this));
        }
    }
}
