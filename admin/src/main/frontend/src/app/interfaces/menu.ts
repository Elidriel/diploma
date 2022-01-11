export interface MenuItem {
  title: string;
  icon?: MenuItemIcon;
  color?: string;
  active?: boolean;
  disabled?: boolean;
  groupTitle?: boolean;
  routing?: string;
  externalLink?: string;
  sub?: MenuItemSub[];
  badge?: MenuItemBadge;
  access?: string;
}

export interface MenuItemIcon {
  class?: string;
  color?: string;
  bg?: string;
}
export interface MenuItemSub {
  title: string;
  icon?: string;
  color?: string;
  active?: boolean;
  disabled?: boolean;
  routing?: string;
  externalLink?: string;
  sub?: MenuItemSub[];
  access?: string;
}
export interface MenuItemBadge {
  text?: string;
  color?: string;
  bg?: string;
}
