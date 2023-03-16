package tech.limeacademy.blockchain.exception;

public class NotExistingEntityException extends RuntimeException{
    public NotExistingEntityException() {
    }

    public NotExistingEntityException(String message) {
        super(message);
    }
}