import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Cookies from "js-cookie";
import Swal from "sweetalert2";
import CardService from "/src/services/CardService";
import TransactionType from "/src/models/TransactionType";
import TransactionComponent from "./TransactionComponent";
import "./card-view-component-style.css";

export const CardViewComponent = () => {
  const navigate = useNavigate();

  const { idCard } = useParams();
  const [card, setCard] = useState({});
  const [transactions, setTransactions] = useState([]);
  const [isInputVisible, setInputVisible] = useState(false);
  const [amount, setAmount] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    const cookieValue = Cookies.get("client");
    if (!cookieValue) {
      navigate("/login");
    } else {
      CardService.getCardById(idCard).then((card) => {
        setCard(card);
      });

      CardService.getTransactions(idCard).then((transactions) => {
        setTransactions(transactions);
      });
    }
  }, [idCard, navigate]);

  function reloadCardData() {
    CardService.getCardById(idCard).then((card) => {
      setCard(card);
    });
  }

  function deposit() {
    setError("");
    if (!amount) {
      setError("Todos los campos son obligatorios");
    }

    const deposit = {
      amount: amount,
      description: `Deposito`,
      transactionType: TransactionType.DEPOSIT,
      destination: "",
    };

    CardService.createTransaction(idCard, deposit).then(() => {
      Swal.fire({
        title: "¡Éxito!",
        text: "Depósito realizado",
        icon: "success",
        confirmButtonText: "OK",
        customClass: { popup: "swal-wide" },
      }).then(() => {
        setInputVisible(false);
      });

      reloadCardData();
    });
  }

  function handleAmountChange(event) {
    setAmount(event.target.value);
  }

  return (
    <div className="card card-view-container">
      <div className="card-view">
        <h2 className="card-view__title">Tarjeta {card.cardType}</h2>

        <div className="card card-view__balance">
          <p>Saldo disponible</p>
          <output>${card.balance}</output>
        </div>

        <div className="buttons">
          <button
            className="card-info__button"
            onClick={() => navigate(`/client/transfer/${card.idCard}`)}
          >
            <img src="/src/assets/icons/transfer-icon.svg" />
            Tranferir
          </button>
          <button
            className="card-info__button"
            onClick={() => setInputVisible(!isInputVisible)}
          >
            <img src="/src/assets/icons/statement_icon.svg" />
            Depositar
          </button>
        </div>

        <div>
          <input
            type="number"
            placeholder="$0 000 000"
            disabled={!isInputVisible}
            onChange={handleAmountChange}
            className={`input deposit-input ${isInputVisible ? "visible" : ""}`}
          />
          <button
            className={`button deposit-button ${
              isInputVisible ? "visible" : ""
            }`}
            onClick={deposit}
          >
            Aceptar
          </button>
        </div>
        <p className="form__error">{error}</p>

        <div className="card transactions-section">
          <div className="transaction__title">
            <h3>Transacciones</h3>
          </div>

          {transactions.map((transaction) => (
            <TransactionComponent
              key={transaction.idTransaction}
              transaction={transaction}
            />
          ))}
        </div>
      </div>

      <div className="card card-view__statment">
        <h3>Mi tarjeta</h3>
        <picture className="card-section__image">
          <source
            srcSet={`/src/assets/cards/${card.cardType}Card.webp`}
            type="image/webp"
          />
          <source
            srcSet={`/src/assets/cards/${card.cardType}Card.avif`}
            type="image/avif"
          />
          <img
            src={`/src/assets/cards/${card.cardType}Card.png.png`}
            alt={`Imagen tarjeta ${card.cardType}`}
          />
        </picture>

        <div className="switch-vip">
          <label className="switch">
            <input type="checkbox" />
            <span className="slider round"></span>
          </label>
          <p>VIP</p>
        </div>

        <h3 className="statment-title">Estados de cuenta</h3>
        <div className="statment-date">
          <label htmlFor="">Fecha</label>
          <input type="date" />
        </div>

        <button className="button statment-button">Imprimir</button>
      </div>
    </div>
  );
};

export default CardViewComponent;
