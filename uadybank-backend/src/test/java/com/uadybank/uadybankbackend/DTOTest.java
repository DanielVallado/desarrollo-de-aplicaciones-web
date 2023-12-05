package com.uadybank.uadybankbackend;

import com.uadybank.uadybankbackend.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class DTOTest {

    private AccountDTO accountDTO;
    private AdministratorDTO adminDTO;
    private CardDTO cardDTO;
    private ClientDTO clientDTO;
    private ErrorDTO errorDTO;
    private LoginDTO loginDTO;
    private TransactionDTO transactionDTO;

    @BeforeEach
    public void setUp() {
        accountDTO = new AccountDTO();
        accountDTO.setIdAccount(1L);
        accountDTO.setClient(new ClientDTO());
        accountDTO.setCards(Arrays.asList(new CardDTO(), new CardDTO()));

        adminDTO = new AdministratorDTO();
        adminDTO.setRole("admin");
        adminDTO.setCreationDate("2023-01-01T00:00:00");
        adminDTO.setIdEmployee(1L);
        adminDTO.setName("John Doe");
        adminDTO.setEmail("john.doe@example.com");
        adminDTO.setPassword("password");
        adminDTO.setPhoneNumber("1234567890");
        adminDTO.setVerified(true);

        cardDTO = new CardDTO();
        cardDTO.setIdCard("1234567890");
        cardDTO.setCardType("debit");
        cardDTO.setBalance(new BigDecimal("1000.00"));
        cardDTO.setVip(true);

        clientDTO = new ClientDTO();
        clientDTO.setRole("client");
        clientDTO.setCreationDate("2023-01-01T00:00:00");
        clientDTO.setMatricula("123456");
        clientDTO.setName("John Doe");
        clientDTO.setEmail("john.doe@example.com");
        clientDTO.setPassword("password");
        clientDTO.setPhoneNumber("1234567890");
        clientDTO.setAddress("123 Main St");
        clientDTO.setVerified(true);

        errorDTO = new ErrorDTO();
        errorDTO.setRole("client");
        errorDTO.setMessage("An error occurred");

        loginDTO = new LoginDTO();
        loginDTO.setEmail("john.doe@example.com");
        loginDTO.setPassword("password");

        transactionDTO = new TransactionDTO();
        transactionDTO.setIdTransaction(1L);
        transactionDTO.setAmount(new BigDecimal("100.00"));
        transactionDTO.setDescription("Test transaction");
        transactionDTO.setTransactionType("debit");
        transactionDTO.setTransactionDate(LocalDateTime.now());
        transactionDTO.setDestinationCard("1234567890");
    }

    @Test
    public void testAccountDTO() {
        assertThat(accountDTO.getIdAccount()).isEqualTo(1L);
        assertThat(accountDTO.getClient()).isInstanceOf(ClientDTO.class);
        assertThat(accountDTO.getCards()).hasSize(2).allMatch(Objects::nonNull);
    }

    @Test
    public void testAdministratorDTO() {
        assertThat(adminDTO.getRole()).isEqualTo("admin");
        assertThat(adminDTO.getCreationDate()).isEqualTo("2023-01-01T00:00:00");
        assertThat(adminDTO.getIdEmployee()).isEqualTo(1L);
        assertThat(adminDTO.getName()).isEqualTo("John Doe");
        assertThat(adminDTO.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(adminDTO.getPassword()).isEqualTo("password");
        assertThat(adminDTO.getPhoneNumber()).isEqualTo("1234567890");
        assertThat(adminDTO.isVerified()).isEqualTo(true);
    }

    @Test
    public void testCardDTO() {
        assertThat(cardDTO.getIdCard()).isEqualTo("1234567890");
        assertThat(cardDTO.getCardType()).isEqualTo("debit");
        assertThat(cardDTO.getBalance()).isEqualTo(new BigDecimal("1000.00"));
        assertThat(cardDTO.isVip()).isEqualTo(true);
    }

    @Test
    public void testClientDTO() {
        assertThat(clientDTO.getRole()).isEqualTo("client");
        assertThat(clientDTO.getCreationDate()).isEqualTo("2023-01-01T00:00:00");
        assertThat(clientDTO.getMatricula()).isEqualTo("123456");
        assertThat(clientDTO.getName()).isEqualTo("John Doe");
        assertThat(clientDTO.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(clientDTO.getPassword()).isEqualTo("password");
        assertThat(clientDTO.getPhoneNumber()).isEqualTo("1234567890");
        assertThat(clientDTO.getAddress()).isEqualTo("123 Main St");
        assertThat(clientDTO.isVerified()).isEqualTo(true);
    }

    @Test
    public void testErrorDTO() {
        assertThat(errorDTO.getRole()).isEqualTo("client");
        assertThat(errorDTO.getMessage()).isEqualTo("An error occurred");
    }

    @Test
    public void testLoginDTO() {
        assertThat(loginDTO.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(loginDTO.getPassword()).isEqualTo("password");
    }

    @Test
    public void testTransactionDTO() {
        assertThat(transactionDTO.getIdTransaction()).isEqualTo(1L);
        assertThat(transactionDTO.getAmount()).isEqualTo(new BigDecimal("100.00"));
        assertThat(transactionDTO.getDescription()).isEqualTo("Test transaction");
        assertThat(transactionDTO.getTransactionType()).isEqualTo("debit");
        assertThat(transactionDTO.getTransactionDate()).isInstanceOf(LocalDateTime.class);
        assertThat(transactionDTO.getDestinationCard()).isEqualTo("1234567890");
    }

}