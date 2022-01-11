export class Account {
  id: number;
  login: string;
  fullName: string;
  authorities: Array<string>;
  authenticated = true;
}
