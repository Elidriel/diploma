import { Component, HostBinding, OnInit } from "@angular/core";
import { AppSettings } from "../../../interfaces/settings";
import { Store } from "@ngrx/store";
import { AppState } from "../../../interfaces/app-state";
import * as SettingsActions from "../../../store/actions/app-settings.actions";

@Component({
  selector: "app-vertical-navbar",
  templateUrl: "vertical-navbar.component.html",
  styleUrls: ["vertical-navbar.component.scss"]
})
export class VerticalNavbarComponent implements OnInit {
  @HostBinding("class.app-v-navbar") true;

  appSettings: AppSettings;

  constructor(private store: Store<AppState>) {}

  ngOnInit() {
    this.store.select("appSettings").subscribe(settings => {
      this.appSettings = settings;
    });
  }

  toggleNavbar() {
    this.appSettings.sidebarOpened = !this.appSettings.sidebarOpened;

    this.store.dispatch(new SettingsActions.Update(this.appSettings));
  }
}
