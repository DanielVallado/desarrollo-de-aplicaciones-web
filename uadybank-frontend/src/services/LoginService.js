import axios from "axios";

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
    axios
      .get(`${API_URL}/logout`, { withCredentials: true })
      .then(() => {
        document.cookie =
          "client=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        document.cookie =
          "administrator=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
      })
      .catch((error) => {
        console.error(error);
      });
  }
}

export default new LoginService();
