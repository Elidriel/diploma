import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { UIModule } from "../ui/ui.module";
import {
  MAT_DATE_LOCALE,
  MatNativeDateModule,
  MatRippleModule
} from "@angular/material/core";
import { MatAutocompleteModule } from "@angular/material/autocomplete";
import { MatBadgeModule } from "@angular/material/badge";
import { MatBottomSheetModule } from "@angular/material/bottom-sheet";
import { MatButtonModule } from "@angular/material/button";
import { MatButtonToggleModule } from "@angular/material/button-toggle";
import { MatCardModule } from "@angular/material/card";
import { MatCheckboxModule } from "@angular/material/checkbox";
import { MatChipsModule } from "@angular/material/chips";
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MatDialogModule } from "@angular/material/dialog";
import { MatExpansionModule } from "@angular/material/expansion";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatListModule } from "@angular/material/list";
import { MatMenuModule } from "@angular/material/menu";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatProgressBarModule } from "@angular/material/progress-bar";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatRadioModule } from "@angular/material/radio";
import { MatSelectModule } from "@angular/material/select";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatSlideToggleModule } from "@angular/material/slide-toggle";
import { MatSliderModule } from "@angular/material/slider";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { MatSortModule } from "@angular/material/sort";
import { MatStepperModule } from "@angular/material/stepper";
import { MatTableModule } from "@angular/material/table";
import { MatTabsModule } from "@angular/material/tabs";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatTooltipModule } from "@angular/material/tooltip";
import { MatTreeModule } from "@angular/material/tree";
import { ChartsModule } from "ng2-charts";
import { NgxChartsModule } from "@swimlane/ngx-charts";
import { AgmCoreModule } from "@agm/core";
import { AmChartsModule } from "@amcharts/amcharts3-angular";
import { NgxEchartsModule } from "ngx-echarts";
import { MaterialFileInputModule } from "ngx-material-file-input";
import { LeafletModule } from "@asymmetrik/ngx-leaflet";
import { CalendarModule, DateAdapter } from "angular-calendar";
import { adapterFactory } from "angular-calendar/date-adapters/date-fns";
import { BasePageComponent } from "./base-page/base-page.component";
import { environment } from "../../environments/environment";
import { AngularFireModule } from "@angular/fire";
import { AngularFireDatabaseModule } from "@angular/fire/database";
import { AngularFirestoreModule } from "@angular/fire/firestore";
import { AngularFireStorageModule } from "@angular/fire/storage";
import { RouterModule } from "@angular/router";
import { NgSelectModule } from "@ng-select/ng-select";
import {
  NgxMatDatetimePickerModule,
  NgxMatNativeDateModule,
  NgxMatTimepickerModule
} from "@angular-material-components/datetime-picker";
import { HttpClientModule } from "@angular/common/http";
import { NgxDadataModule } from "@kolkov/ngx-dadata";
import { AngularMarkdownEditorModule } from "angular-markdown-editor";
import { QuillModule } from "ngx-quill";
import { DragDropModule } from "@angular/cdk/drag-drop";


import { LoginComponent } from "./login/login.component";
import { WelcomeComponent } from "./welcome/welcome.component";
import {ModalConfirmComponent} from "../modal/confirm/modal.confirm.component";
import { ProductListComponent } from './product/product-list/product-list.component';
import { ProductDetailComponent } from './product/product-detail/product-detail.component';
import { CategoryDetailComponent } from './category/category-detail/category-detail.component';
import { CategoryListComponent } from './category/category-list/category-list.component';
import { UserListComponent } from './user/user-list/user-list.component';
import { CatalogDetailComponent } from './catalog/catalog-detail/catalog-detail.component';
import { CatalogListComponent } from './catalog/catalog-list/catalog-list.component';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,

    UIModule,

    MatAutocompleteModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatDatepickerModule,
    MatDialogModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatStepperModule,
    MatTreeModule,
    MatBadgeModule,
    MatBottomSheetModule,

    ChartsModule,
    NgxChartsModule,
    AmChartsModule,
    NgxEchartsModule,
    AgmCoreModule.forRoot({
      apiKey: environment.googleMapApiKey
    }),
    LeafletModule.forRoot(),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    }),
    AngularFireModule.initializeApp(environment.firebase, "assist"),
    AngularFirestoreModule,
    AngularFireStorageModule,
    AngularFireDatabaseModule,
    AngularFireDatabaseModule,
    NgSelectModule,
    MaterialFileInputModule,
    NgxMatDatetimePickerModule,
    NgxMatTimepickerModule,
    NgxMatNativeDateModule,
    HttpClientModule,
    NgxDadataModule,
    AngularMarkdownEditorModule.forRoot({ iconlibrary: "fa" }),
    QuillModule.forRoot(),
    DragDropModule
  ],
  declarations: [
    ModalConfirmComponent,

    LoginComponent,
    WelcomeComponent,
    BasePageComponent,
    ProductListComponent,
    ProductDetailComponent,
    CategoryDetailComponent,
    CategoryListComponent,
    UserListComponent,
    CatalogDetailComponent,
    CatalogListComponent
  ],
  exports: [],
  providers: [{ provide: MAT_DATE_LOCALE, useValue: "en-GB" }]
})
export class PagesModule {}
