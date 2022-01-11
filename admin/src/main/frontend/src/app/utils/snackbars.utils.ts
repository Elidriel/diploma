import { MAT_SNACK_BAR_DATA, MatSnackBar } from "@angular/material/snack-bar";
import { Component, Inject, Injectable } from "@angular/core";

@Injectable()
export class SnackbarsUtils {
  constructor(private _snackBar: MatSnackBar) {}

  public handleAnySuccess(message: string) {
    this._snackBar.openFromComponent(CustomSnackbarComponent, {
      duration: 2000,
      horizontalPosition: "end",
      verticalPosition: "bottom",
      data: {
        message: message,
        type: "success"
      }
    });
  }

  public handleAnyError(message: string) {
    console.error(message);
    this._snackBar.openFromComponent(CustomSnackbarComponent, {
      duration: 4000,
      horizontalPosition: "end",
      verticalPosition: "bottom",
      data: {
        message: message,
        type: "fail"
      }
    });
  }
}

@Component({
  selector: "snack-bar-component-success",
  template: `
    <b class="{{ data.type }}">{{ data.message }}</b>
  `,
  styles: [
    `
      .success {
        color: #90ee90;
      }
      .fail {
        color: red;
      }
      background-color: white;
    `
  ]
})
export class CustomSnackbarComponent {
  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: any) {}
}
