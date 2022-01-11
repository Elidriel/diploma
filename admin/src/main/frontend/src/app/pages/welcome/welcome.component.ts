import { Component, OnDestroy, OnInit } from "@angular/core";
import { BasePageComponent } from "../base-page/base-page.component";
import { Store } from "@ngrx/store";
import { AppState } from "../../interfaces/app-state";

@Component({
  selector: "app-welcome",
  templateUrl: "./welcome.component.html",
  styleUrls: ["./welcome.component.scss"]
})
export class WelcomeComponent extends BasePageComponent
  implements OnInit, OnDestroy {
  constructor(store: Store<AppState>) {
    super(store);

    this.pageData = {
      title: "Панель администратора",
      loaded: true
    };
  }

  ngOnInit() {
    super.ngOnInit();
  }

  ngOnDestroy() {
    super.ngOnDestroy();
  }
}
