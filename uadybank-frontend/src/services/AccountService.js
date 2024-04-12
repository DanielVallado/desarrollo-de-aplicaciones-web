import axios from "axios";

const API_URL = "http://localhost:8080/account";

class AccountService {
  // Método para obtener todos las cuentas
  static async getAllAccounts() {
    try {
      const response = await axios.get(`${API_URL}`, {
        withCredentials: true,
      });
      return response.data;
    } catch (error) {
      console.error("Error al obtener las cuentas: ", error);
      throw error;
    }
  }

  // Método para obtener una cuenta por matricula
  static async getAccountByMatricula() {
    try {
      const response = await axios.get(`${API_URL}/client`, {
        withCredentials: true,
      });
      return response.data;
    } catch (error) {
      console.error("Error al obtener la cuenta: ", error);
      throw error;
    }
  }

  // Método para crear una nuevo cuenta
  static async createAccount(accountData) {
    try {
      const response = await axios.post(`${API_URL}`, accountData, {
        withCredentials: true,
      });
      return response.data;
    } catch (error) {
      console.error("Error al crear la cuenta: ", error);
      throw error;
    }
  }

  // Método para actualizar una cuenta existente
  static async updateAccount(accountId, accountData) {
    try {
      const response = await axios.put(`${API_URL}/${accountId}`, accountData, {
        withCredentials: true,
      });
      return response.data;
    } catch (error) {
      console.error("Error al actualizar la cuenta: ", error);
      throw error;
    }
  }

  // Método para eliminar una cuenta
  static async deleteAccount(accountId) {
    try {
      const response = await axios.delete(`${API_URL}/${accountId}`, {
        withCredentials: true,
      });
      return response.data;
    } catch (error) {
      console.error("Error al eliminar la cuenta: ", error);
      throw error;
    }
  }
}

export default AccountService;
