export default class Administrator {
  constructor(
    role,
    creationDate,
    idEmployee,
    name,
    email,
    password,
    phoneNumber,
    verified
  ) {
    this.role = role;
    this.creationDate = creationDate;
    this.idEmployee = idEmployee;
    this.name = name;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.verified = verified;
  }
}
