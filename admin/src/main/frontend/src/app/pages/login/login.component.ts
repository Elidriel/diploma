import { Component, OnInit } from "@angular/core";
import { AccountEventsService } from "../../account/account.events.service";
import { LoginService } from "../../services/login/login.service";
import { ActivatedRoute, Router } from "@angular/router";
import { Account } from "../../account/account";

@Component({
  selector: "page-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"]
})
export class LoginComponent implements OnInit {
  wrongCredentials: boolean;
  account: Account;
  error: string;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private accountEventService: AccountEventsService,
    private loginService: LoginService
  ) {
    this.wrongCredentials = false;

    accountEventService.subscribe(account => {
      if (!account.authenticated) {
        if (account.error) {
          if (account.error.indexOf("BadCredentialsException") !== -1) {
            this.error = "Username and/or password are invalid !";
          } else {
            this.error = account.error;
          }
        }
      }
    });
  }

  ngOnInit() {
    this.authenticate();
  }

  private authenticate() {
    this.loginService.authenticate().subscribe(account => {
      this.account = account;
      let componentUrl = this.route.snapshot.queryParamMap.get("componentUrl");
      if (!componentUrl) {
        componentUrl = "/admin/welcome";
      }
      this.router.navigate([componentUrl]);
    });
  }
}
