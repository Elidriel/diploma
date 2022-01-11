import { async, ComponentFixture, TestBed } from "@angular/core/testing";

import { TCBreadcrumbComponent } from "./breadcrumbs.component";

describe("TCBreadcrumbComponent", () => {
  let component: TCBreadcrumbComponent;
  let fixture: ComponentFixture<TCBreadcrumbComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TCBreadcrumbComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TCBreadcrumbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should be created", () => {
    expect(component).toBeTruthy();
  });
});
