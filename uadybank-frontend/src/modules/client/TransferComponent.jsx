import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import PropTypes from "prop-types";
import CardService from "/src/services/CardService";
import "./transfer-style.css";

export const TransferView = () => {
  const { cardId } = useParams();
  const [card, setCard] = React.useState(null);

  useEffect(() => {
    CardService.getCard(cardId).then((card) => {
      setCard(card);
    });
  }, [cardId]);

  return (
    <div>
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

        <div className="tranfer__card-info__data">
          <h3>{card.cardType}</h3>
          <h4>Número de tarjeta</h4>
          <output className="card-info--number">XXXX XXXXX XXXX XXXXX</output>
          <h4>Saldo disponible</h4>
          <output className="card-info--balance">${card.balance}</output>
        </div>
      </section>
    </div>
  );
};

TransferView.propTypes = {
  card: PropTypes.object,
};

export default TransferView;
