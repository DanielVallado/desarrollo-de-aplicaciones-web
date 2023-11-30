import React, { useState } from "react";
import PropTypes from "prop-types";
import "./login-forms-style.css";
import LoginService from "/src/services/LoginService";

export const LoginForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (email.trim() !== "" && password.trim() !== "") {
      console.log(`Email: ${email} Password: ${password}`);
      LoginService.login(email, password)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.error(error);
        });
    }
  };

  const handleBlur = () => {
    if (email.trim() === "" || password.trim() === "") {
      setError("Todos los campos son requeridos");
    } else {
      setError("");
    }
  };

  return (
    <div className={`login-section`}>
      <form className="login-form" onSubmit={handleSubmit}>
        <h2 className="form__title">Iniciar sesión</h2>

        <p className="form__error">{error}</p>

        <input
          type="text"
          placeholder="Correo electrónico"
          className="field field-icon field-icon--email"
          value={email}
          onChange={handleEmailChange}
          onBlur={handleBlur}
        />
        <input
          type="password"
          placeholder="Contraseña"
          className="field field-icon field-icon--password"
          value={password}
          onChange={handlePasswordChange}
          onBlur={handleBlur}
        />
        <a href="/">¿Olvidaste tu contraseña?</a>
        <input
          value="Iniciar sesión"
          type="submit"
          className="button button--login"
        />
      </form>
    </div>
  );
};

LoginForm.propTypes = {
  isActive: PropTypes.bool.isRequired,
};

export default LoginForm;
