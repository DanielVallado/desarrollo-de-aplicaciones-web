import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";
import uadybankLogo from "/src/assets/uadybank-black.svg";
import defaultUserIcon from "/src/assets/user-icon.svg";
import BurguerButton from "./BurguerButton";
import LoginService from "/src/services/LoginService";
import AccountService from "/src/services/AccountService";
import "./header-style.css";

export const Header = () => {
  const navigate = useNavigate();

  const [client, setClient] = useState({});
  const [clicked, setClicked] = useState(false);

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

  const handleClick = () => {
    setClicked(!clicked);
  };

  function getImagePath() {
    const path = localStorage.getItem("userImagePath");
    if (path) {
      return path;
    } else {
      return defaultUserIcon;
    }
  }

  function home() {
    if (Cookies.get("client")) {
      navigate("/client");
    } else if (Cookies.get("administrator")) {
      navigate("/administrator");
    }
  }

  function profile() {
    if (Cookies.get("client")) {
      navigate("/client/profile");
    } else if (Cookies.get("administrator")) {
      navigate("/administrator/profile");
    }
  }

  function logout() {
    LoginService.logout();
  }

  return (
    <header className="header">
      <div className="header__container">
        <div className="header__logo">
          <a onClick={profile}>
            <img src={uadybankLogo} alt="Logo UADYBank" />
            <span className="header__barra"></span>
            <h1>UADYBANK</h1>
          </a>
        </div>

        <div className="menu-icon">
          <BurguerButton onClick={handleClick} isOpen={clicked} />
        </div>

        {client ? (
          <div className="header__user">
            <a onClick={profile}>
              <div className="header__image">
                <img src={getImagePath()} alt="" />
              </div>
              <p>{client.name}</p>
            </a>
          </div>
        ) : (
          <div className="header__user">
            <a onClick={profile}>
              <div className="header__image">
                <img src={defaultUserIcon} alt="" />
              </div>
              <p>Usuario</p>
            </a>
          </div>
        )}
      </div>

      <nav className={`header__options ${clicked ? "open" : ""}`}>
        <ul>
          <li>
            <a onClick={home}>Inicio</a>
          </li>
          <li>
            <a onClick={profile}>Perfil</a>
          </li>
          <li>
            <a onClick={logout}>Salir</a>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
