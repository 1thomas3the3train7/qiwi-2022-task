package com.example.qiwi2022backend;

import com.example.qiwi2022backend.DTO.AccountDTO;
import com.example.qiwi2022backend.DTO.AmountDTO;
import com.example.qiwi2022backend.DTO.PaymentMethodDTO;
import com.example.qiwi2022backend.DTO.TransactionDTO;
import com.example.qiwi2022backend.Service.TransactionService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

@SpringBootTest
class Qiwi2022BackendApplicationTests {
	private final String token = "3bdd4091-3aaa-49c5-a644-19abb9670a23";
	private final String siteId = "sa3khn-08";
	private final String payToken = "73c9585e-dda0-4caa-b314-c319ed1bcded";
	private final String accountId = "32383a6a-60b1-4ca2-913b-ac6a11fdfba6";
	private final Gson gson = new Gson();
	@Autowired
	private TransactionService transactionService;

	@Test
	void contextLoads() {
	}
	@Test
	void testTransaction(){
		DecimalFormat df = new DecimalFormat("#.00");
		String s = df.format(123);
		System.out.println(s);
		final AmountDTO amountDTO = new AmountDTO(String.valueOf(100) + ".00");
		final PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO(payToken);
		final AccountDTO accountDTO = new AccountDTO(accountId);
		final TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setAmount(amountDTO);
		transactionDTO.setPaymentMethod(paymentMethodDTO);
		transactionDTO.setCustomer(accountDTO);
		System.out.println(gson.toJson(transactionDTO));
		transactionService.doTransactionAndValid(transactionDTO,token,siteId);
	}

}
