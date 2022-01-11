import { throwError as observableThrowError } from "rxjs/internal/observable/throwError";

export abstract class BaseHttpService {
  handleError(error: any) {
    return observableThrowError(error.error || "Server error");
  }
}
