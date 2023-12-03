export default class Account {
  constructor(idAccount = null, client, cards) {
    this.idAccount = idAccount;
    this.client = client;
    this.cards = cards;
  }
}
