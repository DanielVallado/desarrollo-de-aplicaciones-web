import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

class LoginService {
  async login(username, password) {
    const response = await axios.post(API_URL + "signin", {
      username,
      password,
    });
    if (response.data.accessToken) {
      localStorage.setItem("user", JSON.stringify(response.data));
      localStorage.setItem("username", username);
    }
    return response.data;
  }

  logout() {
    localStorage.removeItem("user");
    localStorage.removeItem("username");
  }

  register(username, email, password) {
    return axios.post(API_URL + "signup", {
      username,
      email,
      password,
    });
  }
}

export default new LoginService();
