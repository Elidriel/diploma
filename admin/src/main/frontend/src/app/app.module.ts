import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HttpClientModule } from "@angular/common/http";

import { AppComponent } from "./app.component";
import { RoutingModule } from "./routing/routing.module";
import { LayoutsModule } from "./layouts/layouts.module";
import { PagesModule } from "./pages/pages.module";
import { StoreModule } from "@ngrx/store";
import { pageDataReducer } from "./store/reducers/page-data.reducer";
import { appSettingsReducer } from "./store/reducers/app-settings.reducer";
import { MatInputModule } from "@angular/material/input";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatTableModule } from "@angular/material/table";
import { SnackbarsUtils } from "./utils/snackbars.utils";
import { AccountEventsService } from "./account/account.events.service";
import { NavigationStart, Router } from "@angular/router";
import { LoginService } from "./services/login/login.service";
import { IsAuthorized } from "./utils/is.authorized";
import { MAT_DIALOG_DEFAULT_OPTIONS } from "@angular/material/dialog";
import { NgSelectModule } from "@ng-select/ng-select";

@NgModule({
  declarations: [AppComponent, IsAuthorized],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    StoreModule.forRoot({
      pageData: pageDataReducer,
      appSettings: appSettingsReducer
    }),
    RoutingModule,
    LayoutsModule,
    PagesModule,
    NgSelectModule
  ],
  bootstrap: [AppComponent],
  providers: [
    SnackbarsUtils,
    AccountEventsService,
    { provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: { hasBackdrop: true } }
  ]
})
export class AppModule {
  constructor(
    router: Router,
    loginService: LoginService,
    accountEventsService: AccountEventsService
  ) {
    router.events.subscribe(e => {
      if (e instanceof NavigationStart && !e.url.startsWith("/login-page")) {
        if (!loginService.isAuthenticated()) {
          router.navigate(["/login-page"], {
            queryParams: { componentUrl: e.url }
          });
          return;
        }
      }
    });

    accountEventsService.subscribe(event => {
      if (!event.authenticated) {
        loginService.logout(false);
      }
    });

  }
}
