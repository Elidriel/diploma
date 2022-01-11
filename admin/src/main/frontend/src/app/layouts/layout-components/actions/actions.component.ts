import { Component, EventEmitter, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Store } from "@ngrx/store";
import { AppSettings } from "../../../interfaces/settings";
import { AppState } from "../../../interfaces/app-state";
import { LoginService } from "../../../services/login/login.service";
import { Account } from "../../../account/account";

@Component({
  selector: "app-actions",
  templateUrl: "./actions.component.html",
  styleUrls: ["./actions.component.scss"]
})
export class ActionsComponent implements OnInit {
  appSettings: AppSettings;
  closeDropdown: EventEmitter<boolean>;
  account: Account;

  constructor(
    private router: Router,
    private store: Store<AppState>,
    private loginService: LoginService
  ) {
    this.closeDropdown = new EventEmitter<boolean>();
  }

  ngOnInit() {
    this.store.select("appSettings").subscribe(settings => {
      this.appSettings = settings;
    });
    this.account = this.loginService.getAccount();
  }

  logout() {
    this.loginService.logout();
  }
}
