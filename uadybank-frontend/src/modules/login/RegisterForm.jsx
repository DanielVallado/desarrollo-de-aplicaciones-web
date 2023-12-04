import { useState } from "react";
import Account from "/src/models/account";
import AccountService from "/src/services/AccountService";
import CardType from "/src/models/CardType";
import "./login-forms-style.css";

export const RegisterForm = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [confirmEmail, setConfirmEmail] = useState("");
  const [matricula, setMatricula] = useState("");
  const [phone, setPhone] = useState("");
  const [address, setAddress] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [error, setError] = useState("");

  function createAccount() {
    return new Account(
      null,
      {
        name: name,
        email: email,
        matricula: matricula,
        phoneNumber: phone,
        address: address,
        password: password,
      },
      [{ cardType: CardType.CLASSIC, balance: 0, vip: true }]
    );
  }

  const handleInputChange = (e) => {
    setError("");
    const { name, value } = e.target;
    switch (name) {
      case "name":
        setName(value);
        break;
      case "email":
        setEmail(value);
        break;
      case "confirmEmail":
        setConfirmEmail(value);
        if (value !== email) {
          setError("Los correos electrónicos no coinciden");
        } else {
          setError("");
        }
        break;
      case "matricula":
        setMatricula(value);
        break;
      case "phone":
        setPhone(value);
        break;
      case "address":
        setAddress(value);
        break;
      case "password":
        setPassword(value);
        break;
      case "confirmPassword":
        setConfirmPassword(value);
        if (value !== password) {
          setError("Las contraseñas no coinciden");
        } else {
          setError("");
        }
        break;
      default:
        break;
    }
  };

  const handleBlur = (e) => {
    const { value } = e.target;
    if (value.trim() === "") {
      setError(`Todos los campos son requeridos`);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (
      name.trim() !== "" ||
      email.trim() !== "" ||
      confirmEmail.trim() !== "" ||
      matricula.trim() !== "" ||
      phone.trim() !== "" ||
      address.trim() !== "" ||
      password.trim() !== "" ||
      confirmPassword.trim() !== ""
    ) {
      const account = createAccount();
      console.log(account);
      AccountService.createAccount(account)
        .then(() => {
          window.location.reload();
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      setError("Por favor, complete todos los campos");
    }
  };

  return (
    <div className={`register-section`}>
      <form className="register-form" onSubmit={handleSubmit}>
        <h2 className="form__title">Registro</h2>

        <p className="form__error">{error}</p>

        <input
          type="text"
          placeholder="Nombre completo"
          className="field field-icon field-icon--name"
          id="input-name"
          name="name"
          value={name}
          onChange={handleInputChange}
          onBlur={handleBlur}
        />
        <input
          type="email"
          placeholder="Correo electrónico"
          className="field field-icon field-icon--email"
          id="input-email"
          name="email"
          value={email}
          onChange={handleInputChange}
          onBlur={handleBlur}
        />
        <input
          type="email"
          placeholder="Confirmar correo electrónico"
          className="field field-icon field-icon--email"
          id="input-confirm-email"
          name="confirmEmail"
          value={confirmEmail}
          onChange={handleInputChange}
          onBlur={handleBlur}
        />

        <div className="fields-container">
          <input
            type="text"
            placeholder="Matrícula"
            className="field field--medium field-icon field-icon--matricula"
            id="input-matricula"
            name="matricula"
            value={matricula}
            onChange={handleInputChange}
            onBlur={handleBlur}
          />
          <input
            type="tel"
            placeholder="Número celular"
            className="field field--medium field-icon field-icon--phone"
            id="input-phone"
            name="phone"
            value={phone}
            onChange={handleInputChange}
            onBlur={handleBlur}
          />
        </div>

        <input
          type="text"
          placeholder="Dirección"
          className="field field-icon field-icon--address"
          id="input-adress"
          name="address"
          value={address}
          onChange={handleInputChange}
          onBlur={handleBlur}
        />

        <div className="fields-container">
          <input
            type="password"
            placeholder="Contraseña"
            className="field field--medium field-icon field-icon--password"
            id="input-password"
            name="password"
            value={password}
            onChange={handleInputChange}
            onBlur={handleBlur}
          />
          <input
            type="password"
            placeholder="Confirmar contraseña"
            className="field field--medium field-icon field-icon--password"
            id="input-confirm-password"
            name="confirmPassword"
            value={confirmPassword}
            onChange={handleInputChange}
            onBlur={handleBlur}
          />
        </div>

        <input
          type="submit"
          value="Registrarse"
          className="button button--login"
        />
      </form>
    </div>
  );
};

export default RegisterForm;
