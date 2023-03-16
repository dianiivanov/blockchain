package tech.limeacademy.blockchain;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.limeacademy.blockchain.model.Block;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootApplication
public class BlockchainApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockchainApplication.class, args);
//		Date d = new Date();
//		System.out.println("4567890======================================================567890");
//		System.out.println(d.getTime());
//
//		Timestamp ts = new Timestamp(d.getTime());
//		System.out.println(ts.getTime());
//		System.out.println("4567890======================================================567890");
//		System.out.println(d.getTime());

	}

}
