import React from "react";
import PropTypes from "prop-types";
import "./login-panel-style.css";

export const LoginPanel = ({ type, onButtonClick }) => {
  const imageSrc = "";
  const buttonText = type === "register" ? "Registrarse" : "Iniciar sesión";

  return (
    <div className={`${type}-panel`}>
      <img src={imageSrc} alt="Logotipo UADYBANK" />
      <h2>UADYBANK</h2>
      <button
        className="button button--login"
        onClick={() => onButtonClick(type)}
      >
        {buttonText}
      </button>
    </div>
  );
};

LoginPanel.propTypes = {
  type: PropTypes.oneOf(["register", "login"]).isRequired,
  onButtonClick: PropTypes.func.isRequired,
};

export default LoginPanel;
