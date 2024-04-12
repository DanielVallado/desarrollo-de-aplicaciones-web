import axios from "axios";

const API_URL = "http://localhost:8080/client";

class ClientService {
  static async getAllClients() {
    try {
      const response = await axios.get(API_URL, { withCredentials: true });
      return response.data;
    } catch (error) {
      console.log(error);
      throw error;
    }
  }

  static async getClientById(id) {
    try {
      const response = await axios.get(`${API_URL}/${id}`, {
        withCredentials: true,
      });
      return response.data;
    } catch (error) {
      console.log(error);
      throw error;
    }
  }

  static async createClient(clientData) {
    try {
      const response = await axios.post(API_URL, clientData, {
        withCredentials: true,
      });
      return response.data;
    } catch (error) {
      console.log(error);
      throw error;
    }
  }

  static async updateClient(id, clientData) {
    try {
      const response = await axios.put(`${API_URL}/${id}`, clientData, {
        withCredentials: true,
      });
      return response.data;
    } catch (error) {
      console.log(error);
      throw error;
    }
  }

  static async deleteClient(id) {
    try {
      const response = await axios.delete(`${API_URL}/${id}`, {
        withCredentials: true,
      });
      return response.data;
    } catch (error) {
      console.log(error);
      throw error;
    }
  }
}

export default ClientService;
