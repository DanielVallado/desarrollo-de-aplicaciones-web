import axios from "axios";

const API_URL = "http://localhost:8080/card";

class CardService {
  static async getAllCards() {
    const response = await axios.get(`${API_URL}`, {
      withCredentials: true,
    });
    return response.data;
  }

  static async getCardById(cardId) {
    const response = await axios.get(`${API_URL}/${cardId}`, {
      withCredentials: true,
    });
    return response.data;
  }

  static async createCard(cardData) {
    const response = await axios.post(`${API_URL}`, cardData, {
      withCredentials: true,
    });
    return response.data;
  }

  static async updateCard(cardId, cardData) {
    const response = await axios.put(`${API_URL}/${cardId}`, cardData, {
      withCredentials: true,
    });
    return response.data;
  }

  static async deleteCard(cardId) {
    const response = await axios.delete(`${API_URL}/${cardId}`, {
      withCredentials: true,
    });
    return response.data;
  }

  static async getTransactions(cardId) {
    const response = await axios.get(`${API_URL}/${cardId}/transactions`, {
      withCredentials: true,
    });
    return response.data;
  }

  static async createTransaction(cardId, transactionData) {
    const response = await axios.post(`${API_URL}/${cardId}`, transactionData, {
      withCredentials: true,
    });
    return response.data;
  }
}

export default CardService;
