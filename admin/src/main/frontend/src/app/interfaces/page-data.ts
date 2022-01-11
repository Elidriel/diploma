import { Breadcrumb } from "../ui/interfaces/breadcrumbs";

export interface PageData {
  title: string;
  loaded?: boolean;
  breadcrumbs?: Breadcrumb[];
  fullFilled?: boolean;
}
