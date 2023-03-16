package tech.limeacademy.blockchain.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.limeacademy.blockchain.network.BlockchainNetwork;
import tech.limeacademy.blockchain.dto.BlockDTO;
import tech.limeacademy.blockchain.dto.TransactionDataDTO;
import tech.limeacademy.blockchain.network.impl.BlockchainNetworkImpl;
import tech.limeacademy.blockchain.model.Block;
import tech.limeacademy.blockchain.model.BlockTransaction;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Block, BlockDTO> propertyMapper = modelMapper.createTypeMap(Block.class, BlockDTO.class);
        propertyMapper.addMappings(
                mapper -> {
                    mapper.map(Block::getTimestamp, BlockDTO::setTimestamp);
                    mapper.map(Block::getBlockHash, BlockDTO::setCurrentBlockHash);
                    mapper.map(Block::getPreviousHash, BlockDTO::setPreviousBlockHash);
                    mapper.map(Block::getNonce, BlockDTO::setNonce);
                    mapper.using(getTransactionsConverter()).map(Block::getData, BlockDTO::setData);
                }
        );
        return modelMapper;
    }

    private Converter<List<BlockTransaction>, List<TransactionDataDTO>> getTransactionsConverter() {
        return c -> c.getSource().stream().map(
                blockTransaction -> new TransactionDataDTO(blockTransaction.getSender(), blockTransaction.getReceiver(),
                        blockTransaction.getMessage())).collect(Collectors.toList());
    }
}