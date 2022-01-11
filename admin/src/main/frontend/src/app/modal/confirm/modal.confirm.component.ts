import { Component, Inject } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";

@Component({
  selector: "confirm-dialog",
  templateUrl: "./modal.confirm.component.html"
})
export class ModalConfirmComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}
}
