import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";
import CardComponent from "/src/modules/client/CardComponent";
import AccountService from "/src/services/AccountService";
import "./client-main-component-style.css";

export const ClientMainComponent = () => {
  const navigate = useNavigate();

  const [client, setClient] = useState({});
  const [cards, setCards] = useState([]);
  const [selectedCard, setSelectedCard] = useState(null);

  useEffect(() => {
    const cookieValue = Cookies.get("client");
    if (!cookieValue) {
      navigate("/login");
    } else {
      const fetchCards = async () => {
        const account = await AccountService.getAccountByMatricula();
        setClient(account.client);
        setCards(account.cards);
        setSelectedCard(account.cards[0]);
      };

      fetchCards();
    }
  }, [navigate]);

  const handleCardClick = (card) => {
    setSelectedCard(card);
  };

  const handleDoubleClick = (card) => {
    navigate(`/client/transfer/${card.idCard}`);
  };

  const selectedCardView = () => {
    navigate(`/client/card/${selectedCard.idCard}`);
  };

  const cardView = (card) => {
    navigate(`/client/card/${card.idCard}`);
  };

  function formatCardNumber(cardNumber) {
    return cardNumber.toString().replace(/(\d{4})(?=\d)/g, "$1 - ");
  }

  return (
    <section className="card client-main-view">
      <h2 className="saludo">Bienvenido, {client.name}</h2>

      {selectedCard ? (
        <div className="card-info-section">
          <div className="card-section">
            <picture className="card-section__image" onClick={selectedCardView}>
              <source
                srcSet={`/src/assets/cards/${selectedCard.cardType}Card.webp`}
                type="image/webp"
              />
              <source
                srcSet={`/src/assets/cards/${selectedCard.cardType}Card.avif`}
                type="image/avif"
              />
              <img
                src={`/src/assets/cards/${selectedCard.cardType}Card.png`}
                alt={`Imagen tarjeta ${selectedCard.cardType}`}
              />
            </picture>

            <div className="buttons">
              <button
                className="card-info__button"
                onClick={() =>
                  navigate(`/client/transfer/${selectedCard.idCard}`)
                }
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
            <h3>{selectedCard.cardType}</h3>
            <h4>Número de tarjeta</h4>
            <output className="card-info--number">
              {formatCardNumber(selectedCard.idCard)}
            </output>
            <h4>Saldo disponible</h4>
            <output className="card-info--balance">
              ${selectedCard.balance}
            </output>
          </div>
        </div>
      ) : (
        <p className="loading">Cargando...</p>
      )}

      <section className="card-list">
        {cards.map((card, index) => (
          <CardComponent
            key={index}
            card={card}
            onClick={() => handleCardClick(card)}
            onDoubleClick={() => handleDoubleClick(cardView(card))}
          />
        ))}
      </section>
    </section>
  );
};

export default ClientMainComponent;
