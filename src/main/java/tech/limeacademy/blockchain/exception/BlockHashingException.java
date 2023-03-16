package tech.limeacademy.blockchain.exception;

public class BlockHashingException extends RuntimeException{
    public BlockHashingException() {
    }

    public BlockHashingException(String message) {
        super(message);
    }
}