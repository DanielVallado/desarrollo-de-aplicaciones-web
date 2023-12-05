import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";
import ClientPanelComponent from "./ClientPanelComponent";
import ClientService from "/src/services/ClientService";
import "./admin-main-component-style.css";

export const AdminMainComponent = () => {
  const navigate = useNavigate();

  const [clients, setClients] = useState([]);

  useEffect(() => {
    const cookieValue = Cookies.get("administrator");
    if (!cookieValue) {
      navigate("/login");
    } else {
      const fetchClients = async () => {
        const clients = await ClientService.getAllClients();
        setClients(clients);
      };

      fetchClients();
    }
  }, [navigate]);

  return (
    <section className="card admin-main-container">
      <h2>Lista de clientes</h2>

      <div className="admin-main__search-section">
        <input className="search-input" type="text" />
        <button className="button">Agregar cliente</button>
      </div>

      <div className="admin-main__client-list">
        {clients.map((client) => (
          <ClientPanelComponent key={client.matricula} client={client} />
        ))}
      </div>
    </section>
  );
};

export default AdminMainComponent;
