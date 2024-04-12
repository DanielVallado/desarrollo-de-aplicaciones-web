import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Cookies from "js-cookie";
import Swal from "sweetalert2";
import PropTypes from "prop-types";
import TransactionType from "/src/models/TransactionType";
import CardService from "/src/services/CardService";
import "./transfer-component-style.css";

export const TransferView = () => {
  const navigate = useNavigate();
  const { idCard } = useParams();
  const [card, setCard] = useState(null);
  const [beneficiaryName, setBeneficiaryName] = useState("");
  const [beneficiaryAccountNumber, setBeneficiaryAccountNumber] = useState("");
  const [beneficiaryCard, setBeneficiaryCard] = useState(null);
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
    }
  }, [idCard, navigate]);

  function tranfer() {
    setError("");
    if (!beneficiaryName || !beneficiaryAccountNumber || !amount) {
      setError("Todos los campos son obligatorios");
      return;
    }

    if (card.balance - amount < 0) {
      setError("Saldo insuficiente");
      return;
    }

    const transfer = {
      amount: amount,
      description: `Transferencia a ${beneficiaryName}`,
      transactionType: TransactionType.TRANSFER,
      destination: beneficiaryAccountNumber,
    };

    CardService.createTransaction(idCard, transfer).then(() => {
      Swal.fire({
        title: "¡Éxito!",
        text: "Transferencia realizada",
        icon: "success",
        confirmButtonText: "OK",
        customClass: { popup: "swal-wide" },
      }).then((result) => {
        if (result.isConfirmed) {
          navigate(`/client/`);
        }
      });
    });
  }

  function handleBeneficiaryNameChange(event) {
    setBeneficiaryName(event.target.value);
  }

  function handleBeneficiaryAccountNumberChange(event) {
    const accountNumber = event.target.value;
    if (accountNumber.length <= 16) {
      setError("");
      setBeneficiaryCard(null);
      setBeneficiaryAccountNumber(accountNumber);
    }

    if (accountNumber.length === 16) {
      if (accountNumber == idCard) {
        setError("No se puede transferir a la misma cuenta");
      } else {
        CardService.getCardById(accountNumber)
          .then((card) => {
            setBeneficiaryCard(card);
          })
          .catch(() => {
            setError("El número de cuenta no existe");
          });
      }
    }
  }

  function handleAmountChange(event) {
    setAmount(event.target.value);
  }

  function formatCardNumber(cardNumber) {
    return cardNumber.toString().replace(/(\d{4})(?=\d)/g, "$1 - ");
  }

  return (
    <>
      {card ? (
        <div className="card transfer__container">
          <section className="tranfer__card-info">
            <picture className="tranfer__card-info__image">
              <source
                srcSet={`/src/assets/cards/${card.cardType}Card.webp`}
                type="image/webp"
              />
              <source
                srcSet={`/src/assets/cards/${card.cardType}Card.avif`}
                type="image/avif"
              />
              <img
                src={`/src/assets/cards/${card.cardType}Card.png`}
                alt={`Imagen tarjeta ${card.cardType}`}
              />
            </picture>

            <div className="card transfer__card-info__data">
              <h3>{card.cardType}</h3>
              <h4>Número de tarjeta</h4>
              <output className="card-info--number">
                {formatCardNumber(card.idCard)}
              </output>
              <h4>Saldo disponible</h4>
              <output className="card-info--balance">${card.balance}</output>
            </div>
          </section>

          <div className="card recipient">
            <h3 className="recipient__title">Destinatario</h3>
            <p className="form__error">{error}</p>
            <form className="recipient__form">
              <div className="recipient__field">
                <label htmlFor="name">Nombre del beneficiario</label>
                <input
                  type="text"
                  id="name"
                  value={beneficiaryName}
                  onChange={handleBeneficiaryNameChange}
                  className="input"
                />
              </div>
              <div className="recipient__field">
                <label htmlFor="account">Número de cuenta</label>
                <input
                  type="text"
                  id="account"
                  value={beneficiaryAccountNumber}
                  onChange={handleBeneficiaryAccountNumberChange}
                  className="input"
                />
              </div>
            </form>
          </div>

          <div className="card cards-route__container">
            <div className="cards-route">
              <div className="cards-route__card">
                <h3 className="cards-route__title">Cuenta origen</h3>

                <picture className="cards-route__image">
                  <source
                    srcSet={`/src/assets/cards/${card.cardType}Card.webp`}
                    type="image/webp"
                  />
                  <source
                    srcSet={`/src/assets/cards/${card.cardType}Card.avif`}
                    type="image/avif"
                  />
                  <img
                    src={`/src/assets/cards/${card.cardType}Card.png`}
                    alt={`Imagen tarjeta ${card.cardType}`}
                  />
                </picture>

                <output className="cards-route__number">
                  {formatCardNumber(card.idCard)}
                </output>
              </div>

              <img
                src="/src/assets/icons/transfer-icon.svg"
                alt="Transfer icon"
                className="cards-route__icon"
              />

              <div className="cards-route__card">
                <h3 className="cards-route__title">Cuenta destino</h3>

                {beneficiaryCard && (
                  <picture className="cards-route__image">
                    <source
                      srcSet={`/src/assets/cards/${beneficiaryCard.cardType}Card.webp`}
                      type="image/webp"
                    />
                    <source
                      srcSet={`/src/assets/cards/${beneficiaryCard.cardType}Card.avif`}
                      type="image/avif"
                    />
                    <img
                      src={`/src/assets/cards/${beneficiaryCard.cardType}Card.png`}
                      alt={`Imagen tarjeta ${card.cardType}`}
                    />
                  </picture>
                )}
                <output className="cards-route__number">
                  {formatCardNumber(beneficiaryAccountNumber)}
                </output>
              </div>
            </div>

            <div className="cards-route__balance">
              <p>Saldo disponible:</p>
              <output className="cards-route__balance">$ {card.balance}</output>
            </div>

            <div className="cards-route__amount">
              <p>Monto a tranferir: </p>
              <input
                type="number"
                placeholder="$000.00"
                className="input"
                value={amount}
                onChange={handleAmountChange}
              />
            </div>

            <p className="form__error">{error}</p>

            <button className="button button--transfer" onClick={tranfer}>
              Transferir
            </button>
          </div>
        </div>
      ) : (
        <p className="loading">Cargando...</p>
      )}
    </>
  );
};

TransferView.propTypes = {
  card: PropTypes.object,
};

export default TransferView;
