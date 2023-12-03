import React, { useState, useEffect } from "react";
import CardComponent from "/src/modules/client/CardComponent";
import PanelCardInfo from "/src/modules/client/PanelCardInfo";
import AccountService from "/src/services/AccountService";
import "./client-main-component-style.css";

export const ClientMainComponent = () => {
  const [cards, setCards] = useState([]);
  const [selectedCard, setSelectedCard] = useState(null);

  useEffect(() => {
    const fetchCards = async () => {
      const account = await AccountService.getAccountByMatricula();
      setCards(account.cards);
      setSelectedCard(account.cards[0]);
    };

    fetchCards();
  }, []);

  const handleCardClick = (card) => {
    setSelectedCard(card);
  };

  return (
    <div className="card client-main-view">
      <h2 className="saludo">Hola, Usuario</h2>
      <PanelCardInfo card={selectedCard} />

      <section className="card-list">
        {cards.map((card, index) => (
          <CardComponent
            key={index}
            card={card}
            onClick={() => handleCardClick(card)}
          />
        ))}
      </section>
    </div>
  );
};

export default ClientMainComponent;
