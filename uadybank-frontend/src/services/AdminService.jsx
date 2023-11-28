import axios from "axios";

const API_URL = "http://localhost:8000/api/admin";

class AdminService {
  // Método para obtener todos los administradores
  static async getAllAdmins() {
    try {
      const response = await axios.get(`${API_URL}/admins`);
      return response.data;
    } catch (error) {
      console.error("Error al obtener los administradores:", error);
      throw error;
    }
  }

  // Método para crear un nuevo administrador
  static async createAdmin(adminData) {
    try {
      const response = await axios.post(`${API_URL}/admins`, adminData);
      return response.data;
    } catch (error) {
      console.error("Error al crear el administrador:", error);
      throw error;
    }
  }

  // Método para actualizar un administrador existente
  static async updateAdmin(adminId, adminData) {
    try {
      const response = await axios.put(
        `${API_URL}/admins/${adminId}`,
        adminData
      );
      return response.data;
    } catch (error) {
      console.error("Error al actualizar el administrador:", error);
      throw error;
    }
  }

  // Método para eliminar un administrador
  static async deleteAdmin(adminId) {
    try {
      const response = await axios.delete(`${API_URL}/admins/${adminId}`);
      return response.data;
    } catch (error) {
      console.error("Error al eliminar el administrador:", error);
      throw error;
    }
  }
}

export default AdminService;
