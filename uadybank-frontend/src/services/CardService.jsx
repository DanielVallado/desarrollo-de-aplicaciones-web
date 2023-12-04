import axios from "axios";

const API_URL = "http://localhost:8080/card";

class CardService {
  // Método para obtener todos las tarjetas
  static async getAllCards() {
    try {
      const response = await axios.get(`${API_URL}`);
      return response.data;
    } catch (error) {
      console.error("Error al obtener las tarjetas: ", error);
      throw error;
    }
  }

  // Método para obtener una tarjeta por id
  static async getCardById(cardId) {
    try {
      console.log(cardId);
      const response = await axios.get(`${API_URL}/${cardId}`);
      return response.data;
    } catch (error) {
      console.error("Error al obtener la tarjeta: ", error);
      throw error;
    }
  }

  // Método para crear una nuevo tarjeta
  static async createCard(cardData) {
    try {
      const response = await axios.post(`${API_URL}`, cardData);
      return response.data;
    } catch (error) {
      console.error("Error al crear la tarjeta: ", error);
      throw error;
    }
  }

  // Método para actualizar una tarjeta existente
  static async updateCard(cardId, cardData) {
    try {
      const response = await axios.put(`${API_URL}/${cardId}`, cardData);
      return response.data;
    } catch (error) {
      console.error("Error al actualizar la tarjeta: ", error);
      throw error;
    }
  }

  // Método para eliminar una tarjeta
  static async deleteCard(cardId) {
    try {
      const response = await axios.delete(`${API_URL}/${cardId}`);
      return response.data;
    } catch (error) {
      console.error("Error al eliminar la tarjeta: ", error);
      throw error;
    }
  }
}

export default CardService;
