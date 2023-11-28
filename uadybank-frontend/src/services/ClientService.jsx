import axios from "axios";

const API_URL = "http://localhost:5000/api/clients"; // Cambia la URL de acuerdo a tu API

class ClientService {
  getAllClients() {
    return axios.get(API_URL);
  }

  getClientById(id) {
    return axios.get(`${API_URL}/${id}`);
  }

  createClient(clientData) {
    return axios.post(API_URL, clientData);
  }

  updateClient(id, clientData) {
    return axios.put(`${API_URL}/${id}`, clientData);
  }

  deleteClient(id) {
    return axios.delete(`${API_URL}/${id}`);
  }
}

export default new ClientService();
