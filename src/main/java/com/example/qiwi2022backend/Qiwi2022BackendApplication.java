package com.example.qiwi2022backend;

import com.example.qiwi2022backend.ApiQiwi.ApiToken;
import com.example.qiwi2022backend.DTO.TokenInfoDTO;
import com.example.qiwi2022backend.Model.Shop;
import com.example.qiwi2022backend.Repository.ShopRepository;
import com.example.qiwi2022backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class Qiwi2022BackendApplication implements CommandLineRunner {
	@Autowired
	private ApiToken apiToken;
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Qiwi2022BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		final Shop shop = new Shop("ozon","3bdd4091-3aaa-49c5-a644-19abb9670a23","sa3khn-08");
		shopRepository.save(shop);
		final String requestId = UUID.randomUUID().toString();
		final String accountId = UUID.randomUUID().toString();
		final String phone = "78000000035";
		final TokenInfoDTO tokenInfoDTO = new TokenInfoDTO(requestId,phone,accountId);
		System.out.println(apiToken.generatePayToken(shop.getShopToken(), shop.getSiteId(),tokenInfoDTO));
	}
}
