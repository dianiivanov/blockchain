package tech.limeacademy.blockchain.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.limeacademy.blockchain.dto.BlockDTO;
import tech.limeacademy.blockchain.dto.TransactionDataDTO;
import tech.limeacademy.blockchain.exception.BlockHashingException;
import tech.limeacademy.blockchain.exception.NotExistingEntityException;
import tech.limeacademy.blockchain.exception.UnupdatableEntityException;
import tech.limeacademy.blockchain.model.Block;
import tech.limeacademy.blockchain.service.BlockService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("blocks")
public class BlockController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BlockService blockService;

    @GetMapping("/all")
    public List<BlockDTO> getAllBlocks() {
        List<Block> allBlocks = blockService.getAllBlocks();

        List<BlockDTO> resultList = allBlocks.stream()
                .map(block -> modelMapper.map(block, BlockDTO.class))
                .collect(Collectors.toList());

        return resultList;
    }

    @GetMapping("/{hash}")
    public BlockDTO getBlockByHash(@PathVariable String hash) {
        return modelMapper.map(blockService.getBlockByHash(hash), BlockDTO.class);
    }

    @ExceptionHandler({ BlockHashingException.class, NotExistingEntityException.class, UnupdatableEntityException.class})
    public String handleException(RuntimeException ex) {
        return ex.getMessage();
    }
}
