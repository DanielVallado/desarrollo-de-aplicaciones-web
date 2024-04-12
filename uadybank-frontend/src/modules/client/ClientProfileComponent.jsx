import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";
import AccountService from "/src/services/AccountService";
import "./client-profile-component-style.css";

export const ClientProfileComponent = () => {
  const navigate = useNavigate();

  const defaultClient = {
    name: "",
    matricula: "",
    email: "",
    phoneNumber: "",
    address: "",
    creationDate: "",
  };

  const [isEditing, setIsEditing] = useState(false);
  const [client, setClient] = useState(defaultClient);

  useEffect(() => {
    const cookieValue = Cookies.get("client");
    if (!cookieValue) {
      navigate("/login");
    } else {
      const fetchCards = async () => {
        const account = await AccountService.getAccountByMatricula();
        setClient(account.client);
      };

      fetchCards();
    }
  }, [navigate]);

  return (
    <div className="card profile-container">
      <div className="profile-container__info">
        <div className="profile">
          <h2>Perfil</h2>

          <div
            className="edit-profile"
            onClick={() => setIsEditing(!isEditing)}
          >
            <img src="/src/assets/icons/edit-icon.svg" alt="Icono editar" />
            <p>Editar perfil</p>
          </div>

          <img
            src="/src/assets/icons/user-icon.svg"
            alt="Imagen de perfil"
            className="profile__image"
          />

          <form className="profile__form">
            <div className="profile__info">
              <label htmlFor="creationDate">Fecha de registro</label>
              <input
                type="text"
                id="creationDate"
                name="creationDate"
                placeholder="Fecha de registro"
                value={client.creationDate}
                className="input profile__info__input"
                disabled
              />
            </div>
            <div className="profile__info">
              <label htmlFor="matricula">Matricula</label>
              <input
                type="text"
                id="matricula"
                name="matricula"
                placeholder="Matricula"
                value={client.matricula}
                className="input profile__info__input"
                disabled
              />
            </div>
            <div className="profile__info">
              <label htmlFor="name">Nombre</label>
              <input
                type="text"
                id="name"
                name="name"
                placeholder="Nombre"
                value={client.name}
                className="input profile__info__input"
                disabled={!isEditing}
              />
            </div>
            <div className="profile__info">
              <label htmlFor="email">Correo</label>
              <input
                type="email"
                id="email"
                name="email"
                placeholder="Correo"
                value={client.email}
                className="input profile__info__input"
                disabled={!isEditing}
              />
            </div>
            <div className="profile__info">
              <label htmlFor="phone">Teléfono</label>
              <input
                type="tel"
                id="phone"
                name="phone"
                placeholder="Teléfono"
                value={client.phoneNumber}
                className="input profile__info__input"
                disabled={!isEditing}
              />
            </div>
            <div className="profile__info">
              <label htmlFor="address">Dirección</label>
              <input
                type="text"
                id="address"
                name="address"
                placeholder="Dirección"
                value={client.address}
                className="input profile__info__input"
                disabled={!isEditing}
              />
            </div>

            {/* <div className="profile__info">
              <label htmlFor="password">Contraseña</label>
              <input
                type="password"
                id="password"
                name="password"
                placeholder="Contraseña"
                className="input profile__info__input"
              />
            </div> */}
            {/* <div className="profile__info">
              <label htmlFor="password">Confirmar contraseña</label>
              <input
                type="password"
                id="password"
                name="password"
                placeholder="Confirmar contraseña"
                className="input profile__info__input"
              />
            </div> */}
            {isEditing && (
              <input
                type="submit"
                value="Guardar"
                className="button profile__info__button"
              />
            )}
          </form>
        </div>
      </div>
    </div>
  );
};

export default ClientProfileComponent;
