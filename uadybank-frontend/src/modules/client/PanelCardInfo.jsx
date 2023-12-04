import { useNavigate } from "react-router-dom";
import PropTypes from "prop-types";
import "./panel-card-info-style.css";

export const PanelCardInfo = ({ card }) => {
  const navigate = useNavigate();

  function formatCardNumber(cardNumber) {
    return cardNumber.toString().replace(/(\d{4})(?=\d)/g, "$1 - ");
  }

  return (
    <>
      {card ? (
        <div className="card-info-section">
          <div className="card-section">
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
                src={`/src/assets/cards/${card.cardType}Card.png`}
                alt={`Imagen tarjeta ${card.cardType}`}
              />
            </picture>

            <div className="buttons">
              <button
                className="card-info__button"
                onClick={() => navigate(`/client/transfer/${card.idCard}`)}
              >
                <img src="/src/assets/icons/transfer-icon.svg" />
                Tranferir
              </button>
              <button className="card-info__button">
                <img src="/src/assets/icons/statement_icon.svg" />
                Estado de cuenta
              </button>
            </div>
          </div>

          <div className="card card-info">
            <h3>{card.cardType}</h3>
            <h4>Número de tarjeta</h4>
            <output className="card-info--number">
              {formatCardNumber(card.idCard)}
            </output>
            <h4>Saldo disponible</h4>
            <output className="card-info--balance">${card.balance}</output>
          </div>
        </div>
      ) : (
        <p className="loading">Cargando...</p>
      )}
    </>
  );
};

PanelCardInfo.propTypes = {
  card: PropTypes.object,
};

export default PanelCardInfo;
