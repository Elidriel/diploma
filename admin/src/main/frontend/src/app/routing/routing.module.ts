import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { VerticalLayoutComponent } from "../layouts/vertical/vertical.component";
import { PageNotFoundComponent } from "../pages/not-found/not-found.component";
import { LoginComponent } from "../pages/login/login.component";
import { WelcomeComponent } from "../pages/welcome/welcome.component";
import {ProductListComponent} from "../pages/product/product-list/product-list.component";
import {ProductDetailComponent} from "../pages/product/product-detail/product-detail.component";
import {CategoryListComponent} from "../pages/category/category-list/category-list.component";
import {CategoryDetailComponent} from "../pages/category/category-detail/category-detail.component";
import {UserListComponent} from "../pages/user/user-list/user-list.component";
import {CatalogListComponent} from "../pages/catalog/catalog-list/catalog-list.component";
import {CatalogDetailComponent} from "../pages/catalog/catalog-detail/catalog-detail.component";

const mainRoutes: Routes = [

  { path: "welcome", component: WelcomeComponent },
  { path: "product-list", component: ProductListComponent },
  { path: "product/:id", component: ProductDetailComponent },
  { path: "product/new", component: ProductDetailComponent },
  { path: "category-list", component: CategoryListComponent },
  { path: "category/:id", component: CategoryDetailComponent },
  { path: "category/new", component: CategoryDetailComponent },
  { path: "catalog-list", component: CatalogListComponent },
  { path: "catalog/:id", component:  CatalogDetailComponent },
  { path: "catalog/new", component:  CatalogDetailComponent },
  { path: "user-list", component: UserListComponent },
  { path: "**", component: PageNotFoundComponent },
];

const layoutRoutes: Routes = [
  {
    path: "login-page",
    component: LoginComponent
  },
  {
    path: "",
    redirectTo: "/admin/welcome",
    pathMatch: "full"
  },
  {
    path: "admin",
    component: VerticalLayoutComponent,
    children: mainRoutes
  },
  {
    path: "**",
    component: VerticalLayoutComponent,
    children: mainRoutes
  }
];

@NgModule({
  imports: [RouterModule.forRoot(layoutRoutes, { useHash: true })],
  exports: [RouterModule]
})
export class RoutingModule {}
