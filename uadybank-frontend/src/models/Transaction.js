export default class Transaction {
  constructor(
    idTransaction,
    amount,
    description,
    transactionType,
    transactionDate,
    destinationCard
  ) {
    this.idTransaction = idTransaction;
    this.amount = amount;
    this.description = description;
    this.transactionType = transactionType;
    this.transactionDate = transactionDate;
    this.destinationCard = destinationCard;
  }
}
