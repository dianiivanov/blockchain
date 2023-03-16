package tech.limeacademy.blockchain.utils;

import tech.limeacademy.blockchain.exception.BlockHashingException;
import tech.limeacademy.blockchain.model.Block;
import tech.limeacademy.blockchain.model.BlockTransaction;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public class BlockUtils {

    private BlockUtils() {
    }

    public static String calculateBlockHash(Block block) {
        StringBuilder dataToHashBuilder = new StringBuilder(Objects.isNull(block.getPreviousHash()) ? "" : block.getPreviousHash())
                .append(block.getTimestamp())
                .append(block.getNonce())
                .append(Objects.isNull(block.getData())
                        ? null
                        : block.getData().stream().map(BlockTransaction::toString).reduce("", String::concat));

        byte[] bytes = digest(dataToHashBuilder);
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    private static byte[] digest(StringBuilder dataToHashBuilder) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(dataToHashBuilder.toString().getBytes(UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            throw new BlockHashingException("There is an error with the hashing");
        }
    }
}
