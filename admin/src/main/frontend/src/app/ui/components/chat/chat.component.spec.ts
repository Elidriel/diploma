import { async, ComponentFixture, TestBed } from "@angular/core/testing";

import { TCChatComponent } from "./chat.component";

describe("TCChatComponent", () => {
  let component: TCChatComponent;
  let fixture: ComponentFixture<TCChatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TCChatComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TCChatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should be created", () => {
    expect(component).toBeTruthy();
  });
});
