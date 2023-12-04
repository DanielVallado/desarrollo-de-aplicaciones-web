import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";

import "./admin-main-component-style.css";

export const AdminMainComponent = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const cookieValue = Cookies.get("administrator");
    if (!cookieValue) {
      navigate("/login");
    }
  }, [navigate]);

  return (
    <div className="card admin-main-container">
      <div className="admin-main__search-section">
        <h2>Lista de clientes</h2>
      </div>
    </div>
  );
};

export default AdminMainComponent;
