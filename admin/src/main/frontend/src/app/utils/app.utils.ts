export const BACKEND_API_PATH: string = "/admin";
export const BACKEND_API_AUTHENTICATE_PATH: string = "/account";

export class UrlRegistry {
  public static rootBackendUrl(): string {
    return BACKEND_API_PATH;
  }
}
