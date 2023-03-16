package tech.limeacademy.blockchain.exception;

public class UnupdatableEntityException extends RuntimeException{
    public UnupdatableEntityException() {
    }

    public UnupdatableEntityException(String message) {
        super(message);
    }
}