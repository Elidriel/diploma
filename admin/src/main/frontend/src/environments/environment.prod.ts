import { settings } from "./settings";
import * as npm from "../../package.json";

export const environment = {
  production: false,
  appSettings: settings,
  googleMapApiKey: "AIzaSyAU9f7luK3J31nurL-Io3taRKF7w9BItQE",
  firebase: {
    apiKey: "AIzaSyBjHU1XkLXNHDS4za80MLA0-ePqow8SEi0",
    authDomain: "assist-angular.firebaseapp.com",
    databaseURL: "https://assist-angular.firebaseio.com",
    projectId: "assist-angular",
    storageBucket: "assist-angular.appspot.com",
    messagingSenderId: "126593393147",
    appId: "1:126593393147:web:ae499e70ea8b40c5"
  },
  version: npm.version
};
