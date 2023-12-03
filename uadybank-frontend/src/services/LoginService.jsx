import axios from "axios";
import Account from "/src/models/Account";
import Client from "/src/models/Client";
import Administrator from "/src/models/Administrator";

const API_URL = "http://localhost:8080/login";

class LoginService {
  async login(email, password) {
    const response = await axios.post(
      API_URL,
      {
        email,
        password,
      },
      { withCredentials: true }
    );

    return response.data;
  }

  logout() {
    console.log("logout");
    axios
      .get(`${API_URL}/logout`, { withCredentials: true })
      .then((response) => {
        console.log(response);
        document.cookie =
          "user=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
      })
      .catch((error) => {
        console.error(error);
      });
  }
}

function createAccount(account) {
  return new Account(
    account.idAccount,
    {
      role: account.client.role,
      creationDate: account.client.creationDate,
      name: account.client.name,
      email: account.client.email,
      matricula: account.client.matricula,
      password: account.client.password,
      phoneNumber: account.client.phoneNumber,
      address: account.client.address,
      verified: account.client.verified,
    },
    [
      {
        cardType: account.cards[0].cardType,
        balance: account.cards[0].balance,
        vip: account.cards[0].vip,
      },
    ]
  );
}

function createClient(client) {
  return new Client(
    client.role,
    client.creationDate,
    client.name,
    client.email,
    client.matricula,
    client.password,
    client.phoneNumber,
    client.address,
    client.verified
  );
}

function createAdministrator(administrator) {
  return new Administrator(
    administrator.role,
    administrator.creationDate,
    administrator.idEmployee,
    administrator.name,
    administrator.email,
    administrator.password,
    administrator.phoneNumber,
    administrator.address,
    administrator.verified
  );
}

export default new LoginService();
