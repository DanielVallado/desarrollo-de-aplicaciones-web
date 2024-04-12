import React from "react";
import PropTypes from "prop-types";
import "./transaction-component-style.css";

export const TransactionComponent = ({ transaction }) => {
  return (
    <div className="transaction-component">
      <h4>{transaction.description}</h4>
      <output>{transaction.transactionDate}</output>
      <output>{transaction.amount}</output>
    </div>
  );
};

TransactionComponent.propTypes = {
  transaction: PropTypes.object.isRequired,
};

export default TransactionComponent;
