package com.uadybank.uadybankbackend;

import com.uadybank.uadybankbackend.dto.*;
import com.uadybank.uadybankbackend.entity.*;
import com.uadybank.uadybankbackend.enums.CardType;
import com.uadybank.uadybankbackend.enums.TransactionType;
import com.uadybank.uadybankbackend.mapper.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class MapperTest {

    private Account account;
    private Administrator administrator;
    private Card card;
    private Client client;
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        // Create and set fields for a Client
        client = new Client();
        client.setMatricula("12345678");
        client.setAddress("123 Main St");
        client.setName("John Doe");
        client.setEmail("john.doe@example.com");
        client.setPassword("password");
        client.setPhoneNumber("1234567890");
        client.setVerified(true);
        client.setCreationDate(LocalDateTime.now());

        // Create and set fields for a Card
        card = new Card();
        card.setIdCard("1234567890");
        card.setCardType(CardType.CLASSIC);
        card.setBalance(new BigDecimal("1000.00"));
        card.setVip(true);

        // Create and set fields for an Account
        account = new Account();
        account.setIdAccount(1L);
        account.setClient(client);
        account.setCards(List.of(card));
        account.setStatus(true);

        // Create and set fields for a Transaction
        transaction = new Transaction();
        transaction.setIdTransaction(1L);
        transaction.setAmount(new BigDecimal("100.00"));
        transaction.setDescription("Test transaction");
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setDestination("1234567890");

        // Create and set fields for an Administrator
        administrator = new Administrator();
        administrator.setIdEmployee(1L);
        administrator.setName("Jane Doe");
        administrator.setEmail("jane.doe@example.com");
        administrator.setPassword("password");
        administrator.setPhoneNumber("0987654321");
        administrator.setVerified(true);
        administrator.setCreationDate(LocalDateTime.now());
    }

    @Test
    public void testAccountMapper() {
        AccountDTO dto = AccountMapper.mapToDTO(account);
        Assertions.assertEquals(account.getIdAccount(), dto.getIdAccount());
        Assertions.assertEquals(account.getCards().size(), dto.getCards().size());
    }

    @Test
    public void testAdministratorMapper() {
        AdministratorDTO dto = AdministratorMapper.mapToDTO(administrator);
        Assertions.assertEquals(administrator.getIdEmployee(), dto.getIdEmployee());
        Assertions.assertEquals(administrator.getName(), dto.getName());
        Assertions.assertEquals(administrator.getEmail(), dto.getEmail());
        Assertions.assertEquals(administrator.getPhoneNumber(), dto.getPhoneNumber());
        Assertions.assertEquals(administrator.isVerified(), dto.isVerified());
    }

    @Test
    public void testCardMapper() {
        CardDTO dto = CardMapper.mapToDTO(card);
        Assertions.assertEquals(card.getIdCard(), dto.getIdCard());
        Assertions.assertEquals(card.getCardType().toString(), dto.getCardType());
        Assertions.assertEquals(card.getBalance(), dto.getBalance());
        Assertions.assertEquals(card.isVip(), dto.isVip());
    }

    @Test
    public void testClientMapper() {
        ClientDTO dto = ClientMapper.mapToDTO(client);
        Assertions.assertEquals(client.getMatricula(), dto.getMatricula());
        Assertions.assertEquals(client.getAddress(), dto.getAddress());
        Assertions.assertEquals(client.getName(), dto.getName());
        Assertions.assertEquals(client.getEmail(), dto.getEmail());
        Assertions.assertEquals(client.getPhoneNumber(), dto.getPhoneNumber());
        Assertions.assertEquals(client.isVerified(), dto.isVerified());
    }

    @Test
    public void testTransactionMapper() {
        TransactionDTO dto = TransactionMapper.mapToDTO(transaction);
        Assertions.assertEquals(transaction.getIdTransaction(), dto.getIdTransaction());
        Assertions.assertEquals(transaction.getAmount(), dto.getAmount());
        Assertions.assertEquals(transaction.getDescription(), dto.getDescription());
        Assertions.assertEquals(transaction.getTransactionType().toString(), dto.getTransactionType());
        Assertions.assertEquals(transaction.getTransactionDate(), dto.getTransactionDate());
        Assertions.assertEquals(transaction.getDestination(), dto.getDestinationCard());
    }

}
