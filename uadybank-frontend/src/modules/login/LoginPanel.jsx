import PropTypes from "prop-types";
import uadybankLogo from "/src/assets/uadybank-white.svg";
import "./login-panel-style.css";

export const LoginPanel = ({ type, onButtonClick, isActive }) => {
  const buttonText = type === "register" ? "Registrarse" : "Iniciar sesión";
  const activeClass = isActive ? "active" : "inactive";

  return (
    <div className={`panel panel--${type} ${activeClass}`}>
      <div className="panel__content">
        <img
          src={uadybankLogo}
          alt="Logotipo UADYBANK"
          className="panel__image"
        />
        <h2 className="panel__title">UADYBANK</h2>
        <button
          onClick={() => onButtonClick(type)}
          className="button button--panel"
        >
          {buttonText}
        </button>
      </div>
    </div>
  );
};

LoginPanel.propTypes = {
  type: PropTypes.oneOf(["register", "login"]).isRequired,
  onButtonClick: PropTypes.func.isRequired,
  isActive: PropTypes.bool.isRequired,
};

export default LoginPanel;
