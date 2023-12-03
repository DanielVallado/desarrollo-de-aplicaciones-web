export default class Client {
  constructor(
    role,
    creationDate,
    matricula,
    name,
    email,
    password,
    phoneNumber,
    address,
    verified
  ) {
    this.role = role;
    this.creationDate = creationDate;
    this.matricula = matricula;
    this.name = name;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.verified = verified;
  }
}
