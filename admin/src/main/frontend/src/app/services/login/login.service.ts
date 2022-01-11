import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Account } from "../../account/account";
import {
  BACKEND_API_AUTHENTICATE_PATH,
  UrlRegistry
} from "../../utils/app.utils";
import { AccountEventsService } from "../../account/account.events.service";
import { tap } from "rxjs/operators";

@Injectable({
  providedIn: "root"
})
export class LoginService {
  private static uuid: number = 0;
  private account: Account;
  private copy: number;

  constructor(
    private http: HttpClient,
    private accountEventService: AccountEventsService
  ) {
    this.copy = LoginService.uuid++;
  }

  authenticate(): Observable<Account> {
    localStorage.clear();

    let headers = new HttpHeaders();
    headers.append("Content-Type", "application/json");

    return this.http
      .get<Account>(
        UrlRegistry.rootBackendUrl() + BACKEND_API_AUTHENTICATE_PATH,
        {
          headers: headers
        }
      )
      .pipe(
        tap((res: Account) => {
          let account: Account = res;
          this.sendLoginSuccess(account);

          this.account = account;
          return account;
        })
      );
  }

  public getAccount(): Account {
    return this.account;
  }

  sendLoginSuccess(account?: Account): void {
    this.accountEventService.loginSuccess(account);
  }

  isAuthenticated(): boolean {
    return !!this.account;
  }

  logout(callServer: boolean = true): void {
    this.account = null;
    setTimeout(function() {
      window.location.assign("/logout");
    }, 100);
  }

  isAuthorized(role: string): boolean {
    let authorized: boolean = false;
    if (this.isAuthenticated() && role) {
      if (this.account && this.account.authorities) {
        if (this.account.authorities.indexOf(role) !== -1) {
          authorized = true;
        }
      }
    }
    return authorized;
  }
}
