import { useState } from "react";
import "./header-style.css";
import uadybankLogo from "../../assets/uadybank-black.svg";
import defaultUserIcon from "../../assets/user-icon.svg";
import BurguerButton from "./BurguerButton";

export const Header = () => {
  const [clicked, setClicked] = useState(false);

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

  function getUsername() {
    const username = localStorage.getItem("username");
    if (username) {
      return username;
    } else {
      return "Usuario";
    }
  }

  return (
    <header className="header">
      <div className="header__container">
        <div className="header__logo">
          <a href="#">
            <img src={uadybankLogo} alt="Logo UADYBank" />
            <span className="header__barra"></span>
            <h1>UADYBANK</h1>
          </a>
        </div>

        <div className="menu-icon">
          <BurguerButton onClick={handleClick} isOpen={clicked} />
        </div>

        <div className="header__user">
          <a href="#">
            <div className="header__image">
              <img src={getImagePath()} alt="" />
            </div>
            <p>{getUsername()}</p>
          </a>
        </div>
      </div>

      <nav className={`header__options ${clicked ? "open" : ""}`}>
        <ul>
          <li>
            <a href="">Inicio</a>
          </li>
          <li>
            <a href="">Perfil</a>
          </li>
          <li>
            <a href="">Salir</a>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
