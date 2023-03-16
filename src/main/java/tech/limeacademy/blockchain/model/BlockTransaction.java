package tech.limeacademy.blockchain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class BlockTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private String sender;

    @Column(updatable = false)
    private String receiver;

    @Column(updatable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "block_hash", updatable = false)
    private Block block;

    public BlockTransaction(String from, String to, String message) {
        this.sender = from;
        this.receiver = to;
        this.message = message;
    }

    @Override
    public String toString() {
        return sender + receiver + message;
    }
}
