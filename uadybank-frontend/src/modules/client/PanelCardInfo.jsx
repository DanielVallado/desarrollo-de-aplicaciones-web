import React from "react";
import PropTypes from "prop-types";
import "./panel-card-info-style.css";

export const PanelCardInfo = ({ card }) => {
  if (!card) {
    return null; // o puedes renderizar un componente de carga o algún otro placeholder
  }
  return (
    <div className="card-info-section">
      <div className="card-section">
        <img
          src={`/src/assets/cards/${card.cardType}Card.png`}
          alt="Tarjeta"
          className="card-section__image"
        />
        <div className="buttons">
          <button className="card-info__button">
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
        <p className="card-info--number">XXXX XXXXX XXXX XXXXX</p>
        <h4>Saldo disponible</h4>
        <p className="card-info--balance">${card.balance}</p>
      </div>
    </div>
  );
};

PanelCardInfo.propTypes = {
  card: PropTypes.object,
};

export default PanelCardInfo;
